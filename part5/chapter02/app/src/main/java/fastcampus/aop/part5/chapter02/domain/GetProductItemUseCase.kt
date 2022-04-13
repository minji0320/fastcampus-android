package fastcampus.aop.part5.chapter02.domain

import fastcampus.aop.part5.chapter02.data.entity.product.ProductEntity
import fastcampus.aop.part5.chapter02.data.repository.ProductRepository

internal class GetProductItemUseCase(
    private val toDoRepository: ProductRepository,
) : UseCase {

    suspend operator fun invoke(productId: Long): ProductEntity? {
        return toDoRepository.getProductItem(productId)
    }

}
