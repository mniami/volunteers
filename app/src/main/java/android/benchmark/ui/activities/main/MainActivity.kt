package android.benchmark.ui.activities.main

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.services.Services
import android.benchmark.ui.fragments.settings.AuthenticationFragment
import android.benchmark.ui.fragments.settings.SettingsFragment
import android.benchmark.ui.fragments.volunteer.details.VolunteerDetailsFragment
import android.benchmark.ui.fragments.volunteer.list.VolunteerListFragment
import android.benchmark.ui.utils.AppVersionProvider
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

    val presenter by lazy { MainPresenter(this, dataService) }
    val dataService = Services.dataService

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

    override fun openSettings() = changeFragment(SettingsFragment(), "settings")
    override fun openAuthentication() = changeFragment(AuthenticationFragment(), "authentication")
    override fun showVolunteerList() = changeFragment(VolunteerListFragment(), "volunteerList")
    override fun showVolunteer(volunteer: Volunteer) {
        val bundle = Bundle()
        bundle.putSerializable("volunteer", volunteer)

        val volunteerDetailsFragment = VolunteerDetailsFragment()
        volunteerDetailsFragment.arguments = bundle

        changeFragment(volunteerDetailsFragment, "volunteers")
    }

    private fun changeFragment(fragment : Fragment, name : String){
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.fragmentContainer, fragment, name)
                .commit()
    }
}

