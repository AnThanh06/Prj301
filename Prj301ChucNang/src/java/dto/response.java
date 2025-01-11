
package dto;


public class response <E> {
    E infor;
    String msg;

    public response(E infor, String msg) {
        this.infor = infor;
        this.msg = msg;
    }

    public E getInfor() {
        return infor;
    }

    public void setInfor(E infor) {
        this.infor = infor;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
