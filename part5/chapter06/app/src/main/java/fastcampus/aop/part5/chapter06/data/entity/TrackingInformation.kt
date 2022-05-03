package fastcampus.aop.part5.chapter06.data.entity


import com.google.gson.annotations.SerializedName

data class TrackingInformation(
    @SerializedName("adUrl")
    val adUrl: String? = null,
    @SerializedName("complete")
    val complete: Boolean? = null,
    @SerializedName("completeYN")
    val completeYN: String? = null,
    @SerializedName("estimate")
    val estimate: String? = null,
    @SerializedName("firstDetail")
    val firstDetail: TrackingDetail? = null,
    @SerializedName("invoiceNo")
    val invoiceNo: String? = null,
    @SerializedName("itemImage")
    val itemImage: String? = null,
    @SerializedName("itemName")
    val itemName: String? = null,
    @SerializedName("lastDetail")
    val lastDetail: TrackingDetail? = null,
    @SerializedName("lastStateDetail")
    val lastStateDetail: TrackingDetail? = null,
    @SerializedName("level")
    val level: Level? = null,
    @SerializedName("orderNumber")
    val orderNumber: String? = null,
    @SerializedName("productInfo")
    val productInfo: String? = null,
    @SerializedName("result")
    val result: String? = null,
    @SerializedName("trackingDetails")
    val trackingDetails: List<TrackingDetail>? = null,
    @SerializedName("zipCode")
    val zipCode: String? = null,
)