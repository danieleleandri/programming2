package programming2;
/**
 * The class HashMap provides a custom implementation of an HashMap
 * The program must be written without use any built-in Java classes
 * @author daniele leandri
 *
 */
public class HashMap {

    Item start = null;
    Item end = null;
/**
 * After instantiating the class HashMap,
 * The method main provide some basic test of the HashMap Implementation
 * @param Args
 */
    public static void main(String[] Args) {
        HashMap myMap = new HashMap();
        myMap.put( "Aldo", "car");
        myMap.put( "Francesco", "dog");
        myMap.put( "Mary", "plane");
        myMap.put( "Mary", "planes");
        System.out.println(myMap.containsKey("altEr"));
        System.out.println("size " +myMap.size());
        System.out.println("size " +myMap.size());

    }

    /**
     * Them method get returns the linked string value in the Map
     * key-->value
     * @param key the key to find in map (it is turned to lowercase)
     * @return the value String
     */
    String get(String key) {
        key = key.toLowerCase();
        /*The method performs a scan of the whole linked list to find
        the request key, then if it is found it returns the value*/
        for (Item cursor = start; cursor != null; cursor = cursor.right) {
            if (cursor.key.equals(key)) {
                return cursor.value;
            }
        }
        /*If the key is not present the method will return an error*/
        return null;
    }

    /**
     * The method will add a new couple (key,value) in the linked list
     * @param key The key of the new element
     * @param value the value of the new element
     */
    void put(String key, String value) {
        key = key.toLowerCase();
        value = value.toLowerCase();

        Item cursor = start;
        /*If the list is empty it simply add the new element and update the references*/
        if (start == null) {
            Item item = new Item(key, value, end, null);
            start = item;
            end = item;
            return;
        }
        /*
         * The method searches if the key is already in the list.
         * If so it updates the value field
         */
        for (cursor = start; cursor != null; cursor = cursor.right) {
            if (cursor.key.equals(key)) {
                cursor.value = value;
                return;
            }
        }
        /*If the key is not in the list it is added at its end. No order required*/
        Item item = new Item(key, value, null, null);
        end.right = item;
        item.left = end;
        end = item;
    }

    /**
     * The method remove the element having this.key=key
     * @param key
     * @return
     */
    int remove(String key) {
        key = key.toLowerCase();
        //Search of the key and references updating
        for (Item cursor = start; cursor != null; cursor = cursor.right) {
            if (cursor.key.equals(key)) {
         
                if (cursor.left != null) {
                    (cursor.left).right = cursor.right;
                } else {
                    start = cursor.right;
                }
                if (cursor.right != null) {
                    (cursor.right).left = cursor.left;
                } else {
                    end = cursor.left;
                }
                return 1;
            }
        }
        return 0;
    }

    /**
     * Searching method for the HashMap
     * The method will return a boolean equals to true if ther is the key in the map
     * @param key the key to searhc
     * @return if it is true the key is present, else it is not
     */
    boolean containsKey(String key) {
        key = key.toLowerCase();
        for (Item cursor = start; cursor != null; cursor = cursor.right) {
            if (cursor.key.equals(key)) {
                     return true;
            }
        }
        return false;
    }
    
    /**
     * The method  returns the number of elements present in the map
     * @return  the number of elements present in the map
     */
    long size() {
        long size = 0;
        for (Item cursor = start; cursor != null; cursor = cursor.right) {
            size ++;
        }
        return size;
    }
}
