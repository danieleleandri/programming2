package programming2;

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
