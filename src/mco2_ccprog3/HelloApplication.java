package mco2_ccprog3;

import farm.FarmingGame;
import gui.GUI;
import gui.scenes.OpeningScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {

//        FarmingGame farmingGame = new FarmingGame();
//        farmingGame.startSimulator();
          launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        OpeningScene openingScene = new OpeningScene();
        primaryStage = openingScene.getMainStage();
        primaryStage.show();
    }
}