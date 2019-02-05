package ir.beigirad.app

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import ir.beigirad.app.di.DaggerApplicationComponent
import ir.beigirad.zeroapplication.ZeroApplication
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by farhad-mbp on 11/4/17.
 */

class CountryApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)

        Timber.plant(Timber.DebugTree())

    }
}
