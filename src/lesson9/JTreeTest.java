package lesson9;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class JTreeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame	frame = new JFrame();

		DefaultMutableTreeNode	root = new DefaultMutableTreeNode();  		
		root.setUserObject("sdsdsdsdsd");
		
		DefaultMutableTreeNode	inner = new DefaultMutableTreeNode("NESTED");
		inner.add(new DefaultMutableTreeNode("---------------"));
		
		root.add(new DefaultMutableTreeNode("12367821367236478"));
		root.add(inner);
		JTree	tree = new JTree(root);
	
		((DefaultTreeModel)tree.getModel()).addTreeModelListener(new TreeModelListener() {
			@Override
			public void treeStructureChanged(TreeModelEvent e) {
			}
			
			@Override
			public void treeNodesRemoved(TreeModelEvent e) {
			}
			
			@Override
			public void treeNodesInserted(TreeModelEvent e) {
			}
			
			@Override
			public void treeNodesChanged(TreeModelEvent e) {
			}
		});

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				System.err.println(((DefaultMutableTreeNode)e.getNewLeadSelectionPath().getLastPathComponent()).getUserObject());
			}
		});
		tree.setRootVisible(true);
//	((DefaultTreeModel)tree.getModel()).
		
		frame.add(new JScrollPane(tree));
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

}
