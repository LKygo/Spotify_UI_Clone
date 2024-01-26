package com.kygoinc.spotifyuiclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kygoinc.spotifyuiclone.ui.theme.SpotifyUICloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpotifyUICloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  YourLibraryScreen()
                }
            }
        }
    }
}

@Composable
fun MyAppScreen(modifier: Modifier = Modifier) {
   YourLibraryScreen()

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpotifyUICloneTheme {
       MyAppScreen()
    }
}