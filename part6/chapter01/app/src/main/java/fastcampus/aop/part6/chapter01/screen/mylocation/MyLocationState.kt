package fastcampus.aop.part6.chapter01.screen.mylocation

import androidx.annotation.StringRes
import fastcampus.aop.part6.chapter01.data.entity.MapSearchInfoEntity

sealed class MyLocationState {

    object Uninitialized : MyLocationState()

    object Loading : MyLocationState()

    data class Success(
        val mapSearchInfo: MapSearchInfoEntity,
    ) : MyLocationState()

    data class Confirm(
        val mapSearchInfo: MapSearchInfoEntity,
    ) : MyLocationState()

    data class Error(
        @StringRes val messageId: Int
    ) : MyLocationState()
}