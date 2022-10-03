package programming2;

public class HashMapBST {

    ItemBST root = null;
    public static void main(String [] Args) {
        HashMapBST myMap = new HashMapBST();
//        System.out.println(myMap.get(myMap.root,"ebe"));
        myMap.put(myMap.root, "Aldo", "car");
        myMap.put(myMap.root, "Francesco", "dog");
        myMap.put(myMap.root, "Mary", "plane");
        myMap.put(myMap.root, "Ebe", "elephant");
        myMap.put(myMap.root, "Ori", "Beer");
        myMap.put(myMap.root, "Beb", "Cutter");
        myMap.put(myMap.root, "Aeeh", "Papaer");
        myMap.put(myMap.root, "Marcolino", "Boat");
        myMap.put(myMap.root, "Fra", "Monitor");
        myMap.put(myMap.root, "Azzoli", "Alt");
        myMap.put(myMap.root, "Corbk", "Fire");
        myMap.put(myMap.root, "Alter", "Wather");
        myMap.put(myMap.root, "Alter", "Elper");
        
//        System.out.println(myMap.get(myMap.root,"ebe"));
//        System.out.println(myMap.get(myMap.root,"Ebe"));
//        System.out.println(myMap.get(myMap.root,"mARY"));
//        System.out.println(myMap.get(myMap.root,"Mary"));
//        
        myMap.remove(myMap.root, "Mary");
        //
        System.out.println(myMap.containsKey(myMap.root,"Mary"));
        System.out.println(myMap.get(myMap.root,"Mary"));
        
        //myMap.put(myMap.root, "Mary", "plane");
        //System.out.println(myMap.containsKey(myMap.root,"Mary"));
//        System.out.println(myMap.containsKey(myMap.root,"ELiao"));
//        System.out.println(myMap.Size(myMap.root));
//        myMap.remove(myMap.root, "Alter");
        myMap.remove(myMap.root, "Ebe");
//
//        System.out.println(myMap.Size(myMap.root));
        int i = 1;
    }
    
    String get(ItemBST current, String key) {
        if (current == null) {
            return "Key not found!";
        }
        if (current.key == key) {
            if(current.removed) return "Key not found!";
            else return current.value;
        } else if(current.key.compareTo(key) >= 0) {
            return get(current.left,key);
        } else if(current.key.compareTo(key) < 0) {
            return get(current.right,key);
        } else {
            return "Key not found!";
        }
    }
    
    
    void put(ItemBST current, String key,String value) {
        if (current == null) {
            this.root = new ItemBST(key,value, null,null);
        } else if (current.key.equals(key)) {
            current.removed=false;
            current.setValue(value);
        } else if (current.key.compareTo(key)>=0) {
            if(current.left == null) {
                current.left = new ItemBST(key,value,null,null);
            } else {
                put(current.left,key,value);
            }

        } else if (current.key.compareTo(key) < 0) {
            if(current.right == null) {
                current.right = new ItemBST(key,value,null,null);
            } else {
                put(current.right,key,value);
            }
        }
    }
    
//    
//    String inOrderTraversal(Item current) {
//        if(current != null) {
//            inOrderTraversal(current.left);
//            inOrderTraversal(current.right);            
//        }
//    }
//    
    int remove(ItemBST current,String key) {
        if (current == null) {
            return -1;
        }
        if (current.key == key) {
            current.removed = true;
            return 1;
        } else if(current.key.compareTo(key) >= 0) {
            return remove(current.left,key);
        } else if(current.key.compareTo(key) < 0) {
            return remove(current.right,key);
        } else {
            return -1;
        }
    }
    
    boolean containsKey(ItemBST current, String key) {
        if (current == null) {
            return false;
        }
        if (current.key == key) {
            if (current.removed) return false;
            else return true;
        } else if(current.key.compareTo(key) >= 0) {
            return containsKey(current.left,key);
        } else if(current.key.compareTo(key) < 0) {
            return containsKey(current.right,key);
        } else {
            return false;
        }
    }
    
    long Size(ItemBST current) {
        long count = 0;        
        if(current == null) {
           return 0;
        } else {
            if (current.removed)  return Size(current.left) + Size(current.right);
            else return Size(current.left) + Size(current.right) + 1;
        }
        
    }
}
