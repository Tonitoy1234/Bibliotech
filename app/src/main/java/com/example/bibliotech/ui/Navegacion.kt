package com.example.bibliotech.ui

import android.app.Application
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.viewmodel.EstudianteViewModel
import com.example.bibliotech.viewmodel.LibroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navegacion(
    navController: NavHostController
) {
    var mensaje by remember { mutableStateOf<String?>(null) }

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            PantallaPrincipal(
                onCatalogo = {
                    navController.navigate("catalogo")
                },
                onPrestamo = {
                    navController.navigate("prestamo")
                },
                onPrestados = {
                    navController.navigate("prestados")
                },
                onEstudiantes = {
                    navController.navigate("estudiantes")
                }
            )
        }

        composable("catalogo") {
            PantallaCatalogo(
                onRegresar = {
                    navController.popBackStack()
                },
                onVerDetalles = { idLibro -> navController.navigate("detalle/$idLibro") },
                onAgregarLibro = { navController.navigate("agregar") },
                mensaje = mensaje,
                onMensajeMostrado = { mensaje = null }
            )
        }

        //--RUTA PARA ENVIAR A PANTALLA AGREGAR LIBRO
        composable("agregar") {
            val app = LocalContext.current.applicationContext as BibliotecaApplication
            val viewModel: LibroViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return LibroViewModel(app as Application) as T
                    }
                }
            )
            PantallaAgregarLibro(
                viewModel = viewModel,
                onGuardar = {
                    //mensaje a mostrar cuando se guarde el libro
                    mensaje = "✔ Libro guardado con éxito"
                    navController.popBackStack()
                },
                onCancelar = {
                    navController.popBackStack()
                }
            )
        }

        composable("detalle/{idLibro}") {
            val idLibro = it.arguments?.getString("idLibro")?.toIntOrNull()
            val app = LocalContext.current.applicationContext as BibliotecaApplication

            val viewModel: LibroViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(
                        modelClass: Class<T>
                    ): T {
                        return LibroViewModel(app as Application) as T
                    }
                }
            )

            val libro by viewModel.libroSeleccionado.collectAsState()

            LaunchedEffect(idLibro) {
                if (idLibro != null) {
                    viewModel.cargarLibroPorId(idLibro)
                }
            }
            if (libro != null) {
                PantallaDetalleLibro(
                    libro = libro!!,
                    onRegresar = { navController.popBackStack() },
                    navController = navController,
                    onEditar = { idLibroEdit ->
                        navController.navigate("editar/$idLibroEdit")
                    },
                    onEliminar = { libroEliminar ->
                        viewModel.eliminarLibro(libroEliminar)
                        mensaje = "✔ Libro eliminado con éxito"
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("editar/{idLibro}") {
            val idLibro = it.arguments?.getString("idLibro")?.toIntOrNull()
            val app = LocalContext.current.applicationContext as BibliotecaApplication
            val viewModel: LibroViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(
                        modelClass: Class<T>
                    ): T {
                        return LibroViewModel(app as Application) as T
                    }
                }
            )
            val libro by viewModel.libroSeleccionado.collectAsState()

            LaunchedEffect(idLibro) {
                if (idLibro != null) {
                    viewModel.cargarLibroPorId(idLibro)
                }
            }
            if (libro != null) {
                PantallaEditarLibro(
                    libro = libro!!,
                    onGuardar = { libroEditado ->
                        viewModel.actualizarLibro(libroEditado)
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set(
                                "mensaje",
                                "✓ Cambios guardados correctamente"
                            )
                        navController.popBackStack()
                    },
                    onCancelar = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("prestamo") {
            PantallaPrestamo(
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }

        composable("prestados") {
            PantallaLibrosPrestados(
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }

        composable("estudiantes") {
            PantallaEstudiantes(
                onRegresar = {
                    navController.popBackStack()
                },
                onVerDetalles = {},
                onAgregarEstudiante = {
                    navController.navigate("agregarEstudiante")
                },
                mensaje = mensaje,
                onMensajeMostrado = { mensaje = null }
            )
        }

        composable("agregarEstudiante") {
            val app = LocalContext.current.applicationContext as BibliotecaApplication
            val viewModel: EstudianteViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(
                        modelClass: Class<T>
                    ): T {
                        return EstudianteViewModel(app as Application) as T
                    }
                }
            )
            PantallaAgregarEstudiante(
                viewModel = viewModel,
                onGuardar = {
                    mensaje = "✔ Estudiante guardado con éxito"
                    navController.popBackStack()
                },
                onCancelar = {
                    navController.popBackStack()
                }
            )
        }
    }
}