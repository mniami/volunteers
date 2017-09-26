package android.benchmark.ui.fragments.settings

import android.androidkotlinbenchmark.R
import android.benchmark.helpers.Services
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.squareup.picasso.Picasso
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragmentImpl : BaseFragment<AuthenticationPresenter>(), AuthenticationFragment {
    init {
        configuration = FragmentConfiguration.withLayout(R.layout.authentication_fragment)
                .title(R.string.authentication)
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
        Services.instance.database.getCurrentUserAsync().subscribeBy(
                onNext = { currentUser ->
                    signedLayout.visibility = if (currentUser != null) View.VISIBLE else View.GONE

                    if (currentUser != null) {
                        tvHeader.text = currentUser.name
                        tvShortDescription.text = currentUser.email

                        if (currentUser.avatarImageUri.isNotBlank()) {
                            Picasso.with(context).load(currentUser.avatarImageUri).into(ivImage)
                        }
                    }
                })
    }
}