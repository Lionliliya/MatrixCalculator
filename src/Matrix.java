
public class Matrix {
    final private int rows;
    final private int columns;
    private int maxLengthOfValue = 0;
    final private double[][] matrix;
    

    public Matrix(int rows, int columns) throws IndexOutOfBoundsException {
        this.columns = columns;
        this.rows = rows;
        this.matrix = new double[rows][columns];
    }


    public double getElement(int rowNum, int columnsNum) {
        return matrix[rowNum][columnsNum];
    }

    public void setElement(int rowNum, int columnsNum, double value) throws IndexOutOfBoundsException {
        maxLengthOfValue = String.valueOf(matrix[rowNum][columnsNum]).length();
        matrix[rowNum][columnsNum] = value;
    }

    public int getRowsNumber() {
        return rows;
    }

    public int getColumnsNumber() {
        return columns;
    }
}
