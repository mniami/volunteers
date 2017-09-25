package android.benchmark.ui.fragments.settings

import android.androidkotlinbenchmark.R
import android.benchmark.helpers.Services
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
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

    override fun onResume() {
        super.onResume()
        updateUi()
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