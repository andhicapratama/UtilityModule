package com.andhicaaa.corelibrary.util;

import androidx.appcompat.app.AppCompatActivity;

import com.andhicaaa.corelibrary.firebase.FirebaseInstanceIdTokenService;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

/**
 * Created by instagram : @andhicaaa on 9/12/2019.
 */
    @Singleton
    public interface CommonDeps {

        EventBus getEventBus();

        void inject(AppCompatActivity appCompatActivity);

        void inject(FirebaseInstanceIdTokenService firebaseInstanceIdTokenService);
    }
