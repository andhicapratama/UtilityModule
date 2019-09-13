@file:JvmName("FirebaseRemoteConfigService")

package com.andhicaaa.corelibrary.firebase

import android.content.Context
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

/**
 * Created by instagram : @andhicaaa on 9/12/2019.
 */
class FirebaseRemoteConfigService(val context: Context,
                                  val firebaseRemoteConfig: FirebaseRemoteConfig,
                                  val cacheTime: Int) {

    companion object {
        const val TAG = "FirebaseRemoteConfig"
    }

    private var isInitialized    = false

    fun init() {
        val cacheTime = when (firebaseRemoteConfig.info.lastFetchStatus) {
            FirebaseRemoteConfig.LAST_FETCH_STATUS_SUCCESS -> {
                Log.d(TAG, "remote config fetch was SUCCESS")
                cacheTime
            }
            else -> {
                Log.d(TAG, "remote config fetch was FAILURE")
                0
            }
        }
        fetchFirebaseRemoteConfiguration(cacheTime)
    }

    private fun fetchFirebaseRemoteConfiguration(cacheTime: Int) {
        firebaseRemoteConfig.fetch(cacheTime.toLong())
                .addOnSuccessListener {
                    Log.d(TAG, "remote config fetched success")
                    isInitialized = true
                    val result = firebaseRemoteConfig.activateFetched()
                    Log.d(TAG, if (result) "remote config activate fetched: SUCCESS"
                    else "remote config activate fetched: FAILURE")
                }
                .addOnFailureListener {
                    isInitialized = true
                    Log.d(TAG, "remote config fetched failed")
                }
    }

    fun getString(key: String, defaultValue: String = ""): String {
        val value = firebaseRemoteConfig.getValue(key)
        return if (value.source == FirebaseRemoteConfig.VALUE_SOURCE_STATIC) defaultValue else value.asString()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        val value = firebaseRemoteConfig.getValue(key)
        return if (value.source == FirebaseRemoteConfig.VALUE_SOURCE_STATIC) defaultValue else value.asBoolean()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        val value = firebaseRemoteConfig.getValue(key)
        return if (value.source == FirebaseRemoteConfig.VALUE_SOURCE_STATIC) defaultValue else value.asLong().toInt()
    }

    fun getLong(key: String, defaultValue: Long = 0): Long {
        val value = firebaseRemoteConfig.getValue(key)
        return if (value.source == FirebaseRemoteConfig.VALUE_SOURCE_STATIC) defaultValue else value.asLong()
    }

    fun getDouble(key: String, defaultValue: Double = 0.0): Double {
        val value = firebaseRemoteConfig.getValue(key)
        return if (value.source == FirebaseRemoteConfig.VALUE_SOURCE_STATIC) defaultValue else value.asDouble()
    }

    fun hasConfig(key: String): Boolean {
        val value = firebaseRemoteConfig.getValue(key)
        return value.source != FirebaseRemoteConfig.VALUE_SOURCE_STATIC
    }
}