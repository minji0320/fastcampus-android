package fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail.menu

import android.widget.Toast
import androidx.core.os.bundleOf
import fastcampus.aop.part6.chapter01.data.entity.RestaurantFoodEntity
import fastcampus.aop.part6.chapter01.databinding.FragmentListBinding
import fastcampus.aop.part6.chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.part6.chapter01.screen.base.BaseFragment
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail.RestaurantDetailViewModel
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail.review.RestaurantReviewListFragment
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import fastcampus.aop.part6.chapter01.widget.adapter.ModelRecyclerAdapter
import fastcampus.aop.part6.chapter01.widget.adapter.listener.restaurant.FoodMenuListListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantMenuListFragment :
    BaseFragment<RestaurantMenuListViewModel, FragmentListBinding>() {

    override fun getViewBinding(): FragmentListBinding =
        FragmentListBinding.inflate(layoutInflater)

    private val restaurantId by lazy {
        arguments?.getLong(RESTAURANT_ID_KEY, -1)
    }
    private val restaurantFoodList by lazy {
        arguments?.getParcelableArrayList<RestaurantFoodEntity>(FOOD_LIST_KEY)!!
    }

    override val viewModel by viewModel<RestaurantMenuListViewModel> {
        parametersOf(restaurantId, restaurantFoodList)
    }

    private val restaurantDetailViewModel by sharedViewModel<RestaurantDetailViewModel>()

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter by lazy {
        ModelRecyclerAdapter<FoodModel, RestaurantMenuListViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : FoodMenuListListener {
                override fun onClickItem(model: FoodModel) {
                    viewModel.insertMenuInBasket(model)
                }
            }
        )
    }

    override fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    override fun observeData() {
        viewModel.restaurantFoodListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.menuBasketLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                "장바구니에 담겼습니다. 메뉴 : ${it.title}", Toast.LENGTH_SHORT
            ).show()
            restaurantDetailViewModel.notifyFoodMenuListInBasket(it)
        }
        viewModel.isClearNeedInBasketLiveData.observe(viewLifecycleOwner) { (isClearNeed, afterAction) ->
            if (isClearNeed) {
                restaurantDetailViewModel.notifyClearNeedAlertInBasket(isClearNeed, afterAction)
            }
        }
    }


    companion object {

        const val RESTAURANT_ID_KEY = "restaurantId"

        const val FOOD_LIST_KEY = "foodList"

        fun newInstance(
            restaurantId: Long,
            foodList: ArrayList<RestaurantFoodEntity>,
        ): RestaurantMenuListFragment {
            val bundle = bundleOf(
                RESTAURANT_ID_KEY to restaurantId,
                FOOD_LIST_KEY to foodList
            )
            return RestaurantMenuListFragment().apply {
                arguments = bundle
            }
        }
    }
}