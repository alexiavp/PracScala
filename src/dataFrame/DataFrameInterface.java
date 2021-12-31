package dataFrame;

/**
 * Interface fot the DataFrame
 */
public interface DataFrameInterface {

    /**
     * return the value of a single item (row) and column label (name).
     * @param row   row
     * @param label column
     * @return the info
     */
    String at(int row, String label);

    /**
     * access a single value for a row and column by integer position
     * @param row row
     * @param col column
     * @return the info
     */
    String iat(int row, int col);

    /**
     * Method used to know the number of columns
     * @return the number of columns
     */
    int columns();

    /**
     * the size of the DataFrame
     * @return the size
     */
    int size();
}
