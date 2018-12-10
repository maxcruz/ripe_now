package app.ripenow.android.storeDetail


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.ripenow.android.R
import app.ripenow.android.core.image.GlideImageLoader
import app.ripenow.android.core.image.ImageLoader
import app.ripenow.android.core.model.StoreItem
import kotlinx.android.synthetic.main.fragment_store_detail.*

class StoreDetailFragment : Fragment() {

    private val imageLoader: ImageLoader by lazy {
        GlideImageLoader(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_store_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        val storeItem: StoreItem? = arguments?.getParcelable("storeItem")
        storeItem?.let {
            loadStore(it)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.store_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> requireActivity().supportFragmentManager.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadStore(store: StoreItem) {
        imageLoader.load(imgBanner, store.url)
        toolbar.title = store.name
        loadProducts()
    }

    private fun loadProducts() {

    }

}
