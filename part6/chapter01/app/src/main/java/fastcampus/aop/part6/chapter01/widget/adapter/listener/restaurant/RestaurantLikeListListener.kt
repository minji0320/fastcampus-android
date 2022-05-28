package fastcampus.aop.part6.chapter01.widget.adapter.listener.restaurant

import fastcampus.aop.part6.chapter01.model.restaurant.RestaurantModel

interface RestaurantLikeListListener : RestaurantListListener {

    fun onDislikeItem(model: RestaurantModel)
}