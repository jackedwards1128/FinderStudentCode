import java.io.BufferedReader;
import java.io.IOException;
/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: JACK EDWARDS
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";
    private HashTable table;

    public Finder() {}

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {

        // This number is used as it is a large prime number, which reduces the frequency of collisions and therefore
        // makes the linear probing more efficient
        table = new HashTable(125959);

        String line;

        while ((line = br.readLine()) != null) {

            // Split line by comma (change delimiter if needed)
            String[] parts = line.split(",");

            // Check if columns exist
            if (parts.length > Math.max(keyCol, valCol)) {
                String key = parts[keyCol];
                String value = parts[valCol];
                table.insert(key, value);
            }
        }



        br.close();
    }

    public String query(String key){

        return table.search(key);

    }








}


