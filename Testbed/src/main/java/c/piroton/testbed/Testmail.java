package c.piroton.testbed;

public class Testmail {
    public static void main(String[] args){
        MailingList mail = new MailingList();
        Looker a1 = new Looker(mail);
        Looker a2 = new Looker(mail);
        mail.notifyObservers();
        mail.setState(1);
        mail.notifyObservers();
        return;
    }
}
