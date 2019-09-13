@file:JvmName("NetworkError")

package com.andhicaaa.corelibrary.network

import com.andhicaaa.corelibrary.util.TextUtil
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Created by instagram : @andhicaaa on 9/11/2019.
 */
open class NetworkError(private val error: Throwable) : Throwable() {

    val errorMessage: String?
        get() = error.message

    val isNetworkError: Boolean
        get() = error is IOException

    val isHttpError: Boolean
        get() = error is HttpException

    val isAuthFailure: Boolean
        get() = isHttpError && (error as HttpException).code() == HttpURLConnection.HTTP_UNAUTHORIZED

    val isAuthForbidden: Boolean
        get() = isHttpError && (error as HttpException).code() == HttpURLConnection.HTTP_FORBIDDEN

    val httpErrorCode: String
        get() = if (isHttpError) (error as HttpException).code().toString() else TextUtil.BLANK
}