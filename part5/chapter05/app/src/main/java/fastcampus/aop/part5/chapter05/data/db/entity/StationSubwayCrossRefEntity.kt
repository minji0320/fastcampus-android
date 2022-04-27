package fastcampus.aop.part5.chapter05.data.db.entity

import androidx.room.Entity

// StationEntity & SubwayEntity -> 다대다 관계 정의
@Entity(primaryKeys = ["stationName", "subwayId"])
data class StationSubwayCrossRefEntity(
    val stationName: String,
    val subwayId: Int
)