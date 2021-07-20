package com.example.appbraintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    TextView timerTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView correctTextView;
    ConstraintLayout gameLayout;

    ArrayList<Integer> answers= new ArrayList<Integer>();

    int locationOfCorrectAnswer;
    int score;
    int totalQuestionsAttempted;


    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        score=totalQuestionsAttempted=0;
        newQuestion();
        NewTimer();
    }

    public void playAgain(View view)
    {
        score=totalQuestionsAttempted=0;
        correctTextView.setText("");
        scoreTextView.setText("0/0");
        timerTextView.setText("30s");
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        newQuestion();
        NewTimer();
    }

    public void NewTimer()
    {
        new CountDownTimer(30300,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Long.toString(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                correctTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestionsAttempted));
                Log.i("Finished","Quiz Over");
            }

        }.start();
    }


    public void chooseAnswer(View view)
    {
        if((view.getTag().toString()).equals(Integer.toString(locationOfCorrectAnswer))) {
            correctTextView.setText("Correct :)");
            score++;
        }
        else {
            correctTextView.setText("Wrong");
            Log.i("Oops!", "You got it wrong!");
        }
        totalQuestionsAttempted++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestionsAttempted));
        newQuestion();
    }

    @SuppressLint("SetTextI18n")
    public void newQuestion()
    {
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        int wrongAnswer;
        for(int i=0;i<4;i++)
        {
            if(i!=locationOfCorrectAnswer)
            {
                wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==(a+b))
                    wrongAnswer=rand.nextInt(41);
                answers.add(wrongAnswer);
            }
            else
                answers.add(a+b);
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=(Button) findViewById(R.id.goButton);
        button0=(Button) findViewById(R.id.button0);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        playAgainButton=(Button) findViewById(R.id.playAgainButton);

        timerTextView=(TextView) findViewById(R.id.timerTextView);
        scoreTextView=(TextView) findViewById(R.id.scoreTextView);
        sumTextView=(TextView) findViewById(R.id.sumTextView);
        correctTextView=(TextView) findViewById(R.id.correctTextView);

        gameLayout=(ConstraintLayout) findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
