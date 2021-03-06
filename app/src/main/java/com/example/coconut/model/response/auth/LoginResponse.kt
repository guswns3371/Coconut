package com.example.coconut.model.response.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
  @SerializedName("isCorrect") var isCorrect : Boolean,
  @SerializedName("isConfirmed") var isConfirmed : Boolean,
  @SerializedName("id") var user_idx : String // user idx
){
  override fun toString(): String {
    return "LoginResponse(isCorrect=$isCorrect, isConfirmed=$isConfirmed, user_idx='$user_idx')"
  }
}