package android.benchmark.ui.activities.main

import android.benchmark.R
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.services.Services
import android.benchmark.services.cache.AndroidLocalDataCache
import android.benchmark.ui.fragments.settings.AuthenticationFragmentImpl
import android.benchmark.ui.fragments.settings.SettingsFragment
import android.benchmark.ui.fragments.volunteer.details.VolunteerDetailsFragment
import android.benchmark.ui.fragments.volunteer.details.project.ProjectDetailsFragment
import android.benchmark.ui.fragments.volunteer.list.VolunteerListFragment
import android.benchmark.ui.utils.AppVersionProviderImpl
import android.benchmark.ui.views.actionbar.ActionBarTool
import android.benchmark.ui.views.actionbar.ActionBarToolImpl
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

internal class MainActivityImpl : AppCompatActivity(), MainActivity {

    override val actionBarTool: ActionBarTool = ActionBarToolImpl(this)

    var presenter: MainPresenter? = null

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        if (Services.instance == null) {
            Services.instance = Services(
                    dataCache = AndroidLocalDataCache(baseContext),
                    appVersionProvider = AppVersionProviderImpl(packageManager, packageName))
        }
        if (presenter == null) {
            presenter = MainPresenter(this, Services.instance.dataService)
        }
        presenter?.onCreate()
    }

    override fun getResourceText(id: Int): String {
        return getString(id)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                presenter?.onSettingsClick()
                return true
            }

            R.id.action_authentication -> {
                presenter?.onAuthenticationClick()
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
    override fun openAuthentication() = changeFragment(AuthenticationFragmentImpl(), "authentication")
    override fun showVolunteerList() = changeFragment(VolunteerListFragment(), "volunteerList")
    override fun openHome() {
        supportFragmentManager.popBackStack("volunteerList", R.id.fragmentContainer)
    }

    override fun showProject(project: Project) {
        val bundle = Bundle()
        bundle.putSerializable("project", project)

        val projectDetailsFragment = ProjectDetailsFragment()
        projectDetailsFragment.arguments = bundle

        changeFragment(projectDetailsFragment, "project")
    }
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

