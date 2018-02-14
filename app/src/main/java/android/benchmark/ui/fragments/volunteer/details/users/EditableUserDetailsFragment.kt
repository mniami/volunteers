package android.benchmark.ui.fragments.volunteer.details.users

import android.androidkotlinbenchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.Services
import android.benchmark.helpers.databases.actions.AddVolunteer
import android.benchmark.helpers.dataservices.datasource.UserDataSource
import android.benchmark.helpers.dataservices.errors.ErrorMessage
import android.benchmark.helpers.dataservices.errors.ErrorType
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.admin_user_details.*

class EditableUserDetailsFragment : BaseFragment<EditableUserPresenter>() {
    private val usersDataSource : UserDataSource

    companion object {
        val VOLUNTEER_ARG = "person"
    }
    init {
        presenter = EditableUserPresenter()
        configuration = FragmentConfiguration.withLayout(R.layout.admin_user_details).showBackArrow().create()
        usersDataSource = Services.instance.dataSourceContainer.getDataSource(UserDataSource.ID) as UserDataSource
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        mainActivity.actionBarTool.hideBackArrow()

        tabHost?.let {
            it.setup()

            val tab1 = it.newTabSpec("Details")
            tab1.setContent(R.id.detailsLayout)
            tab1.setIndicator("Details")

            it.addTab(tab1)

            val tab2 = it.newTabSpec("Skills")
            tab2.setContent(R.id.skillsLayout)
            tab2.setIndicator("Skills")

            it.addTab(tab2)
        }
        presenter?.person?.let{
            if (it.avatarImageUri.isNotEmpty()) {
                Picasso.with(context).load(it.avatarImageUri).into(imageView)
            }
            etName?.setText(it.name)
            etEmail?.setText(it.email)

            val addressEntry = it.addresses.entries.firstOrNull()
            if (addressEntry != null){
                val address = addressEntry.value
                etCity?.setText(address.city)
                etPostCode?.setText(address.zip)
                etAddress?.setText(String.format("%s %s/%s", address.street, address.house, address.flat))
            }
            etDescription?.setText(it.description)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.editor_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val checkAction = menu?.findItem(R.id.action_check)
        checkAction?.actionView?.setOnClickListener {
            val volunteer = presenter?.volunteer
            if (volunteer is Volunteer) {
                AddVolunteer(volunteer).execute(Services.instance.database,
                        onFailure = {
                            mainActivity.showError(ErrorMessage(ErrorType.UNKNOWN, it.localizedMessage))
                        },
                        onComplete = {
                            mainActivity.goBack()
                        })
            }
        }
    }
    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.let {
            it.volunteer = args?.get(VOLUNTEER_ARG) as Volunteer?
        }
    }
}