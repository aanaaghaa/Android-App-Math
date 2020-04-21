package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startBtn,playAgainBtn,btn0,btn1,btn2,btn3;
    TextView timerTextView,scoreTextView,sumTextView,displayResult;
    GridLayout gridLayout;

    int counter=1,i, locOfCorrectAnswer, randomNumber1,randomNumber2,score=0,noOfQuestions=0;
    ArrayList<Integer> answers=new ArrayList<>(); //to store wrong and right answers
    public void buttonStart(final View view) //when the go btn is clicked
    {
        startBtn.setVisibility(View.INVISIBLE); // go btn will be invisible
        // all other textviews and layouts will be visible
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        countDown(); //to start the countdown
        randomNumGen(); //to start the generation of random nums and answers

    }
    public void countDown()
    {
        CountDownTimer countDownTimer=new CountDownTimer(30100,1000) { //max time(30100), at what time(1000)
            @Override
            public void onTick(long millisUntilFinished) { //on every second
                int seconds=(int)millisUntilFinished/1000; //convert millisecond to second
                String secondString=Integer.toString(seconds); //convert second in int to string
                if(seconds<10)
                {
                    secondString="0" + secondString; //adding zero if second is less than 10
                }
                timerTextView.setText("00 : "+ secondString); //display the timer on textview
            }

            @Override
            public void onFinish() {
                playAgainBtn.setVisibility(View.VISIBLE);// make play again btn visible
                //all other layouts and textviews invisible
                gridLayout.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.INVISIBLE);
                scoreTextView.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                //displayResult.setVisibility(View.INVISIBLE);
                displayResult.setText(" "); //setting the display result to empty string
            }
        }.start(); //starting the countdown timer
    }
    public void randomNumGen()
    {
        Random random= new Random();
        randomNumber1 = random.nextInt(21); //genrating the 1st random number
        randomNumber2 = random.nextInt(21); //generating the second random number
        sumTextView.setText(Integer.toString(randomNumber1) + " + " + Integer.toString(randomNumber2)); //displaying 1st and 2nd random number on text view
        locOfCorrectAnswer=random.nextInt(4); //generating random number for placing the correct answer among the 4 options
        answers.clear(); // clear the options in the array that are already present
        for (i=0;i<4;i++) //for placing the correct and wrong answer
        {
            if (i == locOfCorrectAnswer) //if i i.e., the tag or on the option btn where the compiler is presently on is equal to the crct answer location
            {
                answers.add(randomNumber1+randomNumber2); //add the random numbers that is generated
            }
            else
            {
                int wrongAnswer=random.nextInt(41); //generate wrong answer from 0 to 40
                while (wrongAnswer==randomNumber1+randomNumber2) //if wrong answer is equal to the correct answer
                {
                    wrongAnswer=random.nextInt(41); //generate a new wrong answer
                } answers.add(wrongAnswer); //add it to the array of answers
            }
        }
        //adding the value to the btn using tags
        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));

    }
    public void btnClicked(View view) //when the option button is clicked
    {
        String chosenAnswer=view.getTag().toString(); //gets the tag of the btn that was clicked
        if(Integer.toString(locOfCorrectAnswer).equals(chosenAnswer)) //if the correct answer location and the tag of the btn is clicked are same
        {
            displayResult.setText("Correct!!!"); //display the string in textview
            displayResult.setVisibility(View.VISIBLE); //make the textview visible
            score++; //increase the score
        }else { //when the answer is wrong
            displayResult.setText("Wrong!");
            displayResult.setVisibility(View.VISIBLE);
        }
        noOfQuestions++; //increase the no of questions every time the user clicks any option
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions)); //display the score and no of questions
        randomNumGen(); //again generate a random number
    }
    public void playAgainBtnClicked(View view) //when playagain btn is clicked
    {
        //set the score and no of questions to 0
        score=0;
        noOfQuestions=0;
        //make the playagain btn invisible
        playAgainBtn.setVisibility(View.INVISIBLE);
        //make the layout and all other textviews visible
        gridLayout.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        //start the countdown and random number again
        countDown();
        randomNumGen();
        scoreTextView.setText("0/0"); //setting the textview's score to 0/0
        displayResult.setText(" "); //setting the display result to empty string
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connecting all the btns and textviews to the layout
        startBtn=(Button)findViewById(R.id.startBtn);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        playAgainBtn=(Button)findViewById(R.id.playAgain);
        btn0=(Button)findViewById(R.id.button0);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        displayResult=(TextView)findViewById(R.id.displayResult);
        //displayResult.setText(" ");
    }
}
