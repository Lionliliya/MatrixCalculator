public class MatrixOperations {


/////////////////// MULTIPLY M1*M2//////////////////////////////////////////////////

    public Matrix multiply(Matrix m1, Matrix m2) {
        int RowsNumber = m1.getRowsNumber();
        int ColumsNumber = m2.getColumnsNumber();
        Matrix matrix = new Matrix(RowsNumber, ColumsNumber);
        for (int i = 0; i < RowsNumber; i++) {
            for (int j = 0; j < ColumsNumber; j++) {
                double sum = 0;
                for (int k = 0; k < m2.getRowsNumber(); k++) {
                    matrix.setElement(i, j, sum += (m1.getElement(i, k) * m2.getElement(k, j)));
                }
            }
        }
        return matrix;
    }

/////////////////// MULTIPLY M1*NUMBER//////////////////////////////////////////////////

    public Matrix multiply ( Matrix m1, double number) {
        int ColumnsNumber = m1.getColumnsNumber();
        int RowsNumber = m1.getRowsNumber();
        Matrix  matrix = new Matrix (RowsNumber, ColumnsNumber);
        for (int i = 0; i < RowsNumber; i++) {
            for (int j = 0; j < ColumnsNumber; j++) {
                matrix.setElement(i, j, m1.getElement(i,j)*number);
            }
        }
        return matrix;
    }


/////////////////// ADD M1 + M2//////////////////////////////////////////////////

    public Matrix add ( Matrix m1, Matrix m2 ) {
        int ColumnsNumber = m1.getColumnsNumber();
        int RowsNumber = m1.getRowsNumber();
        Matrix  matrix = new Matrix(RowsNumber, ColumnsNumber);
        for (int i = 0; i < RowsNumber; i++) {
            for (int j = 0; j < ColumnsNumber; j++) {
                matrix.setElement(i, j, m1.getElement(i,j)+m2.getElement(i,j));
            }
        }
        return matrix;
    }


/////////////////// SUBSTRACTION M1 - M2//////////////////////////////////////////////////

    public Matrix substraction ( Matrix m1, Matrix m2 ) {
        int ColumnsNumber = m1.getColumnsNumber();
        int RowsNumber = m1.getRowsNumber();
        Matrix  matrix = new Matrix (RowsNumber, ColumnsNumber);
        for (int i = 0; i < RowsNumber; i++) {
            for (int j = 0; j < ColumnsNumber; j++) {
                matrix.setElement(i, j, (m1.getElement(i,j)-m2.getElement(i,j)));
            }
        }
        return matrix;
    }
}


