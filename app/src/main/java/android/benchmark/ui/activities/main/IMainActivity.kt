package android.benchmark.ui.activities.main

import android.benchmark.ui.fragments.base.IFragmentContainer

interface IMainActivity {
    val actionBarTool: IActionBarTool
    fun navigateTo(fragmentContainer: IFragmentContainer)
    fun openSettings()
    fun getResourceText(id : Int) : String
}

