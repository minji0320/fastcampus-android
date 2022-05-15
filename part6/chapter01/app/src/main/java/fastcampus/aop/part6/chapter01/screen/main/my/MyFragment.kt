package fastcampus.aop.part6.chapter01.screen.main.my

import fastcampus.aop.part6.chapter01.databinding.FragmentHomeBinding
import fastcampus.aop.part6.chapter01.databinding.FragmentMyBinding
import fastcampus.aop.part6.chapter01.screen.base.BaseFragment
import fastcampus.aop.part6.chapter01.screen.main.home.HomeFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MyFragment : BaseFragment<MyViewModel, FragmentMyBinding>() {
    override val viewModel by viewModel<MyViewModel>()

    override fun getViewBinding(): FragmentMyBinding =
        FragmentMyBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    companion object {

        fun newInstance() = MyFragment()

        const val TAG = "MyFragment"
    }
}