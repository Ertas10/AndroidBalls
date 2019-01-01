package ipca.pong.a14875;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ipca.pong.a14875.Classes.Ball;

public class BallView extends SurfaceView implements Runnable {
    private Paint paint;
    private Canvas canvas;
    SurfaceHolder surfaceHolder;
    Random rand;
    Bitmap ballBitmap;
    private List<Ball> balls = new ArrayList<Ball>();
    private List<Ball> deadBalls = new ArrayList<Ball>();
    Thread gameThread = null;
    Boolean playing = true;
    float xMax;
    float yMax;

    public BallView(Context context, float x, float y) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        ballBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        rand = new Random();
        xMax = x;
        yMax = y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                int posX = rand.nextInt(2);
                int posY = rand.nextInt(2);
                float velX = rand.nextFloat();
                float velY = rand.nextFloat();
                if(posX == 1)
                    velX = -velX;
                if(posY == 1)
                    velY = -velY;
                balls.add(new Ball(event.getX(), event.getY(), velX, velY, ballBitmap));
                break;
        }
        return true;
    }


    @Override
    public void run() {
        while(playing) {
            update();
            draw();
            control();
        }
    }

    private void update(){
        for(Ball b : balls){
            b.Update(xMax, yMax);
        }
        for(int i = 0; i < balls.size(); i++){
            for(int j = 0; j < balls.size(); j++){
                Ball b1 = balls.get(i);
                Ball b2 = balls.get(j);
                if(b1 != b2){
                    float b1x = b1.getX();
                    float b2x = b2.getX();
                    float b1y = b1.getY();
                    float b2y = b2.getY();
                    float dist = (float)Math.sqrt(Math.pow((b1x - b2x), 2) + Math.pow((b1y - b2y), 2));
                    if(dist < b1.getColRadius() + b2.getColRadius()){
                        deadBalls.add(b1);
                        deadBalls.add(b2);
                    }
                }
            }
            for(Ball b : deadBalls){
                balls.remove(b);
            }
            deadBalls.clear();
        }
    }

    private void draw(){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();

            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);
            for(Ball b: balls){
                canvas.drawBitmap(b.getBitmap(), b.getX(), b.getY(), paint);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control(){
        try {
            gameThread.sleep(17);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void resume(){
        gameThread = new Thread(this);
        gameThread.start();
    }
}
