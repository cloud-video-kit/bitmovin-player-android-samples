package com.insys.player.bitmovin.drm.kotlin.drmplaybackkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bitmovin.player.api.Player
import com.bitmovin.player.api.source.SourceConfig
import com.bitmovin.player.api.drm.WidevineConfig
import com.insys.player.bitmovin.drm.kotlin.drmplaybackkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player = binding.playerView.player
        initializePlayer()
    }

    override fun onStart() {
        super.onStart()
        binding.playerView.onStart() // Start the player view
    }

    override fun onResume() {
        super.onResume()
        binding.playerView.onResume() // Resume the player view
    }

    override fun onPause() {
        binding.playerView.onPause() // Pause the player view
        super.onPause()
    }

    override fun onStop() {
        binding.playerView.onStop() // Stop the player view
        super.onStop()
    }

    override fun onDestroy() {
        binding.playerView.onDestroy() // Destroy the player view
        super.onDestroy()
    }

    private fun initializePlayer() {
        val sourceConfig = SourceConfig.fromUrl(URL) // Create a source configuration with the media URL
        val drmConfig = WidevineConfig(DRM_LICENSE_URL) // Create a Widevine DRM configuration with the license URL and headers

        drmConfig.httpHeaders = httpHeaders
        sourceConfig.drmConfig = drmConfig // Set the DRM configuration for the source

        player?.load(sourceConfig) // Load the source into the player
    }

    companion object {
        // URL of the media
        private const val URL =
            "https://dtkya1w875897.cloudfront.net/da6dc30a-e52f-4af2-9751-000b89416a4e/assets/357577a1-3b61-43ae-9af5-82b9727e2f22/videokit-720p-dash-hls-drm/dash/index.mpd"

        // DRM license server URL
        private const val DRM_LICENSE_URL =
            "https://insys-marketing.la.drm.cloud/acquire-license/widevine"

        // Custom HTTP headers for DRM requests
        private val httpHeaders: MutableMap<String?, String?> = mutableMapOf(
            "x-drm-brandGuid" to "da6dc30a-e52f-4af2-9751-000b89416a4e",
            "x-drm-usertoken" to "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE4OTM0NTYwMDAsImRybVRva2VuSW5mbyI6eyJleHAiOiIyMDMwLTAxLTAxVDAwOjAwOjAwKzAwOjAwIiwia2lkIjpbIjFmODNhZTdmLTMwYzgtNGFkMC04MTcxLTI5NjZhMDFiNjU0NyJdLCJwIjp7InBlcnMiOmZhbHNlfX19.hElVqrfK-iLeV_ZleJJO8i-Mf1D2yYVXdtgBE0ja9R4",
        )
    }
}