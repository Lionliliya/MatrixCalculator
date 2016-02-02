import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;

public class Calculator {

    private Scanner scanner = new Scanner(System.in);
    boolean bool = true;
    Matrix matrix;
    Matrix resultMatrix;

    private String yesOrNo = "Please, enter \'y\' or \'n\'.";
    private String multByNumder = "Would you like to multiply created matrix by number?";
    private String resultInOperation = "Would you like to use result matrix as the first matrix in your next operation.";
    private String chooseCreationWay = "By what way you want to create the first matrix?";
    private String reenterLastMatrix = "Would you like to reenter last matrix?";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.startDialog();
    }

    private static void sleepingTime(long milisec) {

        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Стартовое сообщение в начале работы программы
    private void startMessages() {

        System.out.printf("Hi! This is matrix calculator!");
        sleepingTime(1000);
        System.out.println();
        System.out.println("Here you can perform these actions:");
        System.out.println("Addition, subtraction or multiplication of matrix ");
        System.out.println("Or you can multiply matrix by number.");
        System.out.println("Also, you can exit out of program.");
    }

    // Любой ввод данных
    private String operationEntering() {

        return scanner.nextLine();
    }

    // Ввод размеров матриц. Должны быть положительные числа.
    private int numberEntering() {

        int k;
        int l;
        String s;

        System.out.println("It must be bigger than 0.");
        while (true) {
            if (MatrixValidator.isInteger(String.valueOf(s = operationEntering()))) {
                k = Integer.parseInt(s);
                if (k > 0) {
                    l = k;
                    break;
                } else {
                    System.out.println("Please, enter a number bigger than 0.");
                }
            } else {
                System.out.println("Please, enter a number bigger than 0.");
            }
        }
        return l;
    }

    // Ввод содержимого матриц. Любые вещественные числа.
    private double readNumber() {

        double d;
        String s;

        System.out.println("Please, enter a number multiplying matrix.");
        while (true) {
            if (MatrixValidator.isDigit(String.valueOf(s = operationEntering()))) {
                d = Double.parseDouble(s);
                break;
            } else {
                System.out.println("Please, enter any number.");
            }
        }
        return d;
    }

    // Ввод аргументов матриц
    private Matrix readOperation(char operation) {

        String s = String.valueOf(operation);
        Matrix matrix = null;
        System.out.println("Please, enter the rows quantity of your matrix.");
        int k = numberEntering();
        System.out.println("Please, enter the columns quantity of your matrix.");
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
        boolean bool = true;

        while (true) {
            System.out.println("If you want to do some action you need to enter one of these letters:");
            System.out.println("\"+\" - is addition, \"-\" - is subtraction, \"*\" - is multiplication");
            System.out.println("Or enter \"e\" to exit out of program.");
            System.out.println("Please, make your choice.");
            String s = operationEntering();
            if (s.equals("e")) {
                scanner.close();
                System.exit(0);
            } else if (s.equals("+") || s.equals("-")) {
                if (bool) {
                    System.out.println(chooseCreationWay);
                    matrix = chooseCreationWay();
                    resultMatrix = makeAction(matrix, s);
                    if (resultMatrix != null) {
                        printResult(resultMatrix);
                        bool = false;
                    }
                } else {
                    System.out.println(resultInOperation);
                    System.out.println(yesOrNo);
                    String s1;
                    switch (s1 = operationEntering()) {
                        case "y": {
                            matrix = resultMatrix;
                            resultMatrix = makeAction(matrix, s);
                            if (resultMatrix != null) {
                                printResult(resultMatrix);
                            }
                            break;
                        }
                        case "n": {
                            System.out.println(chooseCreationWay);
                            matrix = chooseCreationWay();
                            resultMatrix = makeAction(matrix, s);
                            if (resultMatrix != null) {
                                printResult(resultMatrix);
                            }
                            break;
                        }
                        default: {
                            System.out.println(yesOrNo);
                        }
                    }
                }
            } else if (s.equals("*")) {
                if (bool) {
                    System.out.println(multByNumder);
                    System.out.println(yesOrNo);
                    String s2;
                    while (true) {
                        s2 = operationEntering();
                        if (s2.equals("y")) {
                            System.out.println(chooseCreationWay);
                            matrix = chooseCreationWay();
                            double number = readNumber();
                            resultMatrix = MatrixOperations.multiply(matrix, number);
                            if (!resultMatrix.equals(null)) {
                                printResult(resultMatrix);
                                bool = false;
                            }
                            break;
                        }
                        if (s2.equals("n")) {
                            System.out.println(chooseCreationWay);
                            matrix = chooseCreationWay();
                            resultMatrix = makeAction(matrix, s);
                            if (resultMatrix != null) {
                                printResult(resultMatrix);
                                bool = false;
                            }
                            break;
                        } else {
                            System.out.println(yesOrNo);
                        }
                    }
                } else {
                    System.out.println(resultInOperation);
                    System.out.println(yesOrNo);
                    String s1;
                    while (true) {
                        s1 = operationEntering();
                        if (s1.equals("y")) {
                            matrix = resultMatrix;
                            System.out.println(multByNumder);
                            System.out.println(yesOrNo);
                            String s2;
                            while (true) {
                                s2 = operationEntering();
                                if (s2.equals("y")) {
                                    double number = readNumber();
                                    if (!matrix.equals(null)) {
                                        resultMatrix = MatrixOperations.multiply(matrix, number);
                                        printResult(resultMatrix);
                                    }
                                    break;
                                }
                                if (s2.equals("n")) {
                                    if (!matrix.equals(null)) {
                                        resultMatrix = makeAction(matrix, s);
                                        printResult(resultMatrix);
                                    }
                                    break;
                                } else {
                                    System.out.println(yesOrNo);
                                }
                            }
                            break;
                        } else if (s1.equals("n")) {
                            System.out.println(multByNumder);
                            System.out.println(yesOrNo);
                            String s2;
                            while (true) {
                                s2 = operationEntering();
                                if (s2.equals("y")) {
                                    System.out.println(chooseCreationWay);
                                    matrix = chooseCreationWay();
                                    double number = readNumber();
                                    if (!matrix.equals(null)) {
                                        resultMatrix = MatrixOperations.multiply(matrix, number);
                                        printResult(resultMatrix);
                                    }
                                    break;
                                }
                                if (s2.equals("n")) {
                                    System.out.println(chooseCreationWay);
                                    matrix = chooseCreationWay();
                                    if (!matrix.equals(null)) {
                                        resultMatrix = makeAction(matrix, s);
                                        printResult(resultMatrix);
                                    }
                                    break;
                                } else {
                                    System.out.println(yesOrNo);
                                }
                            }
                            break;
                        } else {
                            System.out.println(yesOrNo);
                        }
                    }
                }
            }
        }
    }

    // Выбор и осуществление действия, произодимого между двумя матрицами.
    private Matrix makeAction(Matrix matrix1, String s) {

        outer:
        while (true) {
            System.out.println("By what way you want to create the second matrix?");
            Matrix matrix2 = chooseCreationWay();
            if (s.equals("+") && MatrixValidator.areSameDimension(matrix1, matrix2)) {
                resultMatrix = MatrixOperations.add(matrix1, matrix2);
                break;
            } else if (s.equals("-") && MatrixValidator.areSameDimension(matrix1, matrix2)) {
                resultMatrix = MatrixOperations.substraction(matrix1, matrix2);
                break;
            } else if (s.equals("*") && MatrixValidator.areMultiplyable(matrix1, matrix2)) {
                resultMatrix = MatrixOperations.multiply(matrix1, matrix2);
                break;
            } else if ((s.equals("+") && !MatrixValidator.areSameDimension(matrix1, matrix2))
                    || (s.equals("-") && !MatrixValidator.areSameDimension(matrix1, matrix2))) {
                System.out.println("Matrices have not the same dimension.");
                System.out.println(reenterLastMatrix);
                System.out.println(yesOrNo);
                while (true) {
                    String s1 = operationEntering();
                    if (s1.equals("y")) {
                        continue outer;
                    }
                    if (s1.equals("n")) {
                        bool = true;
                        break outer;
                    } else {
                        System.out.println(yesOrNo);
                    }
                }
            } else if (s.equals("*") && !MatrixValidator.areMultiplyable(matrix1, matrix2)) {
                System.out.println("Matrices are not multiplyable.");
                System.out.println("It means that columns quantity of first matrix not equals");
                System.out.println("rows quantity od second matrix.");
                System.out.println(reenterLastMatrix);
                System.out.println(yesOrNo);
                while (true) {
                    String s1 = operationEntering();
                    if (s1.equals("y")) {
                        continue outer;
                    }
                    if (s1.equals("n")) {
                        bool = true;
                        break outer;
                    } else {
                        System.out.println(yesOrNo);
                    }
                }
            }
        }
        return resultMatrix;
    }

    // Выбор способа создания матрицы (случайное заполнение, из консоли, из файла).
    private Matrix chooseCreationWay() {

        System.out.println("Please, enter next letter:");
        System.out.println("\'r\' - if you want to create matrix with random numbers,");
        System.out.println("\'c\' - if you want to enter numbers by yourself,");
        System.out.println("\'f\' - if you want to download matrix from file.");
        sleepingTime(1000);
        System.out.println("Please, make your choice.");

        while (true) {
            String s = operationEntering();
            if (s.equals("r")) {
                matrix = readOperation('r');
                break;
            }
            if (s.equals("c")) {
                matrix = readOperation('c');
                break;
            }
            if (s.equals("f")) {
                matrix = readOperation('f');
                break;
            } else {
                System.out.println("You must enter one of these letters: 'r', 'c', 'f'.");
            }
        }
        return matrix;
    }

    // Вывод результирующей матрицы на экран.
    private void printResult(Matrix resultMatrix) {

        int k = 0;
        System.out.println("The result matrix is:");
        for (int i = 0; i < resultMatrix.getRowsNumber(); i++) {
            for (int j = 0; j < resultMatrix.getColumnsNumber(); j++) {
                if (resultMatrix.getElement(i, j) != Math.ceil(resultMatrix.getElement(i, j))) {
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
    private Matrix readRandomMatrix(int rows, int columns) {

        Matrix matrix = new Matrix(rows, columns);
        try {
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
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    // Создание матрицы и заполнение ее вручную из консоли.
    private Matrix readMatrixFromConsole(int rows, int columns) {

        System.out.println("Please, fill your matrix by numbers.");
        Matrix matrix = new Matrix(rows, columns);
        String s;

        try {
            for (int k = 0; k < rows; k++) {
                for (int j = 0; j < columns; j++) {
                    System.out.println("Please, enter element [" + k + "]" + "[" + j +"]");
                    while (true) {
                        if (MatrixValidator.isDigit(String.valueOf(s = operationEntering()))) {
                            matrix.setElement(k, j, Double.parseDouble(s));
                            break;
                        } else {
                            System.out.println("You have to enter Integer or Double numbers.");
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    // Создание матрицы и получение значений для нее из файла.
    private Matrix readMatrixFromFile(int rows, int columns) {

        Matrix matrix = new Matrix(rows, columns);
        List<String> list = new ArrayList<>();
        JFileChooser dialog = new JFileChooser();
        dialog.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        dialog.showOpenDialog(null);
        File file = dialog.getSelectedFile();

        if (file != null) {
            try (FileInputStream inF = new FileInputStream(file)) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(inF, "utf-8"));
                String s;
                int subStrBegin = 0;

                while ((s = reader.readLine()) != null) {
                    char[] charsInString = s.toCharArray();
                    for (int i = 0; i < charsInString.length; i++) {
                        if (charsInString[i] == ',' || charsInString[i] == ' ' || charsInString[i] == ';' ||
                                charsInString[i] == ':' || charsInString[i] == '\'' || charsInString[i] == '\"') {
                            String subString = s.substring(subStrBegin, i);
                            list.add(subString);
                            subStrBegin = i + 1;
                        }
                    }
                }
                int l = 0;
                for (int k = 0; k < rows; k++) {
                    for (int j = 0; j < columns; j++) {
                        if (MatrixValidator.isDigit(list.get(l))) {
                            matrix.setElement(k, j, Double.parseDouble(list.get(l)));
                            l++;
                        } else {
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("File should contain numbers and after each of them has to follow by one of the elements : , \'\";:");
                System.out.println("Otherwise, the missing elements are replaced by zeros.");
            }
            return matrix;
        } else {
            System.out.println("You did not choose any file.");
            System.out.println("Instead matrix from file will generated matrix of the same dimension filled by zeros.");
            System.out.println();
            try {
                for (int k = 0; k < rows; k++) {
                    for (int j = 0; j < columns; j++) {
                        matrix.setElement(k, j, 0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return matrix;
        }
    }
}
