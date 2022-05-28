package fastcampus.aop.part6.chapter01.screen.main.like

import androidx.core.view.isGone
import androidx.core.view.isVisible
import fastcampus.aop.part6.chapter01.databinding.FragmentRastaurantLikeListBinding
import fastcampus.aop.part6.chapter01.model.restaurant.RestaurantModel
import fastcampus.aop.part6.chapter01.screen.base.BaseFragment
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail.RestaurantDetailActivity
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import fastcampus.aop.part6.chapter01.widget.adapter.ModelRecyclerAdapter
import fastcampus.aop.part6.chapter01.widget.adapter.listener.restaurant.RestaurantLikeListListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantLikeListFragment :
    BaseFragment<RestaurantLikeListViewModel, FragmentRastaurantLikeListBinding>() {
    override val viewModel by viewModel<RestaurantLikeListViewModel>()

    override fun getViewBinding(): FragmentRastaurantLikeListBinding =
        FragmentRastaurantLikeListBinding.inflate(layoutInflater)

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantModel, RestaurantLikeListViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : RestaurantLikeListListener {
                override fun onDislikeItem(model: RestaurantModel) {
                    viewModel.dislikeRestaurant(model.toEntity())
                }

                override fun onClickItem(model: RestaurantModel) {
                    startActivity(
                        RestaurantDetailActivity.newIntent(
                            requireContext(),
                            model.toEntity()
                        )
                    )
                }
            }
        )
    }

    override fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    override fun observeData() = viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
        checkListEmpty(it)
    }

    private fun checkListEmpty(restaurantList: List<RestaurantModel>) {
        val isEmpty = restaurantList.isEmpty()
        binding.recyclerView.isGone = isEmpty
        binding.emptyResultTextView.isVisible = isEmpty
        if (!isEmpty) {
            adapter.submitList(restaurantList)
        }
    }

    companion object {

        const val TAG = "RestaurantLikeListFragment"

        fun newInstance() = RestaurantLikeListFragment()
    }
}