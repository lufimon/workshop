package th.co.cdgs.workshop.local

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import th.co.cdgs.workshop.R

class LocalAdapter : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            LocalAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_person_detail,
                        parent,
                        false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: LocalAdapter.ViewHolder, position: Int) {

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}