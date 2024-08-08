package com.template;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.template.ImageViewScrolling.IEventEnd;
import com.template.ImageViewScrolling.ImageViewScrolling;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import android.os.Handler;

public class Slots extends AppCompatActivity implements IEventEnd {

    List<Integer> imageViewList = new ArrayList<>();
    ImageViewScrolling image1, image2, image3;
    ImageView bott_spin, auto_spin,bott_back,minus1, plus,positiveButton;
    View layout1,layout2,layout3;
    TextView textView1,textView2;
    boolean isRunning = false;
    int balance = 300;
    int countBet = 0;
    public void setCountBet(int countBet) {
        this.countBet = countBet;
        if(this.countBet < 0){
            this.countBet = 0;
        }
    }
    public int getCountBet() {
        return countBet;
    }
    AlertDialog.Builder builder;
    AlertDialog dialog;
    LayoutInflater inflater;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slots);

        init();
    }


    public void init(){
        builder = new AlertDialog.Builder(this);

        bott_back = findViewById(R.id.bott_back);
        bott_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(dialogView);

        positiveButton = dialogView.findViewById(R.id.bott_res);

        dialog = builder.create();

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                balance = 300;
                textView1.setText(String.valueOf(balance));
            }
        });

        textView1 = findViewById(R.id.textView1);
        textView1.setText(String.valueOf(balance));

        textView2 = findViewById(R.id.textView2);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        image1.setEventEnd(this);
        image2.setEventEnd(this);
        image3.setEventEnd(this);

        bott_spin = findViewById(R.id.bott_spin);
        bott_spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.GONE);
                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
                image3.setVisibility(View.VISIBLE);



                imageSetValue();

            }
        });

        auto_spin = findViewById(R.id.bott_autospin);
        auto_spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layout1.getVisibility() == View.VISIBLE) {
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                }

                auto_spin.setImageResource(R.drawable.stop_auto);

                isRunning = !isRunning;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (isRunning) {
                            imageSetValue();
                            handler.postDelayed(this, 6000);
                            auto_spin.setImageResource(R.drawable.stop_auto);
                        }
                    }
                };

                if(!isRunning){
                    auto_spin.setImageResource(R.drawable.bott_autospin);
                }
                handler.post(runnable);
            }
        });


        minus1 = findViewById(R.id.minus1);
        minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCountBet(countBet -= 5);
                textView2.setText(String.valueOf(getCountBet()));
            }
        });

        plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCountBet(countBet += 5);
                textView2.setText(String.valueOf(getCountBet()));
            }
        });
    }

    private void imageSetValue() {
        image1.setValueRandom(new Random().nextInt((10 - 5) + 1) + 5, imageViewList);
        image2.setValueRandom(new Random().nextInt((10 - 5) + 1) + 5, imageViewList);
        image3.setValueRandom(new Random().nextInt((10 - 5) + 1) + 5, imageViewList);
    }

    @Override
    public void eventEnd(List<Integer> count) {

        if(Objects.equals(count.get(0), count.get(1)) && Objects.equals(count.get(1), count.get(2))) {
            Toast.makeText(this, "You won a big prize", Toast.LENGTH_SHORT).show();

            balance += countBet * 3;
            textView1.setText(String.valueOf(balance));
        } else if (Objects.equals(count.get(0), count.get(1)) ||
                Objects.equals(count.get(1), count.get(2)) ||
                Objects.equals(count.get(0), count.get(2)))
        {
            Toast.makeText(this, "You won a small prize", Toast.LENGTH_SHORT).show();
            balance += countBet;
            textView1.setText(String.valueOf(balance));
        }else {
            Toast.makeText(this, "You lose", Toast.LENGTH_SHORT).show();
            balance -= countBet;
            textView1.setText(String.valueOf(balance));
        }
        imageViewList.clear();
        if (balance <= 0){
            isRunning = false;
            dialog.show();
        }
    }

}