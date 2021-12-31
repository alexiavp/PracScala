package factory;

import dataFrame.DataFrame;

/**
 * Interface for the different types of files
 */
public interface ReadFile {

    /**
     * Read the data of a file
     *
     * @param file : file's name to read
     * @return a String with possible errors
     */
    DataFrame readFile(String file);

}
