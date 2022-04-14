package fastcampus.aop.part5.chapter02.presentation.profile

import androidx.lifecycle.viewModelScope
import fastcampus.aop.part5.chapter02.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProfileViewModel : BaseViewModel() {

    override fun fetchData(): Job = viewModelScope.launch {

    }

}