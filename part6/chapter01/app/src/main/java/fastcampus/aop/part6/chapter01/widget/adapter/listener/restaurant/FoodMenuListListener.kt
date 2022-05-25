package fastcampus.aop.part6.chapter01.widget.adapter.listener.restaurant

import fastcampus.aop.part6.chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.part6.chapter01.widget.adapter.listener.AdapterListener

interface FoodMenuListListener : AdapterListener {

    fun onClickItem(model: FoodModel)
}