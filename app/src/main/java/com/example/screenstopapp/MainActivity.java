import android.content.SharedPreferences;
SeekBar heightSlider = findViewById(R.id.height_slider);
Button saveButton = findViewById(R.id.save_button);

heightSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // Adjust height between 20% and 22%
        int heightPercentage = 20 + progress; // Assuming max is set to 20
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }
});
// Inside your MainActivity class
SharedPreferences sharedPreferences = getSharedPreferences("ScreenStopPrefs", MODE_PRIVATE);
Button saveButton = findViewById(R.id.save_button);

saveButton.setOnClickListener(v -> {
    int sliderValue = heightSlider.getProgress();
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt("HeightPercentage", sliderValue);
    editor.apply();

    // Apply the screen overlay change
    // Call a method to update the ScreenStopService with the new height
});