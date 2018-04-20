package com.example.gebruiker.toc_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    Game game;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing new game
        game = new Game();

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        textView1 = findViewById(R.id.TextView1);
    }

    public void tileClicked(View view) {

        // passing button to view
        Button button = (Button) view;

        // retrieving tag with numer from button
        String raw = button.getTag().toString();
        int id = Integer.parseInt(raw);

        // getting row and column to draw in grid
        int row = id / 3;
        int column = id % 3;

        // calling function to draw tile
        Tile tile = game.draw(row, column);

        // setting text in button
        switch (tile) {
            case CROSS:
                button.setText("X");
                break;
            case CIRCLE:
                button.setText("O");
                break;
            }

            // checking for win or draw
            GameState gamestate = game.won();

            // setting textbox after win or draw
            switch (gamestate){
                case PLAYER_ONE:
                    textView1.setText("Player 1 won");
                     break;
                case PLAYER_TWO:
                    textView1.setText("Player 2 won");
                    break;
                case DRAW:
                    textView1.setText("Draw");
            }
    }

    // method for resetbutton clicked, resets all button and textview
    public void resetClicked (View view){
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");

        textView1.setText("In progress");
        game = new Game();

        }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // saving all buttons text
        String button1text = button1.getText().toString();
        outState.putString("button1", button1text);
        String button2text = button2.getText().toString();
        outState.putString("button2", button2text);
        String button3text = button3.getText().toString();
        outState.putString("button3", button3text);
        String button4text = button4.getText().toString();
        outState.putString("button4", button4text);
        String button5text = button5.getText().toString();
        outState.putString("button5", button5text);
        String button6text = button6.getText().toString();
        outState.putString("button6", button6text);
        String button7text = button7.getText().toString();
        outState.putString("button7", button7text);
        String button8text = button8.getText().toString();
        outState.putString("button8", button8text);
        String button9text = button9.getText().toString();
        outState.putString("button9", button9text);
        String gameProgress = textView1.getText().toString();
        outState.putString("gamestate", gameProgress);

        // saving variabels for game
        int moves = game.movesPlayed;
        outState.putInt("moves", moves);
        boolean playerOne = game.playerOneTurn;
        outState.putBoolean("turn", playerOne);
        boolean gameOver = game.gameOver;
        outState.putBoolean("gameover", gameOver);

        // saving all drawn crosses and circles
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                // calling method of class game
                int tile = game.save(i, j);
                outState.putSerializable("data" + i + j, tile);
            }
        }
    }

    @Override
    public void onRestoreInstanceState (Bundle inState){
        super.onRestoreInstanceState(inState);

        // restoring all buttons
        String button1textrestored = inState.getString("button1");
        button1.setText(button1textrestored);
        String button2textrestored = inState.getString("button2");
        button2.setText(button2textrestored);
        String button3textrestored = inState.getString("button3");
        button3.setText(button3textrestored);
        String button4textrestored = inState.getString("button4");
        button4.setText(button4textrestored);
        String button5textrestored = inState.getString("button5");
        button5.setText(button5textrestored);
        String button6textrestored = inState.getString("button6");
        button6.setText(button6textrestored);
        String button7textrestored = inState.getString("button7");
        button7.setText(button7textrestored);
        String button8textrestored = inState.getString("button8");
        button8.setText(button8textrestored);
        String button9textrestored = inState.getString("button9");
        button9.setText(button9textrestored);
        String gameprogress = inState.getString("gamestate");
        textView1.setText(gameprogress);

        // restoring variabels for game
        int movesrestored = inState.getInt("moves");
        game.movesPlayed = movesrestored;
        boolean turn = inState.getBoolean("turn");
        game.playerOneTurn = turn;
        boolean gameOver = inState.getBoolean("gameover");
        game.gameOver = gameOver;

        // restoring all crosses and circles
        for(int i = 0; i < 3; i++){
            for (int j = 0; j< 3; j++){
                int rest = inState.getInt("data" + i + j);

                // calling method of class game
                game.reload(rest, i, j);
            }
        }
    }



}
