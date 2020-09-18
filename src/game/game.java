package game;
// Подключения необходимых библиотек

import javax.swing.*; // Работа с формами

// Главный класс игры
public class game {
    // Главный метод, который запускает игру
    public static void main(String[] args) {

        // Вызов диалогового окна для ввода сложности игры
        String rez = JOptionPane.showInputDialog(null, "Введите сложность игры от 1 до 9:", "Сложность игры", 1);

        // Помещение результата выбора в переменную целого типа
        int slogn = Integer.parseInt(rez)/*.charAt(0) - '0'*/;

        // Проверка, что введена цифра от 1 до 7
        if ((slogn >= 1)&&(slogn <= 9)){
            // Создание окна, в котором находится игровое поле
            okno window = new okno(slogn);
        }
    }
}



