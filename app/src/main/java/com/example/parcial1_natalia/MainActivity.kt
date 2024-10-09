package com.example.parcial1_natalia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcial1_natalia.ui.theme.Parcial1_NataliaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial1_NataliaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormularioProducto(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FormularioProducto(modifier: Modifier = Modifier) {
    var product by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var categoryproduct by remember { mutableStateOf("") }
    var errormessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    val categorieslist = listOf("Electrónica", "Ropa", "Alimentos")

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.header),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = product,
            onValueChange = {
                product = it
                errormessage = ""
            },
            label = { Text("Producto") },
            isError = errormessage.isNotEmpty() && product.isEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = cost,
            onValueChange = {
                cost = it
                errormessage = ""
            },
            label = { Text("Precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errormessage.isNotEmpty() && cost.isEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Categoría")
        categorieslist.forEach { categoria ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = categoria == categoryproduct,
                    onClick = {
                        categoryproduct = categoria
                        errormessage = ""
                    }
                )
                Text(text = categoria)
            }
        }

        if (errormessage.isNotEmpty()) {
            Text(text = errormessage, color = MaterialTheme.colorScheme.error)
        }

        if (successMessage.isNotEmpty()) {
            Text(text = successMessage, color = MaterialTheme.colorScheme.primary)
        }

        Button(
            onClick = {
                when {
                    product.isEmpty() -> {
                        errormessage = "Ingrese el Producto"
                    }
                    cost.isEmpty() -> {
                        errormessage = "Ingrese el Precio"
                    }
                    categoryproduct.isEmpty() -> {
                        errormessage = "Seleccione Categoria"
                    }
                    else -> {
                        successMessage = "Producto: $product, Precio: $cost, Categoría: $categoryproduct"
                        errormessage = ""
                        product = ""
                        cost = ""
                        categoryproduct = ""
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Enviar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioProductoPreview() {
    Parcial1_NataliaTheme {
        FormularioProducto()
    }
}
