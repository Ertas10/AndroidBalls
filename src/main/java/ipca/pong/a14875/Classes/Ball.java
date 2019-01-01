package ipca.pong.a14875.Classes;

import android.graphics.Bitmap;

public class Ball {
    float x;
    float y;
    float velX;
    float velY;
    float colRadius;
    float speed = 10;
    Bitmap bitmap;

    public Ball(float x, float y, float velX, float velY, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.bitmap = bitmap;
        colRadius = bitmap.getHeight() / 2;
    }

    public void Update(float maxX, float maxY){
        x += velX * speed;
        y += velY * speed;

        if(x < 0 || x > maxX)
            velX = -velX;
        if(y < 0 || y > maxY)
            velY = -velY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getColRadius() {
        return colRadius;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
