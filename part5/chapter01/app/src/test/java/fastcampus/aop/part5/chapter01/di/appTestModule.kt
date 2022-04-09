package fastcampus.aop.part5.chapter01.di

import fastcampus.aop.part5.chapter01.data.repository.TestToDoRepository
import fastcampus.aop.part5.chapter01.data.repository.ToDoRepository
import fastcampus.aop.part5.chapter01.domain.todo.GetToDoListUseCase
import fastcampus.aop.part5.chapter01.domain.todo.InsertToDoListUseCase
import fastcampus.aop.part5.chapter01.presentation.list.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    // ViewModel
    viewModel { ListViewModel(get()) }

    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }

    // Repository
    single<ToDoRepository> { TestToDoRepository() }

}
