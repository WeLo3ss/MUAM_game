package game;

// Подключение необходимых библиотек
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Класс окна, в котором размещено игровое поле
class okno extends JFrame {
    private pole gameP; // Закрытая Переменная класса - игровое поле
    private int slogn; // Закрытая Переменная класса - сложность игры

    // Обработчик событий нажатий на клавиши
    private class myKey implements KeyListener {

        // Метод, который срабатывает при нажатии
        public void keyPressed(KeyEvent e) {

            // Получение кода нажатой клавиши
            int key_ = e.getKeyCode();

            // Выход из программы, если нажат - Esc
            if (key_ == 27) System.exit(0);
            else if(key_ == 37) { // Если нажата клавиша влево

                // Контроль передвижения за пределы экраны
                if (gameP.x - 10 > -48) gameP.x -= 10;
                else gameP.x = 1352;
            }
            else if(key_ == 39) { // Если нажата клавиша вправо
                // Контроль передвижения за пределы экраны
                if (gameP.x + 10 < 1352) gameP.x += 10;
                else gameP.x = -48;
            }
        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    }

    // Конструктор класса
    public okno(int slogn) {
        // Помещение сложности, выбранной пользователем в переменную класса
        this.slogn = slogn;

        // Подключение обработчика события для клавиатуры к окну
        addKeyListener(new myKey());

        // Установка активности окна
        setFocusable(true);

        // Задание размеров и положения окна
        setBounds(0,0,1440,1980);

        // Задание заголовка окна
        setTitle("Игра: Новогодний дождь");

        // Создание объекта - игровое поля в окно
        gameP = new pole(slogn);

        // Прикрепление (вложение) панели - игровое поле - в окно
        Container con = getContentPane();
        con.add(gameP);

        // Сделать окно видимым
        setVisible(true);
    }
}