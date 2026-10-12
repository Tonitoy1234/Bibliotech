package com.example.bibliotech.data

import com.example.bibliotech.model.Libro

val librosPrueba = listOf(
    Libro(
        titulo = "Don Quijote de la Mancha",
        autor = "Miguel de Cervantes",
        categoria = "Literatura",
        anio = 1605,
        descripcion = "Las aventuras del ingenioso hidalgo Don Quijote de la Mancha y su fiel escudero Sancho Panza.",
        disponible = true
    ),
    Libro(
        titulo = "Cien años de soledad",
        autor = "Gabriel García Márquez",
        categoria = "Novela",
        anio = 1967,
        descripcion = "La historia de la familia Buendía en el pueblo ficticio de Macondo.",
        disponible = true
    ),
    Libro(
        titulo = "Aprende Kotlin en 21 Días",
        autor = "John Doe",
        categoria = "Programacion",
        anio = 2023,
        descripcion = "Una guía práctica para dominar la programación en Kotlin desde cero.",
        disponible = true
    )
)
