package com.example.bibliotech.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bibliotech.model.Prestamo

@Dao
interface PretamoDao {
    @Insert
    fun insertar(prestamo: Prestamo)

    @Query("SELECT * FROM Prestamos WHERE devuelto = 0")
    fun obtenerPrestamoActivo(): List<Prestamo>

    @Query("SELECT * FROM Prestamos WHERE id = :id")
    fun obttenerPrestamoPorId(id: Int): Prestamo?
}
