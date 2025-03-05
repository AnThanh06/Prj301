//adt nó là interface dùng đển định nghĩa hành động, các set các map giống nhau
public class MyList{
   private Node head, tail;
   
   public MyList(){
       this.head = null;
       this.tail = null;
   }

    public MyList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
   

    
    public void add(int value) {
        if(this.isEmpty()){
            this.head = new Node(value,null);
            this.tail = this.head;
        }else{
            Node newNode = new Node(value,null);
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        
    }
    
    
    
    
        public Node search(int value){
            Node data = this.head;
            while(data != null){
                if(data.getInfor() == value){
                    return data;
                }else{
                    data = data.getNext();
                }
                
                }
                return null;
                
            }
    }
     
    public boolean isEmpty() {
        if(this.head==null){
            return this.head==null;
        }
       return false;
    }
    

   
}

