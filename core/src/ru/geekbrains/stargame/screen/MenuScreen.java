package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.StarGame;
import ru.geekbrains.stargame.base.Base2DScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprite.Background;
import ru.geekbrains.stargame.sprite.BtnExit;
import ru.geekbrains.stargame.sprite.BtnPlay;
import ru.geekbrains.stargame.sprite.Star;

public class MenuScreen extends Base2DScreen {

    private static final int STAR_COUNT = 256;

    private Texture bgTexture;
    private BtnExit btnExit;
    private BtnPlay btnPlay;
    private Background background;


    private TextureAtlas textureAtlas;
    private Star[] stars;

    private StarGame starGame;

    public MenuScreen (StarGame starGame){
        this.starGame = starGame;
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(bgTexture));
        textureAtlas = new TextureAtlas("menuAtlas.tpack");
        btnExit = new BtnExit(textureAtlas);
        btnPlay = new BtnPlay(textureAtlas, starGame);
        stars =new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(textureAtlas);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();

    }

    public void update(float delta) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        btnExit.draw(batch);
        btnPlay.draw(batch);
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < stars.length; i++) {
            stars[i].resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        textureAtlas.dispose();
        super.dispose();
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        btnPlay.touchDown(touch, pointer);
        btnExit.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        btnPlay.touchUp(touch, pointer);
        btnExit.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }
}
