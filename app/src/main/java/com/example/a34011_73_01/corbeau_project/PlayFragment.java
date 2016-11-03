package com.example.a34011_73_01.corbeau_project;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by 34011-73-09 on 27/10/2016.
 */

public class PlayFragment extends Fragment {

    private Game game;

    private boolean isGameFinished;

    private ImageView greenFruit;
    private ImageView orangeFruit;
    private ImageView violetFruit;
    private ImageView yellowFruit;

    private ImageView raven;

    private ImageButton de;

    private Handler gameHandler;

    private PopupWindow popupWindow;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*
        try {
            listener = (GameInfoFragment.OnOkPressedListener)activity;
        }
        catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnOkPressedListener");
        }
        */
        game = new Game();
        game.setPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        Bundle bundle = this.getArguments();
        game.setPlayerName(bundle.getString("playerName"));

        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        gameHandler = new Handler();

        isGameFinished = false;

        greenFruit = (ImageView)getView().findViewById(R.id.orchardGreenFruit);
        orangeFruit = (ImageView)getView().findViewById(R.id.orchardOrangeFruit);
        violetFruit = (ImageView)getView().findViewById(R.id.orchardVioletFruit);
        yellowFruit = (ImageView)getView().findViewById(R.id.orchardYellowFruit);

        raven = (ImageView)getView().findViewById(R.id.raven);

        de = (ImageButton)getView().findViewById(R.id.imageButton7);
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isGameFinished) {
                    doTurn();
                }
            }
        });

        if(game.getCurrentPlayer() != game.getHumanPlayerID()) {
            Toast message = Toast.makeText(getContext(), "The computer begins!", Toast.LENGTH_SHORT);
            message.show();

            de.setAlpha(0.0f);

            gameHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int result = game.doTurn();

                    showTurnResult(result);

                    updateGame();

                    game.nextPlayer();

                    Toast handlerMessage = Toast.makeText(getContext(), "It's your turn!", Toast.LENGTH_SHORT);
                    handlerMessage.show();

                    de.setAlpha(1.0f);
                }
            }, 3000);
        }
        else {
            Toast message = Toast.makeText(getContext(), "You Start!", Toast.LENGTH_SHORT);
            message.show();
        }
    }

    public void doTurn() {
        int result = game.doTurn(); //The player's turn

        showTurnResult(result);

        updateGame();

        isGameFinished = game.isGameFinished() || game.hasCorbackWon();

        if(!isGameFinished) {
            Toast message = Toast.makeText(getContext(), "It's the computer's turn!", Toast.LENGTH_SHORT);
            message.show();

            de.setAlpha(0.0f);

            game.nextPlayer();

            gameHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int result = game.doTurn();

                    showTurnResult(result);

                    updateGame();

                    isGameFinished = game.isGameFinished() || game.hasCorbackWon();

                    if(!isGameFinished) {
                        game.nextPlayer();

                        Toast handlerMessage = Toast.makeText(getContext(), "It's your turn!", Toast.LENGTH_SHORT);
                        handlerMessage.show();
                    }
                    else {
                        showEndGameMessage();
                    }

                    de.setAlpha(1.0f);
                }
            }, 3000);
        }
        else {
            showEndGameMessage();
        }
    }

    public void showTurnResult(int result) {
        switch(result) {
            case 1:
            case 2:
            case 3:
            case 4: {
                if(game.getCurrentPlayer() != game.getHumanPlayerID()) {
                    Toast message = Toast.makeText(getContext(), "The computer picks a fruit!", Toast.LENGTH_SHORT);
                    message.show();
                }
                else {
                    Toast message = Toast.makeText(getContext(), "You pick a fruit!", Toast.LENGTH_SHORT);
                    message.show();
                }
            }break;

            case 5: {
                if(game.getCurrentPlayer() != game.getHumanPlayerID()) {
                    Toast message = Toast.makeText(getContext(), "the computer skips a turn!", Toast.LENGTH_SHORT);
                    message.show();
                }
                else {
                    Toast message = Toast.makeText(getContext(), "You skip a turn!", Toast.LENGTH_SHORT);
                    message.show();
                }
            }break;

            case 6: {
                Toast message = Toast.makeText(getContext(), "The corback approaches the orchard!", Toast.LENGTH_SHORT);
                message.show();
            }break;
        }
    }

    public void showEndGameMessage() {
        if(game.isGameFinished() && !game.hasCorbackWon()) {
            if(game.getCurrentPlayer() == game.getHumanPlayerID()) {
                Toast message = Toast.makeText(getContext(), "The game is finished! You, " + game.getPlayerName() + ", win in " + game.getNbTurn() + " turns!", Toast.LENGTH_LONG);
                message.show();
            }
            else {
                Toast message = Toast.makeText(getContext(), "The game is finished! The computer wins in " + game.getNbTurn() + " turns!", Toast.LENGTH_LONG);
                message.show();
            }
        }
        else if(!game.isGameFinished() && game.hasCorbackWon()) {
            Toast message = Toast.makeText(getContext(), "Mwahahah! The corback wins in " + game.getNbTurn() + " turns!", Toast.LENGTH_LONG);
            message.show();
        }


        popupWindow = new PopupWindow();
        popupWindow.setContentView(LayoutInflater.from(this.getContext()).inflate(R.layout.popup_game, null,false));
        popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);

        Button buttonYes = (Button)popupWindow.getContentView().findViewById(R.id.buttonPopupYes);
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().recreate();
            }
        });

        Button buttonNo = (Button)popupWindow.getContentView().findViewById(R.id.buttonPopupNo);
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finishActivity(0);
            }
        });
    }

    public void updateGame() {
        updateOrchard();
        updateRaven();
        updateScore();
    }

    private void updateOrchard() {
        switch(game.getRemainingGreenFruit()) {
            case 4: {
                greenFruit.setImageResource(R.drawable.vert4);
                //greenFruit.setImageDrawable(getResources().getDrawable(R.drawable.vert4));
            }break;

            case 3: {
                greenFruit.setImageResource(R.drawable.vert3);
                //greenFruit.setImageDrawable(getResources().getDrawable(R.drawable.vert3));
            }break;

            case 2: {
                greenFruit.setImageResource(R.drawable.vert2);
                //greenFruit.setImageDrawable(getResources().getDrawable(R.drawable.vert2));
            }break;

            case 1: {
                greenFruit.setImageResource(R.drawable.vert1);
                //greenFruit.setImageDrawable(getResources().getDrawable(R.drawable.vert1));
            }break;

            default: {

                greenFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingOrangeFruit()) {
            case 4: {
                orangeFruit.setImageResource(R.drawable.orange4);
                //orangeFruit.setImageDrawable(getResources().getDrawable(R.drawable.orange4));
            }break;

            case 3: {
                orangeFruit.setImageResource(R.drawable.orange3);
                //orangeFruit.setImageDrawable(getResources().getDrawable(R.drawable.orange3));
            }break;

            case 2: {
                orangeFruit.setImageResource(R.drawable.orange2);
                //orangeFruit.setImageDrawable(getResources().getDrawable(R.drawable.orange2));
            }break;

            case 1: {
                orangeFruit.setImageResource(R.drawable.orange1);
                //orangeFruit.setImageDrawable(getResources().getDrawable(R.drawable.orange1));
            }break;

            default: {
                orangeFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingVioletFruit()) {
            case 4: {
                violetFruit.setImageResource(R.drawable.violet4);
               // violetFruit.setImageDrawable(getResources().getDrawable(R.drawable.violet4));
            }break;

            case 3: {
                violetFruit.setImageResource(R.drawable.violet3);
                //violetFruit.setImageDrawable(getResources().getDrawable(R.drawable.violet3));
            }break;

            case 2: {
                violetFruit.setImageResource(R.drawable.violet2);
                //violetFruit.setImageDrawable(getResources().getDrawable(R.drawable.violet2));
            }break;

            case 1: {
                violetFruit.setImageResource(R.drawable.violet1);
                //violetFruit.setImageDrawable(getResources().getDrawable(R.drawable.violet1));
            }break;

            default: {
                violetFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingYellowFruit()) {
            case 4: {
                yellowFruit.setImageResource(R.drawable.rouge4);
                //yellowFruit.setImageDrawable(getResources().getDrawable(R.drawable.rouge4));
            }break;

            case 3: {
                yellowFruit.setImageResource(R.drawable.rouge3);
                //yellowFruit.setImageDrawable(getResources().getDrawable(R.drawable.rouge3));
            }break;

            case 2: {
                yellowFruit.setImageResource(R.drawable.rouge2);
                //yellowFruit.setImageDrawable(getResources().getDrawable(R.drawable.rouge2));
            }break;

            case 1: {
                yellowFruit.setImageResource(R.drawable.rouge1);
                //yellowFruit.setImageDrawable(getResources().getDrawable(R.drawable.rouge1));
            }break;

            default: {
                yellowFruit.setImageDrawable(null);
            }break;
        }
    }

    private void updateRaven() {
        switch(game.getRavenPosition()) {
            case 8: {
                raven.setImageResource(R.drawable.corbeaugagne);
                //raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeaugagne));
            }break;

            case 7: {
                raven.setImageResource(R.drawable.corbeau6);
                //raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau6));
            }break;

            case 6: {
                raven.setImageResource(R.drawable.corbeau5);
                //raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau5));
            }break;

            case 5: {
                raven.setImageResource(R.drawable.corbeau4);
                raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau4));
            }break;

            case 4: {
                raven.setImageResource(R.drawable.corbeau3);
                //raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau3));
            }break;

            case 3: {
                raven.setImageResource(R.drawable.corbeau2);
                //raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau2));
            }break;

            case 2: {
                raven.setImageResource(R.drawable.corbeau1);
                //raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeau1));
            }break;

            default: {
                raven.setImageResource(R.drawable.corbeaudepart);
                //raven.setImageDrawable(getResources().getDrawable(R.drawable.corbeaudepart));
            }break;
        }
    }

    private void updateScore() {

    }
}
