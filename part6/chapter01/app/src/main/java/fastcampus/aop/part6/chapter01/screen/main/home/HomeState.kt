package fastcampus.aop.part6.chapter01.screen.main.home

import androidx.annotation.StringRes
import fastcampus.aop.part6.chapter01.data.entity.MapSearchInfoEntity

sealed class HomeState {

    object Uninitialized : HomeState()

    object Loading : HomeState()

    data class Success(
        val mapSearchInfo: MapSearchInfoEntity,
    ) : HomeState()

    data class Error(
        @StringRes val messageId: Int,
    ) : HomeState()
}