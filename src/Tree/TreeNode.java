package Tree;

public class TreeNode {
    private String s;
    private TreeNode parent = null;
    private TreeNode left = null;
    private TreeNode right = null;
    private boolean swap = true; // swaps when exactly same value is inserted again

    public TreeNode(String s){
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public boolean isSwap() {
        return swap;
    }

    public void swap() {
        this.swap = !this.swap;
    }
}
