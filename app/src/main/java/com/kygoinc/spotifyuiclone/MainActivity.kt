package com.kygoinc.spotifyuiclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryAddCheck
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyGrey
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyUICloneTheme
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyWhite

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            SpotifyUICloneTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomAppBarDefaults(navController = navController)
                    }
                ) { paddingValues ->

                    MyAppScreen(
                        modifier = Modifier.padding(paddingValues.calculateBottomPadding()),
                        navController = navController
                    )

                }
            }
        }
    }
}
//Set up navigation


@Composable
fun MyAppScreen(modifier: Modifier = Modifier, navController: NavHostController) {


    NavHost(navController = navController, startDestination = "home_screen", builder = {
        composable("home_screen", content = { HomeScreen(navController = navController) })
        composable("your_library", content = { YourLibraryScreen(navController = navController) })
        composable("search_screen", content = { DefaultSearchScreen() })
    })
}


@Composable
fun BottomAppBarDefaults(modifier: Modifier = Modifier, navController: NavHostController) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color(0xF0141414),
    ) {
        BottomNavigationItem(selected = true,
            onClick = {
                navController.navigate("home_screen")
            },
            label = {
                Text(
                    text = "Home",
                    color = SpotifyGrey,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(weight = 500),
                        letterSpacing = 0.5.sp
                    )
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = SpotifyWhite,
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = SpotifyGrey
                )
            })
        BottomNavigationItem(selected = false,
            onClick = {
                navController.navigate("search_screen")
            },
            label = {
                Text(
                    text = "Search",
                    color = SpotifyGrey,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(weight = 500),
                        letterSpacing = 0.5.sp
                    )
                )
            },
            selectedContentColor = SpotifyWhite,
            unselectedContentColor = SpotifyGrey,
            icon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = SpotifyGrey
                )
            })
        BottomNavigationItem(selected = false,
            onClick = {
                navController.navigate("your_library")
            },
            label = {
                Text(
                    text = "Your Library",
                    color = SpotifyGrey,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(weight = 500),
                        letterSpacing = 0.5.sp
                    )
                )
            },
            selectedContentColor = SpotifyWhite,
            unselectedContentColor = SpotifyGrey,
            icon = {
                Icon(
                    Icons.Filled.LibraryAddCheck,
                    contentDescription = "Your Library",
                    tint = SpotifyGrey
                )
            })
    }
}

@Preview
@Composable
fun AppNavBarPreview() {
    SpotifyUICloneTheme {
        BottomAppBarDefaults(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpotifyUICloneTheme {
        MyAppScreen(navController = rememberNavController())
    }
}