package ipca.pong.a14875;

import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;

import ipca.pong.a14875.Classes.Ball;

public class MainActivity extends AppCompatActivity {
    private BallView ballView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        ballView = new BallView(this, size.x, size.y);
        setContentView(ballView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ballView.resume();
    }
}
