package app.ripenow.android.storeDetail.dialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import app.ripenow.android.R
import app.ripenow.android.core.image.GlideImageLoader
import app.ripenow.android.core.image.ImageLoader
import app.ripenow.android.core.model.Product
import kotlinx.android.synthetic.main.fragment_dialog_ripe.*


class RipeDialogFragment : DialogFragment() {

    val imageLoader: ImageLoader by lazy {
        GlideImageLoader(requireContext())
    }

    companion object {

        fun newInstance(product: Product): RipeDialogFragment {
            val fragment = RipeDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable("product", product)
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_ripe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product: Product? = arguments?.getParcelable("product")
        product?.let {
            imageLoader.load(imgProduct, it.url)
            txtName.text = it.name
        }
        buttonNotRipe.setOnClickListener {
            dismiss()
        }
        buttonRipe.setOnClickListener {
            dismiss()
        }
    }

}
