package com.travelcar_test.ui.account

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.travelcar_test.Application
import com.travelcar_test.R

class AccountFragment : Fragment() {

    private var preferenceFragment: AccountSettings? = null

    var app = Application.instance
    private val preferenses: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app!!)
    lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listener = (SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            key?.let {

                sharedPreferences?.getString(it, "")?.let { value ->

                    onPreferenceChanged(key, value)
                }

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.account_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        preferenceFragment = AccountSettings()
        preferenceFragment?.let {

            childFragmentManager
                .beginTransaction()
                .replace(R.id.container, it)
                .commit()
        }
        loadPreferences()

    }

    fun onPreferenceChanged(key: String, value: String) {

        preferenceFragment?.updatePreference(key, value)
    }

    fun loadPreferences() {

        preferenses.getString(AccountSettings.LASTNAME, null)?.let {

            onPreferenceChanged(AccountSettings.LASTNAME, it)
        }

        preferenses.getString(AccountSettings.FIRSTNAME, null)?.let {

            onPreferenceChanged(AccountSettings.FIRSTNAME, it)
        }

        preferenses.getString(AccountSettings.ADDRESS, null)?.let {

            onPreferenceChanged(AccountSettings.ADDRESS, it)
        }
    }

    override fun onResume() {
        super.onResume()

        registerPref()
        loadPreferences()
    }

    override fun onPause() {
        super.onPause()

        unregisterPref()
    }

    fun registerPref() {
        preferenses.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterPref() {
        preferenses.unregisterOnSharedPreferenceChangeListener(listener)
    }
}