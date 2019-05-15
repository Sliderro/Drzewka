import Tree.*;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tree tree = null;
        if(args.length<2){
            System.out.println("Not enough arguments");
        } else if (!args[0].equals("--type")){
            System.out.println("Wrong Argument");
        } else {
            switch (args[1]) {
                case "bst":
                    tree = new BSTree();
                    break;
                case "rbt":
                    tree = new RBTree();
                    break;
                case "splay":
                    tree = new SplayBSTree();
                    break;
                default:
                    System.out.println("Wrong argument");
                    System.exit(-1);
            }
            int k = 0;
            try{
                k = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Not a number");
                System.exit(-1);
            }
            for (int i=0; i<k; i++){
                String msg = scanner.nextLine();
                String[] split = msg.split(" ");
                if(split.length==0){
                    System.out.println("You need an argument");
                }
                else if (split.length == 1){
                    if(split[0].equals("inorder")){
                        tree.inorder();
                    } else {
                        System.out.println("wrong argument");
                    }
                } else {
                    if(split[1].length()>100){
                        System.out.println("String is too long");
                        continue;
                    }
                    switch (split[0]) {
                        case "insert":
                            tree.insert(split[1]);
                            break;
                        case "delete":
                            tree.delete(split[1]);
                            break;
                        case "load":
                            tree.load(new File(split[1]));
                            break;
                        case "search":
                            tree.search(split[1]);
                            break;
                        default:
                            System.out.println("Wrong Argument");
                            break;
                    }
                }
            }
        }
    }
}
