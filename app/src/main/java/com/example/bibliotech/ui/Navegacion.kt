package com.example.bibliotech.ui

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.data.librosPrueba

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bibliotech.viewmodel.LibroViewModel


@Composable
fun Navegacion(
    navController: NavHostController
) {
    NavHost(navController = navController,
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
                }
            )
        }

        composable("catalogo") {

            PantallaCatalogo(
                onRegresar = {
                    navController.popBackStack()
                },
                onVerDetalles = { idLibro -> navController.navigate("detalle/$idLibro") },
                onAgregarLibro = { navController.navigate("agregar") }
            )
        }
        //--RUTA PARA ENVIAR A PANTALLA AGREGAR LIBRO
        composable("agregar"){
            PantallaAgregarLibro(
                viewModel = viewModel(),
                onGuardar = {
                    navController.popBackStack()
                },
                onCancelar = {
                    navController.popBackStack()
                }

            )
        }


        composable("detalle/{idLibro}") {
            val idLibro = it.arguments?.getString("idLibro")?.toIntOrNull() //###
            //--
            val app = LocalContext.current.applicationContext as BibliotecaApplication

            val viewModel: LibroViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override  fun <T : ViewModel> create(
                        modelClass: Class<T>
                    ): T {
                        return LibroViewModel(app as Application) as T
                    }
                }
            )
            val libro by viewModel.libroSeleccionado.collectAsState()
            //#
            LaunchedEffect(idLibro) {
                if (idLibro != null){
                    viewModel.cargarLibroPorId(idLibro)
                }
            }
            if (libro != null){
                PantallaDetalleLibro(libro = libro!!,
                    onRegresar = {navController.popBackStack()},
                    onEditar = {},
                    onEliminar = {})
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
    }
}