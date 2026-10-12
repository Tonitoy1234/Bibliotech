package com.example.bibliotech.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bibliotech.model.Estudiante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarEstudiante(
    estudiante: Estudiante,
    onGuardar: (Estudiante) -> Unit,
    onCancelar: () -> Unit
) {
    var carnet by remember { mutableStateOf(estudiante.carnet) }
    var nombres by remember { mutableStateOf(estudiante.nombres) }
    var apellidos by remember { mutableStateOf(estudiante.apellidos) }

    val grados = listOf(
        "1° Bachillerato",
        "2° Bachillerato",
        "3° Bachillerato"
    )
    var grado by remember { mutableStateOf(estudiante.grado) }
    var expandirGrado by remember { mutableStateOf(false) }

    val secciones = listOf("A", "B", "C")
    var seccion by remember { mutableStateOf(estudiante.seccion) }
    var expandirSeccion by remember { mutableStateOf(false) }

    var activo by remember { mutableStateOf(estudiante.activo) }

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text("Editar Estudiante", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Carnet
            OutlinedTextField(
                value = carnet,
                onValueChange = { carnet = it },
                label = { Text("Carnet") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.Yellow,
                    cursorColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Nombres
            OutlinedTextField(
                value = nombres,
                onValueChange = { nombres = it },
                label = { Text("Nombres") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.Yellow,
                    cursorColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Apellidos
            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.Yellow,
                    cursorColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Dropdown Grado
            ExposedDropdownMenuBox(
                expanded = expandirGrado,
                onExpandedChange = { expandirGrado = !expandirGrado }
            ) {
                OutlinedTextField(
                    value = grado,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Grado") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandirGrado
                        )
                    },
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Yellow,
                        unfocusedTextColor = Color.LightGray,
                        focusedBorderColor = Color.Yellow,
                        unfocusedBorderColor = Color.LightGray,
                        focusedLabelColor = Color.Yellow,
                        unfocusedLabelColor = Color.LightGray
                    )
                )

                ExposedDropdownMenu(
                    expanded = expandirGrado,
                    onDismissRequest = { expandirGrado = false }
                ) {
                    grados.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                grado = opcion
                                expandirGrado = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Dropdown Sección
            ExposedDropdownMenuBox(
                expanded = expandirSeccion,
                onExpandedChange = { expandirSeccion = !expandirSeccion }
            ) {
                OutlinedTextField(
                    value = seccion,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Sección") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandirSeccion
                        )
                    },
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Yellow,
                        unfocusedTextColor = Color.LightGray,
                        focusedBorderColor = Color.Yellow,
                        unfocusedBorderColor = Color.LightGray,
                        focusedLabelColor = Color.Yellow,
                        unfocusedLabelColor = Color.LightGray
                    )
                )

                ExposedDropdownMenu(
                    expanded = expandirSeccion,
                    onDismissRequest = { expandirSeccion = false }
                ) {
                    secciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                seccion = opcion
                                expandirSeccion = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Checkbox Activo
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = activo,
                    onCheckedChange = { activo = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Yellow,
                        uncheckedColor = Color.White
                    )
                )
                Text(
                    text = "Activo",
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Boton Guardar Cambios
            Button(
                onClick = {
                    val estudianteActualizado = estudiante.copy(
                        carnet = carnet,
                        nombres = nombres,
                        apellidos = apellidos,
                        grado = grado,
                        seccion = seccion,
                        activo = activo
                    )
                    onGuardar(estudianteActualizado)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Cambios")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Boton Cancelar
            OutlinedButton(
                onClick = { onCancelar() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}