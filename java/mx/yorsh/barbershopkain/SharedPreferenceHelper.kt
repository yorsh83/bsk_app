package mx.yorsh.barbershopkain

import android.content.Context

class SharedPreferenceHelper(val context: Context) {

    companion object {
        const val NAME = "DB"
        const val KEY_IDU = "IDUSER"
        const val KEY_USERNAME = "USERNAME"
        const val KEY_WHATS = "WHATS"
        const val KEY_VISIT = "VISIT"
    }

    fun saveIdu(value: Int) {
        val preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
        preference.putInt(KEY_IDU, value)
        preference.apply()
    }

    fun getIdu(): Int? {
        val preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        return preference.getInt(KEY_IDU, 0)
    }

    fun saveUsername(value: String) {
        val preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
        preference.putString(KEY_USERNAME, value)
        preference.apply()
    }

    fun getUsername(): String? {
        val preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        return preference.getString(KEY_USERNAME, "")
    }

    fun saveWhats(value: String) {
        val preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
        preference.putString(KEY_WHATS, value)
        preference.apply()
    }

    fun getWhats(): String? {
        val preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        return preference.getString(KEY_WHATS, "")
    }

    fun saveNumVisit(value: Int) {
        val preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
        preference.putInt(KEY_VISIT, value)
        preference.apply()
    }

    fun getNumVisit(): Int? {
        val preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        return preference.getInt(KEY_VISIT, 0)
    }

    fun delete() {
        val preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
        preference.clear()
        preference.apply()
    }


}