package fastcampus.aop.part5.chapter02.data.entity.product

import java.util.*

data class ProductEntity(
    val id: Long,
    val createAt: Date,
    val productName: String,
    val productPrice: Int,
    val productImage: String,
    val productType: String,
    val productIntroductionImage: String,
)
