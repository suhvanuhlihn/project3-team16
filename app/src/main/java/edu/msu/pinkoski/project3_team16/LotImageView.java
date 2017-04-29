package edu.msu.pinkoski.project3_team16;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jevin on 4/29/17.
 */

public class LotImageView extends View {
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

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
