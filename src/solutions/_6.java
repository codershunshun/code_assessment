/**
 * Find distance between two node of BST 
 */
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int val) {
		this.val = val;
		this.left = null;
		this.right = null;
	}
}

class _6 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(10);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(20);
		root.left.right = new TreeNode(25);
		root.right.left = new TreeNode(30);
		root.right.right = new TreeNode(35);
		root.left.right.right = new TreeNode(45);
		_6 s = new _6();
		System.out.println("Distance between 45 and 20 is : "
				+ s.findDistance(root, root.left.right.right, root.left.left));
	}

	public int findDistance(TreeNode root, TreeNode n1, TreeNode n2) {
		TreeNode LCA = findLCA(root, n1, n2);
		return pathLength(LCA, n1) + pathLength(LCA, n2) - 2;
	}
	
	public int pathLength(TreeNode root, TreeNode n) {
		if (root == null) {
			return 0;
		}
		
		if (root == n) {
			return 1;
		}
		
		int length = 0;
		
		if ((length = pathLength(root.left, n)) > 0 || (length = pathLength(root.right, n)) > 0) {
			return length + 1;
		}
		
		return 0;
	}
	
	public TreeNode findLCA(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null || root == n1 || root == n2) {
			return root;
		}
		
		TreeNode left = findLCA(root.left, n1, n2);
		TreeNode right = findLCA(root.right, n1, n2);
		
		if (left != null && right != null) {
			return root;
		}
		
		if (left != null) {
			return left;
		}
		
		if (right != null) {
			return right;
		}
		
		return null;
	}
}
