package com.kygoinc.spotifyuiclone

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryAddCheck
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyDarkGrey
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyGrey
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyUICloneTheme
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyWhite

@Composable
fun HomeScreen() {
    SpotifyUICloneTheme {

    }
}

@Preview
@Composable
fun HomeScreenPreview() {

}

@Composable
fun MainBody(modifier: Modifier = Modifier) {

    Column (
        modifier = modifier
            .verticalScroll(rememberScrollState())

    ){


        RecentlyPlayed1Row()
        MadeForComponent()
        YourTopMixesComponent()
        RecentlyPlayedComponent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212, widthDp = 360, heightDp = 640)
@Composable
private fun MainBodyPreview() {

    SpotifyUICloneTheme {
        MainBody()
    }
}


@Composable
fun TopBarElement() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Good Afternoon",
                style = TextStyle(
                    fontSize = 24.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
                ),
                color = SpotifyWhite,
                modifier = Modifier.weight(6f),

                )
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notification Icon",
                tint = SpotifyWhite,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = "Schedule Icon",
                tint = SpotifyWhite,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings Icon",
                tint = SpotifyWhite,
                modifier = Modifier.weight(1f)
            )
        }

        ChipsElement()
    }
}

@Composable
fun ChipsElement() {

    val chipItems = listOf("Music", "Podcasts", "Audiobooks")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(chipItems) { chipItem ->
            SuggestionChip(
                modifier = Modifier.padding(horizontal = 6.dp), // gap between items
                onClick = {},
                label = {
                    Text(text = chipItem)
                },
                shape = MaterialTheme.shapes.large,
            )
        }
    }
}

@Composable
fun RecentlyPlayedComponent(
    modifier: Modifier = Modifier, @DrawableRes imageId: Int, @StringRes value: Int
) {
    Surface(

        modifier = modifier.padding(4.dp),
        shape = MaterialTheme.shapes.extraSmall,
        color = SpotifyDarkGrey
    ) {
        Row(
            modifier = modifier
                .width(160.dp)
                .padding(0.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {


            Image(
                painter = painterResource(imageId),
                contentDescription = "Drake",
                contentScale = ContentScale.Crop,
                modifier = modifier.size(56.dp)
            )

            Text(
                text = stringResource(id = value),
                style = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight(weight = 500)
                ),
                color = SpotifyWhite,
                modifier = modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
                    .padding(horizontal = 16.dp)
                    .height(24.dp)
            )
        }
    }

}

@Composable
fun RecentlyPlayed1Row(modifier: Modifier = Modifier) {

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(130.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(8.dp)
    ) {

        items(recentlyPlayedData) {
            RecentlyPlayedComponent(
                imageId = it.drawable, value = it.text
            )
        }
    }
}

@Composable
fun MadeForComponent(
    @DrawableRes imageId: Int, @StringRes value: Int, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(100.dp)
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "",
            modifier = modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = value),
            style = TextStyle(
                fontSize = 10.sp, fontWeight = FontWeight(weight = 400), letterSpacing = 0.3.sp
            ),
            color = SpotifyGrey,
        )
    }
}

@Composable
fun RecentlyPlayed(
    @DrawableRes imageId: Int, @StringRes value: Int, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(100.dp)
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "",
            modifier = modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = value),
            style = TextStyle(
                fontSize = 12.sp, fontWeight = FontWeight(weight = 500), letterSpacing = 0.3.sp
            ),
            color = SpotifyWhite,
        )
    }
}

@Composable
fun MadeForRow(modifier: Modifier = Modifier) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(madeForData) {
            MadeForComponent(
                imageId = it.drawable, value = it.text
            )
        }
    }
}

@Composable
fun YourTopMixesRow(modifier: Modifier = Modifier) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topMixesData) {
            MadeForComponent(
                imageId = it.drawable, value = it.text
            )
        }
    }
}

@Composable
fun RecentlyPlayedRow(modifier: Modifier = Modifier) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(recentlyPlayedData) {
            RecentlyPlayed(
                imageId = it.drawable, value = it.text
            )
        }
    }
}


@Composable
fun MadeForComponent(
    modifier: Modifier.Companion = Modifier,
) {

    Column {
        Text(
            text = "Made for Kygo", style = TextStyle(
                fontSize = 24.sp, fontWeight = FontWeight(weight = 600)
            ), color = SpotifyWhite, modifier = modifier.padding(16.dp)
        )

        MadeForRow()
    }
}

@Composable
fun YourTopMixesComponent(
    modifier: Modifier.Companion = Modifier,
) {

    Column {
        Text(
            text = "Your top mixes", style = TextStyle(
                fontSize = 24.sp, fontWeight = FontWeight(weight = 600)
            ), color = SpotifyWhite, modifier = modifier.padding(16.dp)
        )

        YourTopMixesRow()
    }
}

@Composable
fun RecentlyPlayedComponent(
    modifier: Modifier.Companion = Modifier,
) {

    Column {
        Text(
            text = "Recently played", style = TextStyle(
                fontSize = 24.sp, fontWeight = FontWeight(weight = 600)
            ), color = SpotifyWhite, modifier = modifier.padding(16.dp)
        )

        RecentlyPlayedRow()
    }
}

//@Composable
//fun AppNavBar() {
//
//    BottomAppBar(
//        containerColor = SpotifyDarkGrey,
//        actions = {
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(
//
//                    imageVector = Icons.Filled.Home,
//                    contentDescription = "Home",
//                    tint = SpotifyWhite,
//                    modifier = Modifier
//                        .weight(1f)
//                )
//            }
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(
//                    imageVector = Icons.Filled.Search,
//                    contentDescription = "Notification Icon",
//                    tint = SpotifyWhite,
//                    modifier = Modifier
//                        .weight(1f)
//                )
//            }
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(
//                    imageVector = Icons.Filled.VideoLibrary,
//                    contentDescription = "Schedule Icon",
//                    tint = SpotifyWhite,
//                    modifier = Modifier
//                        .weight(1f)
//                )
//            }
//        }
//    )
//}


@Composable
fun BottomAppBarDefaults(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color(0x160A0A0A)
    )
    {
        BottomNavigationItem(selected = true,
            onClick = { /*TODO*/ },
            label = { Text(text = "Home") },
            selectedContentColor = SpotifyWhite,
            unselectedContentColor = SpotifyGrey,
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") })
        BottomNavigationItem(selected = false,
            onClick = { /*TODO*/ },
            label = { Text(text = "Search") },
            selectedContentColor = SpotifyWhite,
            unselectedContentColor = SpotifyGrey,
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") })
        BottomNavigationItem(selected = false,
            onClick = { /*TODO*/ },
            label = { Text(text = "Home") },
            selectedContentColor = SpotifyWhite,
            unselectedContentColor = SpotifyGrey,
            icon = { Icon(Icons.Filled.LibraryAddCheck, contentDescription = "Your Library") })
    }
}

private val recentlyPlayedData = listOf(
    R.drawable.album_cover to R.string.drake,
    R.drawable.nf_top_mix to R.string.nf,
    R.drawable.similar_to3 to R.string.future,
    R.drawable.similar_to to R.string.tems,
    R.drawable.album_cover2 to R.string.meek_mill,
    R.drawable.dave_top_mix to R.string.dave
).map { DrawableStringPair(it.first, it.second) }

private val madeForData = listOf(
    R.drawable.daily_mix_one to R.string.daily_mix_one,
    R.drawable.daily_mix_two to R.string.daily_mix_two,
    R.drawable.daily_mix_three to R.string.daily_mix_three,
    R.drawable.daily_mix_four to R.string.daily_mix_four,
    R.drawable.daily_mix_five to R.string.daily_mix_five,
    R.drawable.daily_mix_six to R.string.daily_mix_six
).map { DrawableStringPair(it.first, it.second) }

private val topMixesData = listOf(
    R.drawable.drake_top_mix to R.string.daily_mix_one,
    R.drawable.dave_top_mix to R.string.daily_mix_two,
    R.drawable.nf_top_mix to R.string.daily_mix_three,
    R.drawable.rihanna_top_mix to R.string.daily_mix_four,
    R.drawable.similar_to to R.string.daily_mix_five,
    R.drawable.similar_to2 to R.string.daily_mix_six
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int, @StringRes val text: Int
)


@Preview
@Composable
fun MadeForComponentPreview(
    showBackground: Boolean = true, width: Int = 360, background: Color = Color.Black
) {

    SpotifyUICloneTheme {
        MadeForComponent(
            value = R.string.daily_mix_one, imageId = R.drawable.album_cover
        )
    }
}

@Preview
@Composable
fun MadeForRowPreview() {

    SpotifyUICloneTheme {
        MadeForRow()
    }
}


@Preview
@Composable
fun RecentlyPlayedRowPreview() {

    SpotifyUICloneTheme {
        RecentlyPlayed1Row()
    }
}


@Preview
@Composable
fun RecentlyPlayed1ComponentPreview() {

    SpotifyUICloneTheme {
        RecentlyPlayedComponent(value = R.string.drake, imageId = R.drawable.album_cover)
    }
}

@Preview
@Composable
fun TopBarElementPreview(
    showBackground: Boolean = true, width: Int = 360, background: Color = Color.Black
) {
    SpotifyUICloneTheme {
        TopBarElement()
    }

}

@Preview
@Composable
fun MusicSectionComponentPreview() {
    SpotifyUICloneTheme {
        MadeForComponent()
    }
}

@Preview
@Composable
fun YourTopMixesComponentPreview() {
    SpotifyUICloneTheme {
        YourTopMixesComponent()
    }
}

@Preview
@Composable
fun RecentlyPlayedComponentPreview() {
    SpotifyUICloneTheme {
        RecentlyPlayedComponent()
    }
}

@Preview
@Composable
fun AppNavBarPreview() {
    SpotifyUICloneTheme {
        BottomAppBarDefaults()
    }
}

