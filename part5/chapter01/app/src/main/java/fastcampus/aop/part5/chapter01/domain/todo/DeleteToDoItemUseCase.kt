package fastcampus.aop.part5.chapter01.domain.todo

import fastcampus.aop.part5.chapter01.data.repository.ToDoRepository
import fastcampus.aop.part5.chapter01.domain.UseCase

internal class DeleteToDoItemUseCase(
    private val toDoRepository: ToDoRepository,
) : UseCase {

    suspend operator fun invoke(itemId: Long): Int {
        return toDoRepository.deleteToDoItem(itemId)
    }

}
