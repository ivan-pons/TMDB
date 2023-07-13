package com.mediapro.localdatasource.utils

import javax.inject.Inject

class UserManager @Inject constructor(
//    userLocalDataSource: UserRoomDatasource
) {

    private var _token: String = ""
    val token get() = _token

    private var _profileId: String = ""
    val profileId get() = _profileId

//    var disposable: Disposable

    init {
//        val userFlowable = userLocalDataSource
//            .getUserFlowable()
//        disposable = userFlowable
//            .observeOn(Schedulers.io())
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                _token = it?.token ?: ""
//                _profileId = it?.id ?: ""
//            }, {
//                //Do nothing
//            })
    }
}