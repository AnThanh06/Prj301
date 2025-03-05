




public class Node {
    private int infor;
    public Node next;
    public Node(){
        
    }

    public Node(int infor, Node next) {
        this.infor = infor;
        this.next = next;
    }

    public int getInfor() {
        return infor;
    }

    public void setInfor(int infor) {
        this.infor = infor;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
    
}
