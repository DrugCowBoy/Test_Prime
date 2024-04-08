package com.example.testprime.domain.profile.usecases

import com.example.testprime.common.replacePair
import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class ChangePhoneUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(token: String, phone: String): String{
        val phoneEdit = phone.replacePair(" " to "","(" to "", ")" to "", "-" to "")
        return repository.changeUserPhone(
            token = token,
            phone = phoneEdit
        )
    }

}