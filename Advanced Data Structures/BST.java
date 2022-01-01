import java.util.*;

/**
 * Your implementation of a BST.
 *
 * @author Rayan Dabbagh
 * @version 1.0
 * @userid rdabbagh3
 * @GTID 903591640
 * <p>
 * Collaborators: NA
 * <p>
 * Resources: NA
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     * <p>
     * This constructor should initialize an empty BST.
     * <p>
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     * <p>
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     * <p>
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The collection of data passed in is null, and therefore can't be used to create a BST");
        }
        for (T newData : data) {
            add(newData);
        }

    }

    /**
     * Adds the data to the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * The data becomes a leaf in the tree.
     * <p>
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data can't be added to a BST");
        }
        if (root == null) {
            root = new BSTNode<>(data);
        } else {
            addHelperMethod(data, root);
        }
        size++;
    }

    /**
     * Private Recursive Helper method for the public Add method provided above.
     *
     * @param data the data to add
     * @param node the node whose data is being compared to the data passed in
     * @return the node which was deleted
     */
    private BSTNode<T> addHelperMethod(T data, BSTNode<T> node) {
        if (node == null) {
            return new BSTNode<>(data);
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(addHelperMethod(data, node.getRight()));
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addHelperMethod(data, node.getLeft()));
        } else if (data.equals(node.getData())) {
            size--;
        }
        return node;
    }


    /**
     * Removes and returns the data from the tree matching the given parameter.
     * <p>
     * This must be done recursively.
     * <p>
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     * <p>
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (null == data) {
            throw new IllegalArgumentException("You can't remove data that is null!");
        }
        if (size == 0) {
            throw new NoSuchElementException("You can't remove any node since there is nothing to remove");
        }
        Object removed;
        if (size == 1) {
            if (!data.equals(root.getData())) {
                throw new NoSuchElementException("Data can't be found in the BST");
            }
            removed = root.getData();
            root = null;
        } else {
            BSTNode<T> dummyNode = new BSTNode<>(null);
            root = removeHelperMethod(data, root, dummyNode);

            removed = dummyNode.getData();
            if (removed == null) {
                throw new NoSuchElementException("The data is not found in the BST");
            }
        }
        size--;
        return (T) removed;
    }

    /**
     * Private Recursive Helper method for the public remove method provided above.
     *
     * @param data      data that is about to be removed
     * @param node      the node where the data is looked for
     * @param dummyNode data that was removed from the tree
     * @return
     */
    private BSTNode<T> removeHelperMethod(T data, BSTNode<T> node, BSTNode<T> dummyNode) {
        if (node == null) {
            return null;
        } else if (data.equals(node.getData())) {
            if (node.getLeft() == null && node.getRight() == null) {
                dummyNode.setData(node.getData());
                return null;
            } else if (node.getRight() != null && node.getLeft() == null) {
                dummyNode.setData(node.getData());
                return node.getRight();
            } else if (node.getLeft() != null && node.getRight() == null) {
                dummyNode.setData(node.getData());
                return node.getLeft();
            } else {
                BSTNode<T> dummyTwo = new BSTNode<T>(null);
                node.setRight(getSuccessor(node.getRight(), dummyTwo));
                dummyNode.setData(node.getData());
                node.setData(dummyTwo.getData());
                return node;
            }
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(removeHelperMethod(data, node.getRight(), dummyNode));
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(removeHelperMethod(data, node.getLeft(), dummyNode));
        }
        return node;
    }

    /**
     * Helper function for finding the successor of a node
     *
     * @param node     the right child of the node whose successor is meant to be found
     * @param dummyTwo dummy node
     * @return the data of the successor of a certain node inputted by the user
     */
    private BSTNode<T> getSuccessor(BSTNode<T> node, BSTNode dummyTwo) {
        T successor = node.getData();
        if (node.getLeft() == null) {
            dummyTwo.setData(successor);
            return node.getRight();
        } else {
            node.setLeft(getSuccessor(node.getLeft(), dummyTwo));
        }
        return node;
    }

    /**
     * Returns the data from the tree matching the given parameter.
     * <p>
     * This must be done recursively.
     * <p>
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("We can't search for a null node in the BST");
        }
        BSTNode<T> curr_node = getHelperMethod(data, root);
        if (curr_node == null) {
            throw new NoSuchElementException("The data has not been found in the tree.");
        } else {
            return curr_node.getData();
        }
    }

    /**
     * Private Recursive Helper method for the public get method provided above.
     *
     * @param data the data we are looking for
     * @param node the node where the data passed in is being looked for
     * @return the node where the data passed in is found in the BST
     */
    private BSTNode<T> getHelperMethod(T data, BSTNode<T> node) {
        if (node == null) {
            return null;
        } else if (data.compareTo(node.getData()) > 0) {
            return getHelperMethod(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            return getHelperMethod(data, node.getLeft());
        } else {
            return node;
        }

    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data you entered is nulL! Cannot search for null in the BST.");
        }
        return containsHelperMethod(data, root);
    }

    /**
     * Private Recursive Helper method for the public contains method provided above.
     *
     * @param data the data that is checked to see if it's present in the BST
     * @param node the node where the data passed in is being looked for
     * @return true if the data passed in is found in the BST, false otherwise
     */
    private boolean containsHelperMethod(T data, BSTNode<T> node) {
        if (node == null) {
            return false;
        } else if (data.compareTo(node.getData()) > 0) {
            return containsHelperMethod(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            return containsHelperMethod(data, node.getLeft());
        } else {
            return true;
        }
    }


    /**
     * Generate a pre-order traversal of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> newNodeList = new ArrayList<>();
        preorderHelperMethod(root, newNodeList);
        return newNodeList;
    }

    /**
     * Private Recursive Helper method for the public preorder method provided above.
     *
     * @param node the node we are looking at during the traversal
     * @param list list of nodes representing the preorder traversal of the BST
     */
    private void preorderHelperMethod(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        list.add(node.getData());
        preorderHelperMethod(node.getLeft(), list);
        preorderHelperMethod(node.getRight(), list);
    }

    /**
     * Generate an in-order traversal of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> newNodeList = new ArrayList<>();
        inorderHelperMethod(root, newNodeList);
        return newNodeList;
    }

    /**
     * Private Recursive Helper method for the public inorder method provided above.
     *
     * @param node the node we are looking at during the traversal
     * @param list list of nodes representing the inorder traversal of the BST
     */
    private void inorderHelperMethod(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        inorderHelperMethod(node.getLeft(), list);
        list.add(node.getData());
        inorderHelperMethod(node.getRight(), list);
    }

    /**
     * Generate a post-order traversal of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> newNodeList = new ArrayList<>();
        postorderHelperMethod(root, newNodeList);
        return newNodeList;
    }

    /**
     * Private Recursive Helper method for the public postorder method provided above.
     *
     * @param node the node we are looking at during the traversal
     * @param list list of nodes representing the postorder traversal of the BST
     */
    private void postorderHelperMethod(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        postorderHelperMethod(node.getLeft(), list);
        postorderHelperMethod(node.getRight(), list);
        list.add(node.getData());
    }


    /**
     * Generate a level-order traversal of the tree.
     * <p>
     * This does not need to be done recursively.
     * <p>
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     * <p>
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> newNodeList = new ArrayList<>();
        Queue<BSTNode<T>> newNodeQueue = new LinkedList<>();
        newNodeQueue.add(root);
        BSTNode<T> current;
        while (!newNodeQueue.isEmpty()) {
            current = newNodeQueue.remove();
            if (current != null) {
                newNodeList.add(current.getData());
                if (current.getLeft() != null) {
                    newNodeQueue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    newNodeQueue.add(current.getRight());
                }
            }
        }
        return newNodeList;
    }

    /**
     * Returns the height of the root of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     * <p>
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        }
        return heightHelperMethod(root);
    }

    /**
     * Private Recursive Helper method for the public height method provided above.
     *
     * @param node the node whose height is being determined
     * @return an integer representing the height of the node passed in
     */
    private int heightHelperMethod(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }
        int rightNode = heightHelperMethod(node.getRight());
        int leftNode = heightHelperMethod(node.getLeft());
        if (leftNode < rightNode) {
            return rightNode + 1;
        } else {
            return leftNode + 1;
        }

    }

    /**
     * Clears the tree.
     * <p>
     * Clears all data and resets the size.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     * <p>
     * This must be done recursively.
     * <p>
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     * <p>
     * EXAMPLE: Given the BST below composed of Integers:
     * <p>
     * 50
     * /    \
     * 25      75
     * /  \
     * 12   37
     * /  \    \
     * 10  15    40
     * /
     * 13
     * <p>
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     * <p>
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k).
     *
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     * @throws java.lang.IllegalArgumentException if k > n, the number of data
     *                                            in the BST
     */
    public List<T> kLargest(int k) {
        if (k > size) {
            throw new IllegalArgumentException("the value of k inputted is larger than the size of the BST!");
        }
        List<T> newNodeList = new LinkedList<>();
        kLargestHelperMethod(root, newNodeList, k);
        return newNodeList;
    }

    private void kLargestHelperMethod(BSTNode<T> node, List<T> list, int k) {
        if (node == null) {
            return;
        }
        kLargestHelperMethod(node.getRight(), list, k);
        if (list.size() < k) {
            list.add(0, node.getData());
        }
        if (list.size() < k) {
            kLargestHelperMethod(node.getLeft(), list, k);
        }

    }


    /**
     * Returns the root of the tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
