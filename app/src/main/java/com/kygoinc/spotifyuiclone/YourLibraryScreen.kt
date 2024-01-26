package com.kygoinc.spotifyuiclone

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyBlack
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyGrey
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyUICloneTheme
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyWhite


@Composable
fun YourLibraryScreen() {

    SpotifyUICloneTheme {

        YourLibraryScreenComplete()


    }
}

@Composable
fun YourLibraryScreenComplete(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.background(SpotifyBlack)

    ) {
        SearchTopBarElement()
//        MiddleBarElement()
        LibraryItemColumn()
    }
}


@Composable
fun SearchTopBarElement() {
    Column(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp, start = 18.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_me),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .size(40.dp)
            )


            Text(
                text = "Your Library",
                style = TextStyle(
                    fontSize = 24.sp, fontWeight = FontWeight.SemiBold
                ),
                color = SpotifyWhite,
                modifier = Modifier
                    .weight(6f)
                    .padding(start = 16.dp),

                )

            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search",
                tint = SpotifyWhite,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add",
                tint = SpotifyWhite,
                modifier = Modifier.weight(1f)
            )
        }
        val chipItems = listOf("Playlists", "Artists", "Albums", "Podcasts & Shows")

        ChipsElement(chipItems)
    }
}


@Composable
fun MiddleBarElement(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.SwapVert,
            contentDescription = "Notifications",
            tint = SpotifyWhite,
            modifier = Modifier
        )
        Text(
            text = "Recents", style = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight.SemiBold
            ), color = SpotifyWhite, modifier = Modifier
                .weight(0.8f)
                .padding(start = 8.dp)

        )

        Icon(
            imageVector = Icons.Outlined.GridView,
            contentDescription = "Grid",
            tint = SpotifyWhite,
            modifier = Modifier
                .weight(0.1f)
                .padding(10.dp)
        )
    }
}

@Composable
fun LibraryItems(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    @StringRes title: Int,
    @StringRes value: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 70.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(70.dp),
        )
        Column(
            modifier = Modifier.heightIn(max = 40.dp)
        ) {
            Text(
                text = stringResource(id = title),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(weight = 500),
                    letterSpacing = 0.5.sp,
                ),
                color = SpotifyWhite,
                modifier = Modifier
                    .weight(6f)
                    .padding(start = 16.dp),

                )
            Text(
                text = stringResource(id = value),
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight(weight = 400),
                    letterSpacing = 0.5.sp,
                    color = SpotifyGrey
                ),

                modifier = Modifier
                    .weight(6f)
                    .padding(start = 16.dp, top = 4.dp),

                )
        }
    }
}

@Composable
fun LibraryItemColumn() {

    MiddleBarElement()

    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(start = 16.dp, end = 15.dp, top = 14.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        items(yourLibraryData.size) { index ->
            LibraryItems(
                imageId = yourLibraryData[index].drawable,
                title = yourLibraryData[index].label,
                value = yourLibraryData[index].value
            )
        }

    }
}


private val yourLibraryData = listOf(
    DrawableStringStringGroup(
        R.drawable.liked_songs, R.string.library_one, R.string.library_one_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_two, R.string.library_two, R.string.library_two_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_three, R.string.library_three, R.string.library_three_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_four, R.string.library_four, R.string.library_four_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_five, R.string.library_five, R.string.library_five_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_six, R.string.library_six, R.string.library_six_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_seven, R.string.library_seven, R.string.library_seven_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_eight, R.string.library_eight, R.string.library_eight_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_nine, R.string.library_nine, R.string.library_nine_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_ten, R.string.library_ten, R.string.library_ten_value
    ),
    DrawableStringStringGroup(
        R.drawable.library_item_eleven, R.string.library_eleven, R.string.library_eleven_value
    ),

    ).map { DrawableStringStringGroup(it.drawable, it.label, it.value) }


private data class DrawableStringStringGroup(
    @DrawableRes val drawable: Int, @StringRes val label: Int, @StringRes val value: Int
)


@Preview
@Composable
private fun SearchTopBarPreiew() {
    SpotifyUICloneTheme {
        SearchTopBarElement()
    }
}

@Preview
@Composable
private fun MiddleBarPreview() {
    SpotifyUICloneTheme {
        MiddleBarElement()
    }
}

@Preview
@Composable
private fun SearchItemsPreview() {
    SpotifyUICloneTheme {
        LibraryItems(
            imageId = R.drawable.liked_songs,
            title = R.string.library_one,
            value = R.string.library_one_value
        )
    }
}

@Preview
@Composable
private fun SearchItemsColumnPreview() {
    SpotifyUICloneTheme {
        LibraryItemColumn()
    }
}


@Preview(showBackground = true)
@Composable
fun YourLibraryScreenPreview() {
    SpotifyUICloneTheme {
        YourLibraryScreenComplete()
    }
}