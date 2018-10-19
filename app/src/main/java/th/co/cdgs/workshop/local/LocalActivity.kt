package th.co.cdgs.workshop.local

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.Button
import th.co.cdgs.workshop.R

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

        localAdapter = LocalAdapter()

        rvPersonDetail = findViewById(R.id.rv_person_detail)
        rvPersonDetail.layoutManager =
                LinearLayoutManager(this@LocalActivity)
    }
}
