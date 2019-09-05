package com.app.ecommerceapp.preferences

import android.content.Context

class SharedPreferencesHelper {

    companion object{
        private val PREFERENCES = "pos_preferences"

        fun SetStringValue(context: Context, key: String, save: String?) {
            val sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(key, save)
            editor.apply()
        }

        fun SetIntValue(context: Context, key: String, save: Int?) {
            val sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putInt(key, save!!)
            editor.apply()
        }

        fun SetBooleanValue(context: Context, key: String, save: Boolean?) {
            val sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean(key, save!!)
            editor.apply()
        }

        fun GetStringValue(context: Context, key: String): String? {
            val sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            return sharedPref.getString(key, null)
        }

        fun GetIntValue(context: Context, key: String): Int {
            val sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            return sharedPref.getInt(key, -1)
        }

        fun GetBooleanValue(context: Context, key: String): Boolean {
            val sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            return sharedPref.getBoolean(key, false)
        }

    }
}