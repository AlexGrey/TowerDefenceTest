package com.palkagames.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Zver on 04.07.2016.
 */
public class Game extends Canvas implements Runnable{
    //длина игрового экрана
    public static final int width = 300;
    //высота игрового экрана
    public static final int height = width / 16 * 9;
    //масштабирование?
    public static final int scale = 3;

    //основной игровой поток
    private Thread thread;
    //контент панель
    private JFrame frame;
    //запущен игровой цикл или нет?
    private boolean running = false;

    //конструктор
    public Game(){
        //установка размера экрана
        Dimension size = new Dimension(width * scale, height * scale);
        //установка предпочитаемого разрешения
        setPreferredSize(size);
        //инициализация контент панели
        frame = new JFrame();
    }

    //старт основного потока
    public synchronized void start(){
        //игровой цикл может быть запущен
        running = true;
        //инициализация, и старт потока
        thread = new Thread(this, "Display");
        thread.start();
    }

    //остановка основного потока
    public synchronized void stop(){
        //игровой цикл не может быть запущен
        running = false;
        //попытка завершить поток
        try {
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //стартует при запуске потока
    @Override
    public void run() {
        //запускаем главный цикл
        while(running){
            //обновляем игровые данные
            update();
            //отрисовываем игровые данные
            render();
        }
    }

    //метод обновляющий игровое состояние(каждый кадр)
    public void update(){

    }

    //метод отрисовывающий картинку на экран(каждый кадр)
    public void render(){
        //буфер отрисовки, фронт, бэк буфер и тд
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            //создаем три буфера отрисовки
            createBufferStrategy(3);
            return;
        }

        //контекст графики для отрисовки буферов
        Graphics g = bs.getDrawGraphics();
        //очищаем экран черным цветом
        g.setColor(Color.BLACK);
        //рисуем прямоугольник на весь экран
        g.fillRect(0, 0, getWidth(), getHeight());
        //освобождаем ресурсы
        g.dispose();
        //показываем следующий доступный буфер
        bs.show();

    }

    //стартовый метод
    public static void main(String[] args) {
        //инициализация игры
        Game game = new Game();
        //запрещаем изменять размер экрана
        game.frame.setResizable(false);
        //установка заголовка
        game.frame.setTitle("TowerDefence");
        //добавляем на контент панель нашу игру
        game.frame.add(game);
        //устанавливает такой минимальный размер, чтобы в окно влезли все компоненты
        game.frame.pack();
        //при нажатии на крестик, закрываем окно
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //центрируем игровой окно
        game.frame.setLocationRelativeTo(null);
        //делаем окно видимым
        game.frame.setVisible(true);
        //старт игры
        game.start();
    }
}
