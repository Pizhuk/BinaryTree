import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BSTree<Integer> bsTree = new BSTree<>();
        bsTree.add(12);
        bsTree.add(12);
        bsTree.add(2);
        bsTree.add(24);

        bsTree.add(12);
        bsTree.add(24);
        bsTree.add(245);
        bsTree.add(11111);
        System.out.println(bsTree.getHeight());
        bsTree.println();
    }
}
