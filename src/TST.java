public class TST {

    private TSTNode root;
    private final int SMALLER_CHILD = 0;
    private final int DIRECT_CHILD = 1;
    private final int GREATER_CHILD = 2;

    // Initialize the root node at the letter m, because m is in the center of the alphabet (shared with n), so when
    // a word is being searched, it will reach the target node faster since the m splits two of the three cases
    // (before letter & after letter) equally.
    public TST() {
        root = new TSTNode('m');
    }

    // Search through the TST for a given word and returns whether that word exists within the structure or not
    public String search(String word) {

        // Define variable to store the "location" within the tree that we're at
        TSTNode position = root;
        // For every letter in the given word
        for(int i = 0; i < word.length();) {

            char letter = word.charAt(i);

            char positions_letter = position.getLetter();

            // If the selected letter of the given word is equal to the letter of the node being looked at, go down
            // since we are in the right place
            if(letter == positions_letter) {
                position = position.getChild(DIRECT_CHILD);

                // If there isn't anything left in the TST, our given word isn't a word
                if (position == null)
                    return "INVALID KEY";

                // Increment the loop-variable to move forward onto the next letter in the given word
                i++;
                continue;
            }

            // Move left/right depending on whether the current letter is less than/greater than the current selected
            // node
            if (letter - positions_letter > 0) {
                position = position.getChild(GREATER_CHILD);
            } else {
                position = position.getChild(SMALLER_CHILD);
            }

            // If there isn't anything left in the TST, our given word isn't a word
            if (position == null)
                return "INVALID KEY";
        }
        // Once we finish out moving along the letter-chain, return the word-ness of the final node we arrived at
        if (position.getIsWord() == false)
            return "INVALID KEY";
        else
            return position.getValue();
    }

    // Inserts a given word into the TST
    public void insert(String word, String value) {
        // Define variable to store the "location" within the tree that we're at
        TSTNode position = root;

        // For every letter in the given word
        for(int i = 0; i < word.length();) {

            char letter = word.charAt(i);
            char positions_letter = position.getLetter();

            // If the selected letter of the given word is equal to the letter of the node being looked at, go down
            // since we are in the right place
            if(letter == positions_letter) {

                // If there is nothing left at the spot in the TST, make new nodes to represent the current letter
                // of the given word
                if (position.getChild(DIRECT_CHILD) == null)
                    position.setChild(DIRECT_CHILD, letter);

                position = position.getChild(DIRECT_CHILD);

                // Increment the loop-variable to move forward onto the next letter in the given word
                i++;
                continue;
            }

            // Move left/right depending on whether the current letter is less than/greater than the current selected
            // node, and if there isn't anything there, make a new node to represent the selected letter of the word
            if (letter - positions_letter > 0) {
                if (position.getChild(GREATER_CHILD) == null)
                    position.setChild(GREATER_CHILD, letter);

                position = position.getChild(GREATER_CHILD);
            } else {
                if (position.getChild(SMALLER_CHILD) == null)
                    position.setChild(SMALLER_CHILD, letter);

                position = position.getChild(SMALLER_CHILD);
            }
        }

        // Once we reach the final spot, set the word-ness of our final node to be true (because we just inputted
        // real word)
        position.setWord();
        position.setValue(value);
    }

}
