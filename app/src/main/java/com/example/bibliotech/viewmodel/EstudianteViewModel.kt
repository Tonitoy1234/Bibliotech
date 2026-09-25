package com.example.bibliotech.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.Estudiante
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EstudianteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        (application as BibliotecaApplication).estudianteRepository

    private val _estudiantes =
        MutableStateFlow<List<Estudiante>>(emptyList())

    val estudiantes: StateFlow<List<Estudiante>> =
        _estudiantes.asStateFlow()

    private val _estudianteSeleccionado =
        MutableStateFlow<Estudiante?>(null)

    val estudianteSeleccionado: StateFlow<Estudiante?> =
        _estudianteSeleccionado.asStateFlow()

    fun cargarEstudiantes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _estudiantes.value = repository.obtenerEstudiantes()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertarEstudiante(estudiante: Estudiante) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertarEstudiante(estudiante)
                _estudiantes.value = repository.obtenerEstudiantes()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun cargarEstudiantePorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _estudianteSeleccionado.value =
                    repository.obtenerEstudiantePorId(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun actualizarEstudiante(estudiante: Estudiante) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.actualizarEstudiante(estudiante)
                _estudiantes.value = repository.obtenerEstudiantes()
                _estudianteSeleccionado.value =
                    repository.obtenerEstudiantePorId(estudiante.id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun eliminarEstudiante(estudiante: Estudiante) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.eliminarEstudiante(estudiante)
                _estudiantes.value = repository.obtenerEstudiantes()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
