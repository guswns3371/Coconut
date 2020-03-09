package com.example.coconut.ui.main.account.info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coconut.base.BaseKotlinViewModel
import com.example.coconut.model.MyRepository
import com.example.coconut.model.request.AccountEditPostData
import com.example.coconut.model.response.BaseResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class AccountInfoViewModel(private val myRepository: MyRepository) :BaseKotlinViewModel() {

    private val TAG = "AccountInfoViewModel"

    private val _baseResponseLiveData = MutableLiveData<BaseResponse>()
    val baseResponseLiveData: LiveData<BaseResponse> = _baseResponseLiveData

    fun edit(accountEditPostData: AccountEditPostData){
        addDisposable(myRepository.updateAccountData(accountEditPostData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                        Log.e(TAG, "response : \n${toString()}")
                        _baseResponseLiveData.postValue(this)
                }
            },{
                Log.e(TAG, "response error, message : ${it.message}")
            })
        )
    }
}