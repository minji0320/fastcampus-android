package fastcampus.aop.part5.chapter05.data.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StationWithSubwaysEntity(
    // Embedded : 변수는 하나지만 실제 DB에는 각 컬럼들이 들어가 있음!
    @Embedded val station: StationEntity,
    @Relation(
        parentColumn = "stationName",
        entityColumn = "subwayId",
        associateBy = Junction(StationSubwayCrossRefEntity::class)
    )
    val subways: List<SubwayEntity>
)