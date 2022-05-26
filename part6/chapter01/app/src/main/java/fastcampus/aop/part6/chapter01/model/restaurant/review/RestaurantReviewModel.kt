package fastcampus.aop.part6.chapter01.model.restaurant.review

import android.net.Uri
import fastcampus.aop.part6.chapter01.model.CellType
import fastcampus.aop.part6.chapter01.model.Model

class RestaurantReviewModel(
    override val id: Long,
    override val type: CellType = CellType.REVIEW_CELL,
    val title: String,
    val description: String,
    val grade: Int,
    val thumbnailImageUri: Uri? = null,
) : Model(id, type) {
}