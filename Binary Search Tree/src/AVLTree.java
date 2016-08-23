import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * An implementation of AVL tree which inherits from a binary search tree
 * Algorithms are based on Evangel Quiwa's pseudocode on AVLTrees
 * @author Anter Aaron Custodio & Carl Zachery Viernes
 * CS123 - UP Manila
 * @param <E>
 *
 */
@SuppressWarnings("all")
public class AVLTree<E extends Comparable> extends BinarySearchTree<E> {
	private AVLNode root;
	private AVLNode a;
	private AVLNode y;
	private AVLNode v;
	private AVLNode b;
	private AVLNode p;
	private AVLNode cur;
	private int w = 0;
	
	/**
	 * Default Constructor for this class
	 */
	public AVLTree(){
		root = null;
	}
	/**
	 * Constructor that accepts a default root
	 * @param root Root of the subtree
	 */
	public AVLTree(AVLNode<E> root){
		super(root);
		this.root = root;
	}
	
	/**
	 * AVLTree Insertion which overrides BinarySearchTree's insertion
	 */
	public void insert(E K){
		
		if(root==null){
			v = new AVLNode();
			root = v;
			v.setKey(K);
			v.setRightChild(null);
			v.setLeftChild(null);
			v.setBF(0);
			return;
		}
		a = root;
		y = root;	
		p = new AVLNode();
		AVLNode t = new AVLNode();
		while(true){
			if(K.compareTo(y.getKey())==0){
				System.out.println("Duplicate Key!");
				return;
			}
			if(K.compareTo(y.getKey())<0){
				t = y.getLeftChild();
				if(t==null){
					v = new AVLNode();
					y.setLeftChild(v);
					break;
				}
			}else if(K.compareTo(y.getKey())>0){
				t = y.getRightChild();
				if(t==null){	
					v = new AVLNode();
					y.setRightChild(v);
					break;
				}
			}
			if(t.getBF()!=0){
				a = t;
				a.setParent(y);
			}
			y = t;
		}
		v.setKey(K);
		v.setParent(y);
		v.setLeftChild(null);
		v.setRightChild(null);
		v.setBF(0);
		
		if(K.compareTo(a.getKey())<0){
			b = y = a.getLeftChild();
		}else{
			b = y = a.getRightChild();
		}
		
		while(y!=v){		
			if(K.compareTo(y.getKey())<0){
				y.setBF(-1);
				y = y.getLeftChild();
			}else{
				y.setBF(+1);
				y = y.getRightChild();
			}
		}
		
		if(K.compareTo(a.getKey())<0)
			w= (-1);
		else
			w=1;
		
		if(a.getBF()==0)
			a.setBF(w);
		else if (a.getBF()==(-w))
			a.setBF(0);
		else if (a.getBF()==w){	
			if(K.compareTo(a.getKey())<0&&b.getBF() == (-w))
				rotateLeftRight(a);
			else if(K.compareTo(a.getKey())<0&&b.getBF()==w){
				rotateRight(a);
			}
			else if(K.compareTo(a.getKey())>0&&b.getBF()==w)
				rotateLeft(a);
			else if(K.compareTo(a.getKey())>0&&b.getBF()==(-w))
				rotateRightLeft(a);
			if(a==root)
				root = p;
			else if (a==a.getParent().getLeftChild())
				a.getParent().setLeftChild(p);
			else if (a==a.getParent().getRightChild())
				a.getParent().setRightChild(p);
		}
		setParents(root);
	}
	/**
	 * Rotates Left if the tree is unbalanced
	 */
	private void rotateLeft(AVLNode<E> a){	
		p = a.getRightChild();
		p.setParent(a.getParent());
		a.setRightChild(p.getLeftChild());
		p.setLeftChild(a);
		a .setBF(0);
		p.setBF(0);
		
	}
	/**
	 * Rotates right if the tree is unbalanced
	 */
	private void rotateRight(AVLNode<E> a){
		p = a.getLeftChild();
		p.setParent(a.getParent());
		a.setLeftChild(p.getRightChild());
		p.setRightChild(a);
		a.setBF(0);
		p.setBF(0);
	}
	
	/**
	 * Rotates left then right when the tree is unbalanced
	 */
	private void rotateLeftRight(AVLNode<E> a){
		AVLNode b = new AVLNode();
		b = a.getLeftChild();
		p = b.getRightChild();
		p.setParent(a.getParent());
		b.setRightChild(p.getLeftChild());
		p.setLeftChild(b);
		a.setLeftChild(p.getRightChild());
		p.setRightChild(a);
	
		
		if(p.getBF()==0){	
			a.setBF(0);
			b.setBF(0);
		}else if(p.getBF()==1){
			a.setBF(0);
			b.setBF(-1);
		}else if(p.getBF()==-1){
			a.setBF(1);
			b.setBF(0);
		}
		p.setBF(0);
	
		
	}
	/**
	 * rotates right and then left if the tree is unbalanced
	 */
	private AVLNode<E> rotateRightLeft(AVLNode<E> a){
		AVLNode b = new AVLNode();
		b = a.getRightChild();
		p = b.getLeftChild();
		p.setParent(a.getParent());
		b.setLeftChild(p.getRightChild());
		p.setRightChild(b);
		a.setRightChild(p.getLeftChild());
		p.setLeftChild(a);

		
		if(p.getBF()==0){
			a.setBF(0);
			b.setBF(0);
		}else if(p.getBF()==1){
			a.setBF(-1);
			b.setBF(0);
		}else if(p.getBF()==-1){
			a.setBF(0);
			b.setBF(1);
		}
		p.setBF(0);
		return a;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Level order traversal of this AVLtree
	 * @return level order string of this tree
	 */
	public String levelorder(){
		return(new BinarySearchTree(root).levelorder());
	}
	/**
	 * Post order traversal of this AVLTree
	 * @return post order string of this tree
	 */
	public String postorder(){
		return(new BinarySearchTree(root).postorder());
	}
	/**
	 * InOrder Traversal of this tree
	 * @return inorder string of this tree
	 */
	public String inorder(){
		return(new BinarySearchTree(root).inorder());
	}
	/**
	 * Pre order Traversal of this tree
	 * @return preorder string of this tree
	 */
	public String preorder(){
		return(new BinarySearchTree(root).preorder());
	}
	/**
	 * Gets the predecessor of the node
	 * @return node the predecessor node
	 */
	public AVLNode<E> predecessor(E key){
		return (AVLNode<E>) (new BinarySearchTree(root).predecessor(key));
	}
	/**
	 * Gets the successor of the node
	 * @return node successor node
	 */
	public AVLNode<E> successor(E key){
		return (AVLNode<E>) (new BinarySearchTree(root).successor(key));
	}
	/**
	 * Searches the inputted key in the tree, returns null if not found
	 * @return the node that contains the key being searched
	 */
	public AVLNode<E> search(E key){
		return(AVLNode<E>) (new BinarySearchTree(root).search(key));
	}
	/**
	 * Returns the lowest value key in the AVLTree
	 * @return lowest key node
	 */
	public AVLNode<E> minimum(){
		return(AVLNode<E>) (new BinarySearchTree(root).minimum());
	}
	/**
	 * Returns the highest value key in the AVLTree
	 * @return highest key node
	 */
	public AVLNode<E> maximum(){
		return(AVLNode<E>) (new BinarySearchTree(root).maximum());
	}
	
	//-------------------------------------DELETE----------------------------------------------------------------------//
	/**
	 * Deletes <code>key</code> from the AVL tree
	 * 
	 * @param key key the is to be deleted from the AVL tree 
	 */
	public void delete(E key) {
		AVLNode<E> z = search(key);
		AVLNode<E> temp = new AVLNode<E>();
		if (z == null) {
			throw new InvalidKeyException("Key not found in the binary search tree.");
		} // end if
		
		if (z.getLeftChild() == null) {
			transplant(z, z.getRightChild());
		} // end if
		else if (z.getRightChild() == null) {
			transplant(z, z.getLeftChild());
		} // end else if
		else {
			BinarySearchTree<E> rightSubtree = new BinarySearchTree(z.getRightChild());
			AVLNode<E> y = (AVLNode<E>) rightSubtree.minimum();
			if (y.getParent() != z) {
				transplant(y, y.getRightChild());
				y.setRightChild(z.getRightChild());
				y.getRightChild().setParent(y);
			} // end if
			temp = y.getParent();
			transplant(z, y);
			y.setLeftChild(z.getLeftChild());
			y.getLeftChild().setParent(y);
		} // end else
		cur = new AVLNode();
	
		if(z.getParent()!=null){
			cur = z.getParent(); //sets cur as the parent of the newly deleted node
		}else{
			cur =  temp.getParent();
			if(cur==null){
				cur = root;
			}
		}
		recursiveBalance(); //rebalance the tree after deletion
		
	} // end delete
	
	private void transplant(AVLNode<E> u, AVLNode<E> v) {
		if(u.getParent() == null) {
			root = v;
		} // end if
		else if (u == u.getParent().getLeftChild()) {
			u.getParent().setLeftChild(v);
		} // end else if
		else {
			u.getParent().setRightChild(v);
		} // end else
		
		if (v != null) {
			v.setParent(u.getParent());
		} // end if		
	} // end transplant
	/**
	 * Rebalance the height of the tree 
	 * @param cur current node that was deleted
	 * @return height of this node
	 */
	 private int height(AVLNode cur) {
		  if(cur==null) {
		   return -1;
		  }
		  if(cur.getLeftChild()==null && cur.getRightChild()==null) {
		   return 0;
		  } else if(cur.getLeftChild()==null) {
		   return 1+height(cur.getRightChild());
		  } else if(cur.getRightChild()==null) {
		   return 1+height(cur.getLeftChild());
		  } else {
		   return 1+max(height(cur.getLeftChild()),height(cur.getRightChild()));
		  }
		 }
	 /**
	  * Compares which height is greater
	  * @param a parameter one
	  * @param b parameter two
	  * @return returns the larger between two parameters
	  */
	 private int max(int a, int b) {
		  if(a>=b) {
		   return a;
		  } else {
		   return b;
		  }
	 }
	 /**
	  * Sets Balance Factor of this node
	  * @param cur the node to be rebalanced
	  */
	 private void setBalance(AVLNode cur) {
		  cur.setBF(height(cur.getRightChild())-height(cur.getLeftChild()));
	}
	
	 /**
	  * recursively rebalance the tree using the global variable <code>curr</code> which was recently deleted
	  */
	 private void recursiveBalance() {
		  p = new AVLNode<E>();
		  // we do not use the balance in this class, but the store it anyway
		  setBalance(cur);
		  int balance = cur.getBF();
		  
		  // check the balance
		  if(balance==-2) {
		   
		   if(height(cur.getLeftChild().getLeftChild())>=height(cur.getLeftChild().getRightChild())) {
		    rotateRight(cur);
		    cur = p;
		   } else {
			 rotateLeftRight(cur);
			 cur = p;
		   }
		  } else if(balance==2) {
		   if(height(cur.getRightChild().getRightChild())>=height(cur.getRightChild().getLeftChild())) {
		    rotateLeft(cur);
		    cur = p;
		   } else {
		    rotateRightLeft(cur);
		    cur = p;
		   }
		  }
		  // we did not reach the root yet
		  if(cur.getParent()!=null) {
			  cur = cur.getParent();
			  recursiveBalance();
		  } else {
		   this.root = cur;
		   setParents(root);
		  }
		 }
	 /**
	  * Reupdate the parents of this AVLTree
	  * @param n root of the tree
	  */
	 private void setParents(AVLNode<E> n){
		 
		 AVLNode<E> temp = new AVLNode<E>();
		 temp = n;
		
		 if(temp.getLeftChild()!=null){
			 temp.getLeftChild().setParent(temp);
			 setParents(temp.getLeftChild());
		 }
		 
		 if(temp.getRightChild()!=null){
			 temp.getRightChild().setParent(temp);
			 setParents(temp.getRightChild());
		 }
	
		 
		 
	 }
	//------------------------------------------End of Delete---------------------------------------------------------------
	
	/////////////////////////////////////////////////////////////////////////////////////////////
}
