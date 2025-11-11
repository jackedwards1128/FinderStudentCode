public class TSTNode {

    // Instance variables to store whether the node represents a real word, the letter it holds, and its three children
    private boolean isWord;
    private char letter;
    private TSTNode[] children;
    private String value;

    public TSTNode(char letter) {
        this.letter = letter;
        children = new TSTNode[3];
    }

    // Returns whether the node represents a real word
    public boolean getIsWord() {
        return isWord;
    }

    // Returns the requested child
    public TSTNode getChild(int index) {
        return children[index];
    }

    // Sets the node's validity of the word to true
    public void setWord() {
        isWord = true;
    }

    public void setValue(String val) {
        value = val;
    }

    // Returns the letter that the node stores
    public char getLetter() {
        return  letter;
    }

    // Adds a child to the node at a given index
    public void setChild(int index, char letter) {
        children[index] = new TSTNode(letter);
    }

    public String getValue() {
        return value;
    }
}
