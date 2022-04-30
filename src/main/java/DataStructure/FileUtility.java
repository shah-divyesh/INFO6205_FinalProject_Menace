package DataStructure;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FileUtility {

    /*
    Writing the HashMap values of BoardLayouts and Beadcounts into the file
     */

    public static HashMap<String, int[]> readFromFile(String fileName) throws IOException {

        HashMap<String, int[]> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String readLine = null;

        while ((readLine = br.readLine()) != null) {
            String[] s = readLine.split(":"); // Because have stored the value into file in the form of (key:value)

            String key = s[0].trim();
            String[] valueArray = s[1].trim().split(",");
            int[] value = new int[valueArray.length];

            for (int i = 0; i < valueArray.length; i++) {
                value[i] = Integer.valueOf(valueArray[i].trim());
            }

            if (!key.equals("") && !s[1].trim().equals(""))
                map.put(key, value);
        }
        br.close();

        return map;
    }


    /*
   Reading the HashMap values of BoardLayouts and Beadcounts into the file
    */
    public static void writeToFile(String fileName, Map<String, int[]> map) throws IOException {

        FileWriter writer = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(writer);

        for (Map.Entry<String, int[]> entry : map.entrySet()) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {

                sb.append(entry.getValue()[i]);
                sb.append(",");
            }
            bw.write(entry.getKey() + ":" + sb); // Storing hashmap in the form of "key:value"
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }


    /*
    Reading the statistics of Menace from the file
     */
    public static HashMap<String, Integer> readStatsFromFile() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("Stats.txt"));
        String readLine = null;

        while ((readLine = br.readLine()) != null) {
            String[] s = readLine.split(":"); // Because have stored the value into file in the form of (key:value)

            if (!s[0].equals("") && !s[1].trim().equals(""))
                map.put(s[0].trim(), Integer.valueOf(s[1].trim()));
        }
        br.close();
        return map;
    }

    /*
      Writing the statistics of Menace in the file
     */
    public static void writeStatsToFile(HashMap<String, Integer> map) {
        try {
            FileWriter writer = new FileWriter("Stats.txt");
            BufferedWriter bw = new BufferedWriter(writer);

            for (Map.Entry<String, Integer> entry : map.entrySet()) {

                bw.write(entry.getKey() + ":" + entry.getValue()); // Storing hashmap in the form of "key:value"
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }

    }
}

