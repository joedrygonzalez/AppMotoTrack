package iticbcn.appmototrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import iticbcn.appmototrack.R
import iticbcn.appmototrack.models.Reminder
import iticbcn.appmototrack.utils.toCapitalized
import iticbcn.appmototrack.utils.toFormattedString
import java.util.Date

/**
 * Adapter i ViewHolder per al RecyclerView de recordatoris.
 */
class RemindersAdapter(private val items: List<Reminder>) :
    RecyclerView.Adapter<RemindersAdapter.ReminderViewHolder>() {

    class ReminderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvReminderText: TextView = view.findViewById(R.id.tvReminderText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reminder, parent, false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder = items[position]
        val formattedTitle = reminder.title.toCapitalized()
        val formattedDate = if (reminder.date.isNotEmpty()) {
            reminder.date
        } else {
            Date().toFormattedString()
        }
        holder.tvReminderText.text = "$formattedTitle - $formattedDate"
    }

    override fun getItemCount(): Int = items.size
}
