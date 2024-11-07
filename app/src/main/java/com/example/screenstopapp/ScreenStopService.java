package main.java.com.example.screenstopapp;

import android.accessibilityservice.AccessibilityService;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;

public class ScreenStopService extends AccessibilityService {

    private WindowManager windowManager;
    private FrameLayout overlay;
    private SharedPreferences sharedPreferences;

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("ScreenStopPrefs", MODE_PRIVATE);
        int savedHeightPercentage = 20 + sharedPreferences.getInt("HeightPercentage", 0);

        // Setup overlay
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        overlay = new FrameLayout(this);
        
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            (int) (getResources().getDisplayMetrics().heightPixels * (savedHeightPercentage / 100.0)),
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        );

        windowManager.addView(overlay, params);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Not needed for this app
    }

    @Override
    public void onInterrupt() {}

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlay != null) {
            windowManager.removeView(overlay);
        }
    }
}
