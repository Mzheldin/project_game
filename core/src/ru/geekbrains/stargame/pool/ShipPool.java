package ru.geekbrains.stargame.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.base.SpritesPool;
import ru.geekbrains.stargame.sprite.Ship;

public class ShipPool extends SpritesPool<Ship> {

    private TextureAtlas atlas;
    private BulletPool bulletPool;

    public ShipPool(TextureAtlas atlas, BulletPool bulletPool){
        this.atlas = atlas;
        this.bulletPool = bulletPool;
    }

    @Override
    protected Ship newObject() {
        return new Ship();
    }
}
