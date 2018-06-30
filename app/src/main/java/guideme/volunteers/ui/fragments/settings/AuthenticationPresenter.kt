package guideme.volunteers.ui.fragments.settings

import guideme.volunteers.auth.GoogleAuth
import guideme.volunteers.helpers.dataservices.databases.Database
import guideme.volunteers.ui.fragments.base.BasicPresenter

class AuthenticationPresenter(
        val authenticationFragment: AuthenticationFragment,
        val googleAuth: GoogleAuth,
        val database: Database) :
        BasicPresenter() {

}