package com.example.a30daysapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30daysapp.data.Datasource
import com.example.a30daysapp.model.Fitday

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessTopBar() {
    CenterAlignedTopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.fitness),
                contentDescription = "Fitness Logo",
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size))
                    .padding(dimensionResource(R.dimen.padding_small))
            )
            Text(text = "30 Days Fitness")
        }
    })
}

@Composable
fun FitnessItem(
    fitday: Fitday,
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 10.dp)) {
                MyTitle(fitday.dayNumber)
                Spacer(modifier = Modifier.size(12.dp))
                MyTaskTitle(fitday.titleId)
            }
            MyTaskImage(fitday.imageId, checked, { checked = !checked })
            if(checked){
                MyFullDescription(fitday.moreInformation)
            }
        }
    }
}

@Composable
fun MyFullDescription(moreInformation: String) {
    Text(text = moreInformation, textAlign = TextAlign.Center)
}

@Composable
fun MyTaskImage(imageId: Int, checked: Boolean, onChecked: () -> Unit) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = "Image",
        modifier = Modifier
            .clip(
                RoundedCornerShape(8.dp)
            )
            .clickable { onChecked() }
    )
}

@Composable
fun MyTaskTitle(titleId: Int) {
    Text(text = stringResource(id = titleId), fontWeight = FontWeight.Bold, fontSize = 20.sp)
}

@Composable
fun MyTitle(dayNumber: String) {
    Text(text = dayNumber, fontSize = 14.sp)
}

@Preview
@Composable
fun Fitness() {
    val fitdays = Datasource.fitdays
    FitnessApp(fitdays = fitdays)
}

@Composable
fun FitnessApp(fitdays: List<Fitday>) {
    var clicked by remember { mutableStateOf(false) }
    Scaffold(topBar = { FitnessTopBar() }) {
        LazyColumn(contentPadding = it) {
            items(fitdays) {
                FitnessItem(fitday = it)
            }
        }
    }
}