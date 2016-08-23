
/**
 * Node for an AVLTree which extends a BSTNode
 * @author Anter Aaron M. Custodio and Carl Zachery Viernes
 *	CS123 - UP Manila
 * @param <E> Data type that is to be stored in the binary tree node
 */
public class AVLNode<E extends Comparable> extends BSTNode<E> {
		private int height;
		/**
		 * Defaul constructor
		 */
		public AVLNode(){
			
		}
		/**
		 * Constructor that accepts key, right child, left child, parent
		 * @param K key that will be stored
		 * @param parent the parent of this node
		 * @param left the left child of this node
		 * @param right the right child of this node
		 */
		public AVLNode(E K, BSTNode parent, BSTNode left, BSTNode right){
			super(K, parent, left, right);
			height = 0;
		}
		/**
		 * Records the balance factor/height of this node
		 * @param bf the balance factor which will be stored in height
		 */
		public void setBF(int bf){
			height = bf;
		}
		/**
		 * Getter for balance factor of this node
		 * @return returns height/balance factor of this node
		 */
		public int getBF(){
			return height;
		}
		/**
		 * Setter for the key of this node
		 * @param key the key that will be stored in this node
		 */
		public AVLNode(E key) {
			super(key);
		} // end constructor
	
		/**
		 * Return the left child of the node
		 * @return left child of the node
		 */
		public AVLNode<E> getLeftChild() {
			return (AVLNode<E>) super.getLeftChild();
		} // end getLeftChild
		
		/**
		 * Return the right child of the node
		 * @return right child of the node
		 */
		public AVLNode<E> getRightChild() {
			return (AVLNode<E>) super.getRightChild();
		} // end getRightChild
		
		/**
		 * Return the parent of the node
		 * @return parent of the node
		 */
		public AVLNode<E> getParent() {
			return (AVLNode<E>) super.getParent();
		} // end getParent
		
		@Override
		public String toString() {
			return super.toString();
		}
	}