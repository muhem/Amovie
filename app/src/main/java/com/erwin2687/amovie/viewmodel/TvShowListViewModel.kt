package com.erwin2687.amovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erwin2687.amovie.database.TvDB
import com.erwin2687.amovie.helper.ViewModelHelpers
import com.erwin2687.amovie.model.TvShow
import com.erwin2687.amovie.model.TvShowResponse
import com.erwin2687.amovie.network.ApiRepository
import com.erwin2687.amovie.network.ApiServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowListViewModel : ViewModel() {
    private val listTV = MutableLiveData<ArrayList<TvShow>>()
    private var viewModelHelpers: ViewModelHelpers? = null

    internal fun setViewModelHelpers(helper: ViewModelHelpers) {
        viewModelHelpers = helper
    }

    internal fun setTV() {
        val service = ApiRepository.createService(ApiServices::class.java)
        val call = service.DiscoverTV("en-US")
        call.enqueue(object : Callback<TvShowResponse> {
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                viewModelHelpers?.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                val tvList = response.body() as TvShowResponse
                listTV.postValue(tvList.tvLists as ArrayList<TvShow>)

                viewModelHelpers?.onSuccess()
            }
        })
    }

    internal fun setFavoriteTV(tvDB: TvDB) {
        GlobalScope.launch {
            val list = tvDB.getAllTV() as ArrayList<TvShow>
            listTV.postValue(list)
        }
    }

    internal fun getTV(): LiveData<ArrayList<TvShow>> {
        return listTV
    }

}