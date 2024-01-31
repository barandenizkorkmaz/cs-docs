# 5. Trees

## Tree Terminology

- `root`: The node at the top of a tree. It has no parents.
- `edge (branch)`: The link from a node to its successor.
- `children`: The successors of a node.
- `parent`: The predecessor of a node.
- `siblings`: The nodes that have the same parent.
- `leaf`: A node that has no children.
- `external nodes`: Leaves are also known as external nodes.
- `internal nodes`: Non-leaf nodes are also known as internal nodes.
- `subtree of a node`: A tree whose root is a child of that node.
- `depth`: The depth of a node is the length of the path from the root to the node.
- `level`: The set of all nodes at given depth is called the `level` of the tree. The `root` node is at level `zero`. 
  - `level` and `depth` describe the same concept but level is a tree-oriented term while depth is a node-oriented term (e.g. level of tree vs depth of node).
- `height`: The height of a node is the length of the path from that node to the deepest node. The height of a tree is the length of the path from the root to the deepest node in the tree. A (rooted) tree with only one node (the root) has a height of `zero`.
- `height of the tree`: The height of root node.
- `depth of the tree`: The maximum depth among all the nodes in the tree.
  - For a given tree, `height of the tree` $=$ `depth of the tree`.
- `size`: The size of a node is the number of descendants it has including itself.
- `skew tree`: If every node in a tree has only one child (except leaf nodes).
  - `left skew tree`: If every node has only left child.
  - `right skew tree:` If every node has only right child.
- `strict binary tree`: All nodes have either 2 children or 0 children (leaf nodes).
- `full binary tree`: All nodes have exactly 2 children except the leaf nodes in the final level, level n, for a tree of height n. In other words, a binary tree is full binary tree if each node has exactly two children and all leaf nodes are at the same level.
- `complete binary tree`: A complete binary tree is a full binary tree through level n -1 with some extra leaf nodes at level n and all of the nodes at level n are towards the left of the tree.



## Binary Trees

- A set of nodes `T` is a `binary tree` if either of the following is true:
  - T is empty
  - Else, its root node has two subtrees, $T_L$ and $T_R$, such that they are binary trees.


- A tree is called `binary tree` if each node has at most two children.
  - Observe that a binary tree has a recursive definition.



## Binary Search Tree

- A set of nodes in T is a `binary search tree` if either of the following is true
  - T is empty
  - Else, its root node has two subtrees,  $T_L$ and $T_R$, such that they are binary search trees and the value in the root node of T is greater than all values in $T_L$ and is less than all values in $T_R$.
- Observe that a binary search tree has a recursive definition as well.



### Searching a Binary Tree

```bash
if the tree is empty
	Return null (target is not found).
else if the target matches the root node’s data
	Return the data stored at the root node.
else if the target is less than the root node’s data
	Return the result of searching the left subtree of the root.
else
	Return the result of searching the right subtree of the root.
```

- Just as with a binary search of an array, each probe into the binary search tree has the potential of eliminating half the elements in the tree. If the binary search tree is relatively balanced (i.e., the depths of the leaves are approximately the same), searching a binary search tree is an $O(log n)$ process, just like a binary search of an ordered array.



## Tree Traversals

### Preorder Traversal

```bash
if the tree is empty
	Return.
else
	Visit the root.
	Preorder traverse the left subtree
	Preorder traverse the right subtree
```

### Inorder Traversal

```bash
if the tree is empty
	Return.
else
	Inorder traverse the left subtree
	Visit the root.
	Inorder traverse the right subtree
```

### Postorder Traversal

```bash
if the tree is empty
	Return.
else
	Postorder traverse the left subtree
	Postorder traverse the right subtree
    Visit the root.
```



## Implementing a `Binary Tree` Class

### Class `BinaryTree<E>`

| **Data Field/Attribute** | **Description**                   |
| ------------------------ | --------------------------------- |
| `protected Node<E> root` | Reference to the root of the tree |

| **Constructor/Behavior**                                     | **Description**                                              |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `public BinaryTree()`                                        | Constructs an empty binary tree                              |
| `protected BinaryTree(Node<E> root)`                         | Constructs a binary tree with the given node as the root     |
| `public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)` | Constructs a binary tree with the given data at the root and the two given subtrees |

| **Method/Behavior**                      | **Description**                                      |
| ---------------------------------------- | ---------------------------------------------------- |
| `public BinaryTree<E> getLeftSubtree()`  | Returns the left subtree                             |
| `public BinaryTree<E> getRightSubtree()` | Returns the right subtree                            |
| `public E getData()`                     | Returns the data in the root                         |
| `public boolean isLeaf()`                | Returns true if this tree is a leaf, false otherwise |
| `public String toString()`               | Returns a String representation of the tree          |
| `public void preOrderTraversal()`        | Prints the output of preorder traversal              |
| `public void inOrderTraversal()`         | Prints the output of inorder traversal               |
| `public void postOrderTraversal()`       | Prints the output of postorder traversal             |

