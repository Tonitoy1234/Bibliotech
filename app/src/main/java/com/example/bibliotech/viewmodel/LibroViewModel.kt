package com.example.bibliotech.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.Libro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LibroViewModel(application: Application): AndroidViewModel(application) {

    private val repository =
        (application as BibliotecaApplication).libroRepository

    private val _libros = MutableStateFlow<List<Libro>>(emptyList())
    val libros: StateFlow<List<Libro>> = _libros.asStateFlow()

    private val _libroSeleccionado = MutableStateFlow<Libro?>(null)
    val libroSeleccionado: StateFlow<Libro?> = _libroSeleccionado.asStateFlow()

    fun cargarLibros() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _libros.value = repository.obtenerLibros()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertarLibro(libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertarLibro(libro)
                _libros.value = repository.obtenerLibros()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun cargarLibroPorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _libroSeleccionado.value = repository.obtenerLibroPorId(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun actualizarLibro(libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.actualizarLibro(libro)
                _libros.value = repository.obtenerLibros()
                _libroSeleccionado.value = repository.obtenerLibroPorId(libro.id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun eliminarLibro(libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.eliminarLibro(libro)
                _libros.value = repository.obtenerLibros()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
