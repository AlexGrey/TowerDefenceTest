package com.palkagames.engine.level.tile;

import com.palkagames.engine.graphics.Screen;
import com.palkagames.engine.graphics.Sprite;

/**
 * Created by Zver on 09.07.2016.
 */
public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, this);
    }
}
