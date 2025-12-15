package com.anbui.open.sk.androidxmedia3;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.media3.common.*;
import androidx.media3.exoplayer.*;
import com.anbui.open.sk.androidxmedia3.databinding.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private MainBinding binding;
	private ExoPlayer exoPlayer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		EdgeToEdge.enable(this);
		binding = MainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id._main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout() | WindowInsetsCompat.Type.ime());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		binding.materialbutton1.setOnClickListener(_v -> {
			exoPlayer = new ExoPlayer.Builder(MainActivity.this).build();
			binding.playerView.setPlayer(exoPlayer);
			
			MediaItem mediaItem = MediaItem.fromUri(Uri.parse(binding.edittext1.getText().toString()));
			
			exoPlayer.setMediaItem(mediaItem);
			exoPlayer.prepare();
			exoPlayer.addListener(new Player.Listener() {
				@Override
				public void onEvents(Player player, Player.Events events) {
					
				}
				
				@Override
				public void onPlaybackStateChanged(int state) {
					if (state == Player.STATE_READY) {
						
						Log.d("ExoPlayer", "Ready to play.");
					} else if (state == Player.STATE_BUFFERING) {
						Log.d("ExoPlayer", "Loading video...");
					} else if (state == Player.STATE_ENDED) {
						Log.d("ExoPlayer", "Video finished playing.");
					}
				}
				
				@Override
				public void onIsPlayingChanged(boolean isPlaying) {
					if (isPlaying) {
						
						Log.d("ExoPlayer", "Playing.");
					} else {
						
						Log.d("ExoPlayer", "Pausing.");
					}
				}
				
				@Override
				public void onPlayerError(PlaybackException error) {
					
				}
				
			});
			
		});
	}
	
	private void initializeLogic() {
	}
	
}