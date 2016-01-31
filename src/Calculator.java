import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Calculator {

    private Scanner scanner = new Scanner(System.in);

    private static void sleepingTime(long milisec) {

        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Стартовое сообщение в начале работы программы
    public void startMessages() {

        System.out.printf("Hi! This is matrix calculator!");
        sleepingTime(1000);
        System.out.println();
        System.out.println("Here you can perform these actions:");
        System.out.println("Addition, subtraction or multiplication of matrix ");
        System.out.println("Or you can multiply matrix by number.");
        System.out.println("Also, you can exit out of program.");
        sleepingTime(1000);
        System.out.println("To make choice you need to enter one of these letters:");
        System.out.println("\"+\" - is addition, \"-\" - is subtraction, \"*\" - is multiplication");
        System.out.println("\"e\" - is exit out of program");
        sleepingTime(1000);
        System.out.println("Please, make your choice.");
    }

    // Любой ввод данных
    private String operationEntering() {

      //  scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        return s;
    }

    // Ввод размеров матриц. Должны быть положительные числа.
    private int numberEntering() {
        System.out.println("Please, enter one of sizes of your matrix");
        System.out.println("It must be bigger than 0.");
        int k;
        int l;
        String s;
        while (true) {
          //  scanner = new Scanner(System.in);
            if (MatrixValidator.isInteger(String.valueOf(s = operationEntering()))) {
                k = Integer.parseInt(s);
                if (k > 0) {
                    l = k;
                    break;
                }
                else {
                    System.out.println("Please, enter a number bigger than 0.");
                }
            } else {
                System.out.println("Please, enter a number bigger than 0.");
            }
        }
        return l;
    }

    // Ввод содержимого матриц. Любые вещественные числа.
    public double readNumber() {

        double d;
       // scanner = new Scanner(System.in);
        String s;

        while (true) {
            if (MatrixValidator.isDigit(String.valueOf(s = operationEntering()))) {
                d = Double.parseDouble(s);
                break;
            }
        }
        return d;
    }

    private Matrix readOperation(char operation) {

        String s = String.valueOf(operation);

        Matrix matrix = null;

        int k = numberEntering();
        int l = numberEntering();
        if (s.equals(String.valueOf('r'))) {
            matrix = readRandomMatrix(k, l);
        } else if (s.equals(String.valueOf('c'))) {
            matrix = readMatrixFromConsole(k, l);
        } else if (s.equals(String.valueOf('f'))) {
            matrix = readMatrixFromFile(k, l);
        }
        return matrix;
    }

    public void startDialog() {

        startMessages();

        Matrix matrix;
        Matrix resultMatrix = null;

        while (true) {
            String s = operationEntering();
            if (s.equals("e")) {
                scanner.close();
                System.exit(0);
            }
            else if (s.equals("+") || s.equals("-") || s.equals("*")) {
                System.out.println("It is your first operation?");
                System.out.println("Please, enter \'y\' or \'n\'.");
                while (true) {
                    String s1 = operationEntering();
                    if (s1.equals("y")) {
                        matrix = chooseCreationWay();
                        printResult(matrix);
                        if (s.equals("*")) {
                            System.out.println("Want you to multiply created matrix by number?");
                            System.out.println("Please, enter \'y\' or \'n\'.");
                            while (true) {
                                String s3 = operationEntering();
                                if (s3.equals("y")) {
                                    double number = readNumber();
                                    MatrixOperations operations = new MatrixOperations();
                                    resultMatrix = operations.multiply(matrix, number);
                                    if (!resultMatrix.equals(null)) {
                                        printResult(resultMatrix);
                                    }
                                    break;
                                }
                                else if (s3.equals("n")) {
                                    break;
                                }
                                else {
                                    System.out.println("Please, enter \'y\' or \'n\'.");
                                }
                            }
                        }
                        resultMatrix = makeAction(matrix, s);
                        if (!resultMatrix.equals(numberEntering())) {
                            printResult(resultMatrix);
                        }
                        break;
                    }
                    else if (s1.equals("n")) {
                        System.out.println("Want you to use result matrix as the first matrix in your next operation.");
                        System.out.println("Please, enter \'y\' or \'n\'.");
                        while (true) {
                            String s2 = operationEntering();
                            if (s2.equals("y")) {
                                matrix = resultMatrix;
                                break;
                            }
                            else if (s2.equals("n")) {
                                matrix = chooseCreationWay();
                                break;
                            }
                            else {
                                System.out.println("Please, enter \'y\' or \'n\'.");
                            }
                        }
                        if (s.equals("*")) {
                            System.out.println("Want you to multiply created matrix by number?");
                            System.out.println("Please, enter \'y\' or \'n\'.");
                            while (true) {
                                String s3 = operationEntering();
                                if (s3.equals("y")) {
                                    double number = readNumber();
                                    MatrixOperations operations = new MatrixOperations();
                                    resultMatrix = operations.multiply(matrix, number);
                                    printResult(resultMatrix);
                                    break;
                                }
                                else if (s3.equals("n")) {
                                    break;
                                }
                                else {
                                    System.out.println("Please, enter \'y\' or \'n\'.");
                                }
                            }
                        }
                        resultMatrix = makeAction(matrix, s);
                        printResult(resultMatrix);
                        break;
                    }
                    else {
                        System.out.println("Please, enter \'y\' or \'n\'.");
                    }
                }
            }
            else {
                System.out.println("Here you can calculate your matrices.");
                System.out.println("Please, make your choice.");
                System.out.println("To make choice you need to enter one of these letters: '+', '-', '*','e'");
            }
        }
    }

    // Выбор и осуществление действия, произодимого между двумя матрицами.
    public Matrix makeAction(Matrix matrix1, String s) {

        Matrix matrix2 = null;
        MatrixOperations operations = new MatrixOperations();

        Matrix resultMatrix = null;

        while (true) {
            matrix2 = chooseCreationWay();
            if (s.equals("+") && MatrixValidator.areSameDimension(matrix1, matrix2)) {
                resultMatrix = operations.add(matrix1, matrix2);
                break;
            } else if (s.equals("-") && MatrixValidator.areSameDimension(matrix1, matrix2)) {
                resultMatrix = operations.substraction(matrix1, matrix2);
                break;
            } else if (s.equals("*") && MatrixValidator.areMultiplyable(matrix1, matrix2)) {
                resultMatrix = operations.multiply(matrix1, matrix2);
                break;
            } else if (!MatrixValidator.areSameDimension(matrix1, matrix2) || !MatrixValidator.areMultiplyable(matrix1, matrix2)) {
                System.out.println("Matrices have not the same dimension.");
            }
        }
        return resultMatrix;
    }

    // Выбор способа создания матрицы (случайное заполнение, из консоли, из файла).
    private Matrix chooseCreationWay() {

        System.out.println("By what way you want to create this matrix?");
        System.out.println("Please, enter next letter:");
        System.out.println("\'r\' - if you want to create matrix with random numbers,");
        System.out.println("\'c\' - if you want to enter numbers by yourself,");
        System.out.println("\'f\' - if you want to download matrix from file.");
        sleepingTime(1000);
        System.out.println("Please, make your choice.");

        Matrix matrix = null;

        while (true) {
            String s = operationEntering();
            if (s.equals("r")) {
                matrix = readOperation('r');
                break;
            } else if (s.equals("c")) {
                matrix = readOperation('c');
                break;
            } else if (s.equals("f")) {
                matrix = readOperation('f');
                break;
            } else {
                System.out.println("You must enter one of these letters: 'r', 'c', 'f'.");
            }
        }
        return matrix;
    }

    // Вывод результирующей матрицы на экран.
    public void printResult(Matrix resultMatrix) {

        int k = 0;
        for (int i = 0; i < resultMatrix.getRowsNumber(); i++) {
            for (int j = 0; j < resultMatrix.getColumnsNumber(); j++) {
                if (Double.valueOf(resultMatrix.getElement(i, j)) != Math.ceil(resultMatrix.getElement(i, j))) {
                    k++;
                    break;
                }
            }
        }

        if (k == 0) {
            printIntResult(resultMatrix);
        } else {
            printDoubleResult(resultMatrix);
        }
    }

    // Выбор формата вывода матрицы (целые числа).
    private void printIntResult(Matrix result) {

        for (int i = 0; i < result.getRowsNumber(); i++) {
            for (int j = 0; j < result.getColumnsNumber(); j++) {
                System.out.printf("%8d", (int) (result.getElement(i, j)));
            }
            System.out.println();
        }
    }

    // Выбор формата вывода матрицы(вещественные числа).
    private void printDoubleResult(Matrix result) {

        for (int i = 0; i < result.getRowsNumber(); i++) {
            for (int j = 0; j < result.getColumnsNumber(); j++) {
                System.out.printf("%8.3f", result.getElement(i, j));
            }
            System.out.println();
        }
    }

    // Создание матрицы, заполненной случайными вещественными числами в диапазоне от -100 до 100.
    public Matrix readRandomMatrix(int rows, int columns) {

        Matrix matrix = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int l = 0; l < columns; l++) {
                double indication = Math.random() * 10;
                int k = (int) Math.round(indication);
                if (k >= 5) {
                    matrix.setElement(i, l, Math.random() * 100);
                } else {
                    matrix.setElement(i, l, Math.random() * 100 * (-1));
                }
            }
        }
        return matrix;
    }

    // Создание матрицы и заполнение ее вручную из консоли.
    public Matrix readMatrixFromConsole(int rows, int columns) {

        System.out.println("Please, fill your matrix by numbers.");

       // Scanner scanner = new Scanner(System.in);

        Matrix matrix = new Matrix(rows, columns);
        String s;

        for (int k = 0; k < rows; k++) {
            for (int j = 0; j < columns; j++) {
                while (true) {
                    if (MatrixValidator.isDigit(String.valueOf(s = operationEntering()))){
                        matrix.setElement(k, j, Double.parseDouble(s));
                        break;
                    } else {
                        System.out.println("You have to enter Integer or Double numbers.");
                    }
                }
            }
        }
        return matrix;
    }

    // Создание матрицы и получение значений для нее из файла.
    public Matrix readMatrixFromFile(int rows, int columns) {

        Matrix matrix = new Matrix(rows, columns);


            JFileChooser dialog = new JFileChooser();
            dialog.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            dialog.showOpenDialog(null);
            File file = dialog.getSelectedFile();
        try (FileInputStream inF = new FileInputStream(file)) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(inF, "utf-8"));
            String s;
            List<String> list = new ArrayList<>();
            while (reader.readLine() != null) {
                s = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(s, ", /;.\n\\'\"", true);

                while (tokenizer.hasMoreTokens()) {
                    list.add(tokenizer.nextToken());
                }
            }
            inF.close();
            int l = 0;
            for (int k = 0; k < rows; k++) {
                for (int j = 0; j < columns; j++) {
                    matrix.setElement(k, j, Double.parseDouble(list.get(l)));
                    l++;
                }
            }
        } catch (Exception e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        return matrix;
    }

}
