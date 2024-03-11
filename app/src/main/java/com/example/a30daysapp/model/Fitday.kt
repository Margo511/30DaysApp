package com.example.a30daysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Fitday(
    var dayNumber: String,
    @StringRes var titleId: Int,
    @DrawableRes var imageId: Int,
    var moreInformation: String
)
