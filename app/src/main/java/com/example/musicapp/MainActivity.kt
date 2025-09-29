package com.example.musicapp
// Compose eta Android import-ak
import androidx.compose.ui.tooling.preview.Devices
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.ui.theme.MusicAppTheme

// Musika erreproduzitzailea estilo Spotify berdearekin
val spotifyGreen = Color(0xFF1DB954)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pantaila ertzetatik ertzera (edge to edge) erabiltzeko
        enableEdgeToEdge()

        // Composable funtzio nagusia ezartzen da
        setContent {
            MusicAppTheme {
                MusicPlayerUI() // Musika erreproduzitzailearen UI nagusia
            }
        }
    }
}

// UI nagusia: paisaia edo bertikala detektatzen du
@Composable
fun MusicPlayerUI() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    // Paisaia bada MusicPlayerLandscape, bestela MusicPlayerPortrait erakutsi
    if (isLandscape) MusicPlayerLandscape()
    else MusicPlayerPortrait()
}

@Composable
fun MusicPlayerPortrait() {
    // VERTIKAL moduko musika erreproduzitzailearen UI
    Column(
        modifier = Modifier
            .fillMaxSize() // Pantaila osoa betetzen du
            .background(Color.Black) // Atzeko kolorea beltza
            .padding(16.dp), // Barruko tartea
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Goitik hasita jarri elementuak
    ) {
        Spacer(modifier = Modifier.height(40.dp)) // Goiko tartea

        // Albumaren azala
        Image(
            painter = painterResource(id = R.drawable.album),
            contentDescription = "Album Cover", // Accessibility
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f) // Karratua izan dadin
                .clip(RoundedCornerShape(16.dp)), // Burezurrak biribilduak
            contentScale = ContentScale.Crop // Irudia moztu gabe pantailan egokitu
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Kantuaren izena eta artistaren izena
        Text("Lo Mismo De Siempre", color = Color.White, fontSize = 20.sp)
        Text("Mora", color = Color(0xFFAAAAAA), fontSize = 16.sp)

        Spacer(modifier = Modifier.height(24.dp))

        // Kantuaren aurrerapeneko slider
        var progress by remember { mutableStateOf(0.25f) }

        Slider(
            value = progress,
            onValueChange = { progress = it },
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(15.dp),
            colors = SliderDefaults.colors(
                thumbColor = spotifyGreen,
                activeTrackColor = spotifyGreen,
                inactiveTrackColor = Color(0xFF555555)
            )
        )

        // Denbora erakusteko testuak
        Row(
            modifier = Modifier.fillMaxWidth(0.95f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("0:45", color = Color(0xFFAAAAAA), fontSize = 12.sp)
            Text("2:55", color = Color(0xFFAAAAAA), fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Joko botoiak: play, pause, prev, next
        PlaybackControls()

        Spacer(modifier = Modifier.height(16.dp))

        // Bolumen kontrola
        var volume by remember { mutableStateOf(0.7f) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start, // Hasieratik jarri
            modifier = Modifier.fillMaxWidth()
        ) {
            // Volumen ikonoa con círculo gris detrás
            Card(
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)),
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_volume),
                    contentDescription = "Volume",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Slider gainerako espazioa hartzen du
            Slider(
                value = volume,
                onValueChange = { volume = it },
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp),
                colors = SliderDefaults.colors(
                    thumbColor = spotifyGreen,
                    activeTrackColor = spotifyGreen,
                    inactiveTrackColor = Color(0xFF555555)
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Extra botoiak: share, lyrics, queue
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
        // Ezkerreko zutabea: albumaren azala eta abestiaren informazioa
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.album),
                contentDescription = "Album Cover",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.65f)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Lo Mismo De Siempre", color = Color.White, fontSize = 20.sp)
            Text("Mora", color = Color(0xFFAAAAAA), fontSize = 16.sp)

            Spacer(modifier = Modifier.height(16.dp))

            var progress by remember { mutableStateOf(0.25f) }

            Slider(
                value = progress,
                onValueChange = { progress = it },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(15.dp),
                colors = SliderDefaults.colors(
                    thumbColor = spotifyGreen,
                    activeTrackColor = spotifyGreen,
                    inactiveTrackColor = Color(0xFF555555)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("0:45", color = Color(0xFFAAAAAA), fontSize = 12.sp)
                Text("2:55", color = Color(0xFFAAAAAA), fontSize = 12.sp)
            }
        }

        // Eskubiko zutabea: kontrolak eta bolumena
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PlaybackControls()

            Spacer(modifier = Modifier.height(24.dp))

            var volume by remember { mutableStateOf(0.7f) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.95f)
            ) {
                Card(
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)),
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_volume),
                        contentDescription = "Volume",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxSize()
                    )
                }

                Slider(
                    value = volume,
                    onValueChange = { volume = it },
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = spotifyGreen,
                        activeTrackColor = spotifyGreen,
                        inactiveTrackColor = Color(0xFF555555)
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            ExtraButtons()
        }
    }
}



