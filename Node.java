public class Node	{
	private Node next;
	private Object data;
	public Node(Object data)	{
		this.data = data;
		next = null;
	}
	
	public Node(Object data, Node next)	{
		this.data = data;
		this.next = next;
	}
	
	public Object getItem()	{
		return data;
	}
	
	public void setItem(Object data)	{
		this.data = data;
	}
	
	public Node getNext()	{
		return next;
	}
	
	public void setNext(Node next)	{
		this.next = next;
	}

}