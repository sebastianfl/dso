package org.seb.dso.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sebastian
 *
 */
public class SnapshotTree {
	public TreeNode<Item> itemTree;

	public SnapshotTree() {
		super();
		itemTree = new TreeNode<Item>(new Item());
	}

	public void addChilds(TreeNode<Item> node, List<Collection<Item>> items, int level) {
		if (items.size() == level)
			return;
		Collection<Item> c = items.get(level);
		for (Iterator<Item> iterator = c.iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			node.addChild(item);

		}
		++level;
		for (Iterator<TreeNode<Item>> iterator = node.iterator(); iterator.hasNext();) {
			TreeNode<Item> object = iterator.next();
			addChilds(object, items, level);
		}
	}

}
