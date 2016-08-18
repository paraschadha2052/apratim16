package com.dityish.apratim2k16;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by adnrs96 on 1/8/16.
 */

public class jerryAnimation {

    View myView;
    int height1;
    int width1;

    ImageView one;
    ImageView two;
    ImageView three;// = (ImageView) myView.findViewById(R.id.imageView3);
    ImageView four;// = (ImageView) myView.findViewById(R.id.imageView4);
    ImageView five; //= (ImageView) myView.findViewById(R.id.imageView5);
    ImageView six; //.= (ImageView) myView.findViewById(R.id.imageView6);
    ImageView seven;// = (ImageView) myView.findViewById(R.id.imageView7);
    Keyframe kf0; //= Keyframe.ofFloat(0f, 1f);
    Keyframe kf1; //= Keyframe.ofFloat(.9999999f, 1f);
    Keyframe kf2; //= Keyframe.ofFloat(1f, 0f);
    PropertyValuesHolder Alpha;// = PropertyValuesHolder.ofKeyframe("alpha", kf0, kf1, kf2);

    ObjectAnimator OneF;// = ObjectAnimator.ofPropertyValuesHolder(one, Alpha).setDuration(100);
    ObjectAnimator TwoF ;//= ObjectAnimator.ofPropertyValuesHolder(two, Alpha).setDuration(100);
    ObjectAnimator ThreeF;// = ObjectAnimator.ofPropertyValuesHolder(three, Alpha).setDuration(100);
    ObjectAnimator FourF ;//= ObjectAnimator.ofPropertyValuesHolder(four, Alpha).setDuration(100);
    ObjectAnimator FiveF;// = ObjectAnimator.ofPropertyValuesHolder(five, Alpha).setDuration(100);
    ObjectAnimator SixF ;//= ObjectAnimator.ofPropertyValuesHolder(six, Alpha).setDuration(100);
    ObjectAnimator SevenF ;//;= ObjectAnimator.ofPropertyValuesHolder(seven, Alpha).setDuration(100);

    AnimatorSet animSet, anim;

    ObjectAnimator OneM ;//= ObjectAnimator.ofFloat(one, "translationX", 0, width1);
    ObjectAnimator TwoM;// = ObjectAnimator.ofFloat(two, "translationX", 0, width1);
    ObjectAnimator ThreeM;// = ObjectAnimator.ofFloat(three, "translationX", 0, width1);
    ObjectAnimator FourM;// = ObjectAnimator.ofFloat(four, "translationX", 0, width1);
    ObjectAnimator FiveM;// = ObjectAnimator.ofFloat(five, "translationX", 0, width1);
    ObjectAnimator SixM ;//= ObjectAnimator.ofFloat(six, "translationX", 0, width1);
    ObjectAnimator SevenM ;//= ObjectAnimator.ofFloat(seven, "translationX", 0, width1);

    public jerryAnimation(View myView, int height, int width){
        this.myView=myView;
        this.height1=height;
        this.width1=width;
        one = (ImageView)myView.findViewById(R.id.jerryimageView);
        two = (ImageView) myView.findViewById(R.id.jerryimageView2);
        three = (ImageView) myView.findViewById(R.id.jerryimageView3);
        four = (ImageView) myView.findViewById(R.id.jerryimageView4);
        five = (ImageView) myView.findViewById(R.id.jerryimageView5);
        six = (ImageView) myView.findViewById(R.id.jerryimageView6);
        seven = (ImageView) myView.findViewById(R.id.jerryimageView7);

        kf0 = Keyframe.ofFloat(0f, 1f);
        kf1 = Keyframe.ofFloat(.9999999f, 1f);
        kf2 = Keyframe.ofFloat(1f, 0f);
        Alpha = PropertyValuesHolder.ofKeyframe("alpha", kf0, kf1, kf2);

        OneF = ObjectAnimator.ofPropertyValuesHolder(one, Alpha).setDuration(100);
        TwoF = ObjectAnimator.ofPropertyValuesHolder(two, Alpha).setDuration(100);
        ThreeF = ObjectAnimator.ofPropertyValuesHolder(three, Alpha).setDuration(100);
        FourF = ObjectAnimator.ofPropertyValuesHolder(four, Alpha).setDuration(100);
        FiveF = ObjectAnimator.ofPropertyValuesHolder(five, Alpha).setDuration(100);
        SixF = ObjectAnimator.ofPropertyValuesHolder(six, Alpha).setDuration(100);
        SevenF = ObjectAnimator.ofPropertyValuesHolder(seven, Alpha).setDuration(100);

        animSet = new AnimatorSet();
        anim = new AnimatorSet();

        OneM = ObjectAnimator.ofFloat(one, "translationX", 0, width1);
        TwoM = ObjectAnimator.ofFloat(two, "translationX", 0, width1);
        ThreeM = ObjectAnimator.ofFloat(three, "translationX", 0, width1);
        FourM = ObjectAnimator.ofFloat(four, "translationX", 0, width1);
        FiveM = ObjectAnimator.ofFloat(five, "translationX", 0, width1);
        SixM = ObjectAnimator.ofFloat(six, "translationX", 0, width1);
        SevenM = ObjectAnimator.ofFloat(seven, "translationX", 0, width1);

    }

    public AnimatorSet con_anime()
    {
        animSet.play(SevenF).after(SixF);
        animSet.play(SixF).after(FiveF);
        animSet.play(FiveF).after(FourF);
        animSet.play(FourF).after(ThreeF);
        animSet.play(ThreeF).after(TwoF);
        animSet.play(TwoF).after(OneF);
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        OneM.setDuration(1500).setStartDelay(1800);
        TwoM.setDuration(1500).setStartDelay(1800);
        ThreeM.setDuration(1500).setStartDelay(1800);
        FourM.setDuration(1500).setStartDelay(1800);
        FiveM.setDuration(1500).setStartDelay(1800);
        SixM.setDuration(1500).setStartDelay(1800);
        SevenM.setDuration(1500).setStartDelay(1800);

        anim.play(animSet).with(OneM).with(TwoM).with(ThreeM).with(FourM).with(FiveM).with(SixM).with(SevenM);
        return anim;
    }
}
