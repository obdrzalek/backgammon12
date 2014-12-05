package com.arnostneurad.paralleluniv3rse.basicanimationframework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ParallelUniv3rse on 21/11/14.
 */
public class CustomView extends View {
    Bitmap image = null; // = BitmapFactory.decodeResource(getResources(), R.drawable.your_resource_id)
    ArrayList<DrawableObject> toDraw = new ArrayList<DrawableObject>(); // ArrayList of all DrawableObjects used to draw them onto canvas
    DrawableObject inHand = null;   // variable used for storing the object currently animated - has non-null value only between press and release of a animatable Object
    float coord_X,coord_Y;
    DrawableObject drawableobject = new DrawableObject(coord_X, coord_Y, image, this) { // This is how a DrawableObject is initialized
        @Override
        public void onPress() {

        }

        @Override
        public void onRelease() {
//            for (DrawableObject object: toDraw){
//                if (object.hasInteracted(inHand.getX1(), inHand.getY1())){
//                    // save the destination object
//                }
//            }
        }
    };

    public CustomView(Context context) {  // A CustomView constructor
        super(context);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(inHand == null){
                    for (DrawableObject object: toDraw){
                        if (object.hasInteracted(motionEvent.getX(), motionEvent.getY())){
                            inHand = object;
                            handleInteractionAction(motionEvent);
                            break;
                        }
                    }
                }else{
                    handleInteractionAction(motionEvent);
                }
                return true;
            }
        });
    }
    @Override
    public void onDraw(Canvas canvas) {  // method used to put ("draw") everything onto Canvas
        Paint p = new Paint();
        for (DrawableObject object: toDraw) {
            canvas.drawBitmap(object.getImage(), object.getX1(), object.getY1(), p);
        }
    }

    private void handleInteractionAction(MotionEvent event) {
        int eventAction = event.getAction();
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                inHand.onPress();
                break;
            case MotionEvent.ACTION_MOVE:
                if(inHand.isAnimatable()) {
                    inHand.setX(event.getX());
                    inHand.setY(event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
                inHand.onRelease();
                inHand = null;
        }
        invalidate();
    }
}
