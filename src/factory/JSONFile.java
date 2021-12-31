package factory;

import dataFrame.DataFrame;

import java.io.*;
import java.util.*;

/**
 * Class reads JSON files
 */
public class JSONFile implements ReadFile {


    /**
     * Read the data of a file
     *
     * @param file : file's name to read
     * @return a String with possible errors
     */
    @Override
    public DataFrame readFile(String file) {
        BufferedReader r = null;
        DataFrame data = null;
        LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<>();
        String sentence, result;
        String[] parts = null;

        try {
            r = new BufferedReader(new FileReader(file));
            while ((sentence = r.readLine()) != null) {
                sentence = sentence.strip();
                if (sentence.charAt(sentence.length() - 1) == ',')
                    sentence = sentence.substring(0, sentence.length() - 1);

                if (!sentence.equals("{") && !sentence.equals("}") && !sentence.equals("[") && !sentence.equals("]")) {
                    parts = sentence.replaceAll("\"", "").split(":");
                    map.putIfAbsent(parts[0], new ArrayList<>());
                    map.get(parts[0]).add(parts[1].strip());
                    //System.out.println(map.keySet().toString());
                }
            }
            if (parts != null) {

                data = new DataFrame(map, file);
            }
            r.close();
        } catch (Exception e) {
            result = "";
            try {
                if(r!=null)
                    r.close();
            } catch (IOException e1) {
                result = e1.getMessage();
            }
            System.err.println(result + " " + e.getMessage());
        }
        return data;
    }
}

