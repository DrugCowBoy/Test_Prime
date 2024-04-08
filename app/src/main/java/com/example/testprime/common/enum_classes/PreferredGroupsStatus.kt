package com.example.testprime.common.enum_classes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PreferredGroupsStatus(val status: Int) : Parcelable {
    SelectGroupsCurrentMonth(1),
    SelectGroupsNextMonth(2),
    SelectGroupsNothing(3)
}