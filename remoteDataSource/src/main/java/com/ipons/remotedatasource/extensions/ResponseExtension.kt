package com.ipons.remotedatasource.extensions

import androidx.annotation.VisibleForTesting
import com.ipons.remotedatasource.exception.BusinessException
import com.ipons.remotedatasource.exception.ErrorType
import okhttp3.ResponseBody
import retrofit2.Response

fun <T> Response<T>.parseResponse(): T {
    val body = this.body()
    if (this.isSuccessful && body != null) {
        return body
    } else {
        throw BusinessException(
            getErrorType(this.code(), this.errorBody()), "$this.code() $this.message()"
        )
    }
}

fun <T> Response<T>.parseEmptyResponse() {
    if (!this.isSuccessful) {
        throw BusinessException(
            getErrorType(this.code(), this.errorBody()), "$this.code() $this.message()"
        )
    }
}

@VisibleForTesting
fun getErrorType(code: Int, errorBody: ResponseBody?): ErrorType {
    return when (code) {
        401 -> ErrorType.UNAUTHORIZED
        402 -> ErrorType.SUBSCRIPTION_NEEDED
        403 -> ErrorType.FORBIDEN
        409 -> ErrorType.CONFLICT
        else -> ErrorType.UNEXPECTED
    }
}
