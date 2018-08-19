package guideme.volunteers.ui.fragments.settings

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import guideme.volunteers.R
import guideme.volunteers.domain.User
import guideme.volunteers.helpers.Container
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import guideme.volunteers.helpers.dataservices.errors.ErrorType
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import guideme.volunteers.ui.utils.CircleTransform
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragmentImpl : BaseFragment<AuthenticationPresenter>(), AuthenticationFragment {
    private var actionEdit: MenuItem? = null
    private var currentUser: User? = null

    init {
        configuration = FragmentConfiguration.withLayout(R.layout.authentication_fragment)
                .title(R.string.user_not_signed_in_menu_item)
                .showBackArrow()
                .create()
        presenter = AuthenticationPresenter(this, Container.googleAuth, Container.database)
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

        actionEdit = menu?.findItem(R.id.action_edit)
        actionEdit?.setOnMenuItemClickListener {
            val user = currentUser
            if (user == null) {
                mainActivity.showError(ErrorMessage(ErrorType.ILLEGAL_STATE_EXCEPTION, "Missing user"))
                return@setOnMenuItemClickListener false
            }
            mainActivity.openEditUserDetails(user)
            return@setOnMenuItemClickListener true
        }
    }

    private fun updateUi() {
        val authUser = Container.googleAuth.authResult.authUser
        Container.database.getCurrentUser().subscribeBy(
                onSuccess = {
                    currentUser = it
                    signedLayout.visibility = if (!authUser.isEmpty()) View.VISIBLE else View.GONE
                    tvHeader.text = authUser.email
                    tvShortDescription.text = String.format(getString(R.string.authenticated_user_short_description), it.person.email)

                    if (it.person.avatarImageUri.isNotBlank()) {
                        Picasso.with(context).load(it.person.avatarImageUri).transform(CircleTransform()).into(ivImage)
                    }
                },
                onError = {
                    mainActivity.showError(ErrorMessage(ErrorType.ILLEGAL_STATE_EXCEPTION, it.message))
                }
        )
    }
}