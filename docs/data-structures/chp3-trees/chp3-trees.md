# Tree Terminology

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



# Binary Trees

### Overview:

- A set of nodes `T` is a binary tree if either of the following is true:









- `binary tree`: A tree is called `binary tree` if each node has at most two children.
  - Observe that a binary tree has a recursive definition.
  - **Types of Binary Trees**
    - 