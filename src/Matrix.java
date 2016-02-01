
public class Matrix {
    final private int rows;
    final private int columns;
    final private double[][] matrix;
 
    public Matrix(int rows, int columns) throws IndexOutOfBoundsException {
        this.columns = columns;
        this.rows = rows;
        this.matrix = new double[rows][columns];
    }

    public Matrix(double [][] array) {
        this.matrix = array;
        this.rows = array.length;
        this.columns = array[0].length;
    }


    public double getElement(int rowNum, int columnsNum) {
        return matrix[rowNum][columnsNum];
    }

    public void setElement(int rowNum, int columnsNum, double value) throws IndexOutOfBoundsException {
        matrix[rowNum][columnsNum] = value;
    }

    public int getRowsNumber() {
        return rows;
    }

    public int getColumnsNumber() {
        return columns;
    }
}
