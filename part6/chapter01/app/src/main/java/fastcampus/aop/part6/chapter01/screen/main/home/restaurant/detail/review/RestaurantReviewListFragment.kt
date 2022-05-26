package fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail.review

import android.widget.Toast
import androidx.core.os.bundleOf
import fastcampus.aop.part6.chapter01.databinding.FragmentListBinding
import fastcampus.aop.part6.chapter01.screen.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantReviewListFragment :
    BaseFragment<RestaurantReviewListViewModel, FragmentListBinding>() {

    override fun getViewBinding(): FragmentListBinding =
        FragmentListBinding.inflate(layoutInflater)

    override val viewModel by viewModel<RestaurantReviewListViewModel> {
        parametersOf(arguments?.getString(RESTAURANT_TITLE_KEY))
    }

    override fun observeData() = viewModel.reviewStateLiveDate.observe(viewLifecycleOwner) {
        when (it) {
            is RestaurantReviewState.Success -> {
                handleSuccess(it)
            }
        }
    }

    private fun handleSuccess(state: RestaurantReviewState.Success) {
        Toast.makeText(requireContext(), state.reviewList.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {

        const val RESTAURANT_TITLE_KEY = "restaurantTitle"

        fun newInstance(restaurantTitle: String): RestaurantReviewListFragment {
            val bundle = bundleOf(
                RESTAURANT_TITLE_KEY to restaurantTitle,
            )
            return RestaurantReviewListFragment().apply {
                arguments = bundle
            }
        }
    }
}