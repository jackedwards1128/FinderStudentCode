public class HashTable {

    private String[] keys;
    private String[] values;

    // The size of the arrays
    private int size;

    // Amount of items added to the table
    private int utilizedSize;

    public HashTable (int size) {
        this.size = size;

        // Creates key and value arrays
        keys = new String[size];
        values = new String[size];
        utilizedSize = 0;
    }

    // Doubles the size of the HashTable
    public void doubleSize() {
        this.size *= 2;
        utilizedSize = 0;

        // Create copies of the key and value arrays so the items can be transferred to the new arrays
        String[] oldKeys = keys.clone();
        String[] oldValues = values.clone();

        keys = new String[size];
        values = new String[size];

        // Transfer old key-value pairs to new HashTable arrays
        for(int i = 0, n = oldKeys.length; i < n; i++) {
            if (oldKeys[i] != null) {
                insert(oldKeys[i], oldValues[i]);
            }
        }
    }

    public void insert(String key, String value) {
        // Hash the key
        int hash = hash(key);

        // Linear Probing; searches for first null (empty) spot in keys array
        int i = 0;
        while (keys[hash + i] != null) {
            i++;

            // If loop reaches the end of the array, this loops it back over
            if (hash + i >= size) {
                i = hash * -1;
            }
        }

        // Insert the keys and values
        keys[hash + i] = key;
        values[hash + i] = value;

        // Increment the utilized size, and check whether the HashTable's size must be doubled
        utilizedSize++;
        if ((float)utilizedSize / size > 0.5) {
            doubleSize();
        }
    }

    // Searches the HashTable for the value corresponding to a given key
    public String search(String key) {
        // Hash the key
        int hash = hash(key);

        // Linear Probing; search for a key that matches the key being searched for
        int i = 0;
        while (keys[hash + i] != null && !keys[hash + i].equals(key) ) {
            i++;

            // If loop reaches the end of the array, this loops it back over
            if (hash + i >= size)
                i = hash * -1;
        }

        // If the while-loop ended because it found a null index before the matching key, this signifies a search-miss
        // and therefore the key is invalid
        if (keys[hash + i] == null)
            return "INVALID KEY";

        return values[hash + i];
    }

    // Hash a given string input
    public int hash(String input) {
        // RADIX is 256 because we are using extended ASCII
        int RADIX = 256;

        // Horner's Method: loop through the sequence and add in new terms while multiplying the whole hash by the radix
        int hash = 0;
        for(int i = 0; i < input.length(); i++) {
            hash = ((hash * RADIX) + (int)(input.charAt(i))) % size;
        }
        return hash;
    }
}












