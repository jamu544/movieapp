package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movies
import com.example.movieapp.model.Result
import com.example.movieapp.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Result>>()

    fun getPopularMovies(){
        RetrofitInstance.api.getPopularMovies("770bed8dda637b8e9763fba4101d9433")
            .enqueue(object  :  retrofit2.Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                   if (response.body() != null){
                       movieLiveData.value = response.body()!!.results

                       Log.d("TAG --->", movieLiveData.value.toString())

                       System.out.println("Data f "+movieLiveData.value.toString() )
                   }
                    else {
                       System.out.println("Data  "+movieLiveData.value.toString() )

                   }
                }
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })
    }

    fun observeMovieLiveData(): LiveData<List<Result>>{
        return movieLiveData
    }
}