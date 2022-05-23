package fastcampus.aop.part6.chapter01.util.converter

import androidx.room.TypeConverter

object RoomTypeConverters {

    @TypeConverter
    @JvmStatic
    fun toString(pair: Pair<Int, Int>): String {
        return "${pair.first},${pair.second}"
    }

    @TypeConverter
    @JvmStatic
    fun toIntPair(str: String): Pair<Int, Int> {
        val splittedStr = str.split(",")
        return Pair(Integer.parseInt(splittedStr[0]), Integer.parseInt(splittedStr[1]))
    }
}