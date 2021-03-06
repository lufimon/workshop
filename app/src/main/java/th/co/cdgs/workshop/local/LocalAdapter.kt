package th.co.cdgs.workshop.local

import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import th.co.cdgs.workshop.R
import th.co.cdgs.workshop.local.data.Person

class LocalAdapter : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {

    var dataList: List<Person> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            LocalAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_person_detail,
                        parent,
                        false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: LocalAdapter.ViewHolder, position: Int) {
        val data: Person = dataList[position]
        holder.txtFullName.text = data.firstName.plus(" ").plus(data.lastName)
        holder.txtAge.text = data.birthDay
        holder.txtGender.text = if(data.gender == "M"){
            "Male"
        } else {
            "Female"
        }

        holder.conLayoutItem.setOnClickListener {
            val intent = Intent(holder.itemView.context,
                    LocalAddActivity::class.java)
            intent.putExtra("DATA",data)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //after crate view item and define size item
        val txtFullName = view.findViewById<TextView>(R.id.txt_fullname)
        val txtAge = view.findViewById<TextView>(R.id.txt_age)
        val txtGender = view.findViewById<TextView>(R.id.txt_gender)

        //after create insert person
        val conLayoutItem = view.findViewById<ConstraintLayout>(R.id.con_layout_item)
    }
}