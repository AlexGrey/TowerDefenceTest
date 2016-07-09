package com.palkagames.engine.level.tile;

import com.palkagames.engine.graphics.Screen;
import com.palkagames.engine.graphics.Sprite;

/**
 * Created by Zver on 09.07.2016.
 */
public class Tile {
    public int x,y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){

    }

    public boolean solid(){
        return false;
    }
}
