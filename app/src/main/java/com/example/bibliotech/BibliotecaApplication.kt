package com.example.bibliotech

import android.app.Application
import com.example.bibliotech.data.DatabaseProvider
import com.example.bibliotech.data.EstudianteRepository
import com.example.bibliotech.data.LibroRepository
import com.example.bibliotech.data.PrestamoRepository
import com.example.bibliotech.data.UsuarioRepository

class BibliotecaApplication : Application() {

    val database by lazy { DatabaseProvider.getDatabase(this) }

    val libroRepository by lazy { LibroRepository(database.libroDao()) }

    val estudianteRepository by lazy { EstudianteRepository(database.estudianteDao()) }

    val prestamoRepository by lazy { PrestamoRepository(database.prestamoDao()) }

    val usuarioRepository by lazy { UsuarioRepository(database.usuarioDao()) }
}
