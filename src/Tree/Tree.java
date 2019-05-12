package Tree;

import java.io.File;

public interface Tree {
    void insert(String s);
    void delete(String s);
    boolean search(String s);
    void load(File f);
    void inorder();
}