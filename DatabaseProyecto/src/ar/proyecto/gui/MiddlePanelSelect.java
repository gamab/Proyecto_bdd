package ar.proyecto.gui;

import java.awt.CardLayout;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ar.proyecto.controller.ActionSelectCbox;
import ar.proyecto.controller.ActionSelectOk;

public class MiddlePanelSelect extends MiddlePanel {
	private JLabel from;
	private JComboBox cbTable;
	private JLabel whereNum;
	private JFormattedTextField nroMulta;
	private JPanel centralPanel;
	private JPanel centralPanelEmpty;
	private final static String sPaneEmpty = new String("Empty Pannel");
	private final static String sPaneFilled = new String("Filled Pannel");
	private JPanel centralPanelFilled;
	private int widthpc = 400;
		
	private JButton ok;
	
	//constructor
	public MiddlePanelSelect() {
		super();		
		from = new JLabel("FROM TABLE :");
		
		centralPanel = new JPanel();
		centralPanel.setLayout(new CardLayout());
		
		whereNum = new JLabel("WHERE nro_multa =");
		nroMulta = new JFormattedTextField(NumberFormat.getIntegerInstance());
		nroMulta.setColumns(5);
		initCentralPanelEmpty();
		initCentralPanelFilled();
		
		//Adding both centralPanel one on the top of the other
		centralPanel.add(centralPanelEmpty,sPaneEmpty);
		centralPanel.add(centralPanelFilled,sPaneFilled);
		
		String[] cb = {"Infraccion","Multa"};
		cbTable = new JComboBox(cb);
		cbTable.setAction(new ActionSelectCbox(this,centralPanel));
		
		ok = new JButton(new ActionSelectOk(this,"OK"));
		
		this.add(from);
		this.add(cbTable);
		this.add(centralPanel);
		this.add(ok);
	}

	private void initCentralPanelEmpty() {
		centralPanelEmpty = new JPanel();
		centralPanelEmpty.setBackground(this.getBackground());
	}
	private void initCentralPanelFilled() {
		centralPanelFilled = new JPanel();
		centralPanelFilled.setBackground(this.getBackground());
		centralPanelFilled.add(whereNum);
		centralPanelFilled.add(nroMulta);
	}

	public String getSPaneEmpty() {
		return sPaneEmpty;
	}

	public String getSPaneFilled() {
		return sPaneFilled;
	}

	public JComboBox getCbTable() {
		return cbTable;
	}

	public JFormattedTextField getNroMulta() {
		return nroMulta;
	}
	
	
}
