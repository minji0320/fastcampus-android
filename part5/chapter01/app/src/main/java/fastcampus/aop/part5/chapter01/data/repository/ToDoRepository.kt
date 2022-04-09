package fastcampus.aop.part5.chapter01.data.repository

import fastcampus.aop.part5.chapter01.data.entity.ToDoEntity

/**
 * 1. insertToDoList
 * 2. getTodoList
 */
interface ToDoRepository {

    // coroutine 사용 예정
    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun insertToDoList(toDoList: List<ToDoEntity>)

}