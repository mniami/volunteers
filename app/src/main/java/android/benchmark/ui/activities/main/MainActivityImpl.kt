package android.benchmark.ui.activities.main

import android.androidkotlinbenchmark.R
import android.benchmark.auth.SignInAuthResult
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.Services
import android.benchmark.helpers.databases.actions.AddVolunteer
import android.benchmark.helpers.dataservices.errors.ErrorMessage
import android.benchmark.helpers.dataservices.errors.ErrorType
import android.benchmark.ui.activities.main.base.BaseMainActivityImpl
import android.benchmark.ui.views.actionbar.ActionBarTool
import android.benchmark.ui.views.actionbar.ActionBarToolImpl
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

internal class MainActivityImpl : BaseMainActivityImpl(), MainView {

    override val actionBarTool: ActionBarTool = ActionBarToolImpl(this)

    private var menu: Menu? = null
    private var presenter: MainPresenter? = null
    private val fragmentChanger = Services.instance.fragmentChanger

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.let {
            Services.instance.googleAuth.onActivityResult(requestCode, it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentChanger.supportFragmentManager = supportFragmentManager

        super.onCreate(savedInstanceState)

        Services.instance.googleAuth.init(this)

        setContentView(R.layout.activity_main)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        if (presenter == null) {
            presenter = MainPresenter(this, Services.instance.googleAuth, Services.instance.database, this)
        }
        presenter?.onCreate()
    }

    override fun onStart() {
        fragmentChanger.paused = false
        super.onStart()
        presenter?.onStart()
    }

    override fun onPause() {
        super.onPause()
        fragmentChanger.paused = true
    }

    override fun getResourceText(id: Int): String {
        return getString(id)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        this.menu = menu

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView?

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(text: String?): Boolean {
                return true
            }
        })
        refreshMenu()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.string.action_settings -> {
                presenter?.onSettingsClick()
                return true
            }
            R.id.action_authentication -> {
                presenter?.onAuthenticationClick()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun refreshMenu() {
        val logInMenuItem = menu?.findItem(R.id.action_authentication)
        val logInTextId = if (Services.instance.googleAuth.isSignedIn()) R.string.user_signed_in_menu_item else R.string.user_not_signed_in_menu_item

        logInMenuItem?.title = getString(logInTextId)
    }

    override fun openSettings() = fragmentChanger.openSettings()
    override fun openAuthentication() = fragmentChanger.openAuthentication()
    override fun showVolunteerList() = fragmentChanger.showVolunteerList()
    override fun openHome() = fragmentChanger.openHome()
    override fun showProject(project: Project) = fragmentChanger.showProject(project)
    override fun showVolunteer(volunteer: Volunteer) = fragmentChanger.showVolunteer(volunteer)
    override fun openEditUserDetails(volunteer: Volunteer) = fragmentChanger.openEditUserDetails(volunteer)
    override fun updateUserStatus(signInResult: SignInAuthResult) {}
    override fun showError(errorMessage: ErrorMessage) {
        val content = errorMessage.content
        if (content != null) {
            Snackbar.make(activity_main, content, Snackbar.LENGTH_LONG).show()
        }
    }
}


