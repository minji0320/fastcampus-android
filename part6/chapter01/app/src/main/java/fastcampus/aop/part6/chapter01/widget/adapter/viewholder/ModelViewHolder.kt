package fastcampus.aop.part6.chapter01.widget.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import fastcampus.aop.part6.chapter01.model.Model
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import fastcampus.aop.part6.chapter01.widget.adapter.listener.AdapterListener

abstract class ModelViewHolder<M : Model>(
    binding: ViewBinding,
    protected open val viewModel: BaseViewModel,
    protected open val resourcesProvider: ResourcesProvider,
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun reset()

    open fun bindData(Model: M) {
        reset()
    }

    abstract fun bindViews(model: M, adapterListener: AdapterListener)
}