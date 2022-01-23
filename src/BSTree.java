import java.util.Arrays;

public class BSTree<T extends Comparable<T>> {

    private BSTNode <T> mRoot;
    public class BSTNode <T extends Comparable <T >> {
        T key;
        BSTNode <T> left;
        BSTNode <T> right;
        BSTNode <T> parent;

		public BSTNode(T key, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent) {
            super();
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
        public boolean isLeaf() {
            if(mRoot.right == null && mRoot.left == null) {
                return true;
            }
            return false;
        }
    }

    public void init(T[] ar){
        for (T elem:ar) {
            add(elem);
        }
    }

    public void add(T key){
        if(search(key) == null){
            BSTNode<T> z=new BSTNode<T>(key, null, null, null);
            if (z!=null) {
                add(this,z);
            }
        }
    }
    private void add(BSTree<T> bst, BSTNode<T> z) {
        int cmp;
        BSTNode<T> y=null;
        BSTNode <T> x = bst.mRoot;
        while(x!=null){
            y=x;
            cmp=z.key.compareTo(x.key);
            if (cmp<0) {
                x=x.left;
            }else {
                x=x.right;
            }
        }
        z.parent=y;
        if (y==null) {
            bst.mRoot=z;
        }else {
            cmp=z.key.compareTo(y.key);
            if (cmp<0) {
                y.left=z;
            }else {
                y.right=z;
            }
        }
    }

    public void delete(T key){
        BSTNode<T> z,node;
        if ((z=search(mRoot, key))!=null) {
            if ((node=remove(this,z))!=null) {
                node=null;
            }
        }
    }

    private BSTNode<T> remove(BSTree<T> bst, BSTNode<T> z) {
        BSTNode <T> x = null;
        BSTNode <T> y = null;

        if ((z.left==null) ||(z.right==null)) {
            y = z;
        }else{
            y = successor (z);
        }

        if (y.left!=null) {
            x=y.left;
        }else{
            x=y.right;
        }

        if (x!=null) {
            x.parent=y.parent;
        }

        if (y.parent==null) {
            bst.mRoot = x;
        } else if (y == y.parent.left.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        if (y!=z) {
            z.key = y.key;
        }
        return y;
    }

    public void println(){
        if (mRoot!=null) {
            print(mRoot,mRoot.key,0);
        }
    }
    private void print(BSTNode<T> tree, T key, int i) {
        if (tree!=null) {
            if (i == 0) {
                System.out.printf("%2d is root\n",tree.key);
            }else {
                System.out.printf("%2d is %2d's %6s child\n",tree.key,key,
                        i==1?"right":"left");
            }
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print(){
        System.out.println(Arrays.toString(toArray()));
    }

    public void clear(){
        clear(mRoot);
        mRoot=null;
    }
    private void clear(BSTNode<T> tree) {
        if (tree==null) {
            return ;
        }
        if (tree.left!=null) {
            clear(tree.left);
        }
        if (tree.right!=null) {
            clear(tree.right);
        }
        tree=null;
    }

    public Object[] toArray(){
        Object[] Arr = new Object[size()];
        toArray(mRoot, Arr, 1);
        return Arr;
    }
    private void toArray(BSTNode<T> tree, Object[] arr, int iterator){
        if(size() == 0){

        }
        else {
            arr[0] = min();
            if (tree!=null) {
                toArray(tree.left, arr, iterator);
                arr[iterator] = tree.key;
                iterator++;
                toArray(tree.right, arr, iterator);
            }
        }

    }

    public int size() {
        return size(mRoot);
    }
    private int size(BSTNode<T> tree) {
        if(tree == null)
            return 0;
        else if(tree.isLeaf())
            return 1;
        else {
            if(tree.left == null) {
                return size(tree.right) + 1;
            } else if(tree.right == null) {
                return size(tree.left) + 1;
            } else {
                return size(tree.left) + size(tree.right) + 1;
            }
        }
    }


    public int getHeight() {
        return dept(mRoot);
    }

    private int dept(BSTNode<T> tree) {
        if(tree == null) {
            return 0;
        }else if(tree.isLeaf()) {
            return 1;
        } else {
            if(tree.left == null) {
                return dept(tree.right) + 1;
            } else if(tree.right == null) {
                return dept(tree.left) + 1;
            } else {
                return Math.max(dept(tree.left), dept(tree.left))+1;
            }
        }
    }


    private T min(){
        BSTNode<T> p=min(mRoot);
        if (p!=null) {
            return p.key;
        }
        return null;
    }
    private BSTNode<T> min(BSTNode<T> tree) {
        if (tree==null) {
            return null;
        }
        while(tree.left!=null){
            tree=tree.left;
        }
        return tree;
    }

    private BSTNode<T> search(T key){
        return search(mRoot,key);
    }
    private BSTNode<T> search(BSTNode<T> tree, T key) {
        if (tree==null) {
            return tree;
        }
        int cmp=key.compareTo(tree.key);
        if (cmp<0) {
            return search(tree.left, key);
        }else if (cmp>0) {
            return search(tree.right, key);
        }else {
            return tree;
        }
    }
    private BSTNode<T> successor(BSTNode<T> x){
        if (x.right!=null) {
            return min(x.right);
        }
        BSTNode<T> y=x.parent;
        while((y!=null)&&(x==y.right)){
            x=y;
            y=y.parent;
        }
        return y;
    }

}
