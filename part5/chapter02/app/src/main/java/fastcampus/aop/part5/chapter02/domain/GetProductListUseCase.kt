package fastcampus.aop.part5.chapter02.domain

import fastcampus.aop.part5.chapter02.data.entity.product.ProductEntity
import fastcampus.aop.part5.chapter02.data.repository.ProductRepository

internal class GetProductListUseCase(
    private val toDoRepository: ProductRepository,
) : UseCase {

    suspend operator fun invoke(): List<ProductEntity> {
        return toDoRepository.getProductList()
    }

}
