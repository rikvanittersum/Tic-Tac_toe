package com.example.gebruiker.toc_tac_toe;

import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;

import static com.example.gebruiker.toc_tac_toe.Tile.BLANK;
import static com.example.gebruiker.toc_tac_toe.Tile.CIRCLE;
import static com.example.gebruiker.toc_tac_toe.Tile.CROSS;

public class Game implements Serializable {
    // initializing board
    final private int BOARD_SIZE = 3;
    public Tile[][] board;

    // variables to memorize moves
    public Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    public int movesPlayed;
    public Boolean gameOver;

    // constructor of the class
    public Game() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    // method to memorize values tile's
    public Tile draw(int row, int column) {

        // if tile is blank
        if (board[row][column] == BLANK) {

            // setting value's tile for player 1
            if (playerOneTurn == true && gameOver == false) {
                board[row][column] = CROSS;
                playerOneTurn = false;
                movesPlayed++;
                return CROSS;
            }

            // setting value's tile for player 2
            else if (playerOneTurn == false && gameOver == false){
                board[row][column] = CIRCLE;
                playerOneTurn = true;
                movesPlayed++;
                return Tile.CIRCLE;
            }
        }
        // else Tile is already drawn
        return Tile.INVALID;
    }

    // method to check for win
    public GameState won() {
        // game can only be won after 5 moves are played
        if (movesPlayed > 4) {

            // checking for diagnols player one
            if (board[0][0] == (board[1][1]) && board[0][0] == board[2][2] && board[0][0] == CROSS) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }
            if (board[0][2] == (board[1][1]) && board[0][2] == board[2][0] && board[0][2] == CROSS) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            // checking for rows and columns player one
            if (board[0][0] == (board[0][1]) && board[0][0] == board[0][2] && board[0][0] == CROSS) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }
            if (board[1][0] == (board[1][1]) && board[1][0] == board[1][2] && board[1][0] == CROSS) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }
            if (board[2][0] == (board[2][1]) && board[2][0] == board[2][2] && board[2][0] == CROSS) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }
            if (board[0][0] == (board[1][0]) && board[0][0] == board[2][0] && board[0][0] == CROSS) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }
            if (board[0][1] == (board[1][1]) && board[0][1] == board[2][1] && board[0][1] == CROSS) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }
            if (board[0][2] == (board[1][2]) && board[0][2] == board[2][2] && board[0][2] == CROSS) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            // checking for diagnols player two
            if (board[0][0] == (board[1][1]) && board[0][0] == board[2][2] && board[0][0] == CIRCLE) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
            if (board[0][2] == (board[1][1]) && board[0][2] == board[2][0] && board[0][2] == CIRCLE) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }

            // checking for rows and columns player two
            if (board[0][0] == (board[0][1]) && board[0][0] == board[0][2] && board[0][0] == CIRCLE) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
            if (board[1][0] == (board[1][1]) && board[1][0] == board[1][2] && board[1][0] == CIRCLE) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
            if (board[2][0] == (board[2][1]) && board[2][0] == board[2][2] && board[2][0] == CIRCLE) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
            if (board[0][0] == (board[1][0]) && board[0][0] == board[2][0] && board[0][0] == CIRCLE) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
            if (board[0][1] == (board[1][1]) && board[0][1] == board[2][1] && board[0][1] == CIRCLE) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
            if (board[0][2] == (board[1][2]) && board[0][2] == board[2][2] && board[0][2] == CIRCLE) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        // after 9 moves and no win, game is drawn
        if (movesPlayed == 9) {
            return GameState.DRAW;
        }

        // else still in progress
        return GameState.IN_PROGRESS;
    }

    // method for saving drawn board for save instance
    public int save(int row, int column)
    {
        // returning int 1 for cross
        if (board[row][column] == CROSS){
            return 1;
        }
        // returning int 2 for circle
        else if (board[row][column] == CIRCLE) {
            return 2;
            }
        return 0;
    }

    // method for drawing saved board
    public void reload (int data, int row, int column){
        // int 1 sets cross
        if (data == 1){
            board[row][column] = CROSS;
        }

        // int 2 sets circle
        if (data == 2){
            board[row][column] = CIRCLE;
        }
    }
}