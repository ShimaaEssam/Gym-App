package com.giants.utils

import android.content.Context
import android.util.Log

class SharedPreferenceManager {


    companion object {

          var currentUserID : String? = null

        fun getCurrentUser(context: Context): String? {
            //return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
            if (currentUserID == null) {
                val prefs = context.getSharedPreferences("USER_PREF", 0)
                currentUserID = prefs.getString("USER_ID", null)
            }
            Log.e("Preference",currentUserID)
            return currentUserID
        }

        fun setCurrentUser(context: Context, userID: String) {
            val prefs = context.getSharedPreferences("USER_PREF", 0)
            Log.e("Preference",userID)
            prefs.edit().putString("USER_ID", userID).commit()
        }
    }
}