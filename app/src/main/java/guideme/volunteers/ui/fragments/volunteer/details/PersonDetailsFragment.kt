package guideme.volunteers.ui.fragments.volunteer.details

import guideme.volunteers.R
import guideme.volunteers.domain.Human
import guideme.volunteers.domain.Person
import guideme.volunteers.domain.Privilege
import guideme.volunteers.helpers.Container
import guideme.volunteers.helpers.dataservices.datasource.UserDataSource
import guideme.volunteers.helpers.dataservices.datasource.VolunteerDataSource
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import guideme.volunteers.ui.fragments.volunteer.details.presenters.PersonPresenter
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.squareup.picasso.Picasso
import guideme.volunteers.domain.Address
import kotlinx.android.synthetic.main.admin_user_details.*

class PersonDetailsFragment : BaseFragment<PersonPresenter>() {
    private val usersDataSource: UserDataSource?

    companion object {
        val HUMAN_ARG = "human"
    }

    init {
        val container = Container.dataSourceContainer
        configuration = FragmentConfiguration.withLayout(R.layout.admin_user_details).showBackArrow().create()
        usersDataSource = Container.dataSourceContainer.getDataSource(UserDataSource.ID) as UserDataSource?
        val volunteersDataSource = Container.dataSourceContainer.getDataSource(VolunteerDataSource.ID) as VolunteerDataSource?
        presenter = PersonPresenter(
                userDataSource = usersDataSource,
                volunteerDataSource = volunteersDataSource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        presenter?.mainActivity = mainActivity
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
        presenter?.human?.person?.let {
            if (it.avatarImageUri.isNotEmpty()) {
                Picasso.with(context).load(it.avatarImageUri).into(imageView)
            }
            etName?.setText(it.name)
            etEmail?.setText(it.email)

            val addressEntry = it.addresses.entries.firstOrNull()
            if (addressEntry != null) {
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

        checkAction?.setOnMenuItemClickListener {
            updatePerson()
            return@setOnMenuItemClickListener true
        }
    }

    private fun updatePerson() {
        val sourcePerson = presenter?.human?.person
        //TODO Address should be set
        //val address = Address(etCity.text.toString(), etPostCode.text.toString(), etAddress.text.toString())
        presenter?.updatePerson(Person(
                name = etName.text.toString(),
                email = etEmail.text.toString(),
                key = sourcePerson?.key?:"",
                privilege = sourcePerson?.privilege?:Privilege.USER,
                description = etDescription.text.toString(),
                addresses = emptyMap(),
                avatarImageUri = sourcePerson?.avatarImageUri?:""))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_check ->{
               return true
            }
        }
        return false
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.let {
            // TODO should be cloned, do this after changed Person to data classes
            it.human = args?.get(HUMAN_ARG) as Human?
        }
    }
}