package com.example.audiorecorder

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.audiorecorder.playback.AndroidAudioPlayer
import com.example.audiorecorder.recorder.AndroidAudioRecorder
import com.example.audiorecorder.ui.theme.AudioRecorderTheme
import java.io.File

class MainActivity : ComponentActivity() {


    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private var audioFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)

        setContent {
            AudioRecorderTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        File(cacheDir, "audio.mp3").also {
                            recorder.start(it)
                            audioFile = it
                        }

                    }) {
                        Text(text = "Start Recording")
                    }

                    // Stop Recording
                    Button(onClick = {
                        recorder.stop()

                    }) {
                        Text(text = "Stop Recording")
                    }

                    // Stop Recording
                    Button(onClick = {
                        player.playFile(audioFile ?: return@Button)

                    }) {
                        Text(text = "Play Recording")
                    }

                    // Stop Recording
                    Button(onClick = {
                        player.stop()

                    }) {
                        Text(text = "Stop Playing")
                    }

                }
            }
        }
    }
}

