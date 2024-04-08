package com.dot.prime.presentation.common.helper_class

import com.dot.prime.domain.preferred_groups.model.InfoPreferredGroups
import com.dot.prime.domain.preferred_groups.model.StatusPreferredGroups

data class InfoAndStatusPreferredGroups(
    val infoPreferredGroups: InfoPreferredGroups,
    val statusPreferredGroups: StatusPreferredGroups
)
