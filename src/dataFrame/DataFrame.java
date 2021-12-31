package dataFrame;

import java.lang.Iterable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class implements the principal methods for the DataFrame
 */
public class DataFrame implements DataFrameInterface,Iterable<ArrayList<String>> {
    /**
     * Variables name for the dataFrame
     */
    private final String name;
    /**
     * Variable data for the dataFrame
     */
    public LinkedHashMap<String, ArrayList<String>> data;

    /**
     * Constructor when it's read from file
     *
     * @param data info
     * @param file name
     */
    public DataFrame(LinkedHashMap<String, ArrayList<String>> data, String file) {
        this.data = data;
        this.name = file;
    }

    /**
     * Constructor when the inherited classes call the constructor
     *
     * @param info information
     */
    public DataFrame(DataFrame info) {
        this.data = info.data;
        this.name = info.name;
    }

    /**
     * Getter
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * return the value of a single item (row) and column label (name).
     *
     * @param row   row
     * @param label column
     * @return the info
     */
    public String at(int row, String label) {
        ArrayList<String> a;
        if (this.data.containsKey(label)) {
            a = this.data.get(label);
        } else {
            System.out.println("Error, label " + label);
            return null;
        }

        return a.get(row);

    }

    /**
     * access a single value for a row and column by integer position
     *
     * @param row row
     * @param col column
     * @return the info
     */
    public String iat(int row, int col) {
        String label = (String) this.data.keySet().toArray()[col];
        return this.data.get(label).get(row);
    }

    /**
     * Method used to know the number of columns
     *
     * @return the number of columns
     */
    public int columns() {
        return this.data.size();
    }

    /**
     * the size of the DataFrame
     *
     * @return the size
     */
    public int size() {
        return this.data.get((String) this.data.keySet().toArray()[0]).size();
    }

    /**
     * Second method sort, optimization of the other one
     *
     * @param label     column
     * @param predicate how to sort
     * @return an ArrayList with the column ordered
     */
    public ArrayList<String> sort2(String label, Comparator<String> predicate) {
        return (ArrayList<String>) data.get(label).stream().sorted(predicate).collect(Collectors.toList());
    }

    /**
     * Second method query, optimization of the other one
     *
     * @param label     column
     * @param predicate how to sort
     * @return an ArrayList with the column ordered
     */
    public ArrayList<String> query2(String label, Predicate<String> predicate) {
        return (ArrayList<String>) data.get(label).stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Method sort return the values of a column in the DataFrame following a certain order
     *
     * @param label      column
     * @param comparator how to sort
     * @return an ArrayList with the column ordered
     */
    public ArrayList<String> sort(String label, int comparator) {
        ArrayList<String> result = new ArrayList<>();
        if (this.data.containsKey(label)) {
            try {
                ArrayList<Integer> result1 = new ArrayList<>();
                for (String myInt : this.data.get(label)) {
                    result1.add(Integer.valueOf(myInt));
                }
                switch (comparator) {
                    case 0 -> Collections.sort(result1);
                    case 1 -> result1.sort(Collections.reverseOrder());
                    default -> {

                    }
                }
                for (int i : result1) {
                    result.add(String.valueOf(i));
                }
            } catch (NumberFormatException e) {
                result.addAll(this.data.get(label));
                switch (comparator) {
                    case 0 -> Collections.sort(result);
                    case 1 -> result.sort(Collections.reverseOrder());
                    default -> {

                    }
                }
            }
            return result;
        } else {
            System.out.println("Error, label " + label);
            return null;
        }


    }

    /**
     * Method toString
     *
     * @return the info
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        LinkedHashMap<String, Integer> maxMap = new LinkedHashMap<>();
        int max;
        result.append(name).append(":\n");
        for (String name : this.data.keySet()) {
            max = name.length();
            for (String info : this.data.get(name)) {
                max = Math.max(max, info.length());
            }
            maxMap.put(name, max + 1);
        }
        for (String name : this.data.keySet()) {
            max = maxMap.get(name);
            result.append((name + new String(new char[max]).replace('\0', ' ')), 0, max);
        }
        result.append("\n");
        for (int i = 0; i < this.size(); i++) {
            for (String name : this.data.keySet()) {
                max = maxMap.get(name);
                result.append((this.data.get(name).get(i) + new String(new char[max]).replace('\0', ' ')), 0, max);
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Method query return all elements where a label value fulfills a certain condition.
     *
     * @param label   column
     * @param option  how to search
     * @param compare what are we searching
     * @return the elements in the defined condition
     */
    public DataFrame query(String label, int option, String compare) {
        int num = 0;
        int i;
        ArrayList<String> infoToFilter = new ArrayList<>();
        ArrayList<Integer> numToFilter = new ArrayList<>();
        if (this.data.containsKey(label)) {
            if (option > 2) {
                try {
                    num = Integer.parseInt(compare);
                    for (String myInt : this.data.get(label)) {
                        numToFilter.add(Integer.valueOf(myInt));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Item to compare isn't a number, we can't continue.");
                    return null;
                }
            } else {
                infoToFilter = this.data.get(label);
            }
            LinkedHashMap<String, ArrayList<String>> dataFiltrated = new LinkedHashMap<>();
            for (String info : this.data.keySet()) {
                dataFiltrated.putIfAbsent(info, new ArrayList<>());
            }
            switch (option) {
                case 1 -> {//String equals
                    for (i = 0; i < infoToFilter.size(); i++) {
                        if (infoToFilter.get(i).equals(compare)) {
                            for (String label1 : this.data.keySet()) {
                                dataFiltrated.get(label1).add(this.data.get(label1).get(i));
                            }
                        }
                    }
                }
                case 2 -> {//String NOT equals
                    for (i = 0; i < infoToFilter.size(); i++) {
                        if (!infoToFilter.get(i).equals(compare)) {
                            for (String label1 : this.data.keySet()) {
                                dataFiltrated.get(label1).add(this.data.get(label1).get(i));
                            }
                        }
                    }
                }
                case 3 -> {//Int is equal
                    for (i = 0; i < numToFilter.size(); i++) {
                        if (numToFilter.get(i) == num) {
                            for (String label1 : this.data.keySet()) {
                                dataFiltrated.get(label1).add(this.data.get(label1).get(i));
                            }
                        }
                    }
                }
                case 4 -> {//Int is bigger than
                    for (i = 0; i < numToFilter.size(); i++) {
                        if (numToFilter.get(i) > num) {
                            for (String label1 : this.data.keySet()) {
                                dataFiltrated.get(label1).add(this.data.get(label1).get(i));
                            }
                        }
                    }
                }
                case 5 -> {//Int is smaller than
                    for (i = 0; i < numToFilter.size(); i++) {
                        if (numToFilter.get(i) < num) {
                            for (String label1 : this.data.keySet()) {
                                dataFiltrated.get(label1).add(this.data.get(label1).get(i));
                            }
                        }
                    }
                }
                default -> {
                    System.out.println("Option not available");
                    return null;
                }
            }
            return new DataFrame(dataFiltrated, this.name + "_filtered");
        } else {
            System.out.println("Label doesn't exist");
            return null;
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ArrayList<String>> iterator() {
        return new MyIterator(this);
    }
}
