package com.erwin2687.amovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erwin2687.amovie.helper.ViewModelHelpers
import com.erwin2687.amovie.model.TvShow
import com.erwin2687.amovie.network.ApiRepository
import com.erwin2687.amovie.network.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {
    private val tv = MutableLiveData<TvShow>()
    private var viewModelHelpers: ViewModelHelpers? = null

    internal fun setViewModelHelpers(helper: ViewModelHelpers) {
        viewModelHelpers = helper
    }

    internal fun setTV(tvId: Int, language: String) {
        val service = ApiRepository.createService(ApiServices::class.java)
        val call = service.GetTV(tvId, language)
        call.enqueue(object : Callback<TvShow> {
            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                viewModelHelpers?.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                val item = response.body() as TvShow
                tv.postValue(item)
                viewModelHelpers?.onSuccess()
            }

        })
    }

    internal fun getTV(): LiveData<TvShow> {
        return tv
    }
}