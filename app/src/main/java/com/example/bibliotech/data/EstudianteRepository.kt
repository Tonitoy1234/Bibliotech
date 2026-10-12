package com.example.bibliotech.data


import com.example.bibliotech.model.Estudiante


class EstudianteRepository(


    private val estudianteDao: EstudianteDao


) {


    suspend fun insertarEstudiante(estudiante: Estudiante): Long {
        return estudianteDao.insertarEstudiante(estudiante)
    }

    suspend fun obtenerEstudiantes(): List<Estudiante> {
        return estudianteDao.obtenerEstudiantes()
    }

    suspend fun obtenerEstudiantePorId(id: Int): Estudiante? {
        return estudianteDao.obtenerEstudiantePorId(id)
    }

    suspend fun actualizarEstudiante(estudiante: Estudiante) {
        estudianteDao.actualizarEstudiante(estudiante)
    }

    suspend fun eliminarEstudiante(estudiante: Estudiante) {
        estudianteDao.eliminarEstudiante(estudiante)
    }


}