package com.example.bibliotech.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SesionManager(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("bibliotech_sesion", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USUARIO = "usuario_activo"
        private const val KEY_SESION_ACTIVA = "sesion_activa"
    }

    fun guardarSesion(usuario: String) {
        preferences.edit {
            putString(KEY_USUARIO, usuario)
            putBoolean(KEY_SESION_ACTIVA, true)
        }
    }

    fun sesionActiva(): Boolean {
        return preferences.getBoolean(KEY_SESION_ACTIVA, false)
    }

    fun obtenerUsuario(): String? {
        return preferences.getString(KEY_USUARIO, null)
    }

    fun cerrarSesion() {
        preferences.edit {
            clear()
        }
    }
}
