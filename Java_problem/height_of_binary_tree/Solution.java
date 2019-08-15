package problem.height_of_binary_tree;

public class Solution {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public int getTreeHeight(TreeNode node) {
		int leftPathLength = 0;
		if (node.left != null) {
			leftPathLength = 1 + getTreeHeight(node.left);
		}
		
		int rightPathLength = 0;
		if (node.right != null) {
			rightPathLength = 1 + getTreeHeight(node.right);
		}
		
		return Math.max(leftPathLength, rightPathLength);
	}
}
