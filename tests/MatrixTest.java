import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class MatrixTest {

    private static Matrix matrix;

    @BeforeClass
    public static void setUp() throws Exception {
        final double [][] array = new double [][] {{0, 1}, {1, 2}, {2, 3}};
        matrix = new Matrix(array);

    }

    @Test
    public void testGetElement() throws Exception {
        final int row = 2;
        final int columns = 1;
        final double result = matrix.getElement(row, columns);
        assertEquals(3.0, result, 0.0);
    }


    @Ignore
    @Test
    public void testSetElement() throws Exception {

    }

    @Ignore
    @Test
    public void testGetRowsNumber() throws Exception {

    }

    @Ignore
    @Test
    public void testGetColumnsNumber() throws Exception {

    }
}