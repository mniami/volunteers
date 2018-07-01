package guideme.volunteers.ui.fragments.volunteer.details.users

import guideme.volunteers.R
import guideme.volunteers.domain.Privilege
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.Container
import guideme.volunteers.helpers.databases.actions.AddVolunteer
import guideme.volunteers.helpers.dataservices.datasource.UserDataSource
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import guideme.volunteers.helpers.dataservices.errors.ErrorType
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import guideme.volunteers.ui.fragments.volunteer.details.VolunteerProjectListFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.squareup.picasso.Picasso
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.volunteer_details_fragment.*

class VolunteerDetailsFragment : BaseFragment<VolunteerDetailsPresenter>(), IVolunteerDetailsFragment {
    companion object {
        val VOLUNTEER_ARG = "volunteer"
    }

    init {
        presenter = VolunteerDetailsPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.volunteer_details_fragment).showBackArrow().create()
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun updateView() {
        actionBar.hideOptions()
        val volunteer = presenter?.volunteer
        if (volunteer == null){
            return
        }
        presenter?.volunteer?.let { v ->
            actionBar.setTitle("Volunteers Details")
            tvSubHeader?.text = "${v.person.name} ${v.person.surname}"
            tvHeader?.text = v.volunteerType
            if (v.person.avatarImageUri.isNotEmpty()){
                Picasso.with(context).load(v.person.avatarImageUri).into(ivImage)
            }
            tvShortDescription?.text = v.person.shortDescription

            val userDataSource = Container.dataSourceContainer.getDataSource(UserDataSource.ID) as UserDataSource?
            userDataSource?.let {
                it.data.observable.subscribeBy(onNext = { currentUser ->
                    if (currentUser.person.privilege == Privilege.ADMIN) {
                        tbAdmin?.visibility = View.VISIBLE
                        btEdit?.setOnClickListener {
                            mainActivity.openEditUserDetails(v)
                        }
                    }
                })
            }
        }

        viewPager?.let {
            it.adapter = object : FragmentPagerAdapter(childFragmentManager) {
                override fun getCount(): Int {
                    return 2
                }

                override fun getItem(position: Int): Fragment? {
                    return presenter?.volunteer?.let { volunteer ->
                        var fragment: Fragment? = null
                        when (position) {
                            0 -> fragment = VolunteerDetailsAccountFragment()
                            1 -> fragment = VolunteerProjectListFragment()
                        }
                        val bundle = Bundle()
                        bundle.putSerializable("volunteer", volunteer)
                        fragment?.arguments = bundle
                        return@let fragment
                    }
                }

                override fun getPageTitle(position: Int): CharSequence {
                    when (position) {
                        0 -> return "Info"
                        1 -> return "Projects"
                    }
                    return ""
                }
            }
            slidingTabLayout?.setViewPager(it)
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.let {
            it.volunteer = args?.get(VOLUNTEER_ARG) as Volunteer?
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.volunteers_details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val saveItem = menu?.findItem(R.id.action_save)

        saveItem?.actionView?.setOnClickListener {
            val v = presenter?.volunteer
            if (v != null) {
                AddVolunteer(v).execute(Container.database,
                        onFailure = {
                            mainActivity.showError(ErrorMessage(ErrorType.UNKNOWN, it.localizedMessage))
                        },
                        onComplete = {
                            mainActivity.goBack()
                        })
            }
        }
    }
}