package fastcampus.aop.part6.chapter01.screen.order

import fastcampus.aop.part6.chapter01.databinding.ActivityOrderMenuListBinding
import fastcampus.aop.part6.chapter01.screen.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class OrderMenuListActivity : BaseActivity<OrderMenuListViewModel, ActivityOrderMenuListBinding>() {

    override val viewModel by viewModel<OrderMenuListViewModel>()

    override fun getViewBinding() = ActivityOrderMenuListBinding.inflate(layoutInflater)

    override fun observeData() {

    }
}