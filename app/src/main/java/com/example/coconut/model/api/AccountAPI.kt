package com.example.coconut.model.api

import com.example.coconut.model.response.BaseResponse
import com.example.coconut.model.response.account.UserDataResponse
import com.example.coconut.oauth2.UserInfoResult
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AccountAPI {

    @GET("/api/account/{id}")
    fun getAllUserData(
        @Path("id") myId: String
    ): Single<ArrayList<UserDataResponse>>

    @POST("/api/account/edit")
    @Multipart
    //@Path 와 @Part 동시에 사용 못하네
    fun updateAccount(
        @Part("id") id : RequestBody?,
        @Part("userId") userId : RequestBody?,
        @Part("name") name : RequestBody?,
        @Part("message") message : RequestBody?,
        @Part images : Array<MultipartBody.Part?>?
    ) : Single<BaseResponse>

}