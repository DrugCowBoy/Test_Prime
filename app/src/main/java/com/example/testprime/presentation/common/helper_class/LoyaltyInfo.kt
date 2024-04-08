package com.dot.prime.presentation.common.helper_class

import com.dot.prime.domain.loyalty.model.Banner
import com.dot.prime.domain.loyalty.model.InfoBirthDay
import com.dot.prime.domain.loyalty.model.PersonalOffer
import com.dot.prime.domain.preferred_groups.model.InfoPreferredGroups
import com.dot.prime.domain.preferred_groups.model.StatusPreferredGroups
import com.dot.prime.domain.profile.model.FullUserDataResult

data class LoyaltyInfo(
    val infoBirthDay: InfoBirthDay?,
    val userData: FullUserDataResult?,
    val countEstimateProducts: Int?,
    val banners: List<Banner>?,
    val statusPreferredGroups: StatusPreferredGroups?,
    val infoPreferredGroups: InfoPreferredGroups?,
    val personalDiscounts: List<PersonalOffer>?
)