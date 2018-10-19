package th.co.cdgs.workshop.remote

import retrofit2.Call
import retrofit2.http.*
import th.co.cdgs.workshop.local.data.Person

interface PersonApi {

    @GET(".json")
    fun getPersonAll(): Call<Map<String, Person>>

    @POST(".json")
    fun insertPerson(@Body person: Person): Call<Unit>

    @PUT("{key}.json")
    fun updatePerson(@Path("key") key: String,
                     @Body person: Person): Call<Unit>

    @DELETE("{key}.json")
    fun deletePerson(@Path("key") key: String): Call<Unit>
}