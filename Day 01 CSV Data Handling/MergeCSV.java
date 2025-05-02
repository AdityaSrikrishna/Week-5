import java.io.*;
import java.util.*;

public class MergeCSV {
    public static void main(String[] args) {
        String file1 = "students1.csv";
        String file2 = "students2.csv";
        String output = "students_merged.csv";

        Map<String, String[]> map1 = new HashMap<>();
        Map<String, String[]> map2 = new HashMap<>();

        try (BufferedReader br1 = new BufferedReader(new FileReader(file1))) {
            br1.readLine(); // skip header
            String line;
            while ((line = br1.readLine()) != null) {
                String[] parts = line.split(",");
                map1.put(parts[0], new String[]{parts[1], parts[2]});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
            br2.readLine(); // skip header
            String line;
            while ((line = br2.readLine()) != null) {
                String[] parts = line.split(",");
                map2.put(parts[0], new String[]{parts[1], parts[2]});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
            bw.write("ID,Name,Age,Marks,Grade");
            bw.newLine();
            for (String id : map1.keySet()) {
                String[] left = map1.get(id);
                String[] right = map2.get(id);
                if (right != null) {
                    bw.write(id + "," + left[0] + "," + left[1] + "," + right[0] + "," + right[1]);
                    bw.newLine();
                }
            }
            System.out.println("Merged data written to " + output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
