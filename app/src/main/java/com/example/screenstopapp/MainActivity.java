package main.java.com.example.screenstopapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar heightSlider;
    private TextView statusText;
    private Button saveButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        heightSlider = findViewById(R.id.height_slider);
        statusText = findViewById(R.id.status_text);
        saveButton = findViewById(R.id.save_button);

        sharedPreferences = getSharedPreferences("ScreenStopPrefs", MODE_PRIVATE);

        // Set up slider to adjust height
        heightSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int heightPercentage = 20 + progress; // 20% base
                statusText.setText("Current height: " + heightPercentage + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Save button to persist setting
        saveButton.setOnClickListener(v -> {
            int sliderValue = heightSlider.getProgress();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HeightPercentage", sliderValue);
            editor.apply();

            // Start or update the ScreenStopService
            Intent intent = new Intent(this, ScreenStopService.class);
            startService(intent);
        });
    }
}
