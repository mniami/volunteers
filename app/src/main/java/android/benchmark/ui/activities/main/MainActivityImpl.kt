package android.benchmark.ui.activities.main

import android.benchmark.R
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.Services
import android.benchmark.ui.fragments.VolunteersFragmentPresenter
import android.benchmark.ui.fragments.settings.AuthenticationFragmentImpl
import android.benchmark.ui.fragments.settings.SettingsFragment
import android.benchmark.ui.fragments.volunteer.details.VolunteerDetailsFragment
import android.benchmark.ui.fragments.volunteer.details.project.ProjectDetailsFragment
import android.benchmark.ui.views.actionbar.ActionBarTool
import android.benchmark.ui.views.actionbar.ActionBarToolImpl
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

internal class MainActivityImpl : AppCompatActivity(), MainActivity {

    override val actionBarTool: ActionBarTool = ActionBarToolImpl(this)

    var presenter: MainPresenter? = null
    val fragmentChanger = FragmentChanger(supportFragmentManager, Services.instance.dataSourceContainer)

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            Services.instance.googleAuth.onActivityResult(requestCode, it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Services.instance.googleAuth.init(this)

        setContentView(R.layout.activity_main)

        val myToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        if (presenter == null) {
            presenter = MainPresenter(this)
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

    override fun openSettings() = fragmentChanger.openSettings()
    override fun openAuthentication() = fragmentChanger.openAuthentication()
    override fun showVolunteerList() = fragmentChanger.showVolunteerList()
    override fun openHome() = fragmentChanger.openHome()
    override fun showProject(project: Project) = fragmentChanger.showProject(project)
    override fun showVolunteer(volunteer: Volunteer) = fragmentChanger.showVolunteer(volunteer)
}


