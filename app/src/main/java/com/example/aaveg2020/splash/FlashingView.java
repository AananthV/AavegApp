package com.example.aaveg2020.splash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class FlashingView extends View {

    Paint flashPaint;
    int alpha;
    int flag;
    RemoveFlashView rm;

    public FlashingView(Context context, RemoveFlashView rm) {
        super(context);

        this.rm=rm;
        alpha=0;
        flashPaint=new Paint();
        flashPaint.setColor(Color.parseColor("#FFFFFF"));
        flashPaint.setAlpha(alpha);
        flag=0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,getWidth(),getBottom(),flashPaint);

        if(alpha<=252 && flag==0) {
            alpha+=2;
        }
        else if(alpha>=2) {
            alpha-=2;
            flag=1;
        }
        else {
            rm.removeFlashView();
        }

        System.out.println("Value of alpha - "+alpha);
        flashPaint.setAlpha(alpha);
        invalidate();
    }
}
