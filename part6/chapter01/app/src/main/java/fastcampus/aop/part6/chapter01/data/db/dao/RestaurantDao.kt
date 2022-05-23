package fastcampus.aop.part6.chapter01.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fastcampus.aop.part6.chapter01.data.entity.RestaurantEntity

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM RestaurantEntity WHERE restaurantTitle=:title")
    suspend fun get(title: String): RestaurantEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurantEntity: RestaurantEntity)

    @Query("DELETE From RestaurantEntity WHERE restaurantTitle=:title")
    suspend fun delete(title: String)

    @Query("DELETE From RestaurantEntity")
    suspend fun deleteAll()
}