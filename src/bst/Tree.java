package bst;
/**
 * The class Bst will host and manage the bynary search tree
 * @author daniele.leandri
 *
 */
public class Tree {

    public Node root = null; //Pointer to the root of the Bst
    double numberOfLeaves = 0; /* Variable used to store the number of leaves
    							  it will be updated by the method inorderBstTraversal*/
    double sumOfDeepnesses = 0; /* Variable used to store the sum of deepnesses
	 							it will be updated by the method inorderBstTraversal*/
    double maxLevel = 0;        /* Variable used to store the max level reached
	  								it will be updated by the method inorderBstTraversal*/


    /**
     * The method will insert value into the bst. 
     * The method will find the correct position of the value in the tree, then it 
     * creates  a new element, finally it links it to the rest of the tree.
     * @param value the value to add
     */
	public void insert(double value) {
        Node node = new Node(value);
        if(root == null){
            root = node;
            return;
        } 
        Node runner = root;
        while (true){
            if(value < runner.value){
                if (runner.left == null){
                    runner.left = node;
                    return;
                } else {
                    runner = runner.left;
                }
            } else {
                if (runner.right == null){
                    runner.right = node;
                    return;
                } else{
                    runner = runner.right;
                }
            }
        }
    }

	/**
	 * The method performs a recursive inorder traversal of the whole tree.
	 * When it finds an element having both right and left equal to null
	 * it updates the Tree's class varibles: numberOfLeaves, sumOfDepnesses, and maxLevel
	 * @param root the bst root, the start of method
	 * @param parentLevel This variable is to cont the level
	 */
    public void inorderBstTraversal(Node root, int parentLevel) {
    	if (root != null) {
    		if ((root.left == null ) && ( root.right == null)) {
    			//System.out.println("LEAVE REACHED at level " + parentLevel);
    			numberOfLeaves ++;
    			if (parentLevel > maxLevel) maxLevel = parentLevel;
        		sumOfDeepnesses += parentLevel;
    		}
    		inorderBstTraversal(root.left, parentLevel +1);
    		//System.out.println(root.value + " ");
    		inorderBstTraversal(root.right, parentLevel +1);
    	} 
    }
    
    
    //GETTERS
    public double getNumberOfLeaves() {
		return numberOfLeaves;
	}



	public double getSumOfDeepnessess() {
		return sumOfDeepnesses;
	}
    
	public double getAverageOfDeeps() {
		return sumOfDeepnesses/numberOfLeaves;
	}
	
	public double getMaxLevel() {
		return maxLevel;
	}
	
}
