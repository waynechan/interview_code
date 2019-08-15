class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    # @param p, a tree node
    # @param q, a tree node
    # @return a boolean
    def compare(self, p, q):
        if p is None and q is None:
            return True

        if p is None or q is None:
            return False

        return p.val == q.val and self.compare(p.left, q.left) and self.compare(p.right, q.right)
