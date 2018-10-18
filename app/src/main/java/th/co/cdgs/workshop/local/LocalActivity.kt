package th.co.cdgs.workshop.local

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import th.co.cdgs.workshop.R

class LocalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_local)
        title = "Local"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
