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

    private static void sleepingTime(long milisec) {

        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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

    private String operationEntering() {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        return s;
    }

    private int numberEntering() {
        System.out.println("Please, enter one of sizes of your matrix");
        System.out.println("It must be bigger than 0.");
        int k;
        int l;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                k = scanner.nextInt();
                if (k > 0) {
                    l = k;
                    break;
                }
            } else {
                System.out.println("Please, enter a number bigger than 0.");
            }
            scanner.close();
        }
        return l;
    }

    public double readNumber() {

        double d;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (scanner.hasNextDouble()) {
                d = scanner.nextDouble();
                break;
            }
        }
        scanner.close();
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
                System.exit(0);
            }
            else if (s.equals("+") || s.equals("-") || s.equals("*")) {
                System.out.println("It is your first operation?");
                System.out.println("Please, enter \'y\' or \'n\'.");
                while (true) {
                    String s1 = operationEntering();
                    if (s1.equals("y")) {
                        System.out.println("By what way you want to create matrix?");
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
                break;
            }
            else {
                System.out.println("Please, make your choice.");
                System.out.println("To make choice you need to enter one of these letters: '+', '-', '*','e'");
            }
        }
    }


    public Matrix makeAction(Matrix matrix1, String s) {

        Matrix matrix2 = chooseCreationWay();
        MatrixOperations operations = new MatrixOperations();

        Matrix resultMatrix = null;

        if (s.equals("+") && MatrixValidator.areSameDimension(matrix1, matrix2)) {
            resultMatrix = operations.add(matrix1, matrix2);
        } else if (s.equals("-") && MatrixValidator.areSameDimension(matrix1, matrix2)) {
            resultMatrix = operations.substraction(matrix1, matrix2);
        } else if (s.equals("*") && MatrixValidator.areMultiplyable(matrix1, matrix2)) {
            resultMatrix = operations.multiply(matrix1, matrix2);
        }
        System.out.println("Matrices have not the same dimension.");
        return resultMatrix;
    }

    private Matrix chooseCreationWay() {

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

    private void printIntResult(Matrix result) {

        for (int i = 0; i < result.getRowsNumber(); i++) {
            for (int j = 0; j < result.getColumnsNumber(); j++) {
                System.out.printf("%8d", (int) (result.getElement(i, j)));
            }
            System.out.println();
        }
    }

    private void printDoubleResult(Matrix result) {

        for (int i = 0; i < result.getRowsNumber(); i++) {
            for (int j = 0; j < result.getColumnsNumber(); j++) {
                System.out.printf("%8.3f", result.getElement(i, j));
            }
            System.out.println();
        }
    }


    public Matrix readRandomMatrix(int rows, int columns) {

        Matrix matrix = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            int j = 0;
            for (int l = 0; l < columns; l++) {
                j++;
                double indication = Math.random() * 10;
                int k = (int) Math.round(indication);
                if (k >= 5) {
                    matrix.setElement(i, j, Math.random() * 100);
                } else {
                    matrix.setElement(i, j, Math.random() * 100 * (-1));
                }
            }
        }
        return matrix;
    }

    public Matrix readMatrixFromConsole(int rows, int columns) {

        System.out.println("Please, fill your matrix by numbers.");

        Scanner scanner = new Scanner(System.in);

        Matrix matrix = new Matrix(rows, columns);

        for (int k = 0; k < rows; k++) {
            for (int j = 0; j < columns; j++) {
                while (true) {
                    if (scanner.hasNextDouble() || scanner.hasNextInt()) {
                        matrix.setElement(k, j, scanner.nextDouble());
                        break;
                    } else {
                        System.out.println("You have to enter Integer or Double numbers.");
                    }
                }
            }
        }
        return matrix;
    }

    public Matrix readMatrixFromFile(int rows, int columns) {

        Matrix matrix = new Matrix(rows, columns);

        try {
            JFileChooser dialog = new JFileChooser();
            dialog.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            dialog.showOpenDialog(null);
            File file = dialog.getSelectedFile();

            FileInputStream inF = new FileInputStream(file);
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
