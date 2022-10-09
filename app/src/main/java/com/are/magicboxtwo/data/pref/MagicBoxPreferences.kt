package com.are.magicboxtwo.data.pref

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class MagicBoxPreferences @Inject constructor(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    fun setString(key: String?, stringToSave: String?) {
        preferences.edit().putString(key, stringToSave).apply()
    }

    fun getString(key: String?, defaultValue: String?): String? {
        return preferences.getString(key, defaultValue)
    }

    operator fun contains(key: String?): Boolean {
        return preferences.contains(key)
    }

    /**
     * Remove a Preference by Key
     * @param key String Key
     */
    fun removePreferenceByKey(key: String?) {
        preferences.edit().remove(key).apply()
    }

    companion object {
        private const val PREFERENCES = "magic_box_2_preferences"
    }

}