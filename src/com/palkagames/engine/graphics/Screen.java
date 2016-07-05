package com.palkagames.engine.graphics;

/**
 * Created by Zver on 05.07.2016.
 */
public class Screen {
    //длина и высота экрана
    private int width, height;
    //буфер пикселей экрана
    public int[] pixels;

    //какие-то счетчики
    private int counter = 0;
    //счетчики предотвращения выхода за границы int
    private int xTime = 20;
    private int yTime = 20;

    public Screen(int width, int height) {
        //конструктор устанавливающий длину и высоту
        this.width = width;
        this.height = height;
        //и массив пикселей
        pixels = new int[width * height];
    }

    //метод очистки экрана
    public void clear(){
        //очищаем каждый пиксель экрана 0
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    //метод отрисовки экрана
    public void render(){
        //манипуляции со счетчиками, в дальнейшем изменится
        counter++;
        if (counter % 100 == 0){
            xTime--;
        }
        if (counter % 100 == 0){
            yTime--;
        }
        // заполняем буфер пикселей сплошным цветом
        for (int x = 0; x < width; x++) {
            //выходим из цикла если вышли за границы высоты
            if (yTime < 0 || yTime >= height) break;
            for (int y = 0; y < height; y++) {
                //выходим из цикла если вышли за границы длины
                if (xTime < 0 || xTime >= width) break;
                pixels[xTime + xTime * width] = 0xff00ff;
            }
        }
    }

}
