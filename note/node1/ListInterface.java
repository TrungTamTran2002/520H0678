public interface ListInterface{
	public Node getHead();
	public void addFirst(char data);
	public void addLast(char data);
	public boolean addAfterFirstKey(char data, char key);
	public int largestCharPostition();
	public int smallestCharPosition();
}