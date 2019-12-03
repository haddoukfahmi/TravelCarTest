package com.travelcar_test.ui.account

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.travelcar_test.R


class AccountSettings:PreferenceFragmentCompat() {

    companion object {
        const val LASTNAME = "lastname"
        const val FIRSTNAME = "firstname"
        const val ADDRESS = "address"
    }


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    fun updatePreference(key: String, value: String) {

        when(key) {
            LASTNAME -> updateLastname(value)
            FIRSTNAME -> updateFirstname(value)
            ADDRESS -> updateAddress(value)
        }
    }

    private fun updateLastname(value: String) {

        findPreference<EditTextPreference>(LASTNAME)?.let {

            it.summary = value
        }
    }

    private fun updateFirstname(value: String) {

        findPreference<EditTextPreference>(FIRSTNAME)?.let {

            it.summary = value
        }
    }

    private fun updateAddress(value: String) {

        findPreference<EditTextPreference>(ADDRESS)?.let {

            it.summary = value
        }
    }

}