package com.example.musicapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.ui.theme.MusicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                MusicPlayerUI()
            }
        }
    }
}

@Composable
fun MusicPlayerUI() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        MusicPlayerLandscape()
    } else {
        MusicPlayerPortrait()
    }
}

@Composable
fun MusicPlayerPortrait() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.album),
            contentDescription = "Album Cover",
            modifier = Modifier
                .size(263.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Finales de Agosto", color = Color.White, fontSize = 20.sp)
        Text("Saiko", color = Color(0xFFAAAAAA), fontSize = 16.sp)

        Spacer(modifier = Modifier.height(24.dp))

        var progress by remember { mutableStateOf(0.25f) }

        Slider(
            value = progress,
            onValueChange = { progress = it },
            modifier = Modifier.fillMaxWidth(0.95f),
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF1DB954),
                activeTrackColor = Color(0xFF1DB954)
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(0.95f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("0:45", color = Color(0xFFAAAAAA), fontSize = 12.sp)
            Text("2:55", color = Color(0xFFAAAAAA), fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        PlaybackControls()

        Spacer(modifier = Modifier.height(16.dp))

        VolumeControl()

        Spacer(modifier = Modifier.height(24.dp))

        ExtraButtons()
    }
}

@Composable
fun MusicPlayerLandscape() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.album),
                contentDescription = "Album Cover",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Finales de Agosto", color = Color.White, fontSize = 20.sp)
            Text("Saiko", color = Color(0xFFAAAAAA), fontSize = 16.sp)

            Spacer(modifier = Modifier.height(12.dp))

            var progress by remember { mutableStateOf(0.25f) }

            Slider(
                value = progress,
                onValueChange = { progress = it },
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF1DB954),
                    activeTrackColor = Color(0xFF1DB954)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("0:45", color = Color(0xFFAAAAAA), fontSize = 12.sp)
                Text("2:55", color = Color(0xFFAAAAAA), fontSize = 12.sp)
            }
        }

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PlaybackControls()

            Spacer(modifier = Modifier.height(24.dp))

            VolumeControl()

            Spacer(modifier = Modifier.height(24.dp))

            ExtraButtons()
        }
    }
}

@Composable
fun PlaybackControls() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerButton(icon = R.drawable.ic_prev, size = 70.dp)
        Spacer(modifier = Modifier.width(16.dp))
        PlayerButton(icon = R.drawable.ic_play, size = 90.dp)
        Spacer(modifier = Modifier.width(16.dp))
        PlayerButton(icon = R.drawable.ic_next, size = 70.dp)
    }
}

@Composable
fun VolumeControl() {
    var volume by remember { mutableStateOf(0.7f) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(0.95f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_volume),
            contentDescription = "Volume",
            modifier = Modifier.size(30.dp)
        )

        Slider(
            value = volume,
            onValueChange = { volume = it },
            modifier = Modifier.weight(1f),
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF1DB954),
                activeTrackColor = Color(0xFF1DB954)
            )
        )
    }
}

@Composable
fun ExtraButtons() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExtraButton(icon = R.drawable.ic_share)
        Spacer(modifier = Modifier.width(16.dp))
        ExtraButton(icon = R.drawable.ic_lyrics)
        Spacer(modifier = Modifier.width(16.dp))
        ExtraButton(icon = R.drawable.ic_queue)
    }
}

@Composable
fun PlayerButton(icon: Int, size: Dp) {
    Card(
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)),
        modifier = Modifier.size(size)
    ) {
        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(size * 0.5f)
            )
        }
    }
}

@Composable
fun ExtraButton(icon: Int) {
    Card(
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)),
        modifier = Modifier.size(56.dp)
    ) {
        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PortraitPreview() {
    MusicAppTheme {
        MusicPlayerPortrait()
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 400)
@Composable
fun LandscapePreview() {
    MusicAppTheme {
        MusicPlayerLandscape()
    }
}
