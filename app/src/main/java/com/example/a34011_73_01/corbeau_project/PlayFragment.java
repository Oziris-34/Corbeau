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

/**
 * Created by 34011-73-09 on 27/10/2016.
 */

public class PlayFragment extends Fragment {

    private Game game;

    private MediaPlayer backgroundMusicPlayer;

    private boolean isGameFinished;

    private ImageView greenFruitTree;
    private ImageView orangeFruitTree;
    private ImageView violetFruitTree;
    private ImageView yellowFruitTree;

    private ImageView raven;

    private ImageButton dice;

    private Handler gameAsyncHandler;

    private PopupWindow newGamePopupWindow;

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

        newGamePopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button buttonYes = (Button)popupView.findViewById(R.id.buttonPopupYes);
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().recreate();
                newGamePopupWindow.dismiss();
            }
        });

        Button buttonNo = (Button)popupView.findViewById(R.id.buttonPopupNo);
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                newGamePopupWindow.dismiss();
            }
        });

        return playContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        backgroundMusicPlayer = MediaPlayer.create(getContext(), R.raw.bgm);
        if(backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setLooping(true);
            backgroundMusicPlayer.start();
        }

        gameAsyncHandler = new Handler();

        isGameFinished = false;

        greenFruitTree = (ImageView)getView().findViewById(R.id.orchardGreenFruit);
        orangeFruitTree = (ImageView)getView().findViewById(R.id.orchardOrangeFruit);
        violetFruitTree = (ImageView)getView().findViewById(R.id.orchardVioletFruit);
        yellowFruitTree = (ImageView)getView().findViewById(R.id.orchardYellowFruit);

        raven = (ImageView)getView().findViewById(R.id.raven);

        dice = (ImageButton)getView().findViewById(R.id.imageButton7);
        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isGameFinished) {
                    doTurn();
                }
            }
        });

        if(game.getCurrentPlayer() != game.getHumanPlayerID()) {
            showToast(getContext().getString(R.string.computer_starts));

            dice.setAlpha(0.0f);
            dice.setEnabled(false);

            gameAsyncHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int result = game.doTurn();

                    showTurnResultMessage(result);

                    updateGameView();

                    game.nextPlayer();

                    showToast(getContext().getString(R.string.player_turn));

                    dice.setAlpha(1.0f);
                    dice.setEnabled(true);
                }
            }, 3000);
        }
        else {
            showToast(getContext().getString(R.string.player_starts));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if(backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
            backgroundMusicPlayer.release();
        }

        newGamePopupWindow.isShowing();
        newGamePopupWindow.dismiss();
    }

    private void doTurn() {
        int result = game.doTurn(); //The player's turn
        showTurnResultMessage(result);

        updateGameView();

        isGameFinished = game.isTreeEmpty() || game.hasCorbackWon();
        if(!isGameFinished) {
            showToast(getContext().getString(R.string.computer_turn));

            dice.setAlpha(0.0f);
            dice.setEnabled(false);

            game.nextPlayer();

            gameAsyncHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int result = game.doTurn();
                    showTurnResultMessage(result);

                    updateGameView();

                    isGameFinished = game.isTreeEmpty() || game.hasCorbackWon();
                    if(!isGameFinished) {
                        game.nextPlayer();

                        showToast(getContext().getString(R.string.player_turn));
                    }
                    else {
                        showEndGameMessage();
                    }

                    dice.setAlpha(1.0f);
                    dice.setEnabled(true);
                }
            }, 3000);
        }
        else {
            showEndGameMessage();
        }
    }

    private Toast showToast(String message) {
        Toast messageToast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        messageToast.setGravity(Gravity.BOTTOM, 0, 0);
        messageToast.show();

        return messageToast;
    }

    private void showTurnResultMessage(int result) {
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

        final Toast messageToast = showToast(message);

        gameAsyncHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                messageToast.cancel();
            }
        }, 1000);
    }

    private void showEndGameMessage() {
        String message = "";

        if(game.isTreeEmpty() && !game.hasCorbackWon()) {
            if(game.getCurrentPlayer() == game.getHumanPlayerID()) {
                message = "The game is finished! You, " + game.getPlayerName() + ", win in " + game.getNbTurn() + " turns!";
            }
            else {
                message = "The game is finished! The computer wins in " + game.getNbTurn() + " turns!";
            }
        }
        else if(!game.isTreeEmpty() && game.hasCorbackWon()) {
            message = "Mwahahah! The corback wins in " + game.getNbTurn() + " turns!";
        }

        showToast(message);

        showPopupWindow();
    }

    private void showPopupWindow() {
        gameAsyncHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newGamePopupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);
            }
        }, 4000);
    }

    private void updateGameView() {
        updateRavenPosition();
        updateOrchardView();
    }

    private void updateOrchardView() {
        switch(game.getRemainingGreenFruit()) {
            case 4: {
                greenFruitTree.setImageResource(R.drawable.vert4);
            }break;

            case 3: {
                greenFruitTree.setImageResource(R.drawable.vert3);
            }break;

            case 2: {
                greenFruitTree.setImageResource(R.drawable.vert2);
            }break;

            case 1: {
                greenFruitTree.setImageResource(R.drawable.vert1);
            }break;

            default: {

                greenFruitTree.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingOrangeFruit()) {
            case 4: {
                orangeFruitTree.setImageResource(R.drawable.orange4);
            }break;

            case 3: {
                orangeFruitTree.setImageResource(R.drawable.orange3);
            }break;

            case 2: {
                orangeFruitTree.setImageResource(R.drawable.orange2);
            }break;

            case 1: {
                orangeFruitTree.setImageResource(R.drawable.orange1);
            }break;

            default: {
                orangeFruitTree.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingVioletFruit()) {
            case 4: {
                violetFruitTree.setImageResource(R.drawable.violet4);
            }break;

            case 3: {
                violetFruitTree.setImageResource(R.drawable.violet3);
            }break;

            case 2: {
                violetFruitTree.setImageResource(R.drawable.violet2);
            }break;

            case 1: {
                violetFruitTree.setImageResource(R.drawable.violet1);
            }break;

            default: {
                violetFruitTree.setImageDrawable(null);
            }break;
        }

        switch(game.getRemainingYellowFruit()) {
            case 4: {
                yellowFruitTree.setImageResource(R.drawable.rouge4);
            }break;

            case 3: {
                yellowFruitTree.setImageResource(R.drawable.rouge3);
            }break;

            case 2: {
                yellowFruitTree.setImageResource(R.drawable.rouge2);
            }break;

            case 1: {
                yellowFruitTree.setImageResource(R.drawable.rouge1);
            }break;

            default: {
                yellowFruitTree.setImageDrawable(null);
            }break;
        }
    }

    private void updateRavenPosition() {
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
}
