package nickita.gq.ndock;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by admin on 27/6/17.
 */
public class NDock extends RelativeLayout {
    private ViewGroup target;
    private RelativeLayout panel;
    float dX, dY;
    int resetHeight;
    boolean openMode = true;

    public void install(ViewGroup viewGroup) {
        setVisibility(GONE);
        this.target = viewGroup;
        viewGroup.addView(this);
        panel = (RelativeLayout) findViewById(R.id.panel);
        panel.setX(target.getLayoutParams().width / 2);
        resetHeight = panel.getLayoutParams().height;
    }

    public void pop() {
        openMode = true;
        setVisibility(VISIBLE);
        this.animate()
                .y(0)
                .setDuration(150)
                .start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = this.getX() - event.getRawX();
                dY = this.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getRawY() + dY < (event.getRawY() + dY) + (event.getRawY() + dY) * 0.8) {
                    if (event.getRawY() + dY + (event.getRawY() + dY) * 0.3 < panel.getLayoutParams().height) {
                        this.animate()
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                    } else {
                        openMode = false;
                        this.animate().setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {
                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                if (openMode) {
                                    setVisibility(VISIBLE);
                                } else {
                                    setVisibility(GONE);
                                }
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {
                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {
                            }
                        })
                                .y(resetHeight)
                                .setDuration(150)
                                .start();
                    }
                }
                break;
            default:
                return false;
        }
        return true;
    }

    public NDock(Context context) {
        super(context);
        init();
    }

    public NDock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NDock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NDock(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ndock_layout, this);
    }
}
