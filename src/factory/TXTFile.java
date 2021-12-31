package factory;

import dataFrame.DataFrame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

/**
 * Class reads TXT files
 */
public class TXTFile implements ReadFile {

    /**
     * Read the data of a file
     *
     * @param file : file's name to read
     * @return a String with possible errors
     */
    @Override
    public DataFrame readFile(String file) {
        String result = "";
        String delim;
        BufferedReader r = null;
        DataFrame df = null;
        LinkedHashMap<String, ArrayList<String>> data = new LinkedHashMap<>();
        String[] keys;
        int i;

        try {
            String sentence;
            r = new BufferedReader(new FileReader(file));
            delim = r.readLine();
            sentence = r.readLine();
            StringTokenizer st = new StringTokenizer(sentence, delim);
            keys = new String[st.countTokens()];
            for (i = 0; st.hasMoreElements(); i++) {
                keys[i] = st.nextToken().replace("\"", "").strip();
                data.putIfAbsent(keys[i], new ArrayList<>());
            }
            sentence = r.readLine();
            while (sentence != null) {
                st = new StringTokenizer(sentence, delim);
                for (i = 0; st.hasMoreElements(); i++) {
                    data.get(keys[i]).add(st.nextToken().replace("\"", "").strip());
                    //System.out.println(data.keySet().toString());
                }
                sentence = r.readLine();
            }
            r.close();
            if (keys[0] != null) {
                df = new DataFrame(data, file);
            }

        } catch (IOException e) {
            try {
                assert r != null;
                r.close();
            } catch (IOException e1) {
                result = e1.getMessage();
            }
            System.out.println(result + " " + e.getMessage());
        }

        return df;
    }
}
