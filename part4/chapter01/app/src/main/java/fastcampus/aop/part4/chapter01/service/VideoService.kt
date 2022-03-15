package fastcampus.aop.part4.chapter01.service

import fastcampus.aop.part4.chapter01.dto.VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {
    @GET("/v3/369f157d-4599-45d6-9f4c-930d1e08be26")
    fun listVideos(): Call<VideoDto>
}