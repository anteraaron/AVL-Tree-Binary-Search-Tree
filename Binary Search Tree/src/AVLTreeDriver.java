/**
 *Driver for the AVLTree
 * @author Anter Aaron M. Custodio and Carl Zachery Viernes
 *
 */
public class AVLTreeDriver {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AVLTree avl = new AVLTree();
		
		//insert values into our Tree
		avl.insert(60);	
		avl.insert(70);
		avl.insert(50);
		avl.insert(45);
		avl.insert(55);
		avl.insert(56);
		avl.insert(1);
		
		//displays all the functions of this avltree
		System.out.println("Level Order: " + avl.levelorder());
		System.out.println("Pre Order: " + avl.preorder());
		System.out.println("Post Order: " + avl.postorder());
		System.out.println("InOrder: " + avl.inorder());
		System.out.println("The Maximum key is: " + avl.maximum());
		System.out.println("The Minimum key is: " + avl.minimum());
		System.out.println("The Predecessor of 50 is: " + avl.predecessor(50));
		System.out.println("The Successor of 55 is: " + avl.successor(55));
		System.out.println("Seaching for 100 which is not in the tree: " + avl.search(100));
		System.out.println("Searching for 56: " +avl.search(56) );
		
		
		
		//deletes the nodes in our tree
		
		System.out.println("\n Original Tree ");
		System.out.println(avl.levelorder());
		avl.delete(70);
		System.out.println("Level Order after Deletion of 70: " + avl.levelorder());
		avl.delete(56);
		System.out.println("Level Order after Deletion of 56: " + avl.levelorder());
		avl.delete(60);
		System.out.println("Level Order after Deletion of 60: " + avl.levelorder());
		System.out.println("The tree was rebalanced after deletion");
		
	}

}
