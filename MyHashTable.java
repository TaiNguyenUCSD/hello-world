/**
 * Contains MyHashTable and 
 */

/* Name: Tai Nguyen
 * Login: cs12sp20amu
 * Email: tan005@ucsd.edu */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class MyHashTable implements MyHashTableInterface {
	//Constant used to double the size and do addition
	private final static int CONSTANT_TWO = 2;
	//Constant used to initialize 
	private final static int zero = 0;
	//One 
	private static final int one = 1;
	//Three
	private static final int three = 3;
	private static final double floatrandomNumber = 17.5353442314123;
	private static final int input = 4;
	private static final int ARRAY_SIZE = 256;
	private static final double twothirds = 0.67;

	LinkedList<String>[] array;//Array that stores linkedlists
	int nelems;  //Number of element stored in the hash table
	int expand;  //Number of times that the table has been expanded
	int collision;  //Number of collisions since last expansion
	String statsFileName;     //FilePath for the file to write statistics
	//upon every rehash
	boolean printStats = false;   //Boolean to decide whether to write
	//stats to file or not after rehashing

	//Feel free to add more :)
	/**
	 * Is used to initialize MyHashTable just given a size. Since
	 * No File name is specified, no statistics can be allowed to
	 * be written on any file
	 * @param size
	 */
	@SuppressWarnings("unchecked")
	public MyHashTable(int size) {
		if (size < zero) {
			throw new IllegalArgumentException("size can't be less"
					+ "than zero");
		}
		array = new LinkedList[size];
		nelems = zero;
		expand = zero;
		collision = zero;
		statsFileName = null; 
		printStats = false;
		
	}
	/**
	 * Used to initialize MyHashTable given a size and 
	 * a filename to write statistics to before 
	 * resizing
	 * @param size
	 * @param fileName
	 */
	@SuppressWarnings("unchecked")
	public MyHashTable(int size, String fileName){
		if (size < zero) {
			throw new IllegalArgumentException("size can't be less"
					+ "than zero");
		}
		if (fileName == null) {
			throw new NullPointerException("file name is null");
		}
		array = new LinkedList[size];
		nelems = zero;
		expand = zero;
		collision = zero;
		statsFileName = fileName;
		printStats = true;
	}
	/** Used to insert a String into MyHashTable. If the 
	 * String already exists then it will return false and not
	 * insert the String.
	 * @param value - String argument passed in
	 * @return boolean - true if successfully inserted
	 */
	@Override
	public boolean insert(String value) {
		if (value == null) {
			throw new NullPointerException("value is null, try again");
		}
		int index = this.hashString(value);
		
		/*Check the Linked List at this index to see if contains the value
		* will only check if the array index is not null (because then the String wouldn't
		*exist)
		*/
		if (array[index] != null) {
			for (int indexOfList = zero; indexOfList < array[index].size(); indexOfList++) {
				if (value.equals(array[index].get(indexOfList))) {
					return false;
				}
			} 
		}
		// Checks if loadFactor with one more element
		float loadFactor = (float)(nelems+one)/array.length;
		//Rehashes if loadfactor reaches 2/3 or more
		
		if (loadFactor >= twothirds) {
			this.rehash();
		}
		//Creates new linkedList after rehash if array element is null
		if (array[index] == null) {
			array[index] = new LinkedList<String>();
		}
		//Check if there are any elements in the LinkedList before insertion
		if (array[index].size() > zero) {
			collision++;
		}
		
		
		/* After checking if the value is unique and the hashtable is 
		 * big enough, it will finally insert the String
		 */
		array[index].addLast(value);
		// Update instance variables
		// If the LinkedList has more than one element, a collision occured
		
		nelems++;
		return true;
	}
	/**
	 * Used to delete a String from the HashTable
	 * @param value - String wanted to delete
	 */
	@Override
	public boolean delete(String value) {
		//Throw NullPointerException if value is null
		if (value == null) {
			throw new NullPointerException("Value is null, try again");
		}
		int indexToCheck = hashString(value);
		// If array index is null, element does not exist there
		if (array[indexToCheck] == null) {
			return false;
		}

		//Keeps track of size of LinkedList 
		int previousSize = array[indexToCheck].size();
		/* Will run through for loop to attempt to remove a node 
		 * from the linkedList that contains the String
		 */
		boolean deleteSuccess = false;
		for (int indexOfList = zero; indexOfList < array[indexToCheck].size(); 
				indexOfList++) {
			if (value.equals(array[indexToCheck].get(indexOfList))) {
				array[indexToCheck].remove(indexOfList);
				deleteSuccess = true;
				break;
			}
		}
		//Updates instance variables 
		/*If the previous size was less than the current size,
		 * the amount of collisions will have decreased
		 */
		if (array[indexToCheck].size() < previousSize) {
			collision--;
		}
		if (array[indexToCheck].size() <= zero) {
			array[indexToCheck] = null;
		}
		if (deleteSuccess == true) {
			nelems--;
		}
		return deleteSuccess;
	}
	/**
	 * Returns true if the hashtable contains the string 
	 * @param value - the String the user wants to check 
	 * @return boolean - true if the hash table contains the String
	 */
	@Override
	public boolean contains(String value) {
		int index = hashString(value);
		LinkedList<String> slotToCheck = array[index];
		if (slotToCheck != null) {
			for (int indexOfList = zero; indexOfList < slotToCheck.size();
					indexOfList++) {
				if (value.equals(slotToCheck.get(indexOfList))) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Is used to print the contents of the HashTable to stdout, sorting 
	 * it by index of the array and displaying contents of the linked lists
	 */
	@Override
	public void printTable() {
		String printedString = new String();
		for (int indexOfArray = zero; indexOfArray < array.length; 
				indexOfArray++) {
			printedString = printedString + indexOfArray + ":"; 
			LinkedList<String> slot = array[indexOfArray];
			if (slot != null) {
				//Ending the Counting linked list at zero so there is no comma
				//after the last element
				for (int indexOfLinkedList = zero; indexOfLinkedList < slot.size()-one;
						indexOfLinkedList++) {
					printedString = printedString + " " + slot.get(indexOfLinkedList);
					printedString += ",";
				}
				printedString = printedString + " "+ slot.get(slot.size()-one);
			}
			printedString += "\n";
		}
		System.out.println(printedString);
	}
	/**
	 * Returns the number of elements stored in MyHashTable
	 * @return int - number of Strings stored
	 */
	@Override
	public int getSize() {
		return nelems;
	}
	/**
	 * Expands the underlying linkedList Array and reinserts 
	 * all of the values
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void rehash() {
		int newSize = this.primeGen();
		expand++;
		// Print statistics to a new file
		if (this.printStats == true) {
			this.printStatistics();
		}
		// I'm going to make a copy of the array so I can easily
		// just go through and rehash all the values for a completely new array
		LinkedList<String>[] clonedArray = array.clone();
		array = new LinkedList[newSize];
	
		
		for (int indexOfArray = zero; indexOfArray < clonedArray.length; 
				indexOfArray++) {
			LinkedList<String> slot = clonedArray[indexOfArray];
			if (slot != null) {
				for (int indexOfLinkedList = zero; indexOfLinkedList < slot.size();
						indexOfLinkedList++) {
					String value = slot.get(indexOfLinkedList);
					int index = this.hashString(value);
					if (array[index] == null) {
						array[index] = new LinkedList<String>();
					}
					array[index].add(value);
				}
			}	
		}
		return;
	}

	/**
	* Calculate the hash value of a given string using CRC Hash
	* @param str the string value
	* @return the hash value
	*/
	public int hashString(String str){
		int h = 0;
		for (int j = 0; j < str.length(); j++) {
			int ki = str.charAt(j);
			int highorder = h & 0xf8000000;
			h = h<<5;
			h = h ^ (highorder>>>27);
			h = h ^ ki;
			
		}
		return Math.abs(h%(this.array.length));
	}
	
	public int PJW(String str) {
		int h = 0;
		for (int j = str.length()-1; j >= 0; j--) {
			int ki = str.charAt(j);
			h = (h<<4) + ki;
			int g = h & 0xf0000000;
			if (g != 0) {
				h = h ^ (g>>>24);
				h = h ^ g;
			}
		}
		return Math.abs(h%(this.array.length));
	}
	
	public int BUZ(String str) {
		Random r = new Random();
		r.setSeed(1);
		int R[] = new int[ARRAY_SIZE];
		for (int i = 0; i<ARRAY_SIZE; i++) {
			R[i] = r.nextInt();
		}
		int h = 0;
		for (int j = str.length() - 1; j>=0; j--) {
			int ki = str.charAt(j);
			int highorder = h & 0x80000000;
			h = h << 1;
			h = h ^ (highorder >>> 31);
			h = h ^ R[ki];
			
		}
		return Math.abs(h%(array.length)); 
		
	}
	/**
	* Print statistics to the given file.
	* @return True if successfully printed statistics, false if the file
	*         could not be opened/created.
	*/
	@Override
	public boolean printStatistics(){
		PrintStream out;
		try {
			out = new PrintStream( new FileOutputStream( this.statsFileName,
			true ) );
		} catch(FileNotFoundException e) {
			return false;
		}
		out.print(this.expand + " resizes, ");//Print resize times
		//Calculate the load factor
		double loadFactor = ( (double) nelems / array.length );
		DecimalFormat df = new DecimalFormat("#.##"); //Print the load factor
		out.print("load factor " + df.format( loadFactor ) + ", ");
		out.print(this.collision + " collisions, "); //Print collision times
		int length = 0;
		for(int i = 0; i < this.array.length; i++){
			if(this.array[i] != null && this.array[i].size() > length)
			length = this.array[i].size();
		}
		//Print the length of the longest chain
		out.println(length + " longest chain");
		out.close();
		return true;
	}

	/**
	* Generate a prime number that is close to the double of current array
	* size
	* @return a prime number used as array size
	*/
	private int primeGen(){
		boolean isPrime = false;
		int num = array.length*CONSTANT_TWO;//Double the size

		/*
		* Generate next prime number that is greater than the double of
		* current array size
		*/
		while(!isPrime){
			num++;
			/*
			* Try divides the number with all numbers greater than two and
			* less than or equal to the square root of itself
			*/
			for(int divisor = CONSTANT_TWO; divisor <= Math.sqrt(num);
			divisor++){
				if(num % divisor == 0)//The number is divisible
				break;//No need for further testing, break inner loop
				if(divisor == (int)Math.sqrt(num))//The number is indivisible
				isPrime = true;//Then it is a prime
			}
		}
		return num;
	}	
	/**Lists out all the elements with arrows relating to the LinkedList
	 * @return String - the String containing all of the data in MyHashTable
	 */
	public String toString() {
		String printing ="";
		printing += "# of elements: " + nelems;
		printing += "\n# of collisions: " + collision;
		printing += "\n# of expansions: " + expand;
		return printing;
		}
			
		
	
	public static void main(String[] args) {
		MyHashTable tai = new MyHashTable(2);
		tai.insert("hi");
		tai.insert("bye");
		tai.insert("dye");
		System.out.println(tai);
		
		
	

		
	}
	

}