@Composable
fun PlaybackControls() {
    // Play/pause egoera gordetzeko aldagaia
    var isPlaying by remember { mutableStateOf(false) }

    // Kontrolen lerroa: aurrekoa, play/pause, hurrengoa
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Aurreko botoia
        PlayerButton(icon = R.drawable.ic_prev, size = 70.dp, onClick = { /* TODO */ })

        Spacer(modifier = Modifier.width(16.dp))

        // Play edo Pause botoia, egoeraren arabera aldatzen da
        PlayerButton(
            icon = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play,
            size = 90.dp,
            onClick = { isPlaying = !isPlaying } // Egoera aldatu
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Hurrengo botoia
        PlayerButton(icon = R.drawable.ic_next, size = 70.dp, onClick = { /* TODO */ })
    }
}


@Composable
fun PlayerButton(icon: Int, size: Dp, onClick: () -> Unit) {
    // Zirkulu formako botoia
    Card(
        shape = CircleShape, // Botoia zirkulua da
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp), // Itzala
        colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)), // Atzeko kolorea
        modifier = Modifier.size(size) // Botoiaren tamaina
    ) {
        // Klik egiteko botoia
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize() // Espazioa guztiz betetzen du
        ) {
            // Ikonoa botoiaren barruan
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White, // Ikonoaren kolorea
                modifier = Modifier.size(size * 0.5f) // Ikonoaren tamaina botoiaren erdia
            )
        }
    }
}


@Composable
fun ExtraButtons() {
    // Botoi osagarriak lerro batean: share, lyrics, queue
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExtraButton(icon = R.drawable.ic_share)   // Partekatzeko botoia
        Spacer(modifier = Modifier.width(16.dp))
        ExtraButton(icon = R.drawable.ic_lyrics)  // Letren botoia
        Spacer(modifier = Modifier.width(16.dp))
        ExtraButton(icon = R.drawable.ic_queue)   // Hurrengo abestiak ikusteko botoia
    }
}


@Composable
fun ExtraButton(icon: Int) {
    // Zirkulu formako botoi osagarria
    Card(
        shape = CircleShape, // Botoia zirkulua da
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Itzala
        colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)), // Atzeko kolorea
        modifier = Modifier.size(56.dp) // Tamaina finkoa
    ) {
        // Klik egiteko botoia
        IconButton(
            onClick = { /* TODO */ }, // Ekintza hurrengoa gehitu daiteke
            modifier = Modifier.fillMaxSize() // Botoiak espazioa guztiz betetzen du
        ) {
            // Ikonoa botoiaren barruan
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White // Ikonoaren kolorea
            )
        }
    }
}


@Preview(showBackground = true, name = "Portrait Mode", heightDp = 800)
@Composable
fun PortraitPreview() {
    // Portrait moduko aurrebista
    MusicAppTheme {
        MusicPlayerPortrait()
    }
}

@Preview(
    showBackground = true,
    name = "Landscape Mode",
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 800,
    heightDp = 400
)
@Composable
fun LandscapePreview() {
    // Landscape moduko aurrebista
    MusicAppTheme {
        MusicPlayerLandscape()
    }
}

