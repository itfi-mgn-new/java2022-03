package lesson8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class TableTEst extends JFrame {
	public TableTEst() {
		final MyModel		model = new MyModel();
		final JTable		table = new JTable(model);
		final JScrollPane	scroll = new JScrollPane(table);
		
		add(scroll, BorderLayout.CENTER);
		
		// table.setDefaultEditor(columnClass, editor); 
		table.setDefaultRenderer(String.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				final JLabel	label = new JLabel(value.toString());
				
				label.setOpaque(true);
				if (row % 2 == 0) {
					label.setForeground(Color.red);
					label.setBackground(Color.BLUE);
				}
				else {
					label.setForeground(Color.GREEN);
					label.setBackground(Color.YELLOW);
				}
				
				return label;
			}
		});
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		table.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		table.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		table.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					System.err.println("INsert");
					model.insertNew();
				}
			}
		});
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TableTEst	tt = new TableTEst();
		
		tt.setVisible(true);
	}

	public static class MyModel extends DefaultTableModel {
		private static final long serialVersionUID = -4735900769545137425L;
		private final List<StringAndBoolean>	list = new ArrayList<>();
		
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
				case 0 : return String.class;
				case 1 : return Boolean.class;
				default : return Object.class;
			}
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
				case 0 : return "column1";
				case 1 : return "column2";
				default : return "?????";
			}
		}

		@Override
		public int getRowCount() {
			if (list != null) {
				return list.size();
			}
			else {
				return 0;
			}
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
				case 0 : return list.get(rowIndex).s;
				case 1 : return list.get(rowIndex).b;
				default : return "?????";
			}
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == 1;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			list.get(rowIndex).b = (Boolean)aValue;
		}
		
		public void insertNew() {
			list.add(new StringAndBoolean("str  ", false));
			fireTableDataChanged();
		}
	}
	
	private static class StringAndBoolean {
		String	s;
		boolean	b;
		public StringAndBoolean(String s, boolean b) {
			this.s = s;
			this.b = b;
		}
		
		
	}
}
