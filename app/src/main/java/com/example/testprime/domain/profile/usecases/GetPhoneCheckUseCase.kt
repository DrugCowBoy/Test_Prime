package com.example.testprime.domain.profile.usecases

import com.example.testprime.common.replacePair
import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class GetPhoneCheckUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(phone: String): String{
        return repository.getPhoneCheck(phone.replacePair(" " to "","(" to "", ")" to ""))
    }

}