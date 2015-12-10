package org.seb.dso;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.seb.dso.model.Item;
import org.seb.dso.model.SnapshotTree;
import org.seb.dso.model.TreeNode;
import org.seb.dso.util.ItemUtils;

public class MainTest {
	private Inventory inv;

	@Before
	public void setUp() throws Exception {
		Collection<Item> items = ItemUtils.getItems(new File("items.csv"));
		this.inv = ItemUtils.parseInventoryFromItems(items);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTwohanded() {
		inv.setupItemMatrix(true);
		List<Collection<Item>> matrix = inv.getItemMatrix();
		assertEquals(12, matrix.size());

		SnapshotTree st = new SnapshotTree();
		st.addChilds(st.itemTree, matrix, 0);
		print(st.itemTree);
	}

	void print(TreeNode<Item> node) {
		System.out.println(node.getData());
		if (null != node.getChildren() && node.getChildren().size() > 0)
			print(node.getChildren().get(0));
	}
}
