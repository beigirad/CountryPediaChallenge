package ir.beigirad.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CachedCountry(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val population: Long,
    val flagUrl: String
)