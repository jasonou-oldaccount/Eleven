package com.example.messiah.eleven;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Random;

/**
 * Created by Messiah on 3/16/2016.
 */
public class Hand {

    public static List<Integer> createHand(List<Integer> player) {
        Random randNumHand = new Random();
        int[] newHand = new int[11];
        int max = 10;
        int min = 1;

        for(int i = 0; i < 10; ++i) {
            newHand[i] = randNumHand.nextInt((max - min) + 1) + min;
            max += 10;
            min += 10;
        }
        newHand[10] = randNumHand.nextInt(100) + 1;

        Random rand = new Random();

        for(int i = newHand.length - 1; i > 0; --i) {
            int index = rand.nextInt(i + 1);
            int temp = newHand[index];
            newHand[index] = newHand[i];
            newHand[i] = temp;
        }

        for(int i = 0; i < 11; ++i) player.add(newHand[i]);

        return player;
    }

    public static void printHand(List<Integer> hand) {
        for(int i = 0; i < hand.size(); ++i) System.out.print(hand.get(i) + " ");
        System.out.println();
    }

}
