package fastcampus.aop.part5.chapter01.data.repository

import fastcampus.aop.part5.chapter01.data.entity.ToDoEntity

class TestToDoRepository: ToDoRepository {

    private val toDoList: MutableList<ToDoEntity> = mutableListOf()

    override suspend fun getToDoList(): List<ToDoEntity> {
        return toDoList
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        this.toDoList.addAll(toDoList)
    }
}