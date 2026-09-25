package com.example.bibliotech.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bibliotech.model.Estudiante
import com.example.bibliotech.model.Libro

@Database(
    entities = [Libro::class, Estudiante::class],
    version = 3,
    exportSchema = false
)
abstract class BibliotecaDatabase : RoomDatabase() {

    abstract fun libroDao(): LibroDao
    abstract fun estudianteDao(): EstudianteDao
}
