package ru.geekbrains.stargame.pool;

import ru.geekbrains.stargame.base.SpritesPool;
import ru.geekbrains.stargame.sprite.Ship;

public class ShipPool extends SpritesPool<Ship> {

    @Override
    protected Ship newObject() {
        return new Ship();
    }
}
