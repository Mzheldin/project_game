package ru.geekbrains.stargame.pool;

import com.badlogic.gdx.audio.Sound;
import ru.geekbrains.stargame.base.SpritesPool;
import ru.geekbrains.stargame.sprite.Enemy;
import ru.geekbrains.stargame.math.Rect;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Rect worldBounds;
    private Sound shootSound;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound shootSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.shootSound = shootSound;
        this.explosionPool = explosionPool;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, worldBounds, shootSound);
    }
}
