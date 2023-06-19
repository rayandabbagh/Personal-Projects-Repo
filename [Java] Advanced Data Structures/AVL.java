import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Rayan Dabbagh
 * @version 2.0
 * @userid rdabbagh3
 * @GTID 903591640
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty AVL.
     * <p>
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null! You can't create an AVL from null values");
        }
        for (T dataValue : data) {
            add(dataValue);
        }

    }

    /**
     * Adds the data to the AVL. Start by adding it as a leaf like in a regular
     * BST and then rotate the tree as needed.
     * <p>
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     * <p>
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @param data the data to be added
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data null!");
        }
        if (root == null) {
            root = new AVLNode<>(data);
            root.setHeight(0);
            root.setBalanceFactor(0);
        } else {
            root = addHelperMethod(data, root);
        }
        size++;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     * <p>
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the successor to replace the data,
     * not the predecessor. As a reminder, rotations can occur after removing
     * the successor node.
     * <p>
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     * @throws IllegalArgumentException         if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data null!");
        }
        if (size == 0) {
            throw new NoSuchElementException("data not found in the tree!");
        }
        T rem;

        if (size == 1) {
            if (!data.equals(root.getData())) {
                throw new NoSuchElementException("data not found in the tree!");
            }
            rem = root.getData();
            root = null;
        } else {
            AVLNode<T> dummyNode = new AVLNode<>(null);
            root = removeHelper(data, root, dummyNode);
            rem = dummyNode.getData();

            if (rem == null) {
                throw new NoSuchElementException("data not found in the tree!");
            }
        }
        size--;
        return rem;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     * @throws IllegalArgumentException         if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data null!");
        }
        AVLNode<T> newNode = getHelperMethod(data, root);

        if (newNode == null) {
            throw new NoSuchElementException("data is not found!");
        } else {
            return newNode.getData();
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     * @throws IllegalArgumentException if the data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data you entered is nulL! Cannot search for null in the AVL.");
        }
        return containsHelper(data, root);
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     * <p>
     * Your list should not duplicate data, and the data of a branch should be
     * listed in order going from the root to the leaf of that branch.
     * <p>
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     * <p>
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     * <p>
     * Example Tree:
     * 10
     * /        \
     * 5          15
     * /   \      /    \
     * 2     7    13    20
     * / \   / \     \  / \
     * 1   4 6   8   14 17  25
     * /           \          \
     * 0             9         30
     * <p>
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {
        ArrayList<T> resultingList = new ArrayList<>();
        deepestBranchesHelper(resultingList, root);
        return resultingList;
    }

    /**
     * Private helper method
     *
     * @param alist   list representing the nodes in the deepest branch
     * @param current Node to which we will compute the balance factor
     */
    private void deepestBranchesHelper(List<T> alist, AVLNode<T> current) {
        if (current == null) {
            return;
        }
        alist.add(current.getData());
        if (current.getBalanceFactor() == 0) {
            deepestBranchesHelper(alist, current.getLeft());
            deepestBranchesHelper(alist, current.getRight());
        }
        if (current.getBalanceFactor() < 0) {
            deepestBranchesHelper(alist, current.getRight());
        }
        if (current.getBalanceFactor() > 0) {
            deepestBranchesHelper(alist, current.getLeft());
        }
    }


    /**
     * Returns a sorted list of data that are within the threshold bounds of
     * data1 and data2. That is, the data should be > data1 and < data2.
     * <p>
     * Should run in worst case O(n), but this is heavily dependent on the
     * threshold data. You should not explore branches of the tree that do not
     * satisfy the threshold.
     * <p>
     * Example Tree:
     * 10
     * /        \
     * 5          15
     * /   \      /    \
     * 2     7    13    20
     * / \   / \     \  / \
     * 1   4 6   8   14 17  25
     * /           \          \
     * 0             9         30
     * <p>
     * sortedInBetween(7, 14) returns [8, 9, 10, 13]
     * sortedInBetween(3, 8) returns [4, 5, 6, 7]
     * sortedInBetween(8, 8) returns []
     *
     * @param data1 the smaller data in the threshold
     * @param data2 the larger data in the threshold
     *              or if data1 > data2
     * @return a sorted list of data that is > data1 and < data2
     * @throws java.lang.IllegalArgumentException if data1 or data2 are null
     */
    public List<T> sortedInBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("either data passed in is null");
        }
        if (data1.compareTo(data2) > 0) {
            throw new IllegalArgumentException("");

        }
        ArrayList<T> sortedList = new ArrayList<>();
        sortedInBetweenHelper(sortedList, root, data1, data2);
        return sortedList;


    }

    /**
     * Private Helper method to the public sort in between
     * @param alist list representing sorting data
     * @param current Node to which we will compare our data to
     * @param data1 integer representing the lower bound
     * @param data2 integer representing the upper bound
     */
    private void sortedInBetweenHelper(List<T> alist, AVLNode<T> current, T data1, T data2) {
        if (current == null) {
            return;
        }
        if (data1.compareTo(current.getData()) < 0) {
            sortedInBetweenHelper(alist, current.getLeft(), data1, data2);
        }
        if (data1.compareTo(current.getData()) < 0 && data2.compareTo(current.getData()) > 0) {
            alist.add(current.getData());
        }
        sortedInBetweenHelper(alist, current.getRight(), data1, data2);

    }

    /**
     * Clears the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the height of the root of the tree.
     * <p>
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Returns the size of the AVL tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the AVL tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * Returns the root of the AVL tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the AVL tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Helper method for a right rotation
     *
     * @param current the node on which we are performing the rotation
     * @return the new root of the subtree
     */
    private AVLNode<T> rightRotateHelper(AVLNode<T> current) {
        AVLNode<T> a = current.getLeft();
        if (root == current) {
            root = a;
        }
        AVLNode<T> tempVar = a.getRight();
        a.setRight(current);
        current.setLeft(tempVar);
        current.setHeight(Math.max(nodeHeightHelper(current.getLeft()), nodeHeightHelper(current.getRight())) + 1);
        a.setHeight(Math.max(nodeHeightHelper(a.getLeft()), nodeHeightHelper(a.getRight())) + 1);
        a.setBalanceFactor(nodeBalanceHelper(a));
        current.setBalanceFactor(nodeBalanceHelper(current));

        return a;
    }

    /**
     * Helper method for a left rotation
     *
     * @param current the node on which we are performing the rotation
     * @return the new root of the subtree
     */
    private AVLNode<T> leftRotateHelper(AVLNode<T> current) {
        AVLNode<T> a = current.getRight();
        AVLNode<T> tempVar = a.getLeft();
        a.setLeft(current);
        current.setRight(tempVar);

        if (root == current) {
            root = a;
        }

        current.setHeight(Math.max(nodeHeightHelper(current.getLeft()), nodeHeightHelper(current.getRight())) + 1);
        a.setHeight(Math.max(nodeHeightHelper(a.getLeft()), nodeHeightHelper(a.getRight())) + 1);
        a.setBalanceFactor(nodeBalanceHelper(a));
        current.setBalanceFactor(nodeBalanceHelper(current));

        return a;
    }

    /**
     * Helper method for finding the height of a node
     *
     * @param current the node whose height is being found
     * @return the height of the node
     */

    private int nodeHeightHelper(AVLNode<T> current) {
        if (current == null) {
            return -1;
        }
        return current.getHeight();
    }

    /**
     * Helper method for finding the balance factor of a node
     *
     * @param current the node whose height is being found
     * @return the height of the node
     */

    private int nodeBalanceHelper(AVLNode<T> current) {
        if (current == null) {
            return 0;
        }
        return nodeHeightHelper(current.getLeft()) - nodeHeightHelper(current.getRight());
    }

    /**
     * Recursive helper function for the add function
     *
     * @param data data to be added.
     * @param current node that is being checked
     * @return returns the node which was deleted
     * @throws java.lang.IllegalArgumentException if data is already in the AVL
     */

    private AVLNode<T> addHelperMethod(T data, AVLNode<T> current) {

        if (current == null) {
            return new AVLNode<>(data);
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(addHelperMethod(data, current.getRight()));
        } else if (data.compareTo(current.getData()) < 0) {

            current.setLeft(addHelperMethod(data, current.getLeft()));
        } else if (data.equals(current.getData())) {
            size--;
        }
        current.setHeight(Math.max(nodeHeightHelper(current.getLeft()), nodeHeightHelper(current.getRight())) + 1);
        current.setBalanceFactor(nodeBalanceHelper(current));
        if (current.getBalanceFactor() == -2 && current.getRight().getBalanceFactor() <= 0) {
            return leftRotateHelper(current);
        } else if (current.getBalanceFactor() == 2 && current.getLeft().getBalanceFactor() >= 0) {
            return rightRotateHelper(current);
        } else if (current.getBalanceFactor() == 2 && current.getLeft().getBalanceFactor() < 0) {
            current.setLeft(leftRotateHelper(current.getLeft()));
            return rightRotateHelper(current);
        } else if (current.getBalanceFactor() == -2 && current.getRight().getBalanceFactor() > 0) {
            current.setRight(rightRotateHelper(current.getRight()));
            return leftRotateHelper(current);
        }

        return current;
    }

    /**
     * Recursive helper function for the remove method
     *
     * @param data      the data to be removed
     * @param current      the node where we are looking for the data
     * @param dummyNode the data that was removed from the AVL
     * @return the node where the data is located
     */
    private AVLNode<T> removeHelper(T data, AVLNode<T> current, AVLNode<T> dummyNode) {
        if (current == null) {
            return null;
        } else if (data.equals(current.getData())) {
            if (current.getRight() == null && current.getLeft() == null) {
                dummyNode.setData(current.getData());

                return null;
            } else if (current.getRight() != null && current.getLeft() == null) {
                dummyNode.setData(current.getData());

                return current.getRight();
            } else if (current.getLeft() != null && current.getRight() == null) {

                dummyNode.setData(current.getData());

                return current.getLeft();
            } else {

                Object newData = getSuccessorHelper(current.getRight());
                removeHelper((T) newData, current, dummyNode);
                dummyNode.setData(current.getData());
                current.setData((T) newData);
                return current;
            }
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(removeHelper(data, current.getRight(), dummyNode));
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(removeHelper(data, current.getLeft(), dummyNode));
        }
        current.setHeight(Math.max(nodeHeightHelper(current.getLeft()), nodeHeightHelper(current.getRight())) + 1);
        current.setBalanceFactor(nodeBalanceHelper(current));
        if (current.getBalanceFactor() == -2 && current.getRight().getBalanceFactor() <= 0) {
            return leftRotateHelper(current);
        } else if (current.getBalanceFactor() == 2 && current.getLeft().getBalanceFactor() >= 0) {
            return rightRotateHelper(current);
        } else if (current.getBalanceFactor() == 2 && current.getLeft().getBalanceFactor() < 0) {
            current.setLeft(leftRotateHelper(current.getLeft()));
            return rightRotateHelper(current);
        } else if (current.getBalanceFactor() == -2 && current.getRight().getBalanceFactor() > 0) {
            current.setRight(rightRotateHelper(current.getRight()));
            return leftRotateHelper(current);
        }

        return current;
    }

    /**
     * Helper function for finding the successor of a node
     *
     * @param current the left child of the node whose successor is meant to be found
     * @return the data of the successor of the inputted node's parent
     */
    private T getSuccessorHelper(AVLNode<T> current) {
        if (current.getLeft() == null) {
            return current.getData();
        }
        return getSuccessorHelper(current.getLeft());
    }

    /**
     * Recursive helper function for get.
     *
     * @param data the data that is being looked for in the list.
     * @param current the node where the node where the data is being looked for
     * @return the node where the data is found if the tree contains the data, null if not
     */
    private AVLNode<T> getHelperMethod(T data, AVLNode<T> current) {
        if (current == null) {
            return null;
        } else if (data.compareTo(current.getData()) > 0) {
            return getHelperMethod(data, current.getRight());
        } else if (data.compareTo(current.getData()) < 0) {
            return getHelperMethod(data, current.getLeft());
        } else {
            return current;
        }

    }

    /**
     * Recursive helper function for contains
     *
     * @param data the data that is being looked for in the list.
     * @param current the node where the node where the data is being looked for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     */
    private boolean containsHelper(T data, AVLNode<T> current) {
        if (current == null) {
            return false;
        } else if (data.compareTo(current.getData()) > 0) {
            return containsHelper(data, current.getRight());
        } else if (data.compareTo(current.getData()) < 0) {
            return containsHelper(data, current.getLeft());
        } else {
            return true;
        }
    }


}