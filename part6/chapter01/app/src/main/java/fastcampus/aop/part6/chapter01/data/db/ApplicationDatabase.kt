package fastcampus.aop.part6.chapter01.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fastcampus.aop.part6.chapter01.data.db.dao.LocationDao
import fastcampus.aop.part6.chapter01.data.db.dao.RestaurantDao
import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.part6.chapter01.data.entity.RestaurantEntity

@Database(
    entities = [LocationLatLngEntity::class, RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "ApplicationDataBase.db"
    }

    abstract fun LocationDao(): LocationDao

    abstract fun RestaurantDao(): RestaurantDao
}