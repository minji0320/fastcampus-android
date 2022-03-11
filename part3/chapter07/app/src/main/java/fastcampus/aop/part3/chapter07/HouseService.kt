package fastcampus.aop.part3.chapter07

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/dac754ab-3e98-40db-beb5-ff529a668a5b")
    fun getHouseList(): Call<HouseDto>
}