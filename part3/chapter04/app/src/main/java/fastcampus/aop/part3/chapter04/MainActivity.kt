package fastcampus.aop.part3.chapter04

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aop.part3.chapter04.adapter.BookAdapter
import fastcampus.aop.part3.chapter04.adapter.HistoryAdapter
import fastcampus.aop.part3.chapter04.api.BookService
import fastcampus.aop.part3.chapter04.databinding.ActivityMainBinding
import fastcampus.aop.part3.chapter04.model.BestCellerDto
import fastcampus.aop.part3.chapter04.model.History
import fastcampus.aop.part3.chapter04.model.SearchBookDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var bookService: BookService

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookRecyclerView()
        initHistoryRecyclerView()

        db = getAppDatabase(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks(getString(R.string.interparkAPIkey))
            .enqueue(object : Callback<BestCellerDto> {
                override fun onResponse(
                    call: Call<BestCellerDto>,
                    response: Response<BestCellerDto>,
                ) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    adapter.submitList(response.body()?.books.orEmpty())
                }

                override fun onFailure(call: Call<BestCellerDto>, t: Throwable) {
                    Log.e(TAG, t.toString())
                }

            })

        initSearchEditText()
    }

    private fun search(keyword: String) {
        bookService.getBooksByName(getString(R.string.interparkAPIkey), keyword)
            .enqueue(object : Callback<SearchBookDto> {
                override fun onResponse(
                    call: Call<SearchBookDto>,
                    response: Response<SearchBookDto>,
                ) {
                    hideHistoryView()
                    saveSearchKeyword(keyword)

                    if (response.isSuccessful.not()) {
                        return
                    }

                    adapter.submitList(response.body()?.books.orEmpty())
                }

                override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {
                    Log.e(TAG, t.toString())
                    hideHistoryView();
                }

            })
    }

    private fun initBookRecyclerView() {
        adapter = BookAdapter(itemClickedListener = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("bookModel", it)
            startActivity(intent)
        })

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    private fun initHistoryRecyclerView() {
        historyAdapter = HistoryAdapter(
            historyDeleteClickedListener = {
                deleteSearchKeyword(it)
            },
            itemClickedListener = {
                hideHistoryView()
                search(it)
            }
        )

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historyRecyclerView.adapter = historyAdapter
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initSearchEditText() {
        binding.searchEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN) {
                search(binding.searchEditText.text.toString())
                return@setOnKeyListener true
            }

            return@setOnKeyListener false
        }

        binding.searchEditText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                showHistoryView()
                return@setOnTouchListener true
            }

            return@setOnTouchListener false
        }
    }

    private fun showHistoryView() {
        Thread {
            val keywords = db.historyDao().getAll().reversed()
            runOnUiThread {
                historyAdapter.submitList(keywords)
                binding.historyRecyclerView.isVisible = keywords.isNotEmpty()
            }
        }.start()
    }

    private fun hideHistoryView() {
        binding.historyRecyclerView.isVisible = false
    }

    private fun saveSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().insertHistory(History(null, keyword))
        }.start()
    }

    private fun deleteSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().delete(keyword)
            showHistoryView()
        }.start()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
