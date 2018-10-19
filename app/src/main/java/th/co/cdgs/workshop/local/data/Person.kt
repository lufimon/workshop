package th.co.cdgs.workshop.local.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "first_name") var firstName: String? = null,
    @ColumnInfo(name = "last_name") var lastName: String? = null,
    @ColumnInfo(name = "birth_day") var birthDay: String? = null,
    @ColumnInfo(name = "gender") var gender: String? = null
): Serializable