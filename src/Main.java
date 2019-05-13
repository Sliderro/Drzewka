import Tree.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tree bstTree = new SplayBSTree();
        bstTree.insert("Abc");
        bstTree.insert("Bdw");
        bstTree.insert("adg");
        bstTree.insert("adg");
        bstTree.insert("adg");
        bstTree.insert("adg");
        bstTree.insert("tre");
        bstTree.insert("sadkm");
        bstTree.insert("aaksl");
        bstTree.insert("aaa");
        bstTree.inorder();
        bstTree.delete("Abc");
        bstTree.delete("Bdw");
        bstTree.delete("adg");
        bstTree.delete("adg");
        bstTree.delete("adg");
        bstTree.delete("adg");
        bstTree.delete("tre");
        bstTree.delete("sadkm");
        bstTree.delete("aaksl");
        bstTree.delete("rioeri");
        //bstTree.delete("jgl");

        System.out.println(bstTree.search("aaa"));
        System.out.println(bstTree.search("abc"));
        bstTree.inorder();
    }
}
