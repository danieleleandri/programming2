package bst;
/**
 * The class was create to maintain data and links of each tree's node
 * in detail left and right are the pointer to the other nodes
 * value contains the node's value.
 * @author daniele.leandri
 *
 */
public class Node {  
        public Node left = null;
        public Node right = null;
        public double value;
        public Node(double value){
            this.value = value;
        }

}
