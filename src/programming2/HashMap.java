package programming2;

public class HashMap {

    Item start = null;
    Item end = null;

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

    String get(String key) {
        key = key.toLowerCase();
        for (Item cursor = start; cursor != null; cursor = cursor.right) {
            if (cursor.key.equals(key)) {
                return cursor.value;
            }
        }
        return "Key not found";
    }

    void put(String key, String value) {
        key = key.toLowerCase();
        value = value.toLowerCase();
        Item cursor = start;
        if (start == null) {
            Item item = new Item(key, value, end, null);
            start = item;
            end = item;
            return;
        }
        for (cursor = start; cursor != null; cursor = cursor.right) {
            if (cursor.key.equals(key)) {
                cursor.value = value;
                return;
            }
        }
        Item item = new Item(key, value, null, null);
        end.right = item;
        item.left = end;
        end = item;
    }

    int remove(String key) {
        key = key.toLowerCase();
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

    boolean containsKey(String key) {
        key = key.toLowerCase();
        for (Item cursor = start; cursor != null; cursor = cursor.right) {
            if (cursor.key.equals(key)) {
                     return true;
            }
        }
        return false;
    }
    
    long size() {
        long size = 0;
        for (Item cursor = start; cursor != null; cursor = cursor.right) {
            size ++;
        }
        return size;
    }
}
