package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class BtnExit extends Sprite {

    public BtnExit(TextureAtlas atlas){
        super(atlas.findRegion("btExit"));
        setHeightProportion(0.2f);
        pos.set(0.25f, -0.1f);
    }

    @Override
    public void resize(Rect worldBounds) {

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isMe(touch)) scale = 0.75f;
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        scale = 1f;
        if (isMe(touch)) Gdx.app.exit();
        return super.touchUp(touch, pointer);
    }
}
