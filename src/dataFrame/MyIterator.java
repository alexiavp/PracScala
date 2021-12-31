package dataFrame;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class implements the iterator
 */
public class MyIterator extends DataFrame implements Iterator<ArrayList<String>> {

    protected int pos;
    boolean canRemove = false;

    /**
     * Constructor
     */
    public MyIterator(DataFrame info) {
        super(info);
        pos = 0;
    }

    /**
     * Method hasNext
     * @return true or false
     */
    public boolean hasNext() {
        return pos < data.size();
    }

    /**
     * Method next
     * @return the next in the line
     */
    public ArrayList<String> next() {
        pos++;
        canRemove = true;
        return data.get((String) data.keySet().toArray()[pos - 1]);
    }

    /**
     * Method remove
     */
    public void remove() {
        if (canRemove) {
            data.remove((String) data.keySet().toArray()[pos - 1]);
            pos--;
            canRemove = false;
        } else {
            throw new IllegalStateException("Next not called.");
        }
    }
}
