package android.benchmark.ui.fragments.volunteer.details.admin

import android.androidkotlinbenchmark.R
import android.benchmark.domain.Person
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.fragments.volunteer.details.presenters.UserPresenter
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.admin_user_details.*

class AdminUserDetailsFragment: BaseFragment<UserPresenter>() {
    companion object {
        val PERSON_ARG = "person"
    }
    init {
        presenter = UserPresenter()
        configuration = FragmentConfiguration.withLayout(R.layout.admin_user_details).showBackArrow().create()
    }

    override fun onStart() {
        super.onStart()
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
            Picasso.with(context).load(it.avatarImageUri).into(imageView)
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

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.let {
            it.person = args?.get(PERSON_ARG) as Person
        }
    }
}