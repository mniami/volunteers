package android.benchmark.ui.fragments

import android.androidkotlinbenchmark.R
import android.benchmark.helpers.dataservices.errors.ErrorMessage
import android.benchmark.helpers.android.fromSerializable
import android.benchmark.helpers.dataservices.errors.ErrorType
import android.benchmark.ui.activities.main.MainActivityListener
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.BasicView
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.fragments.base.BasicPresenter
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuInflater
import kotlinx.android.synthetic.main.error_fragment.*
import kotlinx.android.synthetic.main.generic_item.*

/**
 * Created by Damian Szczepanski on 11.01.2018.
 */
class ErrorFragmentImpl : BaseFragment<BasicPresenter>(), BasicView {
    companion object {
        private val ERROR_MESSAGE = "error_message"

        fun createFragment(errorMessage: ErrorMessage): ErrorFragmentImpl {
            val fragment = ErrorFragmentImpl()
            val bundle = Bundle().fromSerializable(ERROR_MESSAGE, errorMessage)
            fragment.arguments = bundle
            return fragment
        }
    }
    private var menu : Menu? = null

    private val mainActivityListener = object : MainActivityListener {
        override fun backButtonPressed(): Boolean {
            mainActivity.openHome()
            return true
        }
    }

    init {
        configuration = FragmentConfiguration.withLayout(R.layout.error_fragment)
                .title(R.string.error_title)
                .showBackArrow()
                .create()
        presenter = BasicPresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.error_menu, menu)
        this.menu = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onResume() {
        mainActivity.addListener(mainActivityListener)
        super.onResume()

        val errorMessage = arguments.getSerializable(ERROR_MESSAGE)
        if (errorMessage is ErrorMessage) {
            handleErrorType(errorMessage)
        }
    }

    override fun onPause() {
        mainActivity.removeListener(mainActivityListener)
        super.onPause()
    }

    private fun handleErrorType(errorMessage: ErrorMessage) {
        when(errorMessage.errorType){
            ErrorType.NO_INTERNET_CONNECTION -> {
                messageTextView.text = Html.fromHtml(getString(R.string.no_internet_connection_message))
                mainActivity.actionBarTool.setTitle(getString(R.string.no_internet_connection_title))

                val home = menu?.findItem(R.id.home)
                home?.isVisible = false
            }
        }
    }
}