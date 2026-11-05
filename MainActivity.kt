package com.skardu.mountainracing

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.skardu.mountainracing.audio.AndroidSoundEngine
import com.skardu.mountainracing.data.*
import com.skardu.mountainracing.game.SplineTrack
import com.skardu.mountainracing.physics.VehiclePhysics
import com.skardu.mountainracing.renderer.GameRenderer
import com.skardu.mountainracing.ui.components.GameHUD
import com.skardu.mountainracing.ui.components.TouchControls
import com.skardu.mountainracing.ui.screens.MainMenuScreen
import com.skardu.mountainracing.ui.theme.SkarduRacingTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private lateinit var soundEngine: AndroidSoundEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        insetsController.hide(WindowInsetsCompat.Type.systemBars())

        soundEngine = AndroidSoundEngine(this)
        soundEngine.start()

        setContent {
            SkarduRacingTheme {
                SkarduRacingApp(soundEngine = soundEngine)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundEngine.stop()
    }
}
