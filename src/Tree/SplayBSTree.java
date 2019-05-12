package Tree;

import java.io.File;

public class SplayBSTree implements Tree {
    private StringChecker sc = new StringChecker();
    private TreeNode root = null;

    private TreeNode rotateRight(TreeNode h) {
        TreeNode x = h.getLeft();
        h.setLeft(x.getRight());
        x.setRight(h);
        return x;
    }

    private TreeNode rotateLeft(TreeNode h) {
        TreeNode x = h.getRight();
        h.setRight(x.getLeft());
        x.setLeft(h);
        return x;
    }

    private void splay(TreeNode node){
        TreeNode p = node.getParent();
        TreeNode gp = (p != null) ? p.getParent() : null;
        if (p != null && p == root){
            gp = p.getParent();
            root = node;
            node.setParent(null);

            if (node == p.getLeft()){
                p.setLeft(node.getRight());
                if(node.getRight() != null) node.getRight().setParent(p);
                node.setRight(p);
                p.setParent(node);
            } else {
                p.setRight(node.getLeft());
                if (node.getLeft() != null) node.getLeft().setParent(p);
                node.setLeft(p);
                p.setParent(node);
            }
            return;
        }
        if (p != null && gp != null) {
            TreeNode ggp = gp.getParent();
            if (ggp != null && ggp.getLeft() == gp) {
                ggp.setLeft(node);
                node.setParent(ggp);
            } else if (ggp != null && ggp.getRight() == gp) {
                ggp.setRight(node);
                node.setParent(ggp);
            } else {
                root = node;
                node.setParent(null);
            }

            if ((node == p.getLeft() && p == gp.getLeft())
                    || (node == p.getRight() && p == gp.getRight())) {
                // Zig-zig step
                if (node == p.getLeft()) {
                    TreeNode nr = node.getRight();
                    node.setRight(p);
                    p.setParent(node);

                    p.setLeft(nr);
                    if (nr != null)
                        nr.setParent(p);

                    TreeNode pr = p.getRight();
                    p.setRight(gp);
                    gp.setParent(p);

                    gp.setLeft(pr);
                    if (pr != null)
                        pr.setParent(gp);
                } else {
                    TreeNode nl = node.getLeft();
                    node.setLeft(p);
                    p.setParent(node);

                    p.setRight(nl);
                    if (nl != null)
                        nl.setParent(p);

                    TreeNode pl = p.getLeft();
                    p.setLeft(gp);
                    gp.setParent(p);

                    gp.setRight(pl);
                    if (pl != null)
                        pl.setParent(gp);
                }
                return;
            }

            // Zig-zag step
            if (node == p.getLeft()) {
                TreeNode nl = node.getRight();
                TreeNode nr = node.getLeft();

                node.setRight(p);
                p.setParent(node);

                node.setLeft(gp);
                gp.setParent(node);

                p.setLeft(nl);
                if (nl != null)
                    nl.setParent(p);

                gp.setRight(nr);
                if (nr != null)
                    nr.setParent(gp);
                return;
            }

            TreeNode nl = node.getLeft();
            TreeNode nr = node.getRight();

            node.setLeft(p);
            p.setParent(node);

            node.setRight(gp);
            gp.setParent(node);

            p.setRight(nl);
            if (nl != null)
                nl.setParent(p);

            gp.setLeft(nr);
            if (nr != null)
                nr.setParent(gp);
        }
    }

    private TreeNode splay(TreeNode h, String s) {
        if (h == null) return null;

        int cmp1 = s.compareTo(h.getS());

        if (cmp1 < 0) {
            if (h.getLeft() == null) {
                return h;
            }
            int cmp2 = s.compareTo(h.getLeft().getS());
            if (cmp2 < 0) {
                h.getLeft().setLeft(splay(h.getLeft().getLeft(), s));
                h = rotateRight(h);
            } else if (cmp2 > 0) {
                h.getLeft().setRight(splay(h.getLeft().getRight(), s));
                if (h.getLeft().getRight() != null)
                    h.setLeft(rotateLeft(h.getLeft()));
            }
            if (h.getLeft() == null) return h;
            else return rotateRight(h);
        } else if (cmp1 > 0) {
            if (h.getRight() == null) {
                return h;
            }

            int cmp2 = s.compareTo(h.getRight().getS());
            if (cmp2 < 0) {
                h.getRight().setLeft(splay(h.getRight().getLeft(), s));
                if (h.getRight().getLeft() != null)
                    h.setRight(rotateRight(h.getRight()));
            }
            else if (cmp2 > 0) {
                h.getRight().setRight(splay(h.getRight().getRight(), s));
                h = rotateLeft(h);
            }

            if (h.getRight() == null) return h;
            else return rotateLeft(h);
        }

        else{
            return h;
        }
    }

    @Override
    public void insert(String s){
        s = sc.fixString(s);
        TreeNode z = new TreeNode(s);
        insert(z);
        /*if (root == null) {
            root = new TreeNode(s);
            return;
        }

        root = splay(root, s);
        int cmp = s.compareTo(root.getS());

        // Insert new node at root
        if (cmp < 0) {
            TreeNode n = new TreeNode(s);
            n.setLeft(root.getLeft());
            n.setRight(root);
            root.setLeft(null);
            root = n;
        } else if (cmp > 0) {
            TreeNode n = new TreeNode(s);
            n.setRight(root.getRight());
            n.setLeft(root);
            root.setRight(null);
            root = n;
        }*/
        // Splay the new node to the root position
        while (z.getParent() != null) {
            this.splay(z);
        }
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



    @Override
    public void delete(String s) {
        if(!search(s)) System.out.println("Such string does not exist");
        else {
            delete(searchNode(root, s));
        /*if (root == null) return; // empty tree

        root = splay(root, s);

        int cmp = s.compareTo(root.getS());
        if (cmp == 0) {
            if (root.getLeft() == null) {
                root = root.getRight();
            }
            else {
                TreeNode x = root.getRight();
                root = root.getLeft();
                splay(root, s);
                root.setRight(x);
            }
        }*/
            TreeNode nodeToRemove = searchNode(root, s);
            if (nodeToRemove != null && nodeToRemove.getParent() != null) {
                TreeNode nodeParent = nodeToRemove.getParent();
                // Splay the parent node to the root position
                while (nodeParent.getParent() != null) {
                    this.splay(nodeParent);
                }
            }
        }
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
