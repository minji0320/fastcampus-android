package fastcampus.aop.part5.chapter01.domain.todo

import fastcampus.aop.part5.chapter01.data.entity.ToDoEntity
import fastcampus.aop.part5.chapter01.data.repository.ToDoRepository
import fastcampus.aop.part5.chapter01.domain.UseCase

internal class InsertToDoItemUseCase(
    private val toDoRepository: ToDoRepository,
) : UseCase {

    suspend operator fun invoke(toDoItem: ToDoEntity): Long {
        return toDoRepository.insertToDoItem(toDoItem)
    }

}
