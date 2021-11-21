package com.erwin2687.amovie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    var page: Int,
    @SerializedName("total_results")
    var totalResults: Int,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("results")
    var movieLists: List<Movie>,
    var error: String
) : Parcelable