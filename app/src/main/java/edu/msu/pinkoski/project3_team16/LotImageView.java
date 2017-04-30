package edu.msu.pinkoski.project3_team16;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jevin on 4/29/17.
 */

public class LotImageView extends View {
    // first to draw
    public boolean firstDraw = true;

    // grid minDim
    public int minDim = 0;

    final static float SCALE_IN_VIEW = 0.85f;

    int imgID;

    public transient Bitmap img = null;

    public LotImageView(Context context) {
        super(context);
        init(null, 0);
    }

    public LotImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LotImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
    }

    public void SetImg(int imgID) {
        img = BitmapFactory.decodeResource(getContext().getResources(), imgID);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (img == null) {
            return;
        }
        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        if(firstDraw) {
            // Determine the minimum of the two dimensions
            minDim = wid < hit ? wid : hit;
            firstDraw = false;
        }

        float imgWid = img.getWidth();
        float imgHit = img.getHeight();

        float scaleFactor = (float)minDim / imgWid;

        float marginX = (wid - imgWid * scaleFactor) / 2;
        float marginY = (hit - imgHit * scaleFactor) / 2;

        canvas.save();

        // Convert x,y to pixels and add the margin, then draw
        canvas.translate(marginX, marginY);

        // Scale it to the right size
        canvas.scale(scaleFactor, scaleFactor);

        // This magic code makes the center of the piece at 0, 0
        canvas.translate(0, 0);

        // Draw the bitmap
        canvas.drawBitmap(img, 0, 0, null);
        canvas.restore();

    }
}
