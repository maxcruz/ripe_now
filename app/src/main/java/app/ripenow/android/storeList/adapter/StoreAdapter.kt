package app.ripenow.android.storeList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.ripenow.android.R
import app.ripenow.android.core.image.ImageLoader
import app.ripenow.android.core.model.StoreItem
import kotlinx.android.synthetic.main.store_item.view.*

class StoreAdapter(val imageLoader: ImageLoader, val onClick: (StoreItem) -> Unit):
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    private val list: MutableList<StoreItem> = mutableListOf()

    fun updateList(newList: List<StoreItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.store_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = list[position]
        viewHolder.bind(item)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val address = listOf(
            "313 N. Brook Rd. Nanjemoy at 50mt",
            "89 Warren Street Lineboro at 250mt",
            "9501 Moon Drive Baltimore at 1km",
            "24 Ironwood Ave. College Park at 1.5km",
            "85 Williams Ave. Washington Grove at 3km"
        )

        fun bind(item: StoreItem) {
            imageLoader.load(itemView.imgStore, item.url)
            itemView.txtTitle.text = item.name
            itemView.txtAddress.text = address[item.location.latitude.toInt()]
            if (!item.open) {
                itemView.txtStatus.visibility = View.VISIBLE
            }
            itemView.setOnClickListener {
                onClick.invoke(item)
            }
        }

    }

}