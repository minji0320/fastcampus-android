package fastcampus.aop.part5.chapter01.data.local.db.dao

import androidx.room.*
import fastcampus.aop.part5.chapter01.data.entity.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoEntity")
    suspend fun getAll(): List<ToDoEntity>

    @Query("SELECT * FROM ToDoEntity WHERE id=:id")
    suspend fun getById(id: Long): ToDoEntity?

    @Insert
    suspend fun insert(toDoEntity: ToDoEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(toDoEntityList: List<ToDoEntity>)

    @Query("DELETE FROM ToDoEntity WHERE id=:id")
    suspend fun delete(id: Long): Int

    @Query("DELETE FROM ToDoEntity")
    suspend fun deleteAll()

    @Update
    suspend fun update(toDoEntity: ToDoEntity): Int

}
