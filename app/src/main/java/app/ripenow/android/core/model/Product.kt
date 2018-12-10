package app.ripenow.android.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(val url: String, val name: String, val upVotes: Int, val downVotes: Int): Parcelable