package com.example.testprime.common

class Constants {

    companion object{

        const val BEARER = "Bearer "
        // Paging
        const val MAX_PAGE_LIMIT = 1000
        const val MIN_PAGE_LIMIT = 1
        const val BASE_PAGE_LIMIT = 10

        // Settings EditText
        const val MAX_SYMBOLS_SEARCH = 30

        // DataStore Keys
        const val PREFERENCES_NAME = "prime_preferences"
        const val DEFAULT_PREFERENCE = ""
        // UserInfo
        const val USER_ID = "user_id"
        const val USER_PHONE = "user_phone"
        const val USER_NAME = "user_name"
        const val USER_EMAIl = "user_email"
        const val USER_BIRTH_DATE = "user_birth_date"
        const val USER_STATUS = "user_status"
        const val USER_ID_GENDER = "user_id_gender"
        const val USER_ALLOW_SMS = "user_allow_sms"
        const val USER_ALLOW_EMAIL = "user_allow_email"
        const val USER_ALLOW_PUSH = "user_allow_push"
        const val USER_EMAIL_VERIFIED = "user_email_verified"
        // Token
        const val USER_TOKEN = "user_token"
        // Refresh
        const val USER_REFRESH = "user_refresh"
        // Firebase token is updated (for one loading token on loyalty page)
        const val IS_FCM_TOKEN_UPDATED = "is_fcm_token_updated"
        // My data in profile page loaded or not
        const val IS_PROFILE_LOADED = "is_profile_loaded"


    }
}