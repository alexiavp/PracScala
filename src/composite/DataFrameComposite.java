package composite;

import dataFrame.DataFrame;
import visitor.Visitor;

/**
 * Class that implements the Composite and extends from the DataFrame
 */
public class DataFrameComposite extends DataFrame implements Composite {

    /**
     * Variable of the class
     */
    private Composite dad;

    /**
     * Constructor
     * @param info to pass super
     */
    public DataFrameComposite(DataFrame info) {
        super(info);
        this.dad=null;
    }

    /**
     * Setter
     * @param parent the info to save
     */
    public void setParent(Composite parent) {
        this.dad = parent;
    }

    /**
     * Getter
     * @return the name
     */
    public String getName(){
        return super.getName();
    }

    /**
     * Method toString
     * @return String with the info
     */
    public String toString(){
        return super.toString();
    }

    /**
     * To know the size of the info
     * @return an int of the size
     */
    public int size() {
        return super.size();
    }

    /**
     * Method accepts for the Visitor implementation
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
