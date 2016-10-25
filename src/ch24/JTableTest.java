package ch24;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class JTableTest extends JFrame {

	public static void main(String [] args){
		new JTableTest();
	}
	
	String [] tableColHeader = {"First Name", "Last Name"};
	String [][] tableData = {{"Alex","Jonex"}, {"Anderson","Cooper"}, {"Martha","Stewart"}};
	JTable table;
	
	public JTableTest(){
		
		table = new JTable(tableData, tableColHeader);
		table.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int col = e.getColumn();
				TableModel model = (TableModel) e.getSource();
				Object colName = model.getValueAt(row, col);
				System.out.print(colName);
				
			}
		});
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		mainPanel.add(table);
		add(mainPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}
}
