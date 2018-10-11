package c.piroton.testbed;

public class Looker implements Observer {
    private String currOb = "";
    private Subject following;

    public Looker(Subject s){
        this.following = s;
        s.register(this);
    }
    public void update(String msg) {
        this.currOb = msg;
        System.out.println("Received message: "+msg);
    }
}
