package com.example.bookdb.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.bookdb.R


class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    private val clickTag = "__click__"

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        prefs.registerOnSharedPreferenceChangeListener(this)


    }

    fun NavController.safeNavigate(direction: NavDirections) {
        Log.d(clickTag, "Click happened")
        currentDestination?.getAction(direction.actionId)?.run {
            Log.d(clickTag, "Click Propagated")
            navigate(direction)
        }
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        when (key) {
            "database" -> {
                var checkPref = sharedPreferences.getString(key, "Room")
                if (checkPref == "Room") {
                    view?.post {
                        findNavController().safeNavigate(SettingsFragmentDirections.actionSettingsFragmentToListFragment())
                    }
                } else if (checkPref == "SQLite") {
                    view?.post {
                        findNavController().safeNavigate(SettingsFragmentDirections.actionSettingsFragmentToListSqlFragment())
                    }
                }
            }
        }
    }
}



