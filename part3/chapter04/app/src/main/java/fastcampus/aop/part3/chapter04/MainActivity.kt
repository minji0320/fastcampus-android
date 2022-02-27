package fastcampus.aop.part3.chapter04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fastcampus.aop.part3.chapter04.api.BookService
import fastcampus.aop.part3.chapter04.model.BestCellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks("41C31BA633E8777B9142AE5A87718B2924E66955E911CDCFEAE1F833720465F6")
            .enqueue(object: Callback<BestCellerDto> {
                override fun onResponse(
                    call: Call<BestCellerDto>,
                    response: Response<BestCellerDto>,
                ) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let {
                        Log.d(TAG, it.toString())

                        it.books.forEach { book ->
                            Log.d(TAG, book.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<BestCellerDto>, t: Throwable) {
                    Log.e(TAG, t.toString())
                }

            })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
