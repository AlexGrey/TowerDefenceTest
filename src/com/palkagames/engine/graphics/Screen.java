package com.palkagames.engine.graphics;

import java.util.Random;

/**
 * Created by Zver on 05.07.2016.
 */
public class Screen {
    //длина и высота экрана
    private int width, height;
    //буфер пикселей экрана
    public int[] pixels;
    //размер карты
    private final int MAP_SIZE = 8;
    //размер битовой маски карты
    private final int MAP_MASK = 7;
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
    public void render(int xOffset, int yOffset){
        // заполняем буфер пикселей сплошным цветом
        for (int y = 0; y < height; y++) {
            int yy = y + yOffset;
            //выходим из цикла если вышли за границы высоты
            //if (yy < 0 || yy >= height) break;
            for (int x = 0; x < width; x++) {
                int xx = x + xOffset;
                //выходим из цикла если вышли за границы длины
                //if (xx < 0 || xx >= width) break;
                //один тайл на каждые 16 пикселей (не ясно зачем маска, и битовые сдвиги)
                int tileIndex = ((xx >> 4) & MAP_MASK) + ((yy >> 4) & MAP_MASK) * MAP_SIZE;
                //заполняем буфер пикселей тайлами
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }

}
