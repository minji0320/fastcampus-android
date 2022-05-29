package fastcampus.aop.part6.chapter01.util.event

import fastcampus.aop.part6.chapter01.screen.main.MainTabMenu
import kotlinx.coroutines.flow.MutableSharedFlow

class MenuChangeEventBus {

    val mainTabMenuFlow = MutableSharedFlow<MainTabMenu>()

    suspend fun changeMenu(menu: MainTabMenu) {
        mainTabMenuFlow.emit(menu)
    }
}