import java.util.ArrayList;

/**
 * This is the MyBST.java file that contains a Binary Search Tree class
 * and a nested class which defines a Tree that can hold information
 * in a way that would reduce the amount of time of searching and 
 * sorting the numbers in increasing order. This data structure is the 
 * future of data structures.
 */



/**
 * This is the MyBST<K,V> class that defines how a Binary Search Tree is
 * where all the Nodes in the right subtree have bigger keys and 
 * all the Nodes in the left subtree have smaller keys.
 * @author drink
 *
 * @param <K extends Comparable<K>>
 * @param <V>
 */
public class MyBST<K extends Comparable<K>, V> {
	
	
	/**
	 * This is the MyBSTNode<K,V> class that defines a key and a value
	 * while also containing links to parent and child nodes.
	 * @author drink
	 *
	 * @param <K>
	 * @param <V>
	 */
	static class MyBSTNode<K, V> {
		K key; 
		V value;
		MyBSTNode<K, V> parent;
		MyBSTNode<K, V> left;
		MyBSTNode<K, V> right;
		
		/**
		 * This is the only constructor that initializes a node by setting
		 * the key and value with provided parent node
		 * @param key
		 * @param value
		 * @param parent
		 */
		public MyBSTNode(K key,V value, MyBSTNode<K, V> parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}
		/**
		 * Returns the key of the node
		 * @return K
		 */
		public K getKey() {
			return(key);
		}
		/**
		 * Returns value of the node
		 * @return V
		 */
		public V getValue() {
			return(value);
		}
		/**
		 * Returns the parent Node 
		 * @return MyBSTNode<K, V>
		 */
		public MyBSTNode<K, V> getParent() {
			return(parent);
		}
		/**
		 * Returns the left node
		 * @return - MyBSTNode<K, V>
		 */
		public MyBSTNode<K, V> getLeft() {
			return(left);
		}
		/**
		 * Returns the Right node
		 * @return MyBSTNode<K, V>
		 */
		public MyBSTNode<K, V> getRight() {
			return(right);
		}
		/**
		 * Sets the key of the node from the specified key in the parameter
		 * @param newKey - K
		 */
		public void setKey(K newKey) {
			key = newKey;
		}
		/**
		 * Sets the value of the node from the specified value in the parameter
		 * @param newValue - V
		 */
		public void setValue(V newValue) {
			value = newValue;
		}
		/**
		 * Sets the parent node from the specified node in the parameter
		 * @param newParent - Node
		 */
		public void setParent(MyBSTNode<K, V> newParent) {
			parent = newParent;
		}
		/**
		 * Sets the left node from the specified node in the parameter
		 * @param newLeft - Node
		 */
		public void setLeft(MyBSTNode<K, V> newLeft) {
			left = newLeft;
		}
		/**
		 * Sets the right node from the specified node in the parameter
		 * @param newRight - Node
		 */
		public void setRight(MyBSTNode<K, V> newRight) {
			right = newRight;
		}
		/**
		 * Finds the next highest key after the key specified in this node
		 * @return
		 */
		public MyBSTNode<K, V> successor() {
			K limit = this.getKey();
			// Check that it isn't a root in order to test parent
			if (right == null && parent != null) {
				// Testing parent
				MyBSTNode<K,V> secondNode = parent;
				// Checks to see if parent exists
				while(secondNode != null && secondNode.getParent() != null) {
					// If I had to traverse to the right for the parent, 
					// I stop
					if (secondNode.getLeft() == this) {
						return secondNode;
					}
					secondNode = secondNode.getParent();
				}
			}
			else if (right != null){
				// Testing children
				MyBSTNode<K,V> childNode = right;
				while(childNode != null && childNode.getLeft() != null) {
					childNode = childNode.getLeft();
				}
				return childNode;
			}
			return null;
		}
	}
	// The unchanging constants used in MyBST
	
