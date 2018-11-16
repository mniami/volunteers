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
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragmentImpl : BaseFragment<AuthenticationPresenter>(), AuthenticationFragment {
    private var actionEdit: MenuItem? = null
    private var currentUser: User? = null
    private var userLoader: Disposable? = null

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

    override fun onDestroy() {
        super.onDestroy()
        userLoader?.dispose()
        userLoader = null
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

        signedLayout?.visibility = if (!authUser.isEmpty()) View.VISIBLE else View.GONE
        unsignedLayout?.visibility = if (authUser.isEmpty()) View.VISIBLE else View.GONE

        tvHeader.text = authUser.name
        tvEmail.text = authUser.email

        if (authUser.photoUrl.isNotBlank()) {
            Picasso.with(context).load(authUser.photoUrl).transform(CircleTransform()).into(ivImage)
        }
    }
}