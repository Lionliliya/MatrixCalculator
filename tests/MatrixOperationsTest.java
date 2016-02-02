import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vladi_000 on 2/2/2016.
 */
public class MatrixOperationsTest {
    Matrix basedMatrix;
    Matrix secondMatrix;
    Matrix thirdMatrix;
    final double coefficient = 2.0;


    @BeforeClass
    public void setUp() throws Exception {
        basedMatrix = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        // for add and substraction
        secondMatrix = new Matrix(new double[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}});
        //for multiply
        thirdMatrix = new Matrix(new double[][]{{9, 8}, {6, 5}, {3, 1}});
    }

    @Test
    public void testMultiply() throws Exception {
        final Matrix multiplyExpectedMatrix = new Matrix(new double[][]{{30, 21}, {84, 63}, {138, 105}});
        assertArrayEquals(MatrixOperations.multiply(basedMatrix, thirdMatrix).getMatrix(), multiplyExpectedMatrix.getMatrix());
    }

    @Test
    public void testMultiply1() throws Exception {
        final Matrix multiplyExpectedMatrix = new Matrix(new double[][]{{2, 4, 6}, {8, 10, 12}, {14, 16, 18}});
        assertArrayEquals(MatrixOperations.multiply(basedMatrix, coefficient).getMatrix(), multiplyExpectedMatrix.getMatrix());
    }

    @Test
    public void testAdd() throws Exception {
        final Matrix addExpectedMatrix = new Matrix(new double[][]{{10, 10, 10}, {10, 10, 10}, {10, 10, 10}});
        assertArrayEquals(MatrixOperations.add(basedMatrix, secondMatrix).getMatrix(), addExpectedMatrix.getMatrix());
    }

    @Test
    public void testSubstraction() throws Exception {
        final Matrix substractionExpectedMatrix = new Matrix(new double[][]{{-8, -6, -4}, {-2, 0, 2}, {4, 6, 8}});
        assertArrayEquals(MatrixOperations.substraction(basedMatrix, secondMatrix).getMatrix(), substractionExpectedMatrix.getMatrix());

    }
}