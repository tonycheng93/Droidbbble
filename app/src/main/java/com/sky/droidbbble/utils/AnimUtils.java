package com.sky.droidbbble.utils;

/**
 * 项目名称：Droidbbble
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2017/10/15 18:03
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.FloatProperty;
import android.util.IntProperty;
import android.util.Property;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import java.util.ArrayList;

/**
 * Utility methods for working with animations.
 */

public class AnimUtils {

    private static Interpolator fastOutSlowIn;
    private static Interpolator fastOutLinearIn;
    private static Interpolator linearOutSlowIn;
    private static Interpolator linear;

    private AnimUtils() {
        //utility class,no instance
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Interpolator getFastOutSlowInInterpolator(Context context) {
        if (fastOutSlowIn == null) {
            fastOutSlowIn = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.fast_out_slow_in);
        }
        return fastOutLinearIn;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Interpolator getFastOutLinearInInterpolator(Context context) {
        if (fastOutLinearIn == null) {
            fastOutLinearIn = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.fast_out_linear_in);
        }
        return fastOutLinearIn;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Interpolator getLinearOutSlowInInterpolator(Context context) {
        if (linearOutSlowIn == null) {
            linearOutSlowIn = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.linear_out_slow_in);
        }
        return linearOutSlowIn;
    }

    public static Interpolator getLinearInterpolator(Context context) {
        if (linear == null) {
            linear = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.linear);
        }
        return linear;
    }

    /**
     * Linear interpolate between a and b with parameter t.
     */
    public static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    /**
     * A delegate for creating a {@link android.util.Property} of <code>int</code> type.
     */
    public static abstract class AbstractIntProp<T> {
        public final String name;

        public AbstractIntProp(String name) {
            this.name = name;
        }

        /**
         * set value
         *
         * @param object T
         * @param value  int
         */
        public abstract void set(T object, int value);

        /**
         * get value
         *
         * @param object T
         * @return int
         */
        public abstract int get(T object);
    }

    /**
     * The animation framework has an optimization for <code>Properties</code> of type
     * <code>int</code> but it was only made public in API24, so wrap the impl in our own type
     * and conditionally create the appropriate type, delegating the implementation.
     */
    public static <T> Property<T, Integer> createIntProperty(final AbstractIntProp<T> impl) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return new IntProperty<T>(impl.name) {
                @Override
                public void setValue(T object, int value) {
                    impl.set(object, value);
                }

                @Override
                public Integer get(T object) {
                    return impl.get(object);
                }
            };
        } else {
            return new Property<T, Integer>(Integer.class, impl.name) {
                @Override
                public Integer get(T object) {
                    return impl.get(object);
                }

                @Override
                public void set(T object, Integer value) {
                    impl.set(object, value);
                }
            };
        }
    }

    /**
     * A delegate for creating a {@link Property} of <code>float</code> type.
     */
    public static abstract class AbstractFloatProp<T> {
        public final String name;

        public AbstractFloatProp(String name) {
            this.name = name;
        }

        /**
         * set float value
         *
         * @param object T
         * @param value  float
         */
        public abstract void set(T object, float value);

        /**
         * get float value
         *
         * @param object T
         * @return float
         */
        public abstract float get(T object);
    }

    /**
     * The animation framework has an optimization for <code>Properties</code> of type
     * <code>float</code> but it was only made public in API24, so wrap the impl in our own type
     * and conditionally create the appropriate type, delegating the implementation.
     */
    public static <T> Property<T, Float> creatteFloatProperty(final AbstractFloatProp<T> impl) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return new FloatProperty<T>(impl.name) {
                @Override
                public void setValue(T object, float value) {
                    impl.set(object, value);
                }

                @Override
                public Float get(T object) {
                    return impl.get(object);
                }
            };
        } else {
            return new Property<T, Float>(Float.class, impl.name) {
                @Override
                public Float get(T object) {
                    return impl.get(object);
                }

                @Override
                public void set(T object, Float value) {
                    impl.set(object, value);
                }
            };
        }
    }

    /**
     * https://halfthought.wordpress.com/2014/11/07/reveal-transition/
     * <p/>
     * Interrupting Activity transitions can yield an OperationNotSupportedException when the
     * transition tries to pause the animator. Yikes! We can fix this by wrapping the Animator:
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static class NoPauseAnimator extends Animator {

        private final Animator mAnimator;
        private ArrayMap<AnimatorListener, AnimatorListener> mListeners = new ArrayMap<>();

        public NoPauseAnimator(Animator animator) {
            mAnimator = animator;
        }

        @Override
        public void addListener(AnimatorListener listener) {
            AnimatorListenerWrapper wrapper = new AnimatorListenerWrapper(this, listener);
            if (!mListeners.containsKey(listener)) {
                mListeners.put(listener, wrapper);
                mAnimator.addListener(wrapper);
            }
        }

        @Override
        public void cancel() {
            mAnimator.cancel();
        }

        @Override
        public void end() {
            mAnimator.end();
        }

        @Override
        public long getDuration() {
            return mAnimator.getDuration();
        }

        @Override
        public TimeInterpolator getInterpolator() {
            return mAnimator.getInterpolator();
        }

        @Override
        public void setInterpolator(TimeInterpolator timeInterpolator) {
            mAnimator.setInterpolator(timeInterpolator);
        }

        @Override
        public ArrayList<AnimatorListener> getListeners() {
            return new ArrayList<>(mListeners.keySet());
        }

        @Override
        public long getStartDelay() {
            return mAnimator.getStartDelay();
        }

        @Override
        public void setStartDelay(long delayMS) {
            mAnimator.setStartDelay(delayMS);
        }

        @Override
        public boolean isPaused() {
            return mAnimator.isPaused();
        }

        @Override
        public boolean isRunning() {
            return mAnimator.isRunning();
        }

        @Override
        public boolean isStarted() {
            return mAnimator.isStarted();
        }

        /* We don't want to override pause or resume methods because we don't want them
         * to affect mAnimator.
        public void pause();

        public void resume();

        public void addPauseListener(AnimatorPauseListener listener);

        public void removePauseListener(AnimatorPauseListener listener);
        */

        @Override
        public void removeAllListeners() {
            mListeners.clear();
            mAnimator.removeAllListeners();
        }

        @Override
        public void removeListener(AnimatorListener listener) {
            AnimatorListener wrapper = mListeners.get(listener);
            if (wrapper != null) {
                mListeners.remove(listener);
                mAnimator.removeListener(wrapper);
            }
        }

        @Override
        public Animator setDuration(long durationMS) {
            mAnimator.setDuration(durationMS);
            return this;
        }

        @Override
        public void setTarget(Object target) {
            mAnimator.setTarget(target);
        }

        @Override
        public void setupEndValues() {
            mAnimator.setupEndValues();
        }

        @Override
        public void setupStartValues() {
            mAnimator.setupStartValues();
        }

        @Override
        public void start() {
            mAnimator.start();
        }
    }

    private static class AnimatorListenerWrapper implements Animator.AnimatorListener {

        private final Animator mAnimator;
        private final Animator.AnimatorListener mAnimatorListener;

        private AnimatorListenerWrapper(Animator animator, Animator.AnimatorListener
                animatorListener) {
            mAnimator = animator;
            mAnimatorListener = animatorListener;
        }

        @Override
        public void onAnimationStart(Animator animator) {
            mAnimatorListener.onAnimationStart(mAnimator);
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            mAnimatorListener.onAnimationEnd(mAnimator);
        }

        @Override
        public void onAnimationCancel(Animator animator) {
            mAnimatorListener.onAnimationCancel(mAnimator);
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
            mAnimatorListener.onAnimationRepeat(mAnimator);
        }
    }
}
