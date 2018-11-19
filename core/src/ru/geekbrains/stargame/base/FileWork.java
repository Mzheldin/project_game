package ru.geekbrains.stargame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.IOException;

import ru.geekbrains.stargame.screen.GameScreen;
import ru.geekbrains.stargame.sprite.MainShip;
import ru.geekbrains.stargame.utils.EnemiesEmmiter;

public class FileWork {

    private final String LINESEPARATOR = System.getProperty("line.separator");

    private String filename;
    private FileHandle file;

    public FileWork(String filename){
        this.filename = filename;
        if (Gdx.files.isLocalStorageAvailable()) this.file = Gdx.files.local(filename);
        else file = null;
    }

    public void saveGame(MainShip mainShip, GameScreen gameScreen, EnemiesEmmiter enemiesEmmiter){
        if (!mainShip.isDestroyed()) file.writeString(gameScreen.getFrags() + LINESEPARATOR + mainShip.getHp() + LINESEPARATOR + enemiesEmmiter.getLevel(), false);
        else file.writeString("", false);
    }

    public void loadGame(GameScreen gameScreen, MainShip mainShip, EnemiesEmmiter enemiesEmmiter){
        if (Gdx.files.local(filename).exists()){
            int i = 1;
            String s;
            try(BufferedReader reader = file.reader(8192)) {
                while ((s = reader.readLine()) != null) {
                    switch (i) {
                        case 1:
                            gameScreen.setFrags(Integer.parseInt(s));
                            break;
                        case 2:
                            mainShip.setHp(Integer.parseInt(s));
                            break;
                        case 3:
                            enemiesEmmiter.setLevel(Integer.parseInt(s));
                            break;
                    }
                    i++;
                }
            } catch (IOException e){
                System.out.println("File reading error");
                e.printStackTrace();
            }
        } else System.out.println("File not exists, start with default settings");
    }
}
