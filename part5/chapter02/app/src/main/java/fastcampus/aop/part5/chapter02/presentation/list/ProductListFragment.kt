package fastcampus.aop.part5.chapter02.presentation.list

import fastcampus.aop.part5.chapter02.databinding.FragmentProductListBinding
import fastcampus.aop.part5.chapter02.databinding.FragmentProfileBinding
import fastcampus.aop.part5.chapter02.presentation.BaseFragment
import org.koin.android.ext.android.inject

internal class ProductListFragment :
    BaseFragment<ProductListViewModel, FragmentProductListBinding>() {

    companion object {
        const val TAG = "ProductListFragment"
    }

    override val viewModel by inject<ProductListViewModel>()

    override fun getViewBinding(): FragmentProductListBinding =
        FragmentProductListBinding.inflate(layoutInflater)

    override fun observeData() {
    }

}