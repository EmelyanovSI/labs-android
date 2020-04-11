package by.gsu.siemeljanov.labs.lab10

import android.app.backup.BackupAgentHelper
import android.app.backup.SharedPreferencesBackupHelper

class AndroidLabsBackUp : BackupAgentHelper() {
    override fun onCreate() {
        val helper = SharedPreferencesBackupHelper(this, File_Name_Of_Preferences)
        addHelper(PREFS_BACKUP_KEY, helper)
    }

    companion object {
        const val File_Name_Of_Preferences = "Prefrences"
        const val PREFS_BACKUP_KEY = "backup"
    }
}
