package fastcampus.aop.part6.chapter01.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantFoodEntity(
    val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val restaurantId: Long,
) : Parcelable