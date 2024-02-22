/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    public Node getFirstNode() {
        return first;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        Node NewFirst = new Node(new CharData(chr), first);
        this.first = NewFirst;
        this.size++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        Node pointer = first;
        String str = "(";
        while (pointer != null)
        {
            str += pointer.toString() + " ";
            pointer = pointer.next;
        }
        str = str.substring(0, str.length() - 1) + ")";
        return str;
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        int counter = 0;
        Node pointer = first;

        while (pointer != null)
        {
            if (pointer.cp.equals(chr))
            {
                return counter;
            }
            pointer = pointer.next;
            counter++;
        }
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        Node pointer = first;
        boolean isCharInList = false;

        while (pointer != null)
        {
            if (pointer.cp.equals(chr))
            {
                pointer.cp.count++;
                isCharInList = true;
            }
            pointer = pointer.next;
        }
        if(!isCharInList)
        {
            addFirst(chr);
        }
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        Node pointer = first;
        Node previous = null;
        int nodeCounter = 1;

        while (pointer != null)
        {
            if (pointer.cp.equals(chr))
            {
                if (nodeCounter == 1)
                {
                    first = first.next;
                    return true;
                }
                else
                {
                    previous.next = pointer.next;
                    return true;
                }                    
            }
            previous = pointer;
            pointer = pointer.next;
            nodeCounter++;
        }
        return false;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            Node pointer = first;
            for (int i = 0; i < index; i++)
            {
                pointer = pointer.next;
            }
            return pointer.cp;
        }
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node pointer = first;
	    int i = 0;
        while (pointer != null) {
    	    arr[i++]  = pointer.cp;
    	    pointer = pointer.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node pointer = first;
	    int i = 0;
        while (i < index) {
            pointer = pointer.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(pointer);
    }
}