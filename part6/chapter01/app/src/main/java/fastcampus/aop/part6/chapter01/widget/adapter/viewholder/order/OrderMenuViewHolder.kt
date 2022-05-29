package fastcampus.aop.part6.chapter01.widget.adapter.viewholder.order

import com.bumptech.glide.load.resource.bitmap.CenterCrop
import fastcampus.aop.part6.chapter01.R
import fastcampus.aop.part6.chapter01.databinding.ViewholderFoodMenuBinding
import fastcampus.aop.part6.chapter01.databinding.ViewholderOrderMenuBinding
import fastcampus.aop.part6.chapter01.extensions.clear
import fastcampus.aop.part6.chapter01.extensions.load
import fastcampus.aop.part6.chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import fastcampus.aop.part6.chapter01.widget.adapter.listener.AdapterListener
import fastcampus.aop.part6.chapter01.widget.adapter.listener.order.OrderMenuListListener
import fastcampus.aop.part6.chapter01.widget.adapter.listener.restaurant.FoodMenuListListener
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.ModelViewHolder

class OrderMenuViewHolder(
    private val binding: ViewholderOrderMenuBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider,
) : ModelViewHolder<FoodModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = with(binding) {
        foodImage.clear()
    }

    override fun bindData(model: FoodModel) {
        super.bindData(model)
        with(binding) {
            foodImage.load(model.imageUrl, 24f, CenterCrop())
            foodTitleText.text = model.title
            foodDescription.text = model.description
            priceText.text = resourcesProvider.getString(R.string.price, model.price)
        }
    }

    override fun bindViews(model: FoodModel, adapterListener: AdapterListener) {
        if (adapterListener is OrderMenuListListener) {
            binding.removeButton.setOnClickListener { adapterListener.onRemoveItem(model) }
        }
    }
}
