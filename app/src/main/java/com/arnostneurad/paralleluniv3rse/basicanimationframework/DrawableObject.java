package com.arnostneurad.paralleluniv3rse.basicanimationframework;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by ParallelUniv3rse on 28/11/14.
 */
public abstract class DrawableObject implements Drawable, Interactable {

    float X1,X2,Y1,Y2;
    Bitmap image;
    boolean animatable = false;
    View view;
    public DrawableObject(float X, float Y, Bitmap image, View view){
        this.setX(X);
        this.setY(Y);
        this.image = image;
        this.view = view;
    }

    @Override
    public boolean hasInteracted(float touchX, float touchY) {
        if(isTouchInX(touchX)&&isTouchInY(touchY)){
            return true;
        }else{
            return false;
        }
    }

    public abstract void onPress();

    public abstract void onRelease();

    public void setY(float y) {
        Y1 = y;
        Y2 = y + image.getHeight();
    }

    public void setX(float x) {
        X1 = x;
        X2 = x + image.getWidth();
    }

    @Override
    public float getX1() {
        return this.X1;
    }

    @Override
    public float getX2() {
        return this.X2;
    }

    @Override
    public float getY1() {
        return this.Y1;
    }

    @Override
    public float getY2() {
        return this.Y2;
    }

    public Bitmap getImage() {
        return this.image;
    }

    public boolean isAnimatable() {
        return this.animatable;
    }

    public void setAnimatable(boolean animatable) {
        this.animatable = animatable;
    }

    private boolean isTouchInX(float touchX){
        if((this.getX1() < touchX)&&(this.getX2() > touchX)){
            return true;
        }else {
            return false;
        }
    }

    private boolean isTouchInY(float touchY){
        if((this.getY1() < touchY)&&(this.getY2() > touchY)){
            return true;
        }else {
            return false;
        }
    }
}
