package com.example.bibliotech.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.Estudiante
import com.example.bibliotech.model.Libro
import com.example.bibliotech.model.Prestamo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PrestamoViewModel(application: Application) : AndroidViewModel(application) {

    private val prestamoRepository =
        (application as BibliotecaApplication).prestamoRepository

    private val libroRepository =
        (application as BibliotecaApplication).libroRepository

    private val estudianteRepository =
        (application as BibliotecaApplication).estudianteRepository


    private val _librosDisponibles = MutableStateFlow<List<Libro>>(emptyList())
    val librosDisponibles: StateFlow<List<Libro>> = _librosDisponibles.asStateFlow()

    private val _estudiantesActivos = MutableStateFlow<List<Estudiante>>(emptyList())
    val estudiantesActivos: StateFlow<List<Estudiante>> = _estudiantesActivos.asStateFlow()

    private val _prestamos = MutableStateFlow<List<Prestamo>>(emptyList())
    val prestamos: StateFlow<List<Prestamo>> = _prestamos.asStateFlow()

    private val _prestamoGuardado = MutableStateFlow(false)
    val prestamoGuardado: StateFlow<Boolean> = _prestamoGuardado.asStateFlow()

    private val _librosPrestados = MutableStateFlow<Map<Int, Libro>>(emptyMap())
    val librosPrestados: StateFlow<Map<Int, Libro>> = _librosPrestados.asStateFlow()

    private val _estudiantesPrestamos = MutableStateFlow<Map<Int, Estudiante>>(emptyMap())
    val estudiantesPrestamos: StateFlow<Map<Int, Estudiante>> = _estudiantesPrestamos.asStateFlow()



    fun cargarDatos() {
        viewModelScope.launch(Dispatchers.IO) {
            val libros = libroRepository.obtenerLibros()
            _librosDisponibles.value = libros.filter { it.disponible }

            val estudiantes = estudianteRepository.obtenerEstudiantes()
            _estudiantesActivos.value = estudiantes.filter { it.activo }

            val listaPrestamos = prestamoRepository.obtenerPrestamosActivos()
            _prestamos.value = listaPrestamos

            // Cargar Mapa de Libros Prestados
            val mapaLibros = mutableMapOf<Int, Libro>()
            listaPrestamos.forEach { prestamo ->
                val libro = libroRepository.obtenerLibroPorId(prestamo.idLibro)
                if (libro != null) {
                    mapaLibros[prestamo.idLibro] = libro
                }
            }
            _librosPrestados.value = mapaLibros

            // Cargar Mapa de Estudiantes de los Préstamos
            val mapaEstudiantes = mutableMapOf<Int, Estudiante>()
            listaPrestamos.forEach { prestamo ->
                val estudiante = estudianteRepository.obtenerEstudiantePorId(prestamo.idEstudiante)
                if (estudiante != null) {
                    mapaEstudiantes[prestamo.idEstudiante] = estudiante
                }
            }
            _estudiantesPrestamos.value = mapaEstudiantes
        }
    }

    fun registrarPrestamo(libroId: Int, estudianteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val libro = libroRepository.obtenerLibroPorId(libroId)
            if (libro == null || !libro.disponible) {
                return@launch
            }

            val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            val nuevoPrestamo = Prestamo(
                idLibro = libroId,
                idEstudiante = estudianteId,
                fechaPrestamo = fechaActual,
                fechaDevolucion = null,
                devuelto = false
            )

            prestamoRepository.insertar(nuevoPrestamo)

            val libroActualizado = libro.copy(disponible = false)
            libroRepository.actualizarLibro(libroActualizado)

            // Recargamos los datos para mantener las listas actualizadas
            cargarDatos()

            _prestamoGuardado.value = true
        }
    }


    fun devolverPrestamo(prestamo: Prestamo) {
        viewModelScope.launch(Dispatchers.IO) {
            val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            val prestamoActualizado = prestamo.copy(
                devuelto = true,
                fechaDevolucion = fechaActual
            )

            prestamoRepository.actualizarPrestamo(prestamoActualizado)

            val libro = libroRepository.obtenerLibroPorId(prestamo.idLibro)
            if (libro != null) {
                val libroActualizado = libro.copy(disponible = true)
                libroRepository.actualizarLibro(libroActualizado)
            }

            // Recargar datos actualizados
            cargarDatos()
        }
    }

    fun reiniciarEstadoGuardado() {
        _prestamoGuardado.value = false
    }
}