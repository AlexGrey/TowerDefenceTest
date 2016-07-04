package com.palkagames.engine;

/**
 * Created by Zver on 04.07.2016.
 */
public class Game implements Runnable{
    //длина игрового экрана
    public static final int width = 800;
    //высота игрового экрана
    public static final int height = width / 16 * 9;
    //масштабирование?
    public static final int scale = 3;

    //основной игровой поток
    private Thread thread;

    //старт основного потока
    public synchronized void start(){
        //инициализация, и старт потока
        thread = new Thread(this, "Display");
        thread.start();
    }

    //остановка основного потока
    public synchronized void stop(){
        //попытка завершить поток
        try {
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
