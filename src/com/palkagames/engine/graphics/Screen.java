package com.palkagames.engine.graphics;

/**
 * Created by Zver on 05.07.2016.
 */
public class Screen {
    //длина и высота экрана
    private int width, height;
    //буфер пикселей экрана
    public int[] pixels;


    public Screen(int width, int height) {
        //конструктор устанавливающий длину и высоту
        this.width = width;
        this.height = height;
        //и массив пикселей
        pixels = new int[width * height];
    }

    //метод отрисовки экрана
    public void render(){
        // заполняем буфер пикселей сплошным цветом
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x + y * width] = 0xff00ff;
            }
        }
    }
}
