package fastcampus.aop.part4.chapter03

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part4.chapter03.databinding.ItemSearchResultBinding

class SearchRecyclerAdapter : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {

    private var searchResultList: List<Any> = listOf()
    lateinit var searchResultClickListener: (Any) -> Unit

    inner class ViewHolder(
        val binding: ItemSearchResultBinding,
        val searchResultClickListener: (Any) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) = with(binding) {
            titleTextView.text = "제목"
            subTitleTextView.text = "부제목"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemSearchResultBinding.bind(parent)
        return ViewHolder(view, searchResultClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searchResultList[position])
    }

    override fun getItemCount(): Int = 10
}