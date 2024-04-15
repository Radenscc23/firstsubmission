package com.example.githubuser.Main.AppActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.githubuser.BackupAPI.GithubRetrofit
import com.example.githubuser.BackupAPI.DetailClick
import androidx.lifecycle.ViewModel


class SettingsDetail: ViewModel() {
    val sumofUsers = MutableLiveData<DetailClick>()
    fun userDetails(username: String){
        GithubRetrofit.fetch_api
            .getUserDetail(username)
            .enqueue(object : Callback<DetailClick>{
                override fun onResponse(
                    call: Call<DetailClick>,
                    response: Response<DetailClick>
                ) {
                    if (response.isSuccessful){
                        sumofUsers.postValue(response.body())
                    } }
                override fun onFailure(call: Call<DetailClick>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                } }) }
    fun fetchUserinDetails(): LiveData<DetailClick> {
        return sumofUsers }
}