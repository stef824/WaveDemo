package com.satan.wavedemo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/4/20.
 */

public class FreqGainCurveView extends View{
    private static final boolean DEBUG = true;

    private float mBorderSize;
    private int mBorderColor;
    private int mBgColor;
    private float mBgCornerRadius;
    private float mPadding;
    private float mXEndLength;
    private float mYEndLength;
    private float mHorDashGap;
    private float mVerDashGap;
    private int mTextColor;
    private float mTextSize;
    private int mLineColor;
    private int mCurveFillColor;
    private float mXTextPaddingTop;
    private float mYTextPaddingEnd;
    private float mXPegHeight;
    private float mYPegWidthLong;
    private float mYPegWidthShort;

    private TextPaint mTextPaint;
    private Paint mPaint;
    private Path mPath;
    private List<FreqGainEntry> mFeqGainEntries;
    public FreqGainCurveView(Context context) {
        this(context, null);
    }

    public FreqGainCurveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FreqGainCurveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        Resources res = getResources();
        TypedArray ta = res.obtainAttributes(attrs, R.styleable.FreqGainCurveView);
        mBorderSize = ta.getDimension(R.styleable.FreqGainCurveView_fgc_border_size, dp2px(4));
        mBorderColor = ta.getColor(R.styleable.FreqGainCurveView_fgc_border_color, Color.GRAY);
        mBgColor = ta.getColor(R.styleable.FreqGainCurveView_fgc_bg_color, Color.BLACK);
        mBgCornerRadius = ta.getDimension(R.styleable.FreqGainCurveView_fgc_bg_corner_radius, dp2px(4));
        mPadding = ta.getDimension(R.styleable.FreqGainCurveView_fgc_coordinate_padding, dp2px(4));
        mXEndLength = ta.getDimension(R.styleable.FreqGainCurveView_fgc_coordinate_x_end_length, dp2px(4));
        mYEndLength = ta.getDimension(R.styleable.FreqGainCurveView_fgc_coordinate_y_end_length, dp2px(4));
        mHorDashGap = ta.getDimension(R.styleable.FreqGainCurveView_fgc_coordinate_horizontal_dash_gap, dp2px(4));
        mVerDashGap = ta.getDimension(R.styleable.FreqGainCurveView_fgc_coordinate_vertical_dash_gap, dp2px(4));
        mTextColor = ta.getColor(R.styleable.FreqGainCurveView_fgc_text_color, Color.WHITE);
        mTextSize = ta.getDimension(R.styleable.FreqGainCurveView_fgc_text_size, sp2px(8));
        mLineColor = ta.getColor(R.styleable.FreqGainCurveView_fgc_line_color, 0xaaffffff);
        mCurveFillColor = ta.getColor(R.styleable.FreqGainCurveView_fgc_line_color, Color.CYAN);
        mXTextPaddingTop = ta.getDimension(R.styleable.FreqGainCurveView_fgc_x_text_padding_top, dp2px(4));
        mYTextPaddingEnd = ta.getDimension(R.styleable.FreqGainCurveView_fgc_y_text_padding_end, dp2px(4));
        mXPegHeight = ta.getDimension(R.styleable.FreqGainCurveView_fgc_x_peg_height, dp2px(4));
        mYPegWidthLong = ta.getDimension(R.styleable.FreqGainCurveView_fgc_y_peg_width_long, dp2px(4));
        mYPegWidthShort = ta.getDimension(R.styleable.FreqGainCurveView_fgc_y_peg_width_short, dp2px(2));

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPath = new Path();
        mPath.setFillType(Path.FillType.WINDING);
    }

    public void setFreqGainEntries(List<FreqGainEntry> points) {
        mFeqGainEntries = points;
        invalidate();
    }

    public List<FreqGainEntry> getFreqGainEntries() {
        return mFeqGainEntries;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //处理尺寸冲突



        //画边框

        //画背景

        //画竖线和横轴刻度

        //画横线和纵轴刻度

        //画纵轴文字

        //画横轴文字


        //画曲线
        if (mFeqGainEntries == null || mFeqGainEntries.isEmpty()) return;

        mPath.reset();

        final int xLength = getMeasuredWidth();
        final int yLength = getMeasuredHeight();
        final int centerY = yLength / 2;

        List<PointF> points = transform(mFeqGainEntries, xLength, yLength);
        if (DEBUG)
            debugGainValues(points);

        float lX = 0;
        float lY = 0;

        mPath.moveTo(points.get(0).x, points.get(0).y);
        for (int i = 1; i < points.size(); i++) {
            PointF currentPoint = points.get(i);

            PointF prePoint = points.get(i-1);
            float x1 = prePoint.x + lX;
            float y1 = prePoint.y + lY;

            PointF nexPoint = points.get(i+1 < points.size() ? i+1 : i);
            lX = (nexPoint.x - prePoint.x) / 2 * 0.4f;
            lY = (nexPoint.y - prePoint.y) / 2 * 0.4f;
            float x2 = currentPoint.x - lX;
            float y2 = currentPoint.y - lY;
            if (y1 == currentPoint.y) {
                y2 = y1;
            }

            mPath.cubicTo(x1, y1, x2, y2, currentPoint.x, currentPoint.y);
        }

        if (points.get(points.size() - 1).y != centerY){
            mPath.lineTo(points.get(points.size() - 1).x, centerY);
        }

        mPath.lineTo(points.get(0).x, centerY);
        mPath.lineTo(points.get(0).x, points.get(0).y);

        canvas.drawPath(mPath, mPaint);
    }

    private void debugGainValues(List<PointF> points) {
        Random random = new Random();

        for (PointF point : points){
            point.y = random.nextInt(getMeasuredHeight());
        }
    }


    private List<PointF> transform(List<FreqGainEntry> freqGainEntries, int xLength, int yLength){
        List<PointF> pointFs = new ArrayList<>();
        for (FreqGainEntry entry : freqGainEntries){
            pointFs.add(transform(entry, xLength, yLength));
        }
        return pointFs;
    }

    private PointF transform(FreqGainEntry freqGainEntry, int xLength, int yLength){
        return new PointF(xLength * CoordinateHelper.getRelativeX(freqGainEntry), yLength * CoordinateHelper.getRelativeY(freqGainEntry));
    }

    public int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int sp2px(float spValue) {
        float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
