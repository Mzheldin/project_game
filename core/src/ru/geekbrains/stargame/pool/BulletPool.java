package ru.geekbrains.stargame.pool;

import ru.geekbrains.stargame.base.SpritesPool;
import ru.geekbrains.stargame.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    ExplosionPool explosionPool;

    public BulletPool(ExplosionPool explosionPool){
        this.explosionPool = explosionPool;
    }

    @Override
    protected Bullet newObject() {
        return new Bullet(explosionPool);
    }
}
