package fastcampus.aop.part6.chapter01.widget.adapter.viewholder.order

import fastcampus.aop.part6.chapter01.R
import fastcampus.aop.part6.chapter01.databinding.ViewholderOrderBinding
import fastcampus.aop.part6.chapter01.model.restaurant.order.OrderModel
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import fastcampus.aop.part6.chapter01.widget.adapter.listener.AdapterListener
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.ModelViewHolder

class OrderViewHolder(
    private val binding: ViewholderOrderBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider,
) : ModelViewHolder<OrderModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = Unit

    override fun bindData(model: OrderModel) {
        super.bindData(model)
        with(binding) {
            orderTitleText.text =
                resourcesProvider.getString(R.string.order_history_title, model.orderId)

            val foodMenuList = model.foodMenuList

            foodMenuList.groupBy { it.title }
                .entries.forEach { (title, menuList) ->
                    val orderDataStr = orderContentText.text.toString() +
                            "메뉴 : $title | 가격 : ${menuList.first().price}원 X ${menuList.size}\n"
                    orderContentText.text = orderDataStr
                }
            orderTotalPriceText.text = orderContentText.text.trim()

            orderTotalPriceText.text = resourcesProvider.getString(
                R.string.price,
                foodMenuList.map { it.price }.reduce { total, price -> total + price }
            )
        }
    }

    override fun bindViews(model: OrderModel, adapterListener: AdapterListener) = Unit
}
