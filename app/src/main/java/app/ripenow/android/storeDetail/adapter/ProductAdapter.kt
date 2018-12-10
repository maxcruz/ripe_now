package app.ripenow.android.storeDetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.ripenow.android.R
import app.ripenow.android.core.image.ImageLoader
import app.ripenow.android.core.model.Product
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter(val imageLoader: ImageLoader, val onClick: (Product) -> Unit):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val list: MutableList<Product> = mutableListOf()

    fun updateList(newList: List<Product>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.product_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = list[position]
        viewHolder.bind(item)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Product) {
            imageLoader.load(itemView.imgProduct, item.url)
            itemView.txtNotRipe.text = item.downVotes.toString()
            itemView.txtRipe.text = item.upVotes.toString()
            itemView.setOnClickListener {
                onClick.invoke(item)
            }
        }

    }

}