package com.call.exchangescompose

import android.graphics.Color
import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.call.exchangescompose.data.remote.dto.ExchangesDto
import com.call.exchangescompose.ui.theme.ExchangesComposeTheme
import com.call.exchangescompose.view.ExchangesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangesComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    myApp()
                }
            }
        }
    }
}

@Composable
fun myApp(
    viewModel: ExchangesViewModel = hiltViewModel()) {

    val ScaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Exchanges") })
        },
        scaffoldState = ScaffoldState
    ){it

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.exchanges){ exchanges ->
                    ExchangesItem(exchanges = exchanges, {})
                }
            }
            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}

@Composable
fun ExchangesItem(exchanges: ExchangesDto, onClick : (ExchangesDto)-> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(exchanges) }
        .padding(16.dp)
    ) {
        Text(
            text = "${exchanges.name}",
            style = MaterialTheme.typography.h5,
            overflow = TextOverflow.Ellipsis
        )

        Text(text =  "${exchanges.description}" ,
            style = MaterialTheme.typography.body2,)

        Row(modifier = Modifier.fillMaxWidth()
            .height(30.dp).padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Text(
                text = if(exchanges.active) "Activa" else "Inactiva",
            )

            Text(
                text = "${exchanges.last_updated}",

            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ExchangesComposeTheme {
        myApp()
    }
}