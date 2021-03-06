package fastcampus.aop.part5.chapter01.data.repository

import fastcampus.aop.part5.chapter01.data.entity.ToDoEntity

/**
 * 1. insertToDoList
 * 2. getToDoList
 * 3. updateToDoItem
 * 4. getToDoItem
 */
interface ToDoRepository {

    // coroutine 사용 예정
    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun insertToDoList(toDoList: List<ToDoEntity>)

    suspend fun insertToDoItem(toDoItem: ToDoEntity): Long

    suspend fun updateToDoItem(toDoItem: ToDoEntity): Int

    suspend fun getToDoItem(itemId: Long): ToDoEntity?

    suspend fun deleteAll()

    suspend fun deleteToDoItem(id: Long): Int

}