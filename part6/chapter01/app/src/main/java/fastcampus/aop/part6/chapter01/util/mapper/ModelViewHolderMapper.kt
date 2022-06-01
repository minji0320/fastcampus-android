package fastcampus.aop.part6.chapter01.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import fastcampus.aop.part6.chapter01.databinding.*
import fastcampus.aop.part6.chapter01.model.CellType
import fastcampus.aop.part6.chapter01.model.Model
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.EmptyViewHolder
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.ModelViewHolder
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.food.FoodMenuViewHolder
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.order.OrderMenuViewHolder
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.order.OrderViewHolder
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.restaurant.LikeRestaurantViewHolder
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.restaurant.RestaurantViewHolder
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.review.RestaurantReviewViewHolder

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider,
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.EMPTY_CELL -> EmptyViewHolder(
                ViewholderEmptyBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.RESTAURANT_CELL -> RestaurantViewHolder(
                ViewholderRestaurantBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.LIKE_RESTAURANT_CELL -> LikeRestaurantViewHolder(
                ViewholderLikeRestaurantBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.FOOD_CELL -> FoodMenuViewHolder(
                ViewholderFoodMenuBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.REVIEW_CELL -> RestaurantReviewViewHolder(
                ViewholderRestaurantReviewBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.ORDER_FOOD_CELL -> OrderMenuViewHolder(
                ViewholderOrderMenuBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.ORDER_CELL -> OrderViewHolder(
                ViewholderOrderBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
        }

        return viewHolder as ModelViewHolder<M>
    }
}