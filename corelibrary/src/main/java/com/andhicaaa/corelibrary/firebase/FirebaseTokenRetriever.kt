package com.andhicaaa.corelibrary.firebase

import com.andhicaaa.corelibrary.sharedprefs.CorePreferences

/**
 * Created by instagram : @andhicaaa on 9/12/2019.
 */
class FirebaseTokenRetriever(private val corePreferences: CorePreferences) {

    fun getAuthToken(): String {
        return corePreferences.getString(FirebaseConstant.FIREBASE_ID_TOKEN)
    }
}