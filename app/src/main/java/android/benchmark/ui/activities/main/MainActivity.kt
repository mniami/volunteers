package android.benchmark.ui.activities.main

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.IFragmentContainer
import android.benchmark.ui.fragments.settings.AuthenticationFragment
import android.benchmark.ui.fragments.settings.SettingsFragment
import android.benchmark.ui.fragments.volunteer.details.VolunteerDetailsFragment
import android.benchmark.ui.views.actionbar.ActionBarTool
import android.benchmark.ui.views.actionbar.IActionBarTool
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

internal class MainActivity : AppCompatActivity(), IMainActivity {

    override val actionBarTool: IActionBarTool = ActionBarTool(this)

    val presenter by lazy { MainPresenter(this) }

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        presenter.onCreate()
    }

    override fun getResourceText(id: Int): String {
        return getString(id)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_settings -> {
                presenter.onSettingsClick()
                return true
            }

            R.id.action_authentication -> {
                presenter.onAuthenticationClick()
                return true
            }

            R.id.action_favorite ->
                return true

            android.R.id.home -> {
                if (!actionBarTool.backPressed()) {
                    supportFragmentManager.popBackStack()
                }
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun navigateTo(fragmentContainer: IFragmentContainer) {
        val fragment = fragmentContainer.getFragment()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        val transaction = supportFragmentManager.beginTransaction()
        if (currentFragment != null) {
            transaction.addToBackStack(null)
        }
        transaction.setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        if (currentFragment === null) {
            transaction.add(R.id.fragmentContainer, fragment, fragmentContainer.getName())
        } else {
            transaction.replace(R.id.fragmentContainer, fragment, fragmentContainer.getName())
        }
        transaction.commit()
    }

    override fun openSettings() = changeFragment(SettingsFragment(), "settings")
    override fun openAuthentication() = changeFragment(AuthenticationFragment(), "authentication")

    override fun showVolunteer(volunteer: Volunteer) {
        val bundle = Bundle()
        bundle.putSerializable("volunteer", volunteer)

        val volunteerDetailsFragment = VolunteerDetailsFragment()
        volunteerDetailsFragment.arguments = bundle

        changeFragment(volunteerDetailsFragment, "volunteers")
    }

    fun changeFragment(fragment : Fragment, name : String){
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.fragmentContainer, fragment, name)
                .commit()
    }
}

