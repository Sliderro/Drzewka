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
        //bstTree.delete("jgl");
        bstTree.delete("adg");
        //System.out.println(bstTree.search("aaa"));
        //System.out.println(bstTree.search("abc"));
        bstTree.inorder();
    }
}
