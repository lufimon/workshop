package th.co.cdgs.workshop.remote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import th.co.cdgs.workshop.R
import th.co.cdgs.workshop.Utils
import th.co.cdgs.workshop.local.data.AppDatabase
import th.co.cdgs.workshop.local.data.Person

class RemoteAddActivity : AppCompatActivity() {

    lateinit var edtFirstName: TextView
    lateinit var edtLastName: TextView
    lateinit var edtAge: TextView
    lateinit var rdMale: RadioButton
    lateinit var rdFemale: RadioButton
    lateinit var btnSaveAdd: Button

    lateinit var btnUpdate: Button
    lateinit var btnDelete: Button

    var gender: String? = null
    var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_add)

        edtFirstName = findViewById(R.id.edt_first_name)
        edtLastName = findViewById(R.id.edt_last_name)

        edtAge = findViewById(R.id.edt_age)
        edtAge.setOnClickListener {
            Utils.datePickerDialog(this@RemoteAddActivity,
                    edtAge,
                    edtAge.text.toString())
        }

        rdMale = findViewById(R.id.rd_male)
        rdMale.setOnClickListener {
            this@RemoteAddActivity.gender = "M"
        }

        rdFemale = findViewById(R.id.rd_femele)
        rdFemale.setOnClickListener {
            this@RemoteAddActivity.gender = "F"
        }

        btnSaveAdd = findViewById(R.id.btn_save_add)
        btnSaveAdd.setOnClickListener {
            Person().apply {
                firstName = edtFirstName.text.toString()
                lastName = edtLastName.text.toString()
                birthDay = edtAge.text.toString()
                gender = this@RemoteAddActivity.gender
            }.run {
                retrofitBuilder().insertPerson(this)
                        .enqueue(object : Callback<Unit> {
                            override fun onFailure(call: Call<Unit>, t: Throwable) {

                            }

                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                finish()
                            }
                        })
            }
        }
        val key: String? = intent.getStringExtra("KEY")
        val data = intent.getSerializableExtra("DATA")
        if (data != null) {
            val person = data as Person
            this@RemoteAddActivity.id = person.id
            edtFirstName.text = person.firstName
            edtLastName.text = person.lastName
            edtAge.text = person.birthDay
            if (person.gender == "M") {
                rdMale.isChecked = true
                this@RemoteAddActivity.gender = "M"
            } else {
                rdFemale.isChecked = true
                this@RemoteAddActivity.gender = "F"
            }
        }

        btnUpdate = findViewById(R.id.btn_update)
        btnUpdate.setOnClickListener {
            Person().apply {
                firstName = edtFirstName.text.toString()
                lastName = edtLastName.text.toString()
                birthDay = edtAge.text.toString()
                gender = this@RemoteAddActivity.gender
            }.run {
                retrofitBuilder().updatePerson(key!!, this)
                        .enqueue(object : Callback<Unit> {
                            override fun onFailure(call: Call<Unit>, t: Throwable) {

                            }

                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                finish()
                            }
                        })
            }
        }

        btnDelete = findViewById(R.id.btn_delete)
        btnDelete.setOnClickListener {
            retrofitBuilder().deletePerson(key!!)
                    .enqueue(object : Callback<Unit> {
                        override fun onFailure(call: Call<Unit>, t: Throwable) {

                        }

                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            finish()
                        }
                    })
        }
    }

    private fun retrofitBuilder(): PersonApi {
        return Retrofit.Builder()
                .baseUrl("https://traning-3c90a.firebaseio.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PersonApi::class.java)
    }
}
