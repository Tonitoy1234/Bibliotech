package com.example.bibliotech.data

import com.example.bibliotech.model.Libro

class LibroRepository(
    private val libroDao: LibroDao
) {

    suspend fun insertarLibro(libro: Libro): Long {
        return libroDao.insertarLibro(libro)
    }

    suspend fun obtenerLibros(): List<Libro> {
        return libroDao.obtenerLibros()
    }

    suspend fun obtenerLibroPorId(id: Int): Libro? {
        return libroDao.obtenerLibroPorId(id)
    }

    suspend fun actualizarLibro(Libro: Libro) {
        libroDao.actualizarLibro(Libro)
    }

    suspend fun eliminarLibro(Libro: Libro) {
        libroDao.eliminarLibro(Libro)
    }
}