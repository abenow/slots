package com.template.ImageViewScrolling;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.template.R;

import java.util.List;
import java.util.Random;



public class ImageViewScrolling extends LinearLayout {

    ImageView imageView1, imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,
            imageView9, imageView10,imageView11,imageView12, imageView13,imageView14;

    AnimationSet animationSet;


    private IEventEnd eventEnd;

    int old_value=0;

    public void setEventEnd(IEventEnd eventEnd) {
        this.eventEnd = eventEnd;
    }

    public ImageViewScrolling(Context context) {
        super(context);
        init(context);
    }

    public ImageViewScrolling(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @SuppressLint("CutPasteId")
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.scroll_activity_view,this);
        imageView1 = (ImageView)getRootView().findViewById(R.id.imageView1);
        imageView2 = (ImageView)getRootView().findViewById(R.id.imageView2);
        imageView3 = (ImageView)getRootView().findViewById(R.id.imageView3);
        imageView4 = (ImageView)getRootView().findViewById(R.id.imageView4);
        imageView5 = (ImageView)getRootView().findViewById(R.id.imageView5);
        imageView6 = (ImageView)getRootView().findViewById(R.id.imageView6);
        imageView7 = (ImageView)getRootView().findViewById(R.id.imageView7);
        imageView8 = (ImageView)getRootView().findViewById(R.id.imageView8);
        imageView9 = (ImageView)getRootView().findViewById(R.id.imageView9);
        imageView10 = (ImageView)getRootView().findViewById(R.id.imageView10);
        imageView11 = (ImageView)getRootView().findViewById(R.id.imageView11);
        imageView12 = (ImageView)getRootView().findViewById(R.id.imageView12);
        imageView13 = (ImageView)getRootView().findViewById(R.id.imageView13);
    }



    public void setValueRandom(int rotate_count, List<Integer> imageViewList){

        TranslateAnimation translateAnimationDown = new TranslateAnimation(0f, 0f, 0f, -1400f);
        translateAnimationDown.setDuration(150);
        translateAnimationDown.setInterpolator(new AccelerateInterpolator());

        TranslateAnimation translateAnimationUp = new TranslateAnimation(0f, 0f, 0f, 1400f);
        translateAnimationUp.setStartOffset(150);
        translateAnimationUp.setDuration(150);
        translateAnimationUp.setInterpolator(new AccelerateInterpolator());



        animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimationDown);
        animationSet.addAnimation(translateAnimationUp);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                if(old_value != rotate_count) {
                    old_value++;
                    setValueRandom(rotate_count, imageViewList);
                } else{
                    old_value = 0;
                    int minValue = 1;
                    int maxValue = 8;
                    int[] randomNumbers = new int[3];
                    Random random = new Random();

                    for(int i = 0; i < randomNumbers.length; i++) {
                        int randomNumber;
                        do {
                            randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;
                        } while (contains(randomNumbers, randomNumber));
                        randomNumbers[i] = randomNumber;
                    }

                    setImage(imageView6, randomNumbers[0]);
                    setImage(imageView7, randomNumbers[1]);
                    setImage(imageView8, randomNumbers[2]);

                    imageViewList.add(randomNumbers[1]);

                    if(imageViewList.size() == 3) {
                        eventEnd.eventEnd(imageViewList);
                    }
                 }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });


        imageView1.startAnimation(animationSet);
        imageView2.startAnimation(animationSet);
        imageView3.startAnimation(animationSet);
        imageView4.startAnimation(animationSet);
        imageView5.startAnimation(animationSet);
        imageView6.startAnimation(animationSet);
        imageView7.startAnimation(animationSet);
        imageView8.startAnimation(animationSet);
        imageView9.startAnimation(animationSet);
        imageView10.startAnimation(animationSet);
        imageView11.startAnimation(animationSet);
        imageView12.startAnimation(animationSet);
        imageView13.startAnimation(animationSet);
    }

    private void setImage(ImageView image_View, int value) {
        if(value == Util.WILD)
            image_View.setImageResource(R.drawable.ico_8);
        else if(value == Util.SEVEN)
            image_View.setImageResource(R.drawable.ico_7);
        else if(value == Util.BLUEBELL)
            image_View.setImageResource(R.drawable.ico_6);
        else if(value == Util.STAR)
            image_View.setImageResource(R.drawable.ico_5);
        else if(value == Util.GRAPE)
            image_View.setImageResource(R.drawable.ico_4);
        else if(value == Util.WATERMELON)
            image_View.setImageResource(R.drawable.ico_3);
        else if(value == Util.CHERRY)
            image_View.setImageResource(R.drawable.ico_2);
        else
            image_View.setImageResource(R.drawable.ico_1);
    }

    private boolean contains(int[] arr, int value) {
        for (int num : arr) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }

}
