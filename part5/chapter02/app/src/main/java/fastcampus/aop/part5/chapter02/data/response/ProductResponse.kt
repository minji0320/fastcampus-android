package fastcampus.aop.part5.chapter02.data.response

import fastcampus.aop.part5.chapter02.data.entity.product.ProductEntity
import java.util.*

data class ProductResponse(
    val id: String,
    val createAt: Long,
    val productName: String,
    val productPrice: String,
    val productImage: String,
    val productType: String,
    val productIntroductionImage: String,
) {
    fun toEntity(): ProductEntity = ProductEntity(
        id = id.toLong(),
        createAt = Date(createAt),
        productName = productName,
        productPrice = productPrice.toDouble().toInt(),
        productImage = productImage,
        productType = productType,
        productIntroductionImage = productIntroductionImage
    )
}