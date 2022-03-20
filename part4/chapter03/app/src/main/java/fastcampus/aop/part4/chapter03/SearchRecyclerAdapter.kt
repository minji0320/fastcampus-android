package fastcampus.aop.part4.chapter03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part4.chapter03.databinding.ItemSearchResultBinding
import fastcampus.aop.part4.chapter03.model.SearchResultEntity

class SearchRecyclerAdapter(private val searchResultClickListener: (SearchResultEntity) -> Unit) :
    RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {

    private var searchResultList: List<SearchResultEntity> = listOf()

    inner class ViewHolder(val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SearchResultEntity) = with(binding) {
            titleTextView.text = data.name
            subTitleTextView.text = data.fullAddress

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
        holder.bind(searchResultList[position])
    }

    override fun getItemCount(): Int = searchResultList.size

    fun setSearchResultList(searchResultList: List<SearchResultEntity>) {
        this.searchResultList = searchResultList
        notifyDataSetChanged()
    }
}