package com.example.messiah.eleven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AiActivity extends AppCompatActivity {

    // Contains the hands of player one and player two to keep track of their current cards
    private List<Integer> player_one = new ArrayList<Integer>();
    private List<Integer> player_two = new ArrayList<Integer>();

    private boolean playerOne;
    private boolean playerOnePlayed = false;
    private boolean playerTwoPlayed = false;

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

        playerOne = Math.random() < 0.5;
        if(playerOne) {
            Toast.makeText(getBaseContext(), "Player One Starts", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Player Two Starts", Toast.LENGTH_SHORT).show();
        }
    }

    public void generateHand (LinearLayout player, List<Integer> hand, int whichPlayer) {
        for(int i = 0; i < hand.size(); ++i) {
            final Button btn = new Button(this);
            btn.setId(hand.get(i));
            if(whichPlayer == 1) btn.setText(hand.get(i).toString());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn.getText() == "" && !playerOne && !playerTwoPlayed) {
                        playerOne = true;
                        playerTwoPlayed = true;
                        btn.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(), "VALUE = " + btn.getId(), Toast.LENGTH_SHORT).show();
                        playerOnePlayed = false;
                    } else if (playerOne && !playerOnePlayed) {
                        playerOne = false;
                        playerOnePlayed = true;
                        btn.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(), "VALUE = " + btn.getId(), Toast.LENGTH_SHORT).show();
                        playerTwoPlayed = false;
                    }
                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(-10, 0, -10, 0); //left, top, right, bottom
            player.addView(btn, params);
        }
    }
}
