package fastcampus.aop.part6.chapter01.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import fastcampus.aop.part6.chapter01.databinding.ViewholderEmptyBinding
import fastcampus.aop.part6.chapter01.model.CellType
import fastcampus.aop.part6.chapter01.model.Model
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.EmptyViewHolder
import fastcampus.aop.part6.chapter01.widget.adapter.viewholder.ModelViewHolder

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider,
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.EMPTY_CELL -> {
                EmptyViewHolder(
                    ViewholderEmptyBinding.inflate(inflater, parent, false),
                    viewModel,
                    resourcesProvider
                )
            }
        }

        return viewHolder as ModelViewHolder<M>
    }
}