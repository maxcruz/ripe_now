package app.ripenow.android.storeDetail


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import app.ripenow.android.R
import app.ripenow.android.core.image.GlideImageLoader
import app.ripenow.android.core.image.ImageLoader
import app.ripenow.android.core.model.Product
import app.ripenow.android.core.model.Store
import app.ripenow.android.storeDetail.adapter.ProductAdapter
import kotlinx.android.synthetic.main.fragment_store_detail.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreDetailFragment : Fragment() {

    private val list = listOf(
        Product("https://goo.gl/nbv7QF", "Aguacate", 10, 250),
        Product("https://goo.gl/7WccbY", "Banana", 40, 135),
        Product("https://goo.gl/WHdCTR", "Pineapple", 100, 15),
        Product("https://goo.gl/j5UQUc", "Tomato", 15, 75),
        Product("https://goo.gl/14REkT", "Apple", 28, 120),
        Product("https://goo.gl/gqmVkF", "Orange", 80, 44),
        Product("https://goo.gl/7KgzGX", "Watermelon", 75, 38),
        Product("https://goo.gl/DCv8eU", "Tangerine", 200, 25),
        Product("https://goo.gl/TTx8A9", "Cantaloupe", 2, 50),
        Product("https://goo.gl/pBNGdW", "Papaya", 35, 80)
    )

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
        val store: Store? = arguments?.getParcelable("storeItem")
        store?.let {
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

    private fun loadStore(store: Store) {
        imageLoader.load(imgBanner, store.url)
        toolbar.title = store.name
        loadProducts()
    }

    private fun loadProducts() {
        val adapter = ProductAdapter(GlideImageLoader(requireContext())) { item ->

        }
        adapter.updateList(list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        swipeRefresh.setOnRefreshListener {
            adapter.updateList(listOf())
            GlobalScope.launch {
                delay(2000)
                withContext(Main) {
                    swipeRefresh.isRefreshing = false
                    adapter.updateList(list)
                }
            }
        }
    }

}
