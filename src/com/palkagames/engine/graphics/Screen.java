package com.palkagames.engine.graphics;

import com.palkagames.engine.level.tile.Tile;

import java.util.Random;

/**
 * Created by Zver on 05.07.2016.
 */
public class Screen {
    //длина и высота экрана
    public int width, height;
    //буфер пикселей экрана
    public int[] pixels;
    //размер карты
    private final int MAP_SIZE = 64;
    //размер битовой маски карты
    private final int MAP_MASK = MAP_SIZE - 1;

    public int xOffset, yOffset;
    //буфер тайлов
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    //рандом
    private Random random = new Random();

    public Screen(int width, int height) {
        //конструктор устанавливающий длину и высоту
        this.width = width;
        this.height = height;
        //и массив пикселей
        pixels = new int[width * height];
        //заполняем буфер тайлов рандомным цветом
        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    //метод очистки экрана
    public void clear(){
        //очищаем каждый пиксель экрана 0
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    //метод отрисовки экрана
    public void renderTile(int xp, int yp, Tile tile){
        //вычитаем сдвиг по х и у
        xp -= xOffset;
        yp -= yOffset;

        //проходим по тайлам и устанавливаем значение пикселей экрана в значение пикселей тайла
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height){
                    break;
                }
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }
    //установки сдвига по х и у
    public void setOffset(int xOffset, int yOffset){
        //устнавливаем сдвиг по х и у
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
