package com.example.bibliotech.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bibliotech.model.Libro

@Dao
interface LibroDao {

    @Insert
    suspend fun insertarLibro(libro: Libro): Long

    @Query("SELECT * FROM libros")
    suspend fun obtenerLibros(): List<Libro>

    @Query("SELECT * FROM libros WHERE id = :id")
    suspend fun obtenerLibroPorId(id: Int): Libro?

    @Update
    suspend fun actualizarLibro(libro: Libro)

    @Delete
    suspend fun eliminarLibro(libro: Libro)
}