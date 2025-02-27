/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.plantdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel

@Composable // Statefull: opinianated
fun PlantDetailDescription(plantDetailViewModel: PlantDetailViewModel) {
    val currentPlant by plantDetailViewModel.plant.observeAsState()
    currentPlant?.let { plant: Plant ->
        PlantDetailDescription(plant)
    }

}

@Composable
fun PlantWatering(wateringInterval: Int) {
    Column(modifier = Modifier
        .padding(horizontal = dimensionResource(id = R.dimen.margin_small))) {
        Text(
            text = stringResource(id = R.string.watering_needs_prefix),
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.margin_normal))
                .padding(end = dimensionResource(id = R.dimen.margin_small)),
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold
        )
        val resources = LocalContext.current.resources
        val quantifyString = resources
            .getQuantityString(R.plurals.watering_needs_suffix, wateringInterval)
        Text(text = quantifyString)

    }
}

@Preview(showBackground = true)
@Composable
fun WateringNeedsPreview() {
    MaterialTheme {
        PlantWatering(wateringInterval = 5)
    }
}

@Composable //Stateless: Preview + reusable
private fun PlantDetailDescription(plant: Plant) {
    Surface {
        PlantName(name = plant.name)
        // Watering
        // PlantDescription

    }
}

@Composable
fun PlantDescription() {
    
}
@Composable
fun PlantName(name: String) {
    Text(
        text = name,
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.margin_extra_small))
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        style = MaterialTheme.typography.h5
    )
}

@Preview
@Composable
fun PlantDetailDescriptionPreview() {
    MaterialTheme {
        val fakePlant = Plant(
            plantId = "id",
            name = "Cactus",
            description = "some fun",
            growZoneNumber = 3)
        PlantDetailDescription(plant = fakePlant)
    }
}
@Preview(showBackground = true)
@Composable
fun PlantNamePreview() {
    MaterialTheme {
        PlantName(name = "Advocado")
    }
}