	private static final int zero = 0;
	private static final int one = 1; 
	private static final int minusOne = -1;
	private static final int two = 2; 
	private static final int three = 3; 

	
	MyBSTNode<K, V> root;
	int size;
	/**
	 * This is the only constructor for a Binary Search Tree that sets
	 * the root and the size of the Binary Search Tree to default values 
	 */
	public MyBST() {
		root = null;
		size = zero;
	}
	/**
	 * Returns the node with the next highest key
	 * @param node
	 * @return
	 */
	protected MyBSTNode<K, V> successor(MyBSTNode<K,V> node) {
		if (node == null) {
			return null;
		}
		return(node.successor());
	}
	/**
	 * Returns the amount of elements in the tree
	 * @return
	 */
	public int size() {
		return size;
	}
	/**
	 * Inserts a node with the given key and value. If another Node existed with
	 * the same key, it will be overriden and it's value will be returned
	 * @param key
	 * @param value
	 * @return
	 */
	public V insert(K key, V value) {
		// I'm going to change the size before inserting the node
		// Cross fingers!!
		size++;
		if (key == null) {
			throw new NullPointerException("Key cannot be null");
		}
		if (root == null) {
			root = new MyBSTNode<K,V>(key, value, null);
			return null;
		}
		MyBSTNode<K, V> currentNode = root;
		MyBSTNode<K, V> parentNode = null;
		boolean Left = true;
		while (currentNode != null) {
			
			if (key.compareTo(currentNode.getKey()) < zero) {
				parentNode = currentNode;
				currentNode = currentNode.getLeft();
				Left = true;
				
			}
			else if (key.compareTo(currentNode.getKey()) > zero) {
				parentNode = currentNode;
				currentNode = currentNode.getRight();
				Left = false;
			}
			// Happens if there is a replacement that happens
			else {
				V ValuetoReturn = currentNode.getValue();
				currentNode.setValue(value);
				return ValuetoReturn;
			}
		}
		// Happens if currentNode is null and a new leaf must be created
		if (Left == true) {
			parentNode.setLeft(new MyBSTNode<K,V>(key, value, parentNode));
		}
		else {
			parentNode.setRight(new MyBSTNode<K,V>(key, value, parentNode));
		}
		return null;
	}
	/**
	 * Returns the value given a key to search for
	 * @param key
	 * @return value
	 */
	public V search(K key) {
		// If key is null, we don't need to do anything
		if (key == null) {
				return null;
		}
		MyBSTNode<K, V> currentNode = root;
		while (currentNode != null) {
			if (key.compareTo(currentNode.getKey()) < zero) {
				currentNode = currentNode.getLeft();
			}
			else if (key.compareTo(currentNode.getKey()) > zero) {
				currentNode = currentNode.getRight();
			}
			// Happens if I found the key!! Yay!
			else {
				return(currentNode.getValue());
			}
		}
		//I will reach this part if I haven't found the node. Boooo.
		return null;
	}
	/**
	 * Removes a node given a key and returns a value
	 * @param key
	 * @return value
	 */
	public V remove(K key) {
		// Change size before removing, fingers crossed!
		size--;
		// If key is null, we don't need to do anything
		if (key == null) {
			return null;
		}
		MyBSTNode<K, V> currentNode = root;
		while (currentNode != null) {
			if (key.compareTo(currentNode.getKey()) < zero) {
				currentNode = currentNode.getLeft();
			}
			else if (key.compareTo(currentNode.getKey()) > zero) {
				currentNode = currentNode.getRight();
			}
			// Happens if I found the key!! Yay!
			else if (key.compareTo(currentNode.getKey()) == zero){
				// No kids 
				if (currentNode.getLeft() == null && currentNode.getRight() == null) {
					V ValuetoReturn = currentNode.getValue();
					currentNode = null;
					return ValuetoReturn;
				}
				// One kid (Right)
				else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
					V ValuetoReturn = currentNode.getValue();
					MyBSTNode<K,V> parentNode = currentNode.getParent();
					if (parentNode.getKey().compareTo(currentNode.getRight().getKey()) > zero) {
						parentNode.setLeft(currentNode.getRight());
						parentNode.getLeft().setParent(parentNode);
					}
					else if (parentNode.getKey().compareTo(currentNode.getRight().getKey()) < zero) {
						parentNode.setRight(currentNode.getRight());
						parentNode.getRight().setParent(parentNode);
					}
					currentNode = null;
					return ValuetoReturn;
				}
				// One kid (Left)
				else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
					V ValuetoReturn = currentNode.getValue();
					MyBSTNode<K,V> parentNode = currentNode.getParent();
					// Setting the left node if the key is greater than child
					if (parentNode.getKey().compareTo(currentNode.getLeft().getKey()) > zero) {
						parentNode.setLeft(currentNode.getLeft());
						parentNode.getLeft().setParent(parentNode);
					}
					// Setting the right node if the key is less than child
					else if (parentNode.getKey().compareTo(currentNode.getLeft().getKey()) < zero) {
						parentNode.setRight(currentNode.getLeft());
						parentNode.getLeft().setParent(parentNode);
					}
					currentNode = null;
					return ValuetoReturn;
				}
				// Two kids
				else {
					V ValueToReturn = currentNode.getValue(); 
					K keyOfSuccessor = currentNode.successor().getKey();
					V valueOfSuccessor = currentNode.successor().getValue();
					currentNode.setKey(keyOfSuccessor);
					currentNode.setValue(valueOfSuccessor);
					this.remove(keyOfSuccessor);
					return ValueToReturn;
					
				}
			}
		}
		return null;
	}
	
	public ArrayList<MyBSTNode<K, V>> inorder() {
		ArrayList<MyBSTNode<K,V>> sortedData = new ArrayList<MyBSTNode<K,V>>();
		MyBSTNode<K,V> minNode = root;
		// Should find the minimum key for the Node 
		while (minNode != null) {
			minNode = minNode.getLeft();
		}
		MyBSTNode<K,V> node = minNode;
		// This should add a node for each element in the tree.
		// And if there is nothing in the tree, size should be zero, which means
		// Nothing will be in this array.
		for(int i = zero; i < size; i++) {
			sortedData.add(node);
			node = node.successor();
		}
		return sortedData;
		
	}
	public static void main(String[] args) {
		String five = "five"; 
		String six = "six";
		System.out.println(five.compareTo(six));
	}
}
// When doing remove, replace with suuccessor. If succesor has a right child, 
// replace it with that child, like a remove method with 1 child
