public class BinaryTree {
    Node root;

    //Constructor
    BinaryTree(){
        root = null;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Search
        Node found = tree.find(tree.root, 40);
        System.out.println(found != null ? "Found 40" : "40 not in tree");

        tree.printInOrder();

        //delete
        tree.delete(30);
        System.out.println("Delete tree value : 30");
        tree.printInOrder();

        //insert 35
        tree.insert(35);
        tree.printInOrder();
    }

    void insert(int key){
        if(root != null) System.out.println("Root insert: "+root.value);
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key){
        if(root == null){
            root = new Node(key);
            return root;
        }

        if(key < root.value){
            root.left = insertRec(root.left, key);
        } else if(key > root.value){
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    Node find(Node root, int key){
        if(root == null || root.value == key){
            return root;
        }

        if(key > root.value){
            return find(root.right, key);
        }

        return find(root.left, key);
    }

    void delete(int key){
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key){
        if(root == null) return root;

        if(key < root.value){
            root.left = deleteRec(root.left, key);
        } else if(key > root.value){
            root.right = deleteRec(root.right, key);
        } else {
            if(root.left == null){
                return root.right;
            } else if(root.right == null){
                return root.left;
            }

            System.out.println("Print root right delete: "+root.right.value);
            root.value = minValue(root.right);
            System.out.println("Delete. Root value: "+root.value);
            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    int minValue(Node root){
        int minv = root.value;
        while(root.left != null){
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    void printInOrder(){
        inOrderRec(root);
        System.out.println();
    }

    void inOrderRec(Node root){
        if(root != null){
            inOrderRec(root.left);
            System.out.print(root.value+" ");
            inOrderRec(root.right);
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

    Node(int value){
        this.value = value;
        right = null;
        left = null;
    }
}