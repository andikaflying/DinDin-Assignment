package app.andika.dindinassignment.utilities

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import app.andika.dindinassignment.model.BoughtItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

object SharedPreferencesManager {
    private val TAG = SharedPreferencesManager::class.java.name
    private lateinit var sharedPreferences: SharedPreferences
    private val gson: Gson = Gson()
    private val userSubOffSet = 8
    private const val MYPREF = "General"
    const val TOKEN = "token"

    fun setValue(keyword: String, value: Any?, activity: Activity) {
        sharedPreferences = activity.getSharedPreferences(MYPREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Check variable type of value
        when (value) {
            is Int -> editor.putInt(keyword, value)
            is String -> editor.putString(keyword, value)
            is Long -> editor.putLong(keyword, value)
            is Boolean -> editor.putBoolean(keyword, value)
            is Float -> editor.putFloat(keyword, value)
        }

        editor.apply()
    }

    fun getValue(keyword: String, activity: Activity): Any? {
        sharedPreferences = activity.getSharedPreferences(MYPREF, Context.MODE_PRIVATE)
        val keys: Map<String, Any?> = sharedPreferences.all
        var value: Any? = null

        // Find value from keyword
        for (entry: Map.Entry<String, Any?> in keys.entries) {
            if (entry.key != keyword) {
                continue
            }

            // Check variable type of value
            when (entry.value) {
                is Int -> value = sharedPreferences.getInt(keyword, 0)
                is String -> value = sharedPreferences.getString(keyword, null)
                is Long -> value = sharedPreferences.getLong(keyword, 0)
                is Boolean -> value = sharedPreferences.getBoolean(keyword, false)
                is Float -> value = sharedPreferences.getFloat(keyword, Float.MIN_VALUE)
            }
        }

        return value
    }

    fun saveArrayList(list: List<BoughtItem>, key: String?, activity: Activity) {
        sharedPreferences = activity.getSharedPreferences(MYPREF, Context.MODE_PRIVATE)

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getArrayList(key: String?, activity: Activity): List<BoughtItem>? {

        sharedPreferences = activity.getSharedPreferences(MYPREF, Context.MODE_PRIVATE)

        val gson = Gson()
        val json: String? = sharedPreferences.getString(key, null)
        val type: Type = object : TypeToken<List<BoughtItem>?>() {}.getType()

        return gson.fromJson(json, type)
    }

    fun isExist(key: String?, activity: Activity) : Boolean {
        sharedPreferences = activity.getSharedPreferences(MYPREF, Context.MODE_PRIVATE)

        return sharedPreferences.contains(key)
    }

    fun removeAllData(activity: Activity) {
        sharedPreferences = activity.getSharedPreferences(MYPREF, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}