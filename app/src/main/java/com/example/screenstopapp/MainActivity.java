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

saveButton.setOnClickListener(v -> {
    // Save the setting and apply screen lock logic
});

