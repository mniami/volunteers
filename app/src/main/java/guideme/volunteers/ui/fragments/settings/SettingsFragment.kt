package guideme.volunteers.ui.fragments.settings

import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.view.View
import guideme.volunteers.R
import guideme.volunteers.helpers.Container
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : BaseFragment<SettingsPresenter>(), ISettingsFragment {
    init {
        presenter = SettingsPresenter(
                Container.appVersionProvider,
                Container.dataSourceContainer)
        configuration = FragmentConfiguration
                .withLayout(R.layout.settings_fragment)
                .title(R.string.action_settings)
                .showBackArrow()
                .create()
    }

    override fun onStart() {
        super.onStart()

        refreshViews()

        signInButton.setOnClickListener {
            Container.googleAuth.signIn(activity as FragmentActivity).subscribeBy(
                    onError = {
                        refreshViews()
                        showMessage(it.localizedMessage)
                    },
                    onSuccess = {
                        refreshViews()
                        showMessage(getString(R.string.succesfull_signin))
                    })
        }
        signOutButton.setOnClickListener { _ ->
            val context = context
            if (context == null) {
                return@setOnClickListener
            }
            Container.googleAuth.signOut(activity as FragmentActivity).subscribeBy(onSuccess = {
                refreshViews()
                showMessage(getString(R.string.successfull_signout))
            }, onError = {
                showMessage(it.localizedMessage)
                refreshViews()
            })
        }
    }

    private fun refreshViews() {
        signInButton.visibility = if (Container.googleAuth.isSignedIn()) View.GONE else View.VISIBLE
        signOutButton.visibility = if (Container.googleAuth.isSignedIn()) View.VISIBLE else View.GONE
        tvHeader.text = if (Container.googleAuth.isSignedIn()) getString(R.string.signin_header).replace("%user%", Container.googleAuth.authResult.authUser.email) else getString(R.string.signout_header)
    }

    private fun showMessage(message: String) {
        val view = view
        if (view == null) {
            return
        }
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}
