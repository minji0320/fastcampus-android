package fastcampus.aop.part5.chapter01.data.repository

import fastcampus.aop.part5.chapter01.data.entity.ToDoEntity
import fastcampus.aop.part5.chapter01.data.local.db.dao.ToDoDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultToDoRepository(
    private val toDoDao: ToDoDao,
    private val ioDispatcher: CoroutineDispatcher
) : ToDoRepository {

    override suspend fun getToDoList(): List<ToDoEntity> = withContext(ioDispatcher) {
        toDoDao.getAll()
    }

    override suspend fun getToDoItem(itemId: Long): ToDoEntity? = withContext(ioDispatcher) {
        toDoDao.getById(itemId)
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) = withContext(ioDispatcher) {
        toDoDao.insert(toDoList)
    }

    override suspend fun insertToDoItem(toDoItem: ToDoEntity): Long = withContext(ioDispatcher) {
        toDoDao.insert(toDoItem)
    }

    override suspend fun updateToDoItem(toDoItem: ToDoEntity): Int = withContext(ioDispatcher) {
        toDoDao.update(toDoItem)
    }

    override suspend fun deleteAll() = withContext(ioDispatcher) {
        toDoDao.deleteAll()
    }

    override suspend fun deleteToDoItem(id: Long): Int = withContext(ioDispatcher) {
        toDoDao.delete(id)
    }
}