package fastcampus.aop.part6.chapter01.widget.adapter.listener.order

import fastcampus.aop.part6.chapter01.widget.adapter.listener.AdapterListener

interface OrderListListener : AdapterListener {

    fun writeRestaurantReview(orderId: String, restaurantTitle: String)

}