package guideme.volunteers.ui.fragments.settings

import guideme.volunteers.R
import guideme.volunteers.helpers.Services
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragmentImpl : BaseFragment<AuthenticationPresenter>(), AuthenticationFragment {
    init {
        configuration = FragmentConfiguration.withLayout(R.layout.authentication_fragment)
                .title(R.string.user_not_signed_in_menu_item)
                .showBackArrow()
                .create()
        presenter = AuthenticationPresenter(this, Services.instance.googleAuth, Services.instance.database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        updateUi()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.authentication_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView?.setOnCloseListener { return@setOnCloseListener false }
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(text: String?): Boolean {
                return true
            }
        })
    }

    private fun updateUi() {
        val authUser = Services.instance.googleAuth.signInAuthResult.authUser
        signedLayout.visibility = if (authUser != null) View.VISIBLE else View.GONE

        if (authUser != null) {
            tvHeader.text = authUser.name
            tvShortDescription.text =
                    String.format(getString(R.string.authenticated_user_short_description), authUser.email)

            if (authUser.photoUrl.isNotBlank()) {
                Picasso.with(context).load(authUser.photoUrl).into(ivImage)
            }
        }
    }
}