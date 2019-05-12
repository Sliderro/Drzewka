import Tree.BSTTree;
import Tree.StringChecker;

public class Main {
    public static void main(String[] args){
        boolean a;
        boolean b;
        boolean c;
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                for (int k = 0; k < 2; k++){
                    a = i == 1;
                    b = j == 1;
                    c = k == 1;
                    System.out.println((a||b&&c) + " " + (a||(b&&c)));
                }
            }
        }
    }
}
