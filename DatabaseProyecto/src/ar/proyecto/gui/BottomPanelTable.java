package ar.proyecto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BottomPanelTable extends JPanel {

	private ResultSet result;
	private ResultSetMetaData resultMeta;
	private JTable table;

	public BottomPanelTable(ResultSet tableResult) throws SQLException {
		super();
		
		this.result = tableResult;
		this.resultMeta = result.getMetaData();
				
		this.table = new JTable(createTableData(this.result),createTableTitle(this.resultMeta));
		
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder("Result"));
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(this.table),BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private int rowCount(ResultSet r) throws SQLException {
		int count = 0;
		if (r.last()) {
			count = r.getRow();
			r.beforeFirst();
		}
		return count;
	}
	private Object[][] createTableData(ResultSet r) throws SQLException {
		ResultSetMetaData rMeta = r.getMetaData();
		Object [][] data = new Object[rowCount(r)][rMeta.getColumnCount()];
		
		int row = 0;
		while (r.next()) {
			for (int cur_col=1;cur_col<=rMeta.getColumnCount();cur_col++) {
				data[row][cur_col-1]=r.getObject(cur_col);
			}
			row = row + 1;
		}
		
		return data;
	}
	
	private String[] createTableTitle(ResultSetMetaData rMeta) throws SQLException {
		String[] result = new String[rMeta.getColumnCount()];
		
		for (int col=1;col<=result.length;col++) {
			result[col-1] = rMeta.getColumnName(col) + "(" + rMeta.getColumnTypeName(col) + ")";
		}
		
		return result;
	}
}

