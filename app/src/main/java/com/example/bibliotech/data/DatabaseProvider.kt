package com.example.bibliotech.data

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// ============================================================
// MIGRACIÓN DE LA BASE DE DATOS
// ============================================================
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(
        db: SupportSQLiteDatabase
    ) {
        db.execSQL(
            """
           CREATE TABLE IF NOT EXISTS `Estudiantes` (
               `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
               `carnet` TEXT NOT NULL,
               `nombres` TEXT NOT NULL,
               `apellidos` TEXT NOT NULL,
               `grado` TEXT NOT NULL,
               `seccion` TEXT NOT NULL,
               `activo` INTEGER NOT NULL
           )
           """.trimIndent()
        )
    }
}

// ============================================================
// PROVEEDOR DE LA BASE DE DATOS
// ============================================================
object DatabaseProvider {

    @Volatile
    private var INSTANCE: BibliotecaDatabase? = null

    fun getDatabase(
        context: Context
    ): BibliotecaDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BibliotecaDatabase::class.java,
                "bibliotech_database"
            )
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance
            instance
        }
    }
}
