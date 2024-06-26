package nz.co.test.transactions.di.activities

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import nz.co.test.transactions.activities.MainActivity

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector()
    abstract fun providesMainActivity(): MainActivity
}