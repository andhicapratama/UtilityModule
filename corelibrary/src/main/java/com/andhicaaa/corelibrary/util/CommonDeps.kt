package com.andhicaaa.corelibrary.util

import androidx.appcompat.app.AppCompatActivity
import com.andhicaaa.corelibrary.firebase.FirebaseInstanceIdTokenService
import javax.inject.Singleton
import org.greenrobot.eventbus.EventBus

/**
 * Created by instagram : @andhicaaa on 9/12/2019.
 */
@Singleton
interface CommonDeps {

    val eventBus: EventBus

    fun inject(appCompatActivity: AppCompatActivity)

    fun inject(firebaseInstanceIdTokenService: FirebaseInstanceIdTokenService)
}