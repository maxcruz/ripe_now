package app.ripenow.android.core.image

import android.widget.ImageView

interface ImageLoader {

    fun load(image: ImageView, url: String)

}