package app.ripenow.android.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(val latitude: Double, val longitude: Double): Parcelable