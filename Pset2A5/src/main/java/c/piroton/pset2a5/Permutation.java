package c.piroton.pset2a5;

import java.util.ArrayList;

public class Permutation {
    private String in = "abc";
    private ArrayList<String> a = new ArrayList<String>();
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
            String newmsg = msg.substring(0,fixed)+msg.charAt(i)
                    +msg.substring(fixed,i)+msg.substring(i+1,msg.length());
            permute2(newmsg, fixed+1, size);
        }
    }

    public ArrayList<String> getA(){
        return a;
    }
}
