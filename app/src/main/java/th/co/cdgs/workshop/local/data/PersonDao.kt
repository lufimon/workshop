package th.co.cdgs.workshop.local.data

import android.arch.persistence.room.*

@Dao
interface PersonDao {

    @Insert
    fun insertPerson(person: Person)

    @Update
    fun updatePerson(person: Person)

    @Delete
    fun deletePerson(person: Person)

    @Query("SELECT * FROM person")
    fun getPersonAll(): List<Person>
}