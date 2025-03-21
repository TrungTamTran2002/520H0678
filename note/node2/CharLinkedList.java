class CharLinkedList implements ListInterface {
    private Node head;

    public CharLinkedList() {
    }

    @Override
    public Node getHead() {
        return head;
    }

    @Override
    public void addFirst(int data) {
        // code here
        // tạo một node mới với data và next là head
        // gán head bằng node mới
        Node newNode = new Node(data, head);
        head = newNode;
    }

    @Override
    public boolean addAfterFirstKey(int data, int key) {
        // code here
        // duyệt qua các node trong danh sách
        // nếu tìm thấy node có data bằng key
        // tạo một node mới với data và next là node tiếp theo của node hiện tại
        // gán next của node hiện tại bằng node mới
        Node current = head;
        while (current != null) {
            if (current.getData() == key) { // tìm thấy node có data bằng key
                Node newNode = new Node(data, current.getNext());
                current.setNext(newNode); // gán next của node hiện tại bằng node mới
                return true;
            }
            current = current.getNext();
        }
        return false; // không tìm thấy node có data bằng key
    }

    @Override
    public int largestCharPostition() {
        // code here
        // duyệt qua các node trong danh sách
        // tìm vị trí của node có data lớn nhất
        // trả về vị trí của node đó
        if (head == null) return -1;
        Node current = head;
        int max = current.getData();
        int position = 0;
        int index = 0;
        while (current != null) {
            if (current.getData() > max) { // cập nhật nếu tìm thấy node có data lớn hơn
                max = current.getData();
                position = index;
            }
            current = current.getNext();
            index++;
        }
        return position; // trả về vị trí của node có data lớn nhất
    }

@Override
 public int findLargest() {
        if (head == null) return Integer.MIN_VALUE; // Trả về giá trị nhỏ nhất nếu danh sách rỗng
        
        Node current = head;
        int max = current.getData();
        
        while (current != null) {
            if (current.getData() > max) { // Cập nhật giá trị lớn nhất
                max = current.getData();
            }
            current = current.getNext();
        }
        return max; // Trả về giá trị lớn nhất

    }   

    @Override
    public boolean removeFirstEven() {
        if (head == null) return false; // Trả về false nếu danh sách rỗng
        
        if (head.getData() % 2 == 0) { // Nếu phần tử đầu tiên là số chẵn
            head = head.getNext(); // Xóa phần tử đầu tiên
            return true;
        }
        
        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData() % 2 == 0) { // Tìm phần tử chẵn đầu tiên
                current.setNext(current.getNext().getNext()); // Xóa phần tử đó
                return true;
            }
            current = current.getNext();
        }
        return false; // Không tìm thấy số chẵn
    }

    public static void main(String[] args) {

        // Tạo danh sách liên kết
        CharLinkedList list = new CharLinkedList();

        // Thêm phần tử vào danh sách
        // 5 -> 10 -> 15 -> 20
        list.addFirst(20);
        list.addFirst(15);
        list.addFirst(10);
        list.addFirst(5);

        // In danh sách
        System.out.println("Danh sách sau khi thêm phần tử là:");
        printList(list);

        // (a) Thêm số 7 sau số 10
        list.addAfterFirstKey(7, 10);
        System.out.println("Danh sách sau khi thêm số 7 sau só 10 là:");
        printList(list);

        // (b) Tìm phần tử lớn nhất
        System.out.println("Giá trị lớn nhất trong danh sách là: " + list.findLargest());
        System.out.println("Vị trí của phần tử lớn nhất là: " + list.largestCharPostition());

        // (c) Xóa phần tử chẵn đầu tiên
        list.removeFirstEven();
        System.out.println("Danh sách sau khi xóa phần tử chẵn đầu tiên là:");
        printList(list);
    }

    public static void printList(CharLinkedList list) {
        Node current = list.getHead();
        while (current != null) {
            System.out.print(current.getData() + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }
}

 