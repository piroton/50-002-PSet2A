package c.piroton.pset2a5;

import java.util.Arrays;

public class Palindrome {
    private static boolean isPalindrome(char[] msg){
        if (msg.length == 0 || msg.length==1) return true;
        if (msg[0] == msg[msg.length-1]){
            char[] substring = Arrays.copyOfRange(msg,1,msg.length-2);
            return isPalindrome(substring);
        }
        return false;
    }
    public static String isPal(String s){
        char[] sc = s.toCharArray();
        if (isPalindrome(sc)){
            return s+" is a Palindrome.";
        } else{
            return s+" is not a Palindrome.";
        }
    }

}
