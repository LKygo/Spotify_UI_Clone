package com.kygoinc.spotifyuiclone

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyBlack
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyDarkGrey
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyGrey
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyUICloneTheme
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyWhite
import kotlin.math.pow
import kotlin.random.Random

@Composable
fun DefaultSearchScreen(modifier: Modifier = Modifier) {

    SpotifyUICloneTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = SpotifyBlack),
            horizontalAlignment = Alignment.Start

        ) {

            Spacer(modifier = Modifier.height(26.dp))
            TopAppBar(modifier = modifier)

            Spacer(modifier = Modifier.height(16.dp))

            SearchViewColumn(modifier = modifier)
        }
    }
}


@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .background(color = SpotifyBlack),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start

    ) {
        Text(
            text = "Search", color = SpotifyWhite, style = TextStyle(
                fontSize = 22.sp, fontWeight = FontWeight.Bold
            ), modifier = Modifier
        )

        Spacer(modifier = Modifier.height(18.dp))

        SearchBar()

    }
}

@Composable
fun SearchBar() {
    var searchInput by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchInput,
        onValueChange = { searchInput = it },
        label = {
            Text(
                "Artists, songs or podcasts", style = TextStyle(
                    fontWeight = FontWeight.Medium, fontSize = 18.sp
                )
            )
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = SpotifyDarkGrey)
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = SpotifyBlack),
        textStyle = TextStyle(
            color = SpotifyGrey, fontWeight = FontWeight.SemiBold
        ),

        )

}


fun calculateLuminance(color: Color): Double {
    val red = color.red
    val green = color.green
    val blue = color.blue
    val r = if (red <= 0.03928) red / 12.92 else ((red + 0.055) / 1.055).pow(2.4)
    val g = if (green <= 0.03928) green / 12.92 else ((green + 0.055) / 1.055).pow(2.4)
    val b = if (blue <= 0.03928) blue / 12.92 else ((blue + 0.055) / 1.055).pow(2.4)
    return 0.2126 * r + 0.7152 * g + 0.0722 * b
}

@Composable
fun SearchViewComponent(
    @StringRes suggestionCategory: Int, @DrawableRes imageId: Int, modifier: Modifier = Modifier
) {

    val randomColor = Color(
        red = Random.nextInt(256),
        green = Random.nextInt(256),
        blue = Random.nextInt(256),
        alpha = 255 // Set alpha to fully opaque
    )
    val textColor = if (calculateLuminance(randomColor) > 0.5) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }



    Surface(
        modifier = modifier
            .background(color = SpotifyBlack)
            .clip(MaterialTheme.shapes.small)
            .padding(4.dp)


    ) {
        Box(
            modifier = Modifier
                .width(120.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .height(80.dp)
                .background(color = randomColor)

        ) {
            Text(
                text = stringResource(suggestionCategory), style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                ), modifier = modifier
                    .align(Alignment.TopStart)
                    .padding(start = 8.dp, top = 8.dp)
            )

            Image(
                painterResource(id = imageId),
                contentDescription = "",
                modifier = modifier
                    .width(60.dp)
                    .height(50.dp)
                    .align(Alignment.CenterEnd)
                    .rotate(30f)
                    .offset(x = 28.dp, y = 4.dp)
                    .clip(MaterialTheme.shapes.extraSmall),
                contentScale = ContentScale.Crop
            )

        }
    }
}


@Composable
fun SearchViewColumn(modifier: Modifier = Modifier.fillMaxSize()) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
    ) {
        items(SearchSuggestions) {
            SearchViewComponent(
                suggestionCategory = it.text, imageId = it.drawable, modifier = modifier
            )
        }
    }
}


private val SearchSuggestions = listOf(
    R.drawable.search_image1 to R.string.search_title1,
    R.drawable.search_image2 to R.string.search_title2,
    R.drawable.search_image3 to R.string.search_title3,
    R.drawable.search_image4 to R.string.search_title4,
    R.drawable.search_image5 to R.string.search_title5,
    R.drawable.search_image6 to R.string.search_title6,
    R.drawable.search_image7 to R.string.search_title7,
    R.drawable.search_image8 to R.string.search_title8,
    R.drawable.search_image9 to R.string.search_title9,
    R.drawable.search_image1 to R.string.search_title10,
    R.drawable.search_image1 to R.string.search_title1,
    R.drawable.search_image2 to R.string.search_title2,
    R.drawable.search_image3 to R.string.search_title3,
    R.drawable.search_image4 to R.string.search_title4,
    R.drawable.search_image5 to R.string.search_title5,
    R.drawable.search_image6 to R.string.search_title6,
    R.drawable.search_image7 to R.string.search_title7,
    R.drawable.search_image8 to R.string.search_title8,
    R.drawable.search_image9 to R.string.search_title9,
    R.drawable.search_image1 to R.string.search_title10,
).map { DrawableStringPair(it.first, it.second) }


@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun DefaultSearchScreenPreview() {
    DefaultSearchScreen()
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarPreview() {
    TopAppBar()
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar()
}

@Preview(showBackground = true)
@Composable
private fun SearchViewComponentPreview() {
    SearchViewComponent(R.string.search_suggestions, R.drawable.library_item_eight)
}

@Preview(showBackground = true)
@Composable
private fun SearchViewColumnPreview() {
    SearchViewColumn()
}