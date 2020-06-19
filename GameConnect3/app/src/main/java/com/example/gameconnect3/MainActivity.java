package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:yellow ,1:red, 2:empty
    int activePlayer=0;

    int gameState[]={2,2,2,2,2,2,2,2,2};

    int winingPositions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;

    public void dropIn(View view){
        Log.i("info","works");
        ImageView imageView=(ImageView) view;
       imageView.setTranslationY(-1500);
       int tappedCounter=Integer.parseInt(view.getTag().toString());

       if(gameState[tappedCounter]==2 && gameActive) {

           gameState[tappedCounter] = activePlayer;
           if (activePlayer == 0) {
               imageView.setImageResource(R.drawable.yellow);
               activePlayer = 1;
           } else {
               imageView.setImageResource(R.drawable.red);
               activePlayer = 0;
           }

           imageView.animate().translationYBy(1500).rotation(3000).setDuration(300);
           for (int winingPosition[] : winingPositions) {
               if (gameState[winingPosition[0]] == gameState[winingPosition[1]] && gameState[winingPosition[1]] == gameState[winingPosition[2]]
                       && gameState[winingPosition[0]] != 2) {
                   String winnerName = "RED";
                   if (activePlayer == 1) {
                       winnerName = "Yellow";
                   }
                   gameActive=false;
                   TextView textView=findViewById(R.id.winnerTextView);
                   Button playAgainButton=findViewById(R.id.button);
                   playAgainButton.setVisibility(View.VISIBLE);
                   textView.setVisibility(View.VISIBLE);
                   textView.setText("Winner is : "+winnerName);
                   //Toast.makeText(this, "Winner is : " + winnerName, Toast.LENGTH_SHORT).show();
               }
           }
       }

    }
    public void playAgain(View view){
        TextView textView=findViewById(R.id.winnerTextView);
        Button playAgainButton=findViewById(R.id.button);
        playAgainButton.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(androidx.gridlayout.widget.GridLayout) findViewById(R.id.grid_layout);
        for(int i=0;i<gridLayout.getChildCount();i++){

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer=0;
        for(int i=0;i<gameState.length;i++) {
            gameState[i] = 2;
        }
        gameActive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
