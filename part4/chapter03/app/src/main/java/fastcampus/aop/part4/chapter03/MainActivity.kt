package fastcampus.aop.part4.chapter03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aop.part4.chapter03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SearchRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initViews()
        initData()
        setData()
    }

    private fun initAdapter() {
        adapter = SearchRecyclerAdapter {
            Toast.makeText(this, "아이템 클릭", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initViews() = with(binding) {
        recyclerView.adapter = adapter
        emptyResultTextView.isVisible = false
    }

    private fun initData() {
        adapter.notifyDataSetChanged()
    }

    private fun setData() {

    }
}