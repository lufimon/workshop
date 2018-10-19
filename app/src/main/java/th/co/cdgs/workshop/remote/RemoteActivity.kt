package th.co.cdgs.workshop.remote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import th.co.cdgs.workshop.R
import th.co.cdgs.workshop.local.LocalAdapter
import th.co.cdgs.workshop.local.LocalAddActivity
import th.co.cdgs.workshop.local.data.Person

class RemoteActivity : AppCompatActivity() {

    lateinit var btnAddPerson: Button

    lateinit var rvPersonDetail: RecyclerView

    lateinit var remoteAdapter: RemoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_remote)
        title = "Remote"
        this.init()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        btnAddPerson = findViewById(R.id.btn_add_person)
        btnAddPerson.setOnClickListener {
            startActivity(Intent(this@RemoteActivity
                    , RemoteAddActivity::class.java))
        }

        remoteAdapter = RemoteAdapter()

        rvPersonDetail = findViewById(R.id.rv_person_detail)
        rvPersonDetail.layoutManager =
                LinearLayoutManager(this@RemoteActivity)
    }

    override fun onResume() {
        super.onResume()
        retrofitBuilder().getPersonAll()
                .enqueue(object : Callback<Map<String, Person>> {
                    override fun onFailure(call: Call<Map<String, Person>>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Map<String, Person>>,
                                            response: Response<Map<String, Person>>) {
                        response.body().run {
                            rvPersonDetail.adapter = remoteAdapter.apply {
                                dataList = this@run
                                notifyDataSetChanged()
                            }
                        }
                    }

                })
    }

    private fun retrofitBuilder(): PersonApi {
        return Retrofit.Builder()
                .baseUrl("https://traning-3c90a.firebaseio.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PersonApi::class.java)
    }
}
