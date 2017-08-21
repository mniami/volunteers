package android.benchmark.ui.activities.main


internal class MainPresenter(val mainActivity: MainActivity) :
        IMainPresenter {
    override fun onAuthenticationClick() = mainActivity.openAuthentication()

    override fun onSettingsClick() = mainActivity.openSettings()

    override fun onCreate() {
        mainActivity.showVolunteerList()
    }
}

