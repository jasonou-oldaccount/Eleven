package com.example.messiah.eleven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AiActivity extends AppCompatActivity {

    // Contains the hands of player one and player two to keep track of their current cards
    private List<Integer> player_one = new ArrayList<Integer>();
    private List<Integer> player_two = new ArrayList<Integer>();

    private boolean playerOne;
    private boolean playerOnePlayed = false;
    private boolean playerTwoPlayed = false;

    private int playerOneCard;
    private int playerTwoCard;

    private int playerOneScore;
    private int playerTwoScore;

    private int turnsPlayed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        // Creates the hands for player one and two
        player_one = Hand.createHand(player_one);
        player_two = Hand.createHand(player_two);

        // Prints out the hands to check it out
        Hand.printHand(player_one);
        Hand.printHand(player_two);

        // Dynamically adds the cards into the view
        LinearLayout player_one_view = (LinearLayout) findViewById(R.id.player_one_view);
        LinearLayout player_two_view = (LinearLayout) findViewById(R.id.player_two_view);
        generateHand(player_one_view, player_one, 1);
        generateHand(player_two_view, player_two, 2);

        // Randomizes which player goes first
        playerOne = Math.random() < 0.5;
        checkPlayer();
    }

    // Print statement to check which player goes first
    public void checkPlayer() {
        if(playerOne) {
            Toast.makeText(getBaseContext(), "Player One's Turn", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Player Two's Turn", Toast.LENGTH_SHORT).show();
        }
    }

    // Generates the hand for both players
    public void generateHand (LinearLayout player, List<Integer> hand, int whichPlayer) {
        for(int i = 0; i < hand.size(); ++i) {
            final Button btn = new Button(this);
            btn.setId(hand.get(i));
            if(whichPlayer == 1) btn.setText(hand.get(i).toString());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn.getText() == "" && !playerOne) {
                        playerOne = true;
                        playerTwoCard = btn.getId();
                        playerTwoPlayed = true;
                        btn.setVisibility(View.GONE);
                    }
                    if (btn.getText() != "" && playerOne) {
                        playerOne = false;
                        playerOneCard = btn.getId();
                        playerOnePlayed = true;
                        btn.setVisibility(View.GONE);
                    }
                    checkGameOver();
                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(-10, 0, -10, 0); //left, top, right, bottom
            player.addView(btn, params);
        }
    }


    // After each round, checks if the game is over
    public void checkGameOver() {
        if(playerTwoPlayed && playerOnePlayed) {
            if(playerOneCard > playerTwoCard) {
                ++playerOneScore;
            } else if(playerTwoCard > playerOneCard) {
                ++playerTwoScore;
            }
            ++turnsPlayed;
            if(turnsPlayed == 11) {
                Toast.makeText(getBaseContext(), "GAME OVER!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), "Player One: " + playerOneScore, Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), "Player Two: " + playerTwoScore, Toast.LENGTH_SHORT).show();
            }
            playerOnePlayed = false;
            playerTwoPlayed = false;

            TextView one = (TextView) findViewById(R.id.player_one_card_show);
            TextView two = (TextView) findViewById(R.id.player_two_card_show);

            one.setText(String.valueOf(playerOneCard));
            two.setText(String.valueOf(playerTwoCard));
        }
    }

    // restarts activity
    public void restartActivity(View view) {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
