import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MatrixTest {

    private static Matrix matrix;

    @BeforeClass
    public static void setUp() throws Exception {
        final double [][] array = new double [][] {{0, 1}, {1, 2}, {2, 3}};
        matrix = new Matrix(array);
    }

    @Test(timeout = 2000)
    public void testGetElement() throws Exception {
        final int row = 2;
        final int columns = 1;
        final double result = matrix.getElement(row, columns);
        assertEquals(3.0, result, 0.0);
    }



    @Test(timeout = 2000)
    public void testSetElement() throws Exception {
        final int row = 0;
        final int columns = 1;
        final double element = 4.5;
        matrix.setElement(row, columns, element);
        final double result = matrix.getElement(row, columns);
        assertEquals(element, result, 0.0);
    }


    @Test(timeout = 2000)
    public void testGetRowsNumber() throws Exception {
         final int rowsNumber = 3;
         assertEquals(rowsNumber, matrix.getRowsNumber());
    }


    @Test(timeout = 2000)
    public void testGetColumnsNumber() throws Exception {
        final int columnsNumber = 2;
        assertEquals(columnsNumber, matrix.getColumnsNumber());
    }
}
