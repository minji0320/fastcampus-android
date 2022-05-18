package fastcampus.aop.part6.chapter01.screen.main.home

import androidx.lifecycle.MutableLiveData
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel

class HomeViewModel: BaseViewModel() {

    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)


}