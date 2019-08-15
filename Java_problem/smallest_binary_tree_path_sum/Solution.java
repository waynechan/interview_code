package problem.smallest_binary_tree_path_sum;

public class Solution {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public int getSmallestSum(TreeNode node) {
		
		if (node == null) {
			return -1;
		}
		
		int leftSum = 0;
		if (node.left != null) {
			leftSum = getSmallestSum(node.left);
		}
		
		int rightSum = 0;
		if (node.right != null) {
			rightSum = getSmallestSum(node.right);
		}
		
		return node.val + Math.min(leftSum, rightSum);
	}
}
