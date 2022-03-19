package fastcampus.aop.part4.chapter03

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SearchRecyclerAdapter : RecyclerView.Adapter<> {

    inner class ViewHolder(itemView: View, val searchResultClickListener: (Any) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(data: Any) = with(itemView) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ??? {

    }

    override fun onBindViewHolder(holder: ???, position: Int) {

    }

    override fun getItemCount(): Int {

    }
}