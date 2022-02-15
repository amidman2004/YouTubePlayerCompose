package com.example.youtubevideocompose

import android.annotation.SuppressLint
import android.media.MediaParser
import android.media.MediaPlayer
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.NonNull
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.youtubevideocompose.ui.theme.YoutubeVideoComposeTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
class MainActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubeVideoComposeTheme {

                // A surface container using the 'background' color from the theme
                ComposeYoutubePlayer("gXWXKjR-qII")

            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YoutubeVideoComposeTheme {
        Greeting("Android")
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ComposeYoutubePlayer(videoId:String) {

    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val youtubePlayerView = remember {
        YouTubePlayerView(context).apply {
            id = R.id.third_party_player_view
        }
    }

//    DisposableEffect(key1 = lifecycle){
//        lifecycle.addObserver(youtubePlayerView)
//        onDispose {
//            lifecycle.removeObserver(youtubePlayerView)
//        }
//    }
    youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
            youTubePlayer.loadOrCueVideo(
                lifecycle = lifecycle,
                videoId = videoId,
                startSeconds = 0f
            )
        }
    })
    Box(){
        AndroidView(factory = {
            youtubePlayerView
        })
    }

}

