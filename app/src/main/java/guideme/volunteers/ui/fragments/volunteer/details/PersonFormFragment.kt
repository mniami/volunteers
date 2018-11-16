package guideme.volunteers.ui.fragments.volunteer.details

import android.os.Bundle
import android.view.*
import android.widget.TabHost
import com.squareup.picasso.Picasso
import guideme.volunteers.R
import guideme.volunteers.domain.Address
import guideme.volunteers.domain.Human
import guideme.volunteers.domain.Person
import guideme.volunteers.domain.Privilege
import guideme.volunteers.helpers.Container
import guideme.volunteers.helpers.dataservices.datasource.UserDataSource
import guideme.volunteers.helpers.dataservices.datasource.VolunteerDataSource
import guideme.volunteers.ui.dialogs.UrlRequestDialog
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import guideme.volunteers.ui.fragments.volunteer.details.presenters.PersonPresenter
import kotlinx.android.synthetic.main.person_details_fragment.*

class PersonFormFragment : BaseFragment<PersonPresenter>() {
    private val usersDataSource: UserDataSource?

    companion object {
        const val HUMAN_ARG = "human"
    }

    init {
        configuration = FragmentConfiguration.withLayout(R.layout.person_details_fragment).showBackArrow().create()
        usersDataSource = Container.dataSourceContainer.getDataSource(UserDataSource.ID) as UserDataSource?
        val volunteersDataSource = Container.dataSourceContainer.getDataSource(VolunteerDataSource.ID) as VolunteerDataSource?
        presenter = PersonPresenter(
                userDataSource = usersDataSource,
                volunteerDataSource = volunteersDataSource)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        if (view == null) {
            return view
        }
        val tabHost = view.findViewById<TabHost?>(R.id.tabHost)
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
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        log.d { "onCreate" }
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    private fun initViews() {
        if (presenter?.human == null) {
            return
        }
        presenter?.mainActivity = mainActivity
        presenter?.human?.person?.let { person ->
            if (person.avatarImageUri.isNotEmpty()) {
                imageView.tag = person.avatarImageUri
                Picasso.with(context).load(person.avatarImageUri).into(imageView)
            }
            etName?.setText(person.name)
            etEmail?.setText(person.email)
            etPersonalityDescription?.setText(person.personalityDescription)
            etShortDescription?.setText(person.shortDescription)
            imageView?.setOnClickListener {
                UrlRequestDialog().show(getString(R.string.url_input_dialog_title), getString(R.string.url_input_dialog_message), context) { url ->
                    imageView.tag = url
                    Picasso.with(context).load(url).into(imageView)
                }
            }

            val addressEntry = person.addresses.entries.firstOrNull()
            if (addressEntry != null) {
                val address = addressEntry.value
                etCity?.setText(address.city)
                etPostCode?.setText(address.zip)
                etAddress?.setText(address.street)
            }
            etDescription?.setText(person.description)
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
                key = sourcePerson?.key ?: "",
                privilege = sourcePerson?.privilege ?: Privilege.USER,
                shortDescription = etShortDescription.text.toString(),
                personalityDescription = etPersonalityDescription.text.toString(),
                description = etDescription.text.toString(),
                addresses = mapOf(Pair(Address.Types.Main, Address(
                        type = Address.Types.Main,
                        city = etCity.text.toString(),
                        street = etAddress.text.toString(),
                        zip = etPostCode.text.toString()))),
                avatarImageUri = imageView.tag as String))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_check -> {
                return true
            }
        }
        return false
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.let {
            it.human = args?.get(HUMAN_ARG) as Human?
        }
    }
}