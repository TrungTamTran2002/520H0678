class CharLinkedList implements ListInterface {
    private Node head;

    public CharLinkedList() {
        this.head = null;
    }

    @Override
    public Node getHead() {
        return head;
    }

    @Override
    public void addFirst(char data) {
        head = new Node(data, head);
    }

    @Override
    public void addLast(char data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
    }

    @Override
    public boolean addAfterFirstKey(char data, char key) {
        Node current = head;
        while (current != null) {
            if (current.getData() == key) {
                current.setNext(new Node(data, current.getNext()));
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    //Trả về vị trí của phần tử đầu tiên có giá trị ASCII lớn nhất.
    public int largestCharPostition() {
        if (head == null) return -1;

        Node current = head;
        char maxChar = current.getData();
        int maxPos = 0, pos = 0;

        while (current != null) {
            if (current.getData() > maxChar) {
                maxChar = current.getData();
                maxPos = pos;
            }
            current = current.getNext();
            pos++;
        }
        return maxPos;
    }

    @Override
    // Trả về vị trí của phần tử đầu tiên có giá trị ASCII nhỏ nhất.
    public int smallestCharPosition() {
        if (head == null) return -1;

        Node current = head;
        char minChar = current.getData();
        int minPos = 0, pos = 0;

        while (current != null) {
            if (current.getData() < minChar) {
                minChar = current.getData();
                minPos = pos;
            }
            current = current.getNext();
            pos++;
        }
        return minPos;
    }

    // Method to print the list (for testing)
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getData() + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        CharLinkedList list = new CharLinkedList();
        list.addFirst('C');
        list.addFirst('b');
        list.addFirst('A');

        list.printList();  // Expected Output: A -> b -> C -> null

        System.out.println("Largest Char Position: " + list.largestCharPostition()); // Expected Output: 2
        System.out.println("Smallest Char Position: " + list.smallestCharPosition()); // Expected Output: 0

        list.addAfterFirstKey('E', 'b');
        list.printList();  // Expected Output: A -> b -> E -> C -> null

        
        list.printList();  // Expected Output: A -> b -> E -> C -> Z -> null
    }
}
