package fastcampus.aop.part4.chapter02.service

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {
    @GET("/v3/f9ee6a13-b22e-4f03-892e-5c0b27c3a761")
    fun listMusics(): Call<MusicDto>
}