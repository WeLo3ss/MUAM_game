package game;

// Подключение необходимых библиотек
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


// Класс панели, которая является игровым полем
class pole extends JPanel {
    private Image shapka; // Закрытая переменная класса, в которую загружается шапка
    private Image fon; // Закрытая переменная класса, в которую загружается фон
    public int x = 800; // Открытая переменная класса, в которую загружается
    private int slogn; // Переменная сложности
    private podar[] gamePodar; // Массив подарков
    private Image end_game; // Изображения окончания игры
    public Timer timerUpdate, timerDraw, timerGame; // Два таймера
    public int ct = 0;
    public int sum=0;
    public int Time;

    // Конструктор класса
    public pole(int slogn) {
        this.slogn = slogn;
        // Загрузка шапки из файла
        try {
            shapka = ImageIO.read(new File("./hat.jpg"));
            JOptionPane.showMessageDialog(null, "Картинка для шапки успешно загружена.");
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Картинка для шапки не загружена.");
        }

        // Загрузка фона из файла
        try {
            fon = ImageIO.read(new File("./screen.jpg"));
            JOptionPane.showMessageDialog(null, "Картинка фона игры успешно загружена.");
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Картинка фона игры не загружена.");
        }

        // Загрузка окончания игры из файла
        try {
            end_game = ImageIO.read(new File("./end.jpg"));
            JOptionPane.showMessageDialog(null, "Картинка конца игры успешно загружена.");
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Картинка конца игры не загружена.");
        }

        // Загрузка изображений подарков
        gamePodar = new podar[15];
        for (int i = 0; i < 15; i++) {
            try {
                gamePodar[i] = new podar(ImageIO.read(new File("./gift" + i + ".png")));
                JOptionPane.showMessageDialog(null, "Картинка подарка  № " + i + " успешно загружена.");
            }
            catch(IOException ex) {
                JOptionPane.showMessageDialog(null, "Картинка подарка  № " + i + " не загружена.");
            }
        }

        // Создание таймера, который будет
        // раз в три секунды проверять подарки
        // и добавлять их на игровое поле
        timerUpdate = new Timer(3000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // Метод для проверки и добавления подарков на игровое поле
                updateStart();
            }
        });

        timerUpdate.start(); // Запуск таймера timerUpdate

        // Создание таймера, который будет перерисовывать
        // игровое поле 20 раз в секунду
        timerDraw = new Timer(50, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                repaint(); // Запуск метода перерисовки поля (public void paintComponent(Graphics gr))
            }
        });

        timerDraw.start(); // Запуск таймера для перерисовки

        timerGame = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Time+=1;
            }
        });

        timerGame.start();
    }

    // Метод, который отрисовывает графические объекты на панели
    public void paintComponent(Graphics gr){
        // Выполнить отрисовку сначала самого окна
        super.paintComponent(gr);
        gr.drawImage(fon, 0, 0, null); // Рисование фона
        gr.drawImage(shapka, x, 950, null); // Рисование шапки
        gr.drawString("Time: " + Time, 1100, 50);
        gr.drawString("Points: " + sum, 1200, 50);
        // Цикл, который отображает подарки на игровом поле
        // и проверяет пропущенные подарки
        for (int i = 0; i < 9; i++){
            gamePodar[i].draw(gr); // Отображение подарка
            if (gamePodar[i].act == true){ // Если подарок пропущен
                // Если подарок достиг нижней границы:
                if(gamePodar[i].y + gamePodar[i].img.getHeight(null) >= 900)
                    if (Math.abs(gamePodar[i].x - x) > 65){ // Если подарок пропущен
                        // Вывод картинки Окончания игры
                        gr.drawImage(end_game, 0, 0, null);
                        timerDraw.stop(); // Остановка таймера timerDraw
                        timerUpdate.stop();  // Остановка таймера timerUpdate
                        timerGame.stop();
                        break; // Прерывание цикла
                    } else {gamePodar[i].act = false;
                      sum+=1;
                    }

            }
        }
    }

    // Метод для проверки и добавления подарков на игровое поле
    private void updateStart(){
        int kol = 0; // Переменная для подсчёта подарков на игровом поле
        for(int i = 0; i < 9; i++){ // Цикл перебора всех подарков массива
            if(gamePodar[i].act == false){ // Если подарок не на игровом поле
                if (kol < slogn){
                    // Активизация подарка на игровом поле,
                    // вывод его сверху игрового поля
                    gamePodar[i].start();
                    ct = ct + 1;
                    break; // Прерывание цикла
                }
            } else kol++; // Если подарок на игровом поле
        }
    }

}