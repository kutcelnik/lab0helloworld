package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    // Константы диапазона рандомных чисел
    public static final int MIN_RANDOM = -50;
    public static final int MAX_RANDOM = 50;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Ввод размеров
        System.out.print("Введіть висоту матриці (до 20): ");
        int rows = scanner.nextInt();

        System.out.print("Введіть ширину матриці (до 20): ");
        int cols = scanner.nextInt();

        if (rows > 20 || cols > 20) {
            System.out.println("Помилка: максимальний розмір 20x20.");
            return;
        }

        // Выбор типа заполнения
        System.out.println("Оберіть спосіб створення матриці:");
        System.out.println("1 — ручне введення");
        System.out.println("2 — рандомне створення");

        int choice = scanner.nextInt();
        int[][] matrix;

        if (choice == 1) {
            matrix = createMatrixManual(rows, cols, scanner);
        } else {
            matrix = createMatrixRandom(rows, cols);
        }

        // Вывод матрицы
        System.out.println("\nМатриця:");
        printMatrix(matrix);

        // Анализы
        int min = findMin(matrix);
        int max = findMax(matrix);
        double avg = findAverage(matrix);
        double geoAvg = findGeometricAverage(matrix);

        // Результаты
        System.out.println("\nМінімальний елемент: " + min);
        System.out.println("Максимальний елемент: " + max);
        System.out.println("Середнє арифметичне: " + avg);
        System.out.println("Середнє геометричне: " + geoAvg);
    }

    //Методы

    // Создание вручную
    public static int[][] createMatrixManual(int rows, int cols, Scanner scanner) {
        int[][] m = new int[rows][cols];
        System.out.println("Вводьте елементи матриці:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("m[" + i + "][" + j + "] = ");
                m[i][j] = scanner.nextInt();
            }
        }
        return m;
    }

    // Создание случайной матрицы
    public static int[][] createMatrixRandom(int rows, int cols) {
        Random rand = new Random();
        int[][] m = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = rand.nextInt(MAX_RANDOM - MIN_RANDOM + 1) + MIN_RANDOM;
            }
        }
        return m;
    }

    // Вывод
    public static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    // Поиск минимума
    public static int findMin(int[][] m) {
        int min = m[0][0];
        for (int[] row : m) {
            for (int v : row) {
                if (v < min) min = v;
            }
        }
        return min;
    }

    // Поиск максимума
    public static int findMax(int[][] m) {
        int max = m[0][0];
        for (int[] row : m) {
            for (int v : row) {
                if (v > max) max = v;
            }
        }
        return max;
    }

    // Среднее арифметическое
    public static double findAverage(int[][] m) {
        double sum = 0;
        int count = 0;
        for (int[] row : m) {
            for (int v : row) {
                sum += v;
                count++;
            }
        }
        return sum / count;
    }

    // Среднее геометрическое
    public static double findGeometricAverage(int[][] m) {
        double product = 1;
        int count = 0;

        for (int[] row : m) {
            for (int v : row) {
                product *= Math.abs(v); // геометричне середнє не працює з відʼємними
                count++;
            }
        }

        return Math.pow(product, 1.0 / count);
    }
}

