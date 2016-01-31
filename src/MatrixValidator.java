
public class MatrixValidator {
    public static boolean areMultiplyable(Matrix m1, Matrix m2) {
        return m1.getColumnsNumber() == m2.getRowsNumber();
    }

    public static boolean areSameDimension(Matrix m1, Matrix m2) {
        return ((m1.getRowsNumber() == m2.getRowsNumber()) && (m1.getColumnsNumber() == m2.getColumnsNumber()));
    }


    public static boolean isDigit(String userInput) {
        try {
            Double.parseDouble(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
