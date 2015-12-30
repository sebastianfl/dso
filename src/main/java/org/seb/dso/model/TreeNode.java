package org.seb.dso.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sebastian
 *
 * @param <T>
 */
public class TreeNode<T> implements Iterable<TreeNode<T>> {

	T data;

	public synchronized T getData() {
		return data;
	}

	public synchronized void setData(T data) {
		this.data = data;
	}

	public synchronized TreeNode<T> getParent() {
		return parent;
	}

	public synchronized void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	public synchronized List<TreeNode<T>> getChildren() {
		return children;
	}

	public synchronized void setChildren(List<TreeNode<T>> children) {
		this.children = children;
	}

	TreeNode<T> parent;
	List<TreeNode<T>> children;

	public TreeNode(T data) {
		this.data = data;
		this.children = new ArrayList<TreeNode<T>>();
	}

	public TreeNode<T> addChild(T child) {
		TreeNode<T> childNode = new TreeNode<T>(child);
		childNode.parent = this;
		this.children.add(childNode);
		return childNode;
	}

	@Override
	public Iterator<TreeNode<T>> iterator() {
		return children.iterator();
	}

}