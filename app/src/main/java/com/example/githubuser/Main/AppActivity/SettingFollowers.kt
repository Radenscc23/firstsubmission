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

class SettingFollowers: ViewModel() {
    val fetchFollowers = MutableLiveData<ArrayList<InfoUser>>()

    fun sumofFollowers(username: String){
        GithubRetrofit.fetch_api
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<InfoUser>>{
                override fun onResponse(
                    call: Call<ArrayList<InfoUser>>,
                    response: Response<ArrayList<InfoUser>>
                ) {
                    if (response.isSuccessful){
                        fetchFollowers.postValue(response.body()) } }

                override fun onFailure(call: Call<ArrayList<InfoUser>>, t: Throwable) {
                    Log.d("Failure", t.message.toString()) } }) }

    fun getListFollowers(): LiveData<ArrayList<InfoUser>>{
        return fetchFollowers
    }
}