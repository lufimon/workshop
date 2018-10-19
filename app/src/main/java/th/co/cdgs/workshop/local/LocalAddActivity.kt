package th.co.cdgs.workshop.local

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import th.co.cdgs.workshop.R
import th.co.cdgs.workshop.Utils
import th.co.cdgs.workshop.local.data.AppDatabase
import th.co.cdgs.workshop.local.data.Person

class LocalAddActivity : AppCompatActivity() {

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
            Utils.datePickerDialog(this@LocalAddActivity,
                    edtAge,
                    edtAge.text.toString())
        }

        rdMale = findViewById(R.id.rd_male)
        rdMale.setOnClickListener {
            this@LocalAddActivity.gender = "M"
        }

        rdFemale = findViewById(R.id.rd_femele)
        rdFemale.setOnClickListener {
            this@LocalAddActivity.gender = "F"
        }

        btnSaveAdd = findViewById(R.id.btn_save_add)
        btnSaveAdd.setOnClickListener {
            Utils.Companion.DoAsync({
                Person().apply {
                    firstName = edtFirstName.text.toString()
                    lastName = edtLastName.text.toString()
                    birthDay = edtAge.text.toString()
                    gender = this@LocalAddActivity.gender
                }.run {
                    AppDatabase
                            .getAppDatabase(this@LocalAddActivity)
                            .personDao()
                            .insertPerson(this)
                }
            }, {
                finish()
            }).execute()
        }

        val data = intent.getSerializableExtra("DATA")
        if (data != null) {
            val person = data as Person
            this@LocalAddActivity.id = person.id
            edtFirstName.text = person.firstName
            edtLastName.text = person.lastName
            edtAge.text = person.birthDay
            if (person.gender == "M") {
                rdMale.isChecked = true
                this@LocalAddActivity.gender = "M"
            } else {
                rdFemale.isChecked = true
                this@LocalAddActivity.gender = "F"
            }
        }

        btnUpdate = findViewById(R.id.btn_update)
        btnUpdate.setOnClickListener {
            Utils.Companion.DoAsync({
                Person().apply {
                    id = this@LocalAddActivity.id
                    firstName = edtFirstName.text.toString()
                    lastName = edtLastName.text.toString()
                    birthDay = edtAge.text.toString()
                    gender = this@LocalAddActivity.gender
                }.run {
                    AppDatabase
                            .getAppDatabase(this@LocalAddActivity)
                            .personDao()
                            .updatePerson(this)
                }
            }, {
                finish()
            }).execute()
        }

        btnDelete = findViewById(R.id.btn_delete)
        btnDelete.setOnClickListener {
            Utils.Companion.DoAsync({
                AppDatabase
                        .getAppDatabase(this@LocalAddActivity)
                        .personDao()
                        .deleteById(this@LocalAddActivity.id!!)
            }, {
                finish()
            }).execute()
        }
    }
}
