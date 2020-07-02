package io.solutions;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
	List<TreeNode> children;
	String name;
	TreeNode parent;

	public TreeNode(String name) {
		this.name = name;
		this.children = new ArrayList<>();
		this.parent = null;
	}
}

public class SimplifyPath {
	public String simplifyPath(String path) {
		TreeNode dummyRoot = new TreeNode("dummy");
		TreeNode iterator = dummyRoot;
		TreeNode leaf = constructTree(path, iterator);
		return travelPath(leaf, dummyRoot);
	}

	private String travelPath(TreeNode leaf, TreeNode root) {
		StringBuilder sb = new StringBuilder("");
		TreeNode iterator = leaf;
		while (iterator != root) {
			sb.insert(0, iterator.name);
			sb.insert(0, "/");
			iterator = iterator.parent;
		}
		String path = sb.toString();
		if (path.equals("")) {
			return "/";
		} else {
			return path;
		}
	}

	private TreeNode constructTree(String path, TreeNode iterator) {
		String[] splits = path.split("/");
		for (String subPath : splits) {
			if (subPath.equals("")) {
				// do nothing
			} else if (subPath.equals("..")) {
				iterator = getParent(iterator);
			} else if (subPath.equals(".")) {
				// do nothing
			} else {
				TreeNode dir = new TreeNode(subPath);
				addChild(iterator, dir);
				dir.parent = iterator;
				iterator = dir;
			}
		}
		return iterator;
	}

	private void addChild(TreeNode parent, TreeNode child) {
		if (parent != null) {
			List<TreeNode> children = parent.children;
			children.add(child);
		}
	}

	private TreeNode getParent(TreeNode node) {
		if (node == null || node.parent == null) {
			return node; // root
		} else {
			return node.parent;
		}
	}
}
