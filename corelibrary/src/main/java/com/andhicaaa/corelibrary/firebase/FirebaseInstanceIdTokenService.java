package com.andhicaaa.corelibrary.firebase;

import android.util.Log;

import com.andhicaaa.corelibrary.sharedprefs.CorePreferences;
import com.andhicaaa.corelibrary.CommonDepsProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.reactivestreams.Publisher;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by instagram : @andhicaaa on 9/12/2019.
 */
public class FirebaseInstanceIdTokenService extends FirebaseInstanceIdService {

    @Inject
    CorePreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("FirebaseIidService", "onCreate()");
        ((CommonDepsProvider) getApplicationContext()).getCommonDeps().inject(this);
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        fetchingInstanceIdToken();
    }

    private void fetchingInstanceIdToken() {
        Log.d("FirebaseIidService", "fetchingInstanceIdToken()");

        Flowable<String> firebaseInstanceIdToken = Flowable.defer(new Callable<Publisher<? extends String>>() {
            @Override
            public Publisher<? extends String> call() throws Exception {
                try {
                    String instanceIdToken = FirebaseInstanceId.getInstance().getToken();
                    Log.d("instanceIdToken", instanceIdToken);
                    return Flowable.just(instanceIdToken != null ?
                            instanceIdToken : FirebaseConstant.FIREBASE_ID_TOKEN_DEFAULT_VALUE);
                } catch (NullPointerException ex) {
                    return Flowable.error(ex);
                }
            }
        });

        firebaseInstanceIdToken
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(Throwable throwable) throws Exception {
                        return Flowable.error(throwable);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String token) throws Exception {
                        preferences.setString(FirebaseConstant.FIREBASE_ID_TOKEN, token);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("FirebaseIidService", throwable.getMessage());
                    }
                });
    }
}

