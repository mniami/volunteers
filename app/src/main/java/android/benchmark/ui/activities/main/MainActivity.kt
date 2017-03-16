package android.benchmark.ui.activities.main

import android.benchmark.R
import android.benchmark.ui.fragments.base.IFragmentContainer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

internal class MainActivity : AppCompatActivity(), IMainActivity {
    val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            presenter.onCreate()
        }
    }

    override fun navigateTo(fragmentContainer: IFragmentContainer) {
        val fragment = fragmentContainer.getFragment()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        val transaction = supportFragmentManager.beginTransaction()

        if (currentFragment == null) {
            transaction.add(R.id.fragmentContainer, fragment, "Frags")
        } else {
            transaction.replace(R.id.fragmentContainer, fragment, "Frags")
        }

        transaction.commit()
    }
}

interface IMainActivity {
    fun navigateTo(fragmentContainer: IFragmentContainer)
}