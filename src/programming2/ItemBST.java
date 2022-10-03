package programming2;

public class ItemBST {
    public String key;
    public String value;
    public ItemBST left;
    public ItemBST right;
    public int hash;
    public boolean removed;
    
    
    public ItemBST(String key,String value, ItemBST left, ItemBST right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.hash = key.hashCode();
        this.removed = false;
    }
    
    

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
   

}
