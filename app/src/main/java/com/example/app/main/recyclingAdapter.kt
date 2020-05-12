package com.example.app.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data.Reminders

import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView

class RecycleAdapter(val Adapter: MainPresenterImpl, private val reminders: MutableList<Reminders>) : RecyclerView.Adapter<RecycleAdapter.viewHolder>() {

    class viewHolder(v: View):RecyclerView.ViewHolder(v){

        val date = v.findViewById<TextView>(R.id.date)
        val time = v.findViewById<TextView>(R.id.time)
        val text = v.findViewById<TextView>(R.id.text)
        val menu =  v.findViewById<ImageView>(R.id.moreOptions)
    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val item_view  = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return viewHolder(item_view)
         }

        override fun getItemCount(): Int {
        return reminders.size
        }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var reminder : Reminders =  reminders[position]

        val menu = PopupMenu(Adapter, holder.menu)

        holder.date.text = reminder.date
        holder.time.text = reminder.time
        holder.text.text = reminder.text


        holder.menu.setOnClickListener{
            menu.inflate(R.menu.rem_menu)
            menu.setOnMenuItemClickListener{

                when(it.itemId) {

                    R.id.editRem -> {
                        Adapter.updateReminders(reminders[position])
                    }
                    R.id.deleteRem -> {
                        Adapter.deleteReminder(reminders[position].ID.toString())
                        Adapter.refreshList()

                    }

                }
                true
            }
        menu.show()
        }

    }



}




