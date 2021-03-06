package ru.geekbrains.stargame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.pool.ExplosionPool;
import ru.geekbrains.stargame.sprite.Bullet;
import ru.geekbrains.stargame.sprite.Explosion;

public abstract class Ship extends Sprite {

    protected Vector2 v = new Vector2();
    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected Rect worldBounds;

    protected Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int bulletDamage;

    protected float reloadInterval;
    protected float reloadTimer;

    protected float damageAnimateInterval = 0.1f;
    protected float damageAnimateTimer;

    protected int hp;
    protected TextureRegion bulletRegion;

    protected enum Mode {NORMAL, FIRE}
    protected Mode mode;

    private Sound shootSound;
    private Sound shootSound2;

    public Ship(TextureRegion region, int rows, int cols, int frames, Sound shootSound, Sound shootSound2) {
        super(region, rows, cols, frames);
        this.shootSound = shootSound;
        this.shootSound2 = shootSound2;
    }

    public Ship(Sound shootSound) {
        this.shootSound = shootSound;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
        if (mode == Mode.NORMAL) shootSound.play();
        else shootSound2.play();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= damageAnimateInterval) {
            frame = 0;
        }
    }

    public void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), pos, true);
    }

    public void damage(int damage) {
        frame = 1;
        damageAnimateTimer = 0f;
        hp -= damage;
        if (hp <= 0) {
            destroy();
        }
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }

    public boolean isFireMode() {
        return mode == Mode.FIRE;
    }
}
