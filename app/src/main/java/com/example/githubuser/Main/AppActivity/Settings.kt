package com.example.appgithub.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.BackupAPI.GithubRetrofit
import com.example.githubuser.BackupAPI.InfoUser
import com.example.githubuser.BackupAPI.ClickUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Settings: ViewModel() {

    val listUsers = MutableLiveData<ArrayList<InfoUser>>()

    fun setSearchUsers(query: String){
        GithubRetrofit.fetch_api
            .getSearchUsers(query)
            .enqueue(object : Callback<ClickUser> {
                override fun onResponse(
                    call: Call<ClickUser>,
                    response: Response<ClickUser>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }
                override fun onFailure(call: Call<ClickUser>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getSearchUsers(): LiveData<ArrayList<InfoUser>> {
        return listUsers
    }
}