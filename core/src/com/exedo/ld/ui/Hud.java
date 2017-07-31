package com.exedo.ld.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.exedo.ld.LudumDare;

public class Hud implements Disposable{
    public Stage stage;
    private Viewport port;

    private float timeCount;
    private float fuelTimer;
    private float consumptionTime;
    private int seconds;
    private int minutes;
    private int score;
    private int maxFuel;
    private int fuel;

    Label countdownLabel;
    Label scoreLabel;
    Label fuelLabel;

    public Hud(SpriteBatch sb) {
        minutes = 0;
        seconds = 0;
        score = 0;
        maxFuel = 10;
        consumptionTime = 1;
        fuel = maxFuel;

        port = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(port, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(minutes + ":" + String.format("%02d", seconds), new Label.LabelStyle(new BitmapFont(Gdx.files.internal("hud.fnt")), Color.WHITE));
        scoreLabel = new Label("Batteries collected: " + score, new Label.LabelStyle(new BitmapFont(Gdx.files.internal("hud.fnt")), Color.WHITE));
        fuelLabel = new Label("ENERGY: " + fuel + "/" + maxFuel, new Label.LabelStyle(new BitmapFont(Gdx.files.internal("hud.fnt")), Color.WHITE));

        table.add(countdownLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);
        table.add(fuelLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public void update(float delta) {
        timeCount += delta;
        fuelTimer += delta;
        if(timeCount >= 1) {
            seconds++;
            if(seconds >= 60) {
                minutes++;
                seconds = 0;
            }
            countdownLabel.setText(minutes + ":" + String.format("%02d", seconds));
            timeCount = 0;
        }
        decreaseFuel();
        fuelLabel.setText("ENERGY: " + fuel + "/" + maxFuel);

        LudumDare.score = score;
        LudumDare.minutes = minutes;
        LudumDare.seconds = seconds;
    }

    public void decreaseFuel() {
        if(fuelTimer >= consumptionTime) {
            fuel--;
            fuelTimer = 0;
        }
    }

    public void addScore() {
        score ++;
        scoreLabel.setText("Batteries collected: " + score);
    }

    public void setFuel(int value) {
        if(fuel + value > maxFuel) {
            fuel = maxFuel;
        } else {
            fuel += value;
        }
    }
    public int getFuel() {return fuel; }
    public int getMaxFuel() {return maxFuel; }
    public int getScore() {return score; }
    public void setMaxFuel(int value) {maxFuel += value; }
    public void setConsumptionTime(float value) {consumptionTime = value; }
    public float getConsumptionTime() {return consumptionTime; }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
