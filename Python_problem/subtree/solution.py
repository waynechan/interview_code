class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    # @param x, a tree node
    # @param y, a tree node
    # @return a boolean
    def is_subtree(self, x, y):
        if self.is_same_tree(x, y):
            return True
        else:
            return (x.left is not None and self.is_subtree(x.left, y)) \
                   or (x.right is not None and self.is_subtree(x.right, y))

    def is_same_tree(self, x, y):
        if x is None and y is None:
            return True
        if x is None or y is None:
            return False
        return x.val == y.val and self.is_same_tree(x.left, y.left) and self.is_same_tree(x.right, y.right)
