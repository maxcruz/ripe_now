package app.ripenow.android.storeList


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import app.ripenow.android.R
import app.ripenow.android.core.image.GlideImageLoader
import app.ripenow.android.core.model.Location
import app.ripenow.android.core.model.StoreItem
import app.ripenow.android.storeList.adapter.StoreAdapter
import kotlinx.android.synthetic.main.fragment_store_list.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreListFragment : Fragment() {

    private val list = listOf(
        StoreItem("https://goo.gl/QtfxLZ", "The Fresh Market", Location(0.0, 0.0), true),
        StoreItem("https://goo.gl/tNaM5W", "Best Market", Location(1.0, 0.0), true),
        StoreItem("https://goo.gl/FbaVWu", "Lucky's Market", Location(2.0, 0.0), true),
        StoreItem("https://goo.gl/S6pFyB", "The Angel's Fruit Market", Location(3.0, 0.0), true),
        StoreItem("https://goo.gl/xSBrK6", "Seeds Motivation", Location(4.0, 0.0), false)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_store_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = StoreAdapter(GlideImageLoader(requireContext())) { itemView, item ->
            val bundle = Bundle()
            //val extras = FragmentNavigatorExtras(itemView.imageView to "header_image")
            itemView.findNavController().navigate(R.id.action_storeListFragment_to_storeDetailFragment, bundle,
                null, null)

        }
        adapter.updateList(list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.store_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
