package ir.beigirad.zeroapplication.bases

import androidx.appcompat.widget.Toolbar

interface HasToolbar {

    val toolbar: Toolbar?

    val toolbarTitle: Int?
    val toolbarTitleS: String?
    val toolbarLogo: Int?

    fun initToolbar(hasBack: Boolean = true) {

        if (toolbar == null)
            return
        toolbarTitle?.let { toolbar?.setTitle(it) }
        toolbarTitleS?.let { toolbar?.setTitle(it) }
        toolbarLogo?.let { toolbar?.setLogo(it) }
    }


    interface BackListener {
        fun onBackTrigger()
    }
}