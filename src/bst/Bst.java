package bst;
/**
 * The Class Bst produce a Binary Search tree and fill it with
 * items = 1023 random values.
 * The class will then call the class method inorderBstTraversal to calculate the requested statistics
 * @author daniele.leandri
 *
 */
public class Bst {
	/*The number of items to be inserted in the BST*/
	static int items = 1023;
    public static void main(String [] args){
        /*Creation of an instance of Tree*/
    	Tree tree = new Tree();
        /*Now the Bst is filled with Random double values*/
    	for (int i = 0; i < items ; i++){
            double value = Math.random();
            tree.insert(value);
            
        }
    	/*The tree's method inorderBstTraversal will now calculate the statistics*/
        tree.inorderBstTraversal(tree.root, 0);
        
        /*Output of the results*/
        System.out.println("**************** ");
        System.out.println( "Found " + 
        					tree.numberOfLeaves +
        					" leaves having an average deep of " + 
        					tree.getAverageOfDeeps() +
        					" and a meximum deep of " +
        					tree.getMaxLevel());
    }


    
}
