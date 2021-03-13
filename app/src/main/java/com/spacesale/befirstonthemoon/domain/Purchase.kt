package com.spacesale.befirstonthemoon.domain

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
class Purchase(
    @DrawableRes
    val planetPicture: Int,
    val planetName: String,
    val sectorNames: String
) : Parcelable