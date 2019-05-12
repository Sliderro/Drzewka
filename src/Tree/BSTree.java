package Tree;

import java.io.File;

public class BSTree implements Tree{
    private TreeNode root = null;
    private StringChecker sc = new StringChecker();

    @Override
    public void insert(String s){
        s = sc.fixString(s);
        TreeNode z = new TreeNode(s);
        insert(z);
    }

    private void insert(TreeNode z) {
        TreeNode y = null;
        TreeNode x = root;
        while (x != null){
            y = x;
            if (z.getS().compareToIgnoreCase(x.getS()) == 0 && x.isSwap()) {
                x.swap();
                x = x.getLeft();
            }
            else if (z.getS().compareToIgnoreCase(x.getS()) < 0) x = x.getLeft();
            else x = x.getRight();
        }
        z.setParent(y);
        if (y == null) this.root = z;
        else if (z.getS().compareToIgnoreCase(y.getS())==0 && y.isSwap()){
            y.swap();
            y.setLeft(z);
        }
        else if (z.getS().compareToIgnoreCase(y.getS())<0) y.setLeft(z);
        else y.setRight(z);
    }

    private TreeNode treeMinimum(TreeNode x){
        while(x.getLeft() != null) x = x.getLeft();
        return x;
    }

    private void transplant(TreeNode u, TreeNode v){
        if (u.getParent() == null) root = v;
        else if (u.equals(u.getParent().getLeft())) u.getParent().setLeft(v);
        else u.getParent().setRight(v);
        if (v != null) v.setParent(u.getParent());
    }

    @Override
    public void delete(String s) {
        if(!search(s)) System.out.println("Such string does not exist");
        else delete(searchNode(root, s));
    }

    private void delete(TreeNode z){
        if(z.getLeft() == null) transplant(z, z.getRight());
        else if (z.getRight() == null) transplant(z, z.getLeft());
        else{
            TreeNode y = treeMinimum(z.getRight());
            if(!y.getParent().equals(z)){
                transplant(y,y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z,y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
        }
    }

    @Override
    public boolean search(String s) {
        return search(root, s);
    }

    private boolean search(TreeNode node, String s){
        if(node == null) return false;
        if(s.compareToIgnoreCase(node.getS()) == 0) return true;
        if(s.compareToIgnoreCase(node.getS()) < 0) return search(node.getLeft(),s);
        else return search(node.getRight(),s);
    }

    private TreeNode searchNode(TreeNode node, String s){
        if(node == null || s.compareToIgnoreCase(node.getS()) == 0) return node;
        if(s.compareToIgnoreCase(node.getS()) < 0) return searchNode(node.getLeft(),s);
        else return searchNode(node.getRight(),s);
    }

    @Override
    public void load(File f) {

    }

    @Override
    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode node){
        if(node != null){
            inorder(node.getLeft());
            System.out.print(node.getS() + ", ");
            inorder(node.getRight());
            if(node.equals(root)) System.out.println();
        }
    }

}