```java
package datastructures.tree;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable{

    /** Class to encapsulate a tree node. */
    protected static class Node<E> implements Serializable {
        // Data Fields
        /** The information stored in this node. */
        protected E data;
        /** Reference to the left child. */
        protected Node<E> left;
        /** Reference to the right child. */
        protected Node<E> right;

        // Constructors
        /** Construct a node with given data and no children.
         @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
        // Methods
        /** Return a string representation of the node.
         @return A string representation of the data fields
         */
        public String toString () {
            return data.toString();
        }
    }

    // Data Field
    /** The root of the binary tree */
    protected Node<E> root;

    // Data Fields
    /** Return value from the public add method. */
    protected boolean addReturn;
    /** Return value from the public delete method. */
    protected E deleteReturn;

    public BinaryTree() {
        root = null;
    }

    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /** Constructs a new binary tree with data in its root leftTree
     as its left subtree and rightTree as its right subtree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
                      BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /** Return the left subtree.
     @return The left subtree or null if either the root or
     the left subtree is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    /** Return the right subtree.
     @return The right subtree or null if either the root or
     the right subtree is null
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }

    /** Determine whether this tree is a leaf.
     @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 1, sb);
        return sb.toString();
    }

    /** Converts a sub‐tree to a string.
     Performs a preorder traversal.
     @param node The local root
     @param depth The depth
     @param sb The StringBuilder to save the output
     */
    private void toString(Node<E> node, int depth,
                          StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }

    public void preOrderTraversal(){
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node<E> node){
        if(node != null){
            System.out.println(node.toString());
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void inOrderTraversal(){
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node<E> node){
        if(node != null){
            inOrderTraversal(node.left);
            System.out.println(node.toString());
            inOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal(){
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node<E> node){
        if(node != null){
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.toString());
        }
    }
}

```



## Implementing a `BinarySearchTree` Class

### Interface `SearchTree<E>`

| **Method**                   | **Description**                                              |
| ---------------------------- | ------------------------------------------------------------ |
| `boolean add(E item)`        | Inserts `item` where it belongs in the tree. Returns true if `item` is inserted; false if it isn’t (already in tree) |
| `boolean contains(E target)` | Returns true if `target` is found in the tree                |
| `E search(E target)`         | Returns a reference to the data in the node that is equal to `target`. If no such node is found, returns `null` |
| `E delete(E target)`         | Removes `target` (if found) from the tree and returns it; otherwise, returns `null` |
| `boolean remove(E target)`   | Removes `target` (if found) from the tree and returns true; otherwise, returns false |



### Operations

#### Search

```bash
if the tree is empty
	Return null (target is not found).
else if the target matches the root node’s data
	Return the data stored at the root node.
else if the target is less than the root node’s data
	Return the result of searching the left subtree of the root.
else
	Return the result of searching the right subtree of the root.
```

#### Insert

```bash
if the root is null
	Replace empty tree with a new tree with the item at the root and return true.
else if the item is equal to root.data
	The item is already in the tree; return false.
else if the item is less than root.data
	Recursively insert the item in the left subtree.
else
	Recursively insert the item in the right subtree.
```

#### Remove

```bash
if the root is null
	The item is not in tree – return null.
Compare the item to the data at the local root.
if the item is less than the data at the local root
	Return the result of deleting from the left subtree.
else if the item is greater than the local root
	Return the result of deleting from the right subtree.
else # The item is in the local root
	Store the data in the local root in deleteReturn.
    if the local root has no children
    	Set the parent of the local root to reference null.
    else if the local root has one child
    	Set the parent of the local root to reference that child.
    else # Find the inorder predecessor
        if the left child has no right child it is the inorder predecessor
        	Set the parent of the local root to reference the left child.
        else
        	Find the rightmost node in the right subtree of the left child.
        	Copy its data into the local root’s data and remove it by setting its parent to reference its left child.
```



### Implementation

```java
package datastructures.tree;

import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<E>>
        extends BinaryTree<E> implements SearchTree<E> {
    // Data Fields
    /** Return value from the public add method. */
    protected boolean addReturn;
    /** Return value from the public delete method. */
    protected E deleteReturn;

    /** Starter method find.
     pre: The target object must implement
     the Comparable interface.
     @param target The Comparable object being sought
     @return The object, if found, otherwise null
     */
    public E search(E target) {
        return search(root, target);
    }

    /** Recursive find method.
     @param localRoot The local subtree's root
     @param target The object being sought
     @return The object, if found, otherwise null
     */
    private E search(Node<E> localRoot, E target) {
        if (localRoot == null)
            return null;
        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0)
            return localRoot.data;
        else if (compResult < 0)
            return search(localRoot.left, target);
        else
            return search(localRoot.right, target);
    }

    /** Starter method add.
     pre: The object to insert must implement the
     Comparable interface.
     @param item The object being inserted
     @return true if the object is inserted, false
     if the object already exists in the tree
     */
    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    /** Recursive add method.
     post: The data field addReturn is set true if the item is added to
     the tree, false if the item is already in the tree.
     @param localRoot The local root of the subtree
     @param item The object to be inserted
     @return The new local root that now contains the
     inserted item
     */
    private Node<E> add(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree — insert it.
            addReturn = true;
            return new Node<>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /** Starter method delete.
     post: The object is not in the tree.
     @param target The object to be deleted
     @return The object deleted from the tree
     or null if the object was not in the tree
     @throws ClassCastException if target does not implement
     Comparable
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /** Recursive delete method.
     post: The item is not in the tree;
     deleteReturn is equal to the deleted item
     as it was stored in the tree or null
     if the item was not found.
     @param localRoot The root of the current subtree
     @param item The item to be deleted
     @return The modified local root that does not contain
     the item
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }
        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
        } else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            } else {
                // Node being deleted has 2 children, replace the data with inorder predecessor.
                localRoot.data = getMin(localRoot.right);
                localRoot.right = delete(localRoot.right, localRoot.data);
            }
        }
        return localRoot;
    }

    private E getMin(){
        if(root == null){
            throw new NoSuchElementException();
        }
        return getMin(root);
    }

    private E getMin(Node<E> localRoot){
        if(localRoot.left == null){
            return localRoot.data;
        }
        else{
            return getMin(localRoot.left);
        }
    }

    @Override
    public boolean remove(E target) {
        root = delete(root, target);
        return deleteReturn != null;
    }

    @Override
    public boolean contains(E target) {
        return search(target) != null;
    }

}
```