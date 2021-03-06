package ir.beigirad.zeroapplication.bases

import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ir.beigirad.app.R
import ir.beigirad.zeroapplication.toast
import ir.beigirad.zeroapplication.util.SharedPrefUtils


/**
 * Created by farhad-mbp on 7/31/17.
 */

abstract class BaseActivity : AppCompatActivity(), HasToolbar {
    override val toolbar: Toolbar?
        get() = null
    override val toolbarTitle: Int?
        get() = null
    override val toolbarTitleS: String?
        get() = null
    override val toolbarLogo: Int?
        get() = null

    private lateinit var parentView: View

    private lateinit var progressDialog: ProgressDialog

    protected lateinit var sharedPref: SharedPrefUtils


    private val menu: Menu? = null


    protected abstract val contentView: Int



    protected open val hasBackConfirmation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)

//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        initVariables()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && isRtl) {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }

        initToolbar()
        initUI()
    }


    private var isRtl: Boolean = false


    protected open fun initVariables() {
        parentView = findViewById(android.R.id.content)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.please_wait))

    }


    protected open fun initUI() {}

    override fun initToolbar(hasBack: Boolean) {
        super.initToolbar(hasBack)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(toolbarTitle != null)

        //TODO remove redundant setTitle
        toolbarTitle?.let { supportActionBar?.setTitle(it) }
        toolbarTitleS?.let { supportActionBar?.setTitle(it) }

        if (hasBack) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //        getMenuInflater().inflateActionView(R.menu.cart, menu);

        //        this.menu = menu;
        //        updateCartNumber();

        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        progressDialog.dismiss()
        super.onDestroy()
    }

    protected open fun showProgress() {
        progressDialog.show()
    }

    protected open fun hideProgress() {
        progressDialog.hide()
    }

    private var lastBackPressed = -1L
    override fun onBackPressed() {
        if (hasBackConfirmation && System.currentTimeMillis() - lastBackPressed > 1500)
            toast(R.string.press_back_twice)
        else
            finish()
        lastBackPressed = System.currentTimeMillis()

    }

}

