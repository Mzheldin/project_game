package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;

public class BtnPlay extends Sprite {

    public BtnPlay(TextureAtlas textureAtlas){
        super(textureAtlas.findRegion("btPlay"));
        setHeightProportion(0.2f);
        pos.set(-0.25f, -0.1f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isMe(touch)) scale = 0.75f;
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        scale = 1f;
        if (isMe(touch)) {

        }
        return super.touchUp(touch, pointer);
    }


}
