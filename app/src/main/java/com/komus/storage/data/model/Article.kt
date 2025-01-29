package com.komus.storage.data.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("value")
    val value: List<ArticleData>,
    @SerializedName("errorCode")
    val errorCode: Int
){
    data class ArticleData(
        @SerializedName("ID")
        val id: String,
        @SerializedName("NAME")
        val name: String,
        @SerializedName("QNT_IN_PALLET")
        val quantityInPallet: Int
    )
}