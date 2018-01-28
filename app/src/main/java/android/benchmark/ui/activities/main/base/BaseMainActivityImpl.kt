package android.benchmark.ui.activities.main.base

import android.androidkotlinbenchmark.R
import android.benchmark.ui.activities.main.MainActivityListener
import android.benchmark.ui.views.actionbar.ActionBarTool
import android.benchmark.ui.views.actionbar.ActionBarToolImpl
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import java.util.*

open class BaseMainActivityImpl : BaseMainActivity, AppCompatActivity() {
    companion object {
        val HOME_FRAGMENT_STACK_NAME = "home"
    }
    private var listeners : MutableList<MainActivityListener> = LinkedList()

    override fun openHome() {
        supportFragmentManager.popBackStack(HOME_FRAGMENT_STACK_NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun addListener(listener: MainActivityListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: MainActivityListener) {
        listeners.remove(listener)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                var handled = false
                listeners.forEach {
                    if (it.backButtonPressed()){
                        handled = true
                    }
                }
                if (handled){
                    return true
                }
                if (!actionBarTool.backPressed()) {
                    supportFragmentManager.popBackStack()
                }
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
    override fun goBack() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        var handled = false
        listeners.forEach {
            if (it.backButtonPressed()){
                handled = true
            }
        }
        if (handled){
            return
        }
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }

    override val actionBarTool: ActionBarTool = ActionBarToolImpl(this)
}