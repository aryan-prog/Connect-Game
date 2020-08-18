package com.example.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int gamestate[]={2,2,2,2,2,2,2,2,2};
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    int activeplayer=0;
    boolean gameActive=true;
    GridLayout gridLayout;
    TextView welcome;
    Button start;
    public  void dropin(View view){
        ImageView counter=(ImageView) view;
        counter.setTranslationY(-1500);
        int f=0;
        int tappedcounter=Integer.parseInt(counter.getTag().toString()) ;
        if(gamestate[tappedcounter]==2 && gameActive) {
            gamestate[tappedcounter]=activeplayer;
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }


            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for(int i=0;i<9;i++)
            {
                if(gamestate[i]==2) {
                    f = 1;
                    break;
                }
                else
                    f=0;
            }
            for (int[] winning : winningpositions) {
                if (gamestate[winning[0]] == gamestate[winning[1]] && gamestate[winning[1]] == gamestate[winning[2]] && gamestate[winning[0]] != 2)
                {
                    //someone has won
                    String winner="";
                    gameActive=false;
                    if(activeplayer==1)
                        winner="Yellow";
                    else
                        winner="Red";

                    Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                    TextView playTextView=(TextView) findViewById(R.id.textView);
                    playTextView.setText(winner+" has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    playTextView.setVisibility(View.VISIBLE);
                    f=1;
                }

            }
            if(f==0)
            {
                Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                TextView playTextView=(TextView) findViewById(R.id.textView);
                playTextView.setText("It is a tie!");
                playAgainButton.setVisibility(View.VISIBLE);
                playTextView.setVisibility(View.VISIBLE);

            }
        }
    }
    public void playAgain(View view){
        Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
        TextView playTextView=(TextView) findViewById(R.id.textView);
        playAgainButton.setVisibility(View.INVISIBLE);
        playTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout= findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageResource(0);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<9;i++)
            gamestate[i]=2;

        activeplayer=0;
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout=findViewById(R.id.gridLayout);
        start=findViewById(R.id.startGame);
        welcome=findViewById(R.id.welcome);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.INVISIBLE);
                welcome.setVisibility(View.INVISIBLE);
                gridLayout.setVisibility(View.VISIBLE);

            }
        });

    }
}
