package fastcampus.aop.part4.chapter03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part4.chapter03.databinding.ItemSearchResultBinding

class SearchRecyclerAdapter(private val searchResultClickListener: (Any) -> Unit) :
    RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {

    private var searchResultList: List<Any> = listOf()

    inner class ViewHolder(val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) = with(binding) {
            titleTextView.text = "제목"
            subTitleTextView.text = "부제목"

            binding.root.setOnClickListener {
                searchResultClickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Any())
    }

    override fun getItemCount(): Int = 10
}