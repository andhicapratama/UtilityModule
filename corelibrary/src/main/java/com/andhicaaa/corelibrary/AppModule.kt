package com.andhicaaa.corelibrary

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by instagram : @andhicaaa on 9/13/2019.
 */
@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }

}