package Tree;

import java.io.*;
import java.util.Stack;

public class RBTree implements Tree{
    private TreeNode guard = new TreeNode(null);
    private TreeNode root = guard;
    private StringChecker sc = new StringChecker();

    private void leftRotate(TreeNode x){
        TreeNode y = x.getRight();
        x.setRight(y.getLeft());
        if(!y.getLeft().equals(guard)) y.getLeft().setParent(x);
        y.setParent(x.getParent());
        if(x.getParent().equals(guard)) root = y;
        else if (x.equals(x.getParent().getLeft())) x.getParent().setLeft(y);
        else x.getParent().setRight(y);
        y.setLeft(x);
        x.setParent(y);
    }

    private void rightRotate(TreeNode x){
        TreeNode y = x.getLeft();
        x.setLeft(y.getRight());
        if(!y.getRight().equals(guard)) y.getRight().setParent(x);
        y.setParent(x.getParent());
        if(x.getParent().equals(guard)) root = y;
        else if (x.equals(x.getParent().getRight())) x.getParent().setRight(y);
        else x.getParent().setLeft(y);
        y.setRight(x);
        x.setParent(y);
    }

    @Override
    public void insert(String s){
        s = sc.fixString(s);
        if(s.length()==0) return;
        TreeNode z = new TreeNode(s);
        insert(z);
    }

    private void insert(TreeNode z){
        TreeNode y = guard;
        TreeNode x = root;
        while (!x.equals(guard)){
            y = x;
            if(z.getS().compareToIgnoreCase(y.getS()) < 0) x = x.getLeft();
            else x = x.getRight();
        }
        z.setParent(y);
        if (y.equals(guard)) root = z;
        else if (z.getS().compareToIgnoreCase(y.getS()) < 0) y.setLeft(z);
        else y.setRight(z);
        z.setLeft(guard);
        z.setRight(guard);
        z.setColor(Color.RED);
        insertFixUp(z);
    }

    private void insertFixUp(TreeNode z){
        while(z.getParent().getColor() == Color.RED){
            if(z.getParent().equals(z.getParent().getParent().getLeft())){
                TreeNode y = z.getParent().getParent().getRight();
                if (y.getColor() == Color.RED){
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z.equals(z.getParent().getRight())){
                        z = z.getParent();
                        leftRotate(z);
                    }
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    rightRotate(z.getParent().getParent());
                }
            } else {
                TreeNode y = z.getParent().getParent().getLeft();
                if (y.getColor() == Color.RED){
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z.equals(z.getParent().getLeft())){
                        z = z.getParent();
                        rightRotate(z);
                    }
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    leftRotate(z.getParent().getParent());
                }
            }
        }
        root.setColor(Color.BLACK);
    }

    private TreeNode treeMinimum(TreeNode x){
        while(x.getLeft() != guard) x = x.getLeft();
        return x;
    }

    private void transplant(TreeNode u, TreeNode v){
        if (u.getParent().equals(guard)) root = v;
        else if (u.equals(u.getParent().getLeft())) u.getParent().setLeft(v);
        else u.getParent().setRight(v);
        v.setParent(u.getParent());
    }

    @Override
    public void delete(String s) {
        if(!search(s)) System.out.println("Such string does not exist");
        else delete(searchNode(root, s));
    }

    private void delete(TreeNode z){
        TreeNode x;
        TreeNode y = z;
        Color c = y.getColor();
        if (z.getLeft().equals(guard)){
            x = z.getRight();
            transplant(z,z.getRight());
        } else if (z.getRight().equals(guard)){
            x = z.getLeft();
            transplant(z,z.getLeft());
        } else {
            y = treeMinimum(z.getRight());
            c = y.getColor();
            x = y.getRight();
            System.out.println(y.getParent().equals(z));
            if (y.getParent().equals(z)) x.setParent(y);
            else {
                transplant(y,y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z,y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if (c == Color.BLACK) deleteFixUp(x);
    }

    private void deleteFixUp(TreeNode x){
        while (!x.equals(root) && x.getColor() == Color.BLACK){
            if (x.equals(x.getParent().getLeft())){
                TreeNode w = x.getParent().getLeft();
                if (w.getColor() == Color.RED){
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }
                if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK){
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor() == Color.BLACK){
                        w.getLeft().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rightRotate(w);
                        w = x.getParent().getRight();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getRight().setColor(Color.BLACK);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                TreeNode w = x.getParent().getRight();
                if (w.getColor() == Color.RED){
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w.getRight().getColor() == Color.BLACK && w.getLeft().getColor() == Color.BLACK){
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == Color.BLACK){
                        w.getRight().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        leftRotate(w);
                        w = x.getParent().getLeft();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getLeft().setColor(Color.BLACK);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(Color.BLACK);
    }

    @Override
    public boolean search(String s) {
        return search(root, s);
    }

    private boolean search(TreeNode node, String s){
        if(node == guard) return false;
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
        int counter = 0;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null){
                String[] split = line.split(" ");
                for(String s: split) {
                    System.out.println(counter++);
                    insert(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void inorder() {
        if (root == guard) return;

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode curr = root;

        while(curr != guard || s.size()>0){
            while(curr != guard){
                s.push(curr);
                curr = curr.getLeft();
            }

            curr = s.pop();

            System.out.print(curr.getS() + " ");

            curr = curr.getRight();
        }
    }
}
