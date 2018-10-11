package c.piroton.pset2a5;

import java.util.ArrayList;

public class TestPermute {
    public static void main(String[] args){
        Permutation p = new Permutation("abc");
        p.permute();
        ArrayList a = p.getA();
        for (int i =0; i<a.size(); i++){
            System.out.println(a.get(i));
        }
    }
}
