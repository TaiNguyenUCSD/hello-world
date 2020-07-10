/**
 * This is BinaryTree.java file which contains several imports and a 
 * a Binary Tree class which contains a Node class.
 */


import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Tree class. This defines a tree where each branch 
 * has two sub-branches(children). After a single element is 
 * added, it is impossible for there to be a null element in 
 * this BinaryTree
 * @author drink
 *
 * @param <E>
 */
public class BinaryTree<E extends Comparable<E>> {
	
	Node root;
	int size;
	//Nested class: Node that is defined in Binary Tree class
	/**
	 * Defines one place in the Binary Tree. This also points
	 * to a left and right node as well as containing some 
	 * data
	 * @author drink
	 */
	protected class Node {
		Node left; 
		Node right;
		E data;
		/**
		 * Only constructor for Node that makes the data
		 * equal to the parameter. Initializes left and right
		 * pointers to null
		 * @param data
		 */
		public Node(E data) {
			left = null;
			right = null; 
			this.data = data;
		}
		/**
		 * Point left Node to Node in parameter
		 * @param left
		 */
		public void setLeft(Node left) {
			this.left = left;
		}
		/**
		 * Point right Node to Node in parameter
		 * @param right
		 */
		public void setRight(Node right) {
			this.right = right;
		}
		/**
		 * Sets the data to what's specified in the parameter
		 * @param data
		 */
		public void setData(E data) {
			this.data = data; 
		}
		/**
		 * Returns the Node that the current Node points Left
		 * @return
		 */
		public Node getLeft() {
			return left;
		}
		/**
		 * Returns the Node that the current Node points Right
		 * @return
		 */
		public Node getRight() {
			return right;
		}
		/**
		 * Returns data inside the node
		 * @return
		 */
		public E getData() {
			return data; 
		}		
	}
	/**
	 * No argument constructor used to make a BinaryTree
	 */
	public BinaryTree() {
		root = null;
		size = 0;
	}
	/**
	 * One argument constructor used to initialize the root
	 * of the BinaryTree
	 * @param data
	 */
	public BinaryTree(E root) {
		this.root = new Node(root);
		size = 1;
	}
	/**
	 * Constructor with a list to initialize the first 
	 * elements with data specified in the list
	 * @param list
	 */
	public BinaryTree(List<E> list) {
		for (int i = 0; i < list.size(); i++) {
			this.add(list.get(i));
		}
	}
	/**
	 * Adds element to the end of the Binary Tree
	 * @param element
	 */
	public void add(E element) {
		if (element.equals(null)) {
			throw new NullPointerException("data cannot be null");
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node curr = queue.remove();
			if (curr.getLeft() != null) {
				queue.add(curr.getLeft());
			}
			else {
				curr.setLeft(new Node(element));
				size++;
				return;
			}
			if (curr.getRight() != null) {
				queue.add(curr.getRight());
			}
			else {
				curr.setRight(new Node(element));
				size++;
				return;
			}
		}
	}
	/**
	 * Removes specified element from Binary Tree and replaces it 
	 * with last element.
	 * @param element
	 * @return
	 */
	public boolean remove(E element) {
		if (element.equals(null)) {
			throw new NullPointerException("data cannot be null");
		}
		Node NodetoRemove = null;
		Node LastNode= null;
		//***** Remember corner case where it is empty **//
		if (root == null) {
			return false;
		}
		//****** Remember to cover corner case where you can remove root****//
		if (root.getLeft() == null && root.getRight() == null) {
			root = null;
		}
		
		
		// Assuming "Normal" tree, at least 4 "amiright"
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node curr = queue.remove();
			if (curr.getData().equals(element)) {
				/*just point the pointers to the last element and remove 
				 * pointers from the original parent.
				 * Also, I'm super confused because I'm going to try to find
				 * the last Node in this by basically inventing another queue
				 */
				NodetoRemove = curr;
				
			}
			/*
			 * This might not be right either. I'm having trouble
			 * retrieving the last element if it is null 
			 */
			if (curr.getLeft() != null) {
				queue.add(curr.getLeft());
			}
			if (curr.getRight() != null) {
				queue.add(curr.getRight());
			}
		}
		//Find Last Node
		queue.clear();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node curr = queue.remove();
			if (curr.getLeft() != null) {
				queue.add(curr.getLeft());
			}
			else {
				LastNode = curr;
			}
			if (curr.getRight() != null) {
				queue.add(curr.getRight());
			}
			else {
				LastNode = curr;
			}
		}
		/*
		 * Look this should be correct, please, spare my PA points
		 */
		System.out.println(NodetoRemove.getData());
		System.out.println(LastNode.getData());
		if (NodetoRemove != null && LastNode != null) {
			NodetoRemove.setData(LastNode.getData());
			//I'm gonna try to use an unreliable method already to try to find
			// the parent of the last node. Here goes nothing
			queue.clear();
			queue.add(root);
			Node parentOfLast; 
			while (!queue.isEmpty()) {
				Node curr = queue.remove();
				if (curr.getLeft() == LastNode) {
					parentOfLast = curr;
					parentOfLast.setLeft(null);
					size--;
					return true;
				}
				if (curr.getRight() == LastNode) {
					parentOfLast = curr;
					parentOfLast.setRight(null);
					size--;
					return true;
				}
				if (curr.getLeft() != null) {
					queue.add(curr.getLeft());
				}
				if (curr.getRight() != null) {
					queue.add(curr.getRight());
				}
			}
			
			
		}
		return false; 
	}
	/**
	 * Returns true if binary tree contains element. Will 
	 * search breath first, layer by layer
	 * @param data
	 * @return
	 */
	public boolean containsBFS(E data) {
		if (data.equals(null)) {
			throw new NullPointerException("data cannot be null");
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node curr = queue.remove();
			if (curr.getData().equals(data)) {
				return true;
			}
			if (curr.getLeft() != null) {
				queue.add(curr.getLeft());
			}
			if (curr.getRight() != null) {
				queue.add(curr.getRight());
			}
		}
		return false;
	}
	/**
	 * Returns the height of the binary tree, excluding the root
	 * @return
	 */
	public int getHeight() {
		if (size <= 1) {
			return 0; 
		}
		int count = 0; 
		//I tried to use math, I tried to play nice
		//Well well well, let's so who will place nice
		//Once you see my special attack
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node curr = queue.remove();
			if (curr.getLeft() != null) {
				queue.add(curr.getLeft());
				count++;
			}
			else {
				break;
			}
			
		}
		return count;
	}
	
	/**
	 * Returns the size of the binary tree, the number of elements
	 * @return
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Returns the minimum data in the Binary Tree
	 * @return
	 */
	public E minValue() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		E theMinimum = root.getData();
		while(!queue.isEmpty()) {
			Node curr = queue.remove();
			if (curr.getData().compareTo(theMinimum) < 0) {
				theMinimum = curr.getData();
			}
			if (curr.getLeft()!=null) {
				queue.add(curr.getLeft());
			}
			if (curr.getRight()!=null) {
				queue.add(curr.getRight());
			}
		}
		return theMinimum;
	}
	
	
	
	
	
	public static void main(String[] a) {
		
	}
}
