package c.piroton.pset2a5;

import java.util.LinkedList;
import java.util.List;

public class Permutation {
    private String in = "abc";
    private List<String> a = new LinkedList<String>();
    // additional attribute if needed

    Permutation(final String str){
        in = str;
    }

    public void permute(){
        permute2(in, 0, in.length());

    }
    private void permute2(String msg, int fixed, int size){
        if (size == fixed) a.add(msg);
        for (int i = fixed; i<size; i++){
            String newmsg = msg.substring(0,fixed)
                    +msg.charAt(i)
                    +msg.substring(fixed,i)
                    +msg.substring(i+1,msg.length());
            permute2(newmsg, fixed+1, size);
        }
    }

    public List<String> getA(){
        return a;
    }
}
