package com.example.musicapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var isPlaying = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajuste de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- PLAY/PAUSE ---
        val playPauseButton: ImageButton = findViewById(R.id.playPauseButton)

        playPauseButton.setOnClickListener {
            isPlaying = !isPlaying
            if (isPlaying) {
                playPauseButton.setImageResource(R.drawable.ic_pause) // icono de pausa
            } else {
                playPauseButton.setImageResource(R.drawable.ic_play) // icono de play
            }
        }
    }
}
