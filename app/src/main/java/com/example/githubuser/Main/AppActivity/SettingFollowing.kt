package com.example.githubuser.Main.AppActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.githubuser.BackupAPI.GithubRetrofit
import com.example.githubuser.BackupAPI.InfoUser

class SettingFollowing: ViewModel() {
    val fetchFollowing = MutableLiveData<ArrayList<InfoUser>>()

    fun sumofFollowing(username: String){
        GithubRetrofit.fetch_api
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<InfoUser>>{
                override fun onResponse(
                    call: Call<ArrayList<InfoUser>>,
                    response: Response<ArrayList<InfoUser>>
                ) {
                    if (response.isSuccessful){
                        fetchFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<InfoUser>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<InfoUser>>{
        return fetchFollowing
    }
}