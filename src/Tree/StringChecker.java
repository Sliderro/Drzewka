package Tree;

public class StringChecker {

    public String fixString(String s){
        if(!isCharValid(s.charAt(0))) return fixString(s.substring(1));
        if(!isCharValid(s.charAt(s.length()-1))) return fixString(s.substring(0,s.length()-1));
        return s;
    }

    private boolean isCharValid(char c){
        return c >= 65 && c <= 90 || c >= 97 && c <= 122;
    }
}
