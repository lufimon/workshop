package th.co.cdgs.workshop.local.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        fun getAppDatabase(context: Context): AppDatabase =
                Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    "person_db").build()
    }

    abstract fun personDao(): PersonDao
}