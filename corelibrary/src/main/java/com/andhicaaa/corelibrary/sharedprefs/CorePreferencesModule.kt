@file:JvmName("CorePreferencesModule")

package com.andhicaaa.corelibrary.sharedprefs

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by instagram : @andhicaaa on 9/12/2019.
 */

@Module
class CorePreferencesModule {

    @Provides
    @Singleton
    fun providesCorePrefs(context: Context) = CorePreferences.getInstance(context)
}