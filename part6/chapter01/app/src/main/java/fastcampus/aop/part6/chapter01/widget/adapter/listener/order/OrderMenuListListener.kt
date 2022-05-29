package fastcampus.aop.part6.chapter01.widget.adapter.listener.order

import fastcampus.aop.part6.chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.part6.chapter01.widget.adapter.listener.AdapterListener

interface OrderMenuListListener : AdapterListener {

    fun onRemoveItem(model: FoodModel)
}