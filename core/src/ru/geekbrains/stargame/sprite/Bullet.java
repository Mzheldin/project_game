package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Ship;
import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.ExplosionPool;

public class Bullet extends Sprite {

    private Rect worldBounds;
    private Vector2 v = new Vector2();
    private int damage;
    private Ship owner;
    private ExplosionPool explosionPool;

    public Bullet(ExplosionPool explosionPool) {
        this.explosionPool = explosionPool;
        regions = new TextureRegion[1];
    }

    public void set(
            Ship owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rect worldBounds,
            int damage
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        if (getOwner().isFireMode()){
            bulletboom();
        }
        this.pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Ship getOwner() {
        return owner;
    }

    public void setOwner(Ship owner) {
        this.owner = owner;
    }

    public void bulletboom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight() * 2f, pos, false);
    }
}

