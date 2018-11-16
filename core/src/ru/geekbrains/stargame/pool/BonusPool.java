package ru.geekbrains.stargame.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.base.SpritesPool;
import ru.geekbrains.stargame.sprite.Bonus;

public class BonusPool extends SpritesPool<Bonus> {

    @Override
    protected Bonus newObject() {
        return new Bonus();
    }
}
