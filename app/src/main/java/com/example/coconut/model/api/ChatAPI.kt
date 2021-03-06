package com.example.coconut.model.api

import com.example.coconut.model.request.chat.ChatRoomDataRequest
import com.example.coconut.model.request.chat.ChatRoomExitRequest
import com.example.coconut.model.request.chat.ChatRoomNameChangeRequest
import com.example.coconut.model.request.chat.ChatRoomSaveRequest
import com.example.coconut.model.response.chat.ChatRoomDataResponse
import com.example.coconut.model.response.chat.ChatHistoryResponse
import com.example.coconut.model.response.chat.ChatRoomListResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ChatAPI {

    @POST("/api/chat")
    @Multipart
    fun sendMessage(
        @Part("chatRoomId") chatRoomId: String?,
        @Part("chatUserId") chatUserId: String?,
        @Part("content") content: String?,
        @Part images: ArrayList<MultipartBody.Part?>?
    ): Single<ChatRoomDataResponse>

    @POST("/api/chat/room/make")
    fun makeChatRoom(
        @Body chatRoomSaveRequest: ChatRoomSaveRequest
    ): Single<ChatRoomDataResponse>

    @POST("/api/chat/room/info")
    fun getChatRoomData(
        @Body chatRoomDataRequest: ChatRoomDataRequest
    ): Single<ChatRoomDataResponse>

    @GET("/api/chat/{chatRoomId}")
    fun getChatHistory(
        @Path("chatRoomId") chatRoomId: String?
    ): Single<ArrayList<ChatHistoryResponse>>

    @GET("/api/chat/room/list/{userId}")
    fun getChatRoomLists(
        @Path("userId") userId: String?
    ): Single<ArrayList<ChatRoomListResponse>>

    @POST("/api/chat/upload/image")
    @Multipart
    fun uploadChatImages(
        @Part("userId") userId: RequestBody?,
        @Part("chatRoomId") chatRoomId: RequestBody?,
        @Part images: ArrayList<MultipartBody.Part?>?
    ): Single<ArrayList<String>>

    @POST("/api/chat/room/name")
    fun changeChatRoomName(
        @Body chatRoomNameChangeRequest: ChatRoomNameChangeRequest
    ): Single<Boolean>

    @POST("/api/chat/room/exit")
    fun exitChatRoom(
        @Body chatRoomExitRequest: ChatRoomExitRequest
    ): Single<Boolean>

    @POST("/api/chat/room/invite")
    fun inviteUser(
        @Body chatRoomDataRequest: ChatRoomDataRequest
    ): Single<ChatRoomDataResponse>
}