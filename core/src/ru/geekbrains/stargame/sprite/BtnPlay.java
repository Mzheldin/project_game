package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.StarGame;
import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.screen.AnotherScreen;

public class BtnPlay extends Sprite {

    StarGame starGame;

    public BtnPlay(TextureAtlas textureAtlas, StarGame starGame){
        super(textureAtlas.findRegion("btPlay"));
        setHeightProportion(0.2f);
        pos.set(-0.25f, -0.1f);
        this.starGame = starGame;
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
            starGame.setScreen(new AnotherScreen());
        }
        return super.touchUp(touch, pointer);
    }


}
