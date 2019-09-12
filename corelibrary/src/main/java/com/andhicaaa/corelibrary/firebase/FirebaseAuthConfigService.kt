package com.andhicaaa.corelibrary.firebase

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

/**
 * Created by instagram : @andhicaaa on 9/12/2019.
 */
class FirebaseAuthConfigService {

    inline fun signInWithCredentials(activity: Activity,
                                     firebaseAuth: FirebaseAuth,
                                     account: GoogleSignInAccount,
                                     crossinline onSuccess: () -> Unit,
                                     crossinline onFailed: () -> Unit) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity) { task ->
                    when {
                        task.isSuccessful -> onSuccess()
                        else -> onFailed()
                    }
                }
    }
}