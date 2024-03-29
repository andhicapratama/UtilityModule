@file:JvmName("FirebaseModule")

package com.andhicaaa.corelibrary.firebase

import android.content.Context
import com.andhicaaa.corelibrary.R
import com.andhicaaa.corelibrary.sharedprefs.CorePreferences
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rioswarawan on 2/28/18.
 */
@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun providesFirebaseConfigService(): FirebaseAuthConfigService =
            FirebaseAuthConfigService()

    @Provides
    @Singleton
    fun providesFirebaseTokenRetriever(corePreferences: CorePreferences): FirebaseTokenRetriever =
            FirebaseTokenRetriever(corePreferences)

    @Provides
    @Singleton
    fun providesFirebaseRemoteConfig(): FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance().apply {
        setDefaults(R.xml.remote_config_default)
    }

    @Provides
    @Singleton
    fun providesFirebaseRemoteConfigService(context: Context, firebaseRemoteConfig: FirebaseRemoteConfig): FirebaseRemoteConfigService {
        return FirebaseRemoteConfigService(context, firebaseRemoteConfig, context.resources.getInteger(R.integer.remote_config_cache_expiry_time))
    }

    @Provides
    @Singleton
    fun providesFirebaseRealtimeDatabase(): FirebaseDatabase =
            FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseRealtimeDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference =
            firebaseDatabase.reference

    @Provides
    @Singleton
    fun providesNotificationHelper(context: Context): NotificationHelper =
            NotificationHelper(context)
}