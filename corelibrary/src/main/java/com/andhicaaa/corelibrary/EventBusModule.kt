@file:JvmName("EventBusModule")

package com.andhicaaa.corelibrary

import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

/**
 * Created by instagram : @andhicaaa on 9/13/2019.
 */
@Module
class EventBusModule {

    @Provides
    @Singleton
    fun providesEventBus(): EventBus = EventBus.getDefault()
}