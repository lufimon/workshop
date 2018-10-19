package th.co.cdgs.workshop.local

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.Button
import th.co.cdgs.workshop.R
import th.co.cdgs.workshop.Utils
import th.co.cdgs.workshop.local.data.AppDatabase
import th.co.cdgs.workshop.local.data.Person

class LocalActivity : AppCompatActivity() {

    lateinit var btnAddPerson: Button

    lateinit var rvPersonDetail: RecyclerView

    lateinit var localAdapter: LocalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_local)
        title = "Local"
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
            startActivity(Intent(this@LocalActivity
                    ,LocalAddActivity::class.java))
        }

        localAdapter = LocalAdapter()

        rvPersonDetail = findViewById(R.id.rv_person_detail)
        rvPersonDetail.layoutManager =
                LinearLayoutManager(this@LocalActivity)
    }

    override fun onResume() {
        super.onResume()
        Utils.Companion.DoAsyncCallBack({
            AppDatabase.getAppDatabase(this@LocalActivity)
                    .personDao()
                    .getPersonAll()
        },{
            rvPersonDetail.adapter = localAdapter.apply {
                dataList = it as List<Person>
                notifyDataSetChanged()
            }
        }).execute()
    }
}
