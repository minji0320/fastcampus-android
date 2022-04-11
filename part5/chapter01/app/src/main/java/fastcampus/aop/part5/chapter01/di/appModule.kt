package fastcampus.aop.part5.chapter01.di

import android.content.Context
import androidx.room.Room
import fastcampus.aop.part5.chapter01.data.local.db.ToDoDatabase
import fastcampus.aop.part5.chapter01.data.repository.DefaultToDoRepository
import fastcampus.aop.part5.chapter01.data.repository.ToDoRepository
import fastcampus.aop.part5.chapter01.domain.todo.DeleteAllToDoItemUseCase
import fastcampus.aop.part5.chapter01.domain.todo.DeleteToDoItemUseCase
import fastcampus.aop.part5.chapter01.domain.todo.GetToDoItemUseCase
import fastcampus.aop.part5.chapter01.domain.todo.GetToDoListUseCase
import fastcampus.aop.part5.chapter01.domain.todo.InsertToDoItemUseCase
import fastcampus.aop.part5.chapter01.domain.todo.InsertToDoListUseCase
import fastcampus.aop.part5.chapter01.domain.todo.UpdateToDoUseCase
import fastcampus.aop.part5.chapter01.presentation.detail.DetailMode
import fastcampus.aop.part5.chapter01.presentation.detail.DetailViewModel
import fastcampus.aop.part5.chapter01.presentation.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // ViewModel
    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) ->
        DetailViewModel(
            detailMode = detailMode,
            id = id,
            get(),
            get(),
            get(),
            get()
        )
    }

    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }

    // Repository
    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }

}

internal fun provideDB(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()