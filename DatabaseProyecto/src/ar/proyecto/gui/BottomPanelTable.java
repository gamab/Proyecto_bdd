package ar.proyecto.gui;

import java.awt.Graphics;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTable;

public class BottomPanelTable extends BottomPanel{

	private JLabel title;
	private ResultSet result;
	private JTable tableResult;


	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
	}

	public void setResult(ResultSet result){
		//modifica el resultado 
	}
	
}

