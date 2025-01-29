package com.komus.storage.data.model
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("value")
    val value: List<ProductData>,
    @SerializedName("errorCode")
    val errorCode: Int
) {
    data class ProductData(
        @SerializedName("ID")
        val id: Int,
        @SerializedName("PRODUCT_ID")
        val productId: Int,
        @SerializedName("PRODUCT_QNT")
        val productQuantity: Int,
        @SerializedName("PRUNIT_TYPE_ID")
        val prunitTypeId: Int
    )
}