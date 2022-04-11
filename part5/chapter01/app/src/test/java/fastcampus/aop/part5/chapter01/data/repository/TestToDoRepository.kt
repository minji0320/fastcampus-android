package fastcampus.aop.part5.chapter01.data.repository

import fastcampus.aop.part5.chapter01.data.entity.ToDoEntity

class TestToDoRepository : ToDoRepository {

    private val toDoList: MutableList<ToDoEntity> = mutableListOf()

    override suspend fun getToDoList(): List<ToDoEntity> {
        return toDoList
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        this.toDoList.addAll(toDoList)
    }

    override suspend fun insertToDoItem(toDoItem: ToDoEntity): Long {
        this.toDoList.add(toDoItem)
        return toDoItem.id
    }

    override suspend fun updateToDoItem(toDoItem: ToDoEntity): Int {
        val foundToDoEntity = toDoList.find { it.id == toDoItem.id }
        return if (foundToDoEntity == null) {
            -1
        } else {
            this.toDoList[toDoList.indexOf(foundToDoEntity)] = toDoItem
            toDoItem.id.toInt()
        }
    }

    override suspend fun getToDoItem(itemId: Long): ToDoEntity? {
        return toDoList.find { it.id == itemId }
    }

    override suspend fun deleteAll() {
        return toDoList.clear()
    }

    override suspend fun deleteToDoItem(id: Long): Int {
        val foundToDoEntity = toDoList.find { it.id == id }
        return if (foundToDoEntity == null) {
            -1
        } else {
            this.toDoList.removeAt(toDoList.indexOf(foundToDoEntity))
            id.toInt()
        }
    }
}