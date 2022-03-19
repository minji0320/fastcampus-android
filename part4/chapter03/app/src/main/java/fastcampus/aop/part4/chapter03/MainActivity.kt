package fastcampus.aop.part4.chapter03

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import fastcampus.aop.part4.chapter03.databinding.ActivityMainBinding
import fastcampus.aop.part4.chapter03.model.LocationLatLngEntity
import fastcampus.aop.part4.chapter03.model.SearchResultEntity

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
            Toast.makeText(this, "빌딩 이름: ${it.name}, 주소: ${it.fullAddress}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initViews() = with(binding) {
        recyclerView.adapter = adapter
        emptyResultTextView.isVisible = false
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initData() {
        adapter.notifyDataSetChanged()
    }

    private fun setData() {
        val dataList = (0..10).map {
            SearchResultEntity(
                name = "빌딩 $it",
                fullAddress = "주소 $it",
                locationLatLng = LocationLatLngEntity(
                    it.toFloat(),
                    it.toFloat()
                )
            )
        }

        adapter.setSearchResultList(dataList)
    }
}