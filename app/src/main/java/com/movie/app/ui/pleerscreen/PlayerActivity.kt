package com.movie.app.ui.pleerscreen

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.movie.app.R
import kotlinx.android.synthetic.main.custom_controller.*
import kotlinx.android.synthetic.main.fragment_player.*

class PlayerActivity : AppCompatActivity() {

    private lateinit var title: String
    private lateinit var url: String
    var flag: Boolean = false
    lateinit var exoPlayer: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arguments = intent.extras
        arguments!!
        url = arguments.getString(URL_KEY)
        setContentView(R.layout.fragment_player)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val video = Uri.parse(url)

        val loadControl = DefaultLoadControl()
        val bandwidthMeter = DefaultBandwidthMeter()
        val trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
        exoPlayer = ExoPlayerFactory.newSimpleInstance(
            this,
            trackSelector,
            loadControl
        )

        val dataFactory = DefaultHttpDataSourceFactory("MovieApp")
        val extractorFactory = DefaultExtractorsFactory()
        val mediaSource = ExtractorMediaSource
            .Factory(dataFactory)
            .createMediaSource(Uri.parse(url))
        player_view.player = exoPlayer
        player_view.keepScreenOn = true
        exoPlayer.prepare(mediaSource)
        exoPlayer.playWhenReady = true
        exoPlayer.addListener(object : Player.EventListener {
            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {

            }

            override fun onTracksChanged(
                trackGroups: TrackGroupArray,
                trackSelections: TrackSelectionArray
            ) {

            }

            override fun onLoadingChanged(isLoading: Boolean) {

            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_BUFFERING) {
                    progress_bar.visibility = View.VISIBLE
                } else
                    if (playbackState == Player.STATE_READY) {
                        progress_bar.visibility = View.GONE
                    }
            }

            override fun onRepeatModeChanged(repeatMode: Int) {

            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {

            }

            override fun onPlayerError(error: ExoPlaybackException) {

            }

            override fun onPositionDiscontinuity(reason: Int) {

            }

            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {

            }

            override fun onSeekProcessed() {

            }

        })

        button_fullscreen.setOnClickListener {
            if (flag) {
                button_fullscreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_fullscreen_24
                    )
                )
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                flag = false
            } else {
                button_fullscreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_fullscreen_exit_24
                    )
                )
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                flag = true
            }

        }

    }

    override fun onPause() {
        super.onPause()
        exoPlayer.playWhenReady = false
        exoPlayer.playbackState
    }

    override fun onRestart() {
        super.onRestart()
        exoPlayer.playWhenReady = true
        exoPlayer.playbackState
    }
}
