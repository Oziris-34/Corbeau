package com.example.a34011_73_01.corbeau_project;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by 34011-73-09 on 27/10/2016.
 */

public class PlayFragment extends Fragment {

    private MediaPlayer mediaPlayer;

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
    public void onAttach(Context context) {
        super.onAttach(context);

        game = new Game();
        game.setPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        Bundle bundle = this.getArguments();
        game.setPlayerName(bundle.getString("playerName"));

        View playContentView = inflater.inflate(R.layout.fragment_play, container, false);
        View popupView = inflater.inflate(R.layout.popup_game, null);

        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button buttonYes = (Button)popupView.findViewById(R.id.buttonPopupYes);
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().recreate();
                popupWindow.dismiss();
            }
        });

        Button buttonNo = (Button)popupView.findViewById(R.id.buttonPopupNo);
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                popupWindow.dismiss();
            }
        });

        return playContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.bgm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

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
            Toast message = Toast.makeText(getContext(), getContext().getString(R.string.computer_starts), Toast.LENGTH_SHORT);
            message.setGravity(Gravity.BOTTOM, 0, 0);
            message.show();

            de.setAlpha(0.0f);

            de.setEnabled(false);

            gameHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int result = game.doTurn();

                    showTurnResult(result);

                    updateGame();

                    game.nextPlayer();

                    Toast handlerMessage = Toast.makeText(getContext(), getContext().getString(R.string.player_turn), Toast.LENGTH_SHORT);
                    handlerMessage.setGravity(Gravity.BOTTOM, 0, 0);
                    handlerMessage.show();

                    de.setAlpha(1.0f);
                    de.setEnabled(true);
                }
            }, 3000);
        }
        else {
            Toast message = Toast.makeText(getContext(), getContext().getString(R.string.player_starts), Toast.LENGTH_SHORT);
            message.setGravity(Gravity.BOTTOM, 0, 0);
            message.show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mediaPlayer.setLooping(false);
        mediaPlayer.stop();
        mediaPlayer.release();
        popupWindow.isShowing();
        popupWindow.dismiss();
    }

    public void doTurn() {
        int result = game.doTurn(); //The player's turn

        showTurnResult(result);

        updateGame();

        isGameFinished = game.isGameFinished() || game.hasCorbackWon();

        if(!isGameFinished) {
            final Toast message = Toast.makeText(getContext(), getContext().getString(R.string.computer_turn), Toast.LENGTH_SHORT);
            message.setGravity(Gravity.BOTTOM, 0, 0);
            message.show();

            de.setAlpha(0.0f);
            de.setEnabled(false);

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

                        Toast handlerMessage = Toast.makeText(getContext(), getContext().getString(R.string.player_turn), Toast.LENGTH_SHORT);
                        handlerMessage.setGravity(Gravity.BOTTOM, 0, 0);
                        handlerMessage.show();
                    }
                    else {
                        showEndGameMessage();
                    }

                    de.setAlpha(1.0f);
                    de.setEnabled(true);
                }
            }, 3000);
        }
        else {
            showEndGameMessage();
        }
    }

    public void showTurnResult(int result) {
        String message = "";

        switch(result) {
            case 1:{
                if(game.getCurrentPlayer() != game.getHumanPlayerID()) {
                    message = getContext().getString(R.string.computer_pick_green_fruit);
                }
                else {
                    message = getContext().getString(R.string.player_pick_green_fruit);
                }
            }break;

            case 2:{
                if(game.getCurrentPlayer() != game.getHumanPlayerID()) {
                    message = getContext().getString(R.string.computer_pick_orange_fruit);
                }
                else {
                    message = getContext().getString(R.string.player_pick_orange_fruit);
                }
            }break;

            case 3: {
                if(game.getCurrentPlayer() != game.getHumanPlayerID()) {
                    message = getContext().getString(R.string.computer_pick_violet_fruit);
                }
                else {
                    message = getContext().getString(R.string.player_pick_violet_fruit);
                }
            }break;

            case 4: {
                if(game.getCurrentPlayer() != game.getHumanPlayerID()) {
                    message = getContext().getString(R.string.computer_pick_red_fruit);
                }
                else {
                    message = getContext().getString(R.string.player_pick_red_fruit);
                }
            }break;

            case 5: {
                if(game.getCurrentPlayer() != game.getHumanPlayerID()) {
                    message = getContext().getString(R.string.computer_skips_turn);
                }
                else {
                    message = getContext().getString(R.string.player_skips_turn);
                }
            }break;

            case 6: {
                message = getContext().getString(R.string.raven_approach);
            }break;
        }

        final Toast messageToast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        messageToast.setGravity(Gravity.BOTTOM, 0, 0);
        messageToast.show();

        gameHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                messageToast.cancel();
            }
        }, 1000);
    }

    public void showEndGameMessage() {
        String message = "";

        if(game.isGameFinished() && !game.hasCorbackWon()) {
            if(game.getCurrentPlayer() == game.getHumanPlayerID()) {
                message = "The game is finished! You, " + game.getPlayerName() + ", win in " + game.getNbTurn() + " turns!";
            }
            else {
                message = "The game is finished! The computer wins in " + game.getNbTurn() + " turns!";
            }
        }
        else if(!game.isGameFinished() && game.hasCorbackWon()) {
            message = "Mwahahah! The corback wins in " + game.getNbTurn() + " turns!";
        }

        final Toast messageToast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
        messageToast.setGravity(Gravity.BOTTOM, 0, 0);
        messageToast.show();

        showPopupWindow();
    }

    public void showPopupWindow() {
        gameHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);
            }
        }, 4000);
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
            }break;

            case 3: {
                greenFruit.setImageResource(R.drawable.vert3);
            }break;

            case 2: {
                greenFruit.setImageResource(R.drawable.vert2);
            }break;

            case 1: {
                greenFruit.setImageResource(R.drawable.vert1);
            }break;

            default: {

                greenFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingOrangeFruit()) {
            case 4: {
                orangeFruit.setImageResource(R.drawable.orange4);
            }break;

            case 3: {
                orangeFruit.setImageResource(R.drawable.orange3);
            }break;

            case 2: {
                orangeFruit.setImageResource(R.drawable.orange2);
            }break;

            case 1: {
                orangeFruit.setImageResource(R.drawable.orange1);
            }break;

            default: {
                orangeFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingVioletFruit()) {
            case 4: {
                violetFruit.setImageResource(R.drawable.violet4);
            }break;

            case 3: {
                violetFruit.setImageResource(R.drawable.violet3);
            }break;

            case 2: {
                violetFruit.setImageResource(R.drawable.violet2);
            }break;

            case 1: {
                violetFruit.setImageResource(R.drawable.violet1);
            }break;

            default: {
                violetFruit.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingYellowFruit()) {
            case 4: {
                yellowFruit.setImageResource(R.drawable.rouge4);
            }break;

            case 3: {
                yellowFruit.setImageResource(R.drawable.rouge3);
            }break;

            case 2: {
                yellowFruit.setImageResource(R.drawable.rouge2);
            }break;

            case 1: {
                yellowFruit.setImageResource(R.drawable.rouge1);
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
            }break;

            case 7: {
                raven.setImageResource(R.drawable.corbeau6);
            }break;

            case 6: {
                raven.setImageResource(R.drawable.corbeau5);
            }break;

            case 5: {
                raven.setImageResource(R.drawable.corbeau4);
            }break;

            case 4: {
                raven.setImageResource(R.drawable.corbeau3);
            }break;

            case 3: {
                raven.setImageResource(R.drawable.corbeau2);
            }break;

            case 2: {
                raven.setImageResource(R.drawable.corbeau1);
            }break;

            default: {
                raven.setImageResource(R.drawable.corbeaudepart);
            }break;
        }
    }

    private void updateScore() {

    }
}
