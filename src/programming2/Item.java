package programming2;
/**
 * This class is to build the elements of the custom HasMap implementation
 * @param key the key of this element
 * @param value the value of this element
 * @param left the reference to the left element
 * @param right the reference to the right element
 */
public class Item {
    public String key;
    public String value;
    public Item left;
    public Item right;
    

    public Item(String key,String value, Item left, Item right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }  

}
