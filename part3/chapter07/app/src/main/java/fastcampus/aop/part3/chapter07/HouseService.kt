package fastcampus.aop.part3.chapter07

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("v3/31af88bd-99b9-4392-9faf-288640f43118")
    fun getHouseList(): Call<HouseDto>
}