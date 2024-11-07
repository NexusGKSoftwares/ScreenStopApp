import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class ScreenStopService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // No need to handle specific accessibility events
    }

    @Override
    public void onInterrupt() {
        // Handle service interruption
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        // Set up overlay to cover the bottom 20-22% of the screen
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        FrameLayout overlay = new FrameLayout(this);

        // Configure layout parameters to position the overlay
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            calculateOverlayHeight(), // Method to calculate 20-22% height
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        );

        // Add the overlay to the window
        windowManager.addView(overlay, params);
    }

    private int calculateOverlayHeight() {
        // Logic to calculate the height for 20-22% of the screen
        // Use screen height and the percentage set by the slider
        return (int) (getResources().getDisplayMetrics().heightPixels * 0.20);
    }
}

