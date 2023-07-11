package com.insys.player.bitmovin.drm.java.drmplaybackjava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.bitmovin.player.PlayerView;
import com.bitmovin.player.api.Player;
import com.bitmovin.player.api.source.SourceConfig;
import com.bitmovin.player.api.drm.WidevineConfig;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private PlayerView playerView;
    private Player player;

    // URL of the media
    private static final String URL = "https://dtkya1w875897.cloudfront.net/da6dc30a-e52f-4af2-9751-000b89416a4e/assets/357577a1-3b61-43ae-9af5-82b9727e2f22/videokit-720p-dash-hls-drm/dash/index.mpd";

    // DRM license server URL
    private static final String DRM_LICENSE_URL = "https://insys-marketing.la.drm.cloud/acquire-license/widevine";

    // Custom HTTP headers for DRM requests
    private static final Map<String, String> httpHeaders = new HashMap<>();

    static {
        httpHeaders.put("x-drm-brandGuid", "da6dc30a-e52f-4af2-9751-000b89416a4e");
        httpHeaders.put("x-drm-userToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE4OTM0NTYwMDAsImRybVRva2VuSW5mbyI6eyJleHAiOiIyMDMwLTAxLTAxVDAwOjAwOjAwKzAwOjAwIiwia2lkIjpbIjFmODNhZTdmLTMwYzgtNGFkMC04MTcxLTI5NjZhMDFiNjU0NyJdLCJwIjp7InBlcnMiOmZhbHNlfX19.hElVqrfK-iLeV_ZleJJO8i-Mf1D2yYVXdtgBE0ja9R4");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.playerView);
        player = playerView.getPlayer();

        initializePlayer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        playerView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playerView.onResume();
    }

    @Override
    protected void onPause() {
        playerView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        playerView.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        playerView.onDestroy();
        super.onDestroy();
    }

    protected void initializePlayer() {
        SourceConfig sourceConfig = SourceConfig.fromUrl(URL);  // Create a new source config with the media URL
        WidevineConfig drmConfig = new WidevineConfig(DRM_LICENSE_URL);  // Create a Widevine DRM configuration with the license server URL

        drmConfig.setHttpHeaders(httpHeaders);  // Set the custom HTTP headers for DRM requests
        sourceConfig.setDrmConfig(drmConfig);  // Attach the DRM configuration to the source config

        player.load(sourceConfig);  // Load the media source into the player
    }
}
