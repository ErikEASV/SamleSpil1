package com.example.samlespil1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;

public class SamleSpil1 extends Application {
    public static void main(String[] args) {
        try {
            launch();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }

    public void start(Stage mainStage)
    {
        // Set window size
        final int XSIZE = 600;
        final int YSIZE = 600;

        mainStage.setTitle("SamleSpil");

        BorderPane root = new BorderPane();

        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);

        Canvas canvas = new Canvas(XSIZE, YSIZE);
        GraphicsContext context = canvas.getGraphicsContext2D();

        root.setCenter(canvas);

        // Aflæsning af tastatur
        ArrayList<String> inputlist = new ArrayList<String>();
        mainScene.setOnKeyPressed(
                (KeyEvent event) ->
                {
                    String keyName = event.getCode().toString();
                    if (!inputlist.contains(keyName))
                        inputlist.add(keyName);
                }
        );
        mainScene.setOnKeyReleased(
                (KeyEvent event) ->
                {
                    String keyName = event.getCode().toString();
                    inputlist.remove(keyName);
                }
        );

        // Init. af spil
        // Opret skildpadde og sæt den forneden
        Sprite turtle = new Sprite();
        turtle.getPosition().set(XSIZE/2, YSIZE - 100);
        turtle.setImage("turtle.png");

        // Opret 20 søstjerner og sæt dem tilfældigt indenfor et område midt i vinduet
        ArrayList<Sprite> starfishList = new ArrayList<Sprite>();
        int starfishCount = 20;
        for (int n = 0; n < starfishCount; n++)
        {
            Sprite starfish = new Sprite();
            double x = Math.random() * XSIZE*0.6 + 100;
            double y = Math.random() * YSIZE*0.6 + 100;
            starfish.getPosition().set(x, y);
            starfish.setImage("starfish.png");

            starfishList.add(starfish);
        }

        // Gameloop
        AnimationTimer gameloop = new AnimationTimer()
        {
            public void handle(long nanotime)
            {
                // UPDATE game world
                turtle.getVelocity().set(0,0);

                // Read input: Handle keys
                double speed = 2;
                if (inputlist.contains("LEFT"))
                    turtle.getVelocity().add(-speed, 0);
                if (inputlist.contains("RIGHT"))
                    turtle.getVelocity().add(speed, 0);
                if (inputlist.contains("UP"))
                    turtle.getVelocity().add(0, -speed);
                if (inputlist.contains("DOWN"))
                    turtle.getVelocity().add(0, speed);
                if (inputlist.contains("E"))
                    System.exit(0);

                // Update game tokens
                // move turtle
                turtle.getPosition().add(turtle.getVelocity());

                // move starfish a little
                for (Sprite starfish : starfishList) {
                    starfish.getVelocity().set(Math.random()*2-1, Math.random()*2-1);
                    starfish.getPosition().add(starfish.getVelocity());
                }

                // check if turtle eat starfish
                for (int s = 0; s < starfishList.size(); s++)
                {
                    Sprite starfish = starfishList.get(s);
                    if (turtle.overlaps(starfish))
                        starfishList.remove(starfish);
                }

                // PAINT game world
                // background
                context.setFill(Color.BLUE);
                context.fillRect(0, 0, 600, 600);

                // draw all starfish
                for (Sprite starfish : starfishList)
                    starfish.render(context);

                // draw turtle on top
                turtle.render(context);

                // display score or win message
                int starfishLeft = starfishList.size();
                String text;
                if (starfishLeft > 0)
                    text = "Starfish left: " + starfishLeft;
                else
                    text = "You win!";

                context.setFont(new Font("Arial", 36));
                context.setFill(Color.YELLOW);
                context.setStroke(Color.ORANGE);
                context.fillText(text, 25, 30);
                context.strokeText(text, 25, 30);
            }
        };

        // Start gameloop and show stage
        gameloop.start();

        mainStage.show();
    }
}