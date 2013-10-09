package ar.proyecto.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import ar.proyecto.gui.MiddlePanelSelect;

public class ActionMiddlePanelSelectCbox extends AbstractAction {

	MiddlePanelSelect panelPere;
	JPanel panelAModifier;
	
	public ActionMiddlePanelSelectCbox(MiddlePanelSelect panelPere, JPanel panelAModifier) {
		this.panelPere = panelPere;
		this.panelAModifier = panelAModifier;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JComboBox cb = (JComboBox) arg0.getSource();
		System.out.println("In ActionSelectCbox : received command : " + (String)cb.getSelectedItem());
		CardLayout cl = (CardLayout) panelAModifier.getLayout();
		if (((String)cb.getSelectedItem()).equals("Multa")) {
			System.out.println("In ActionSelectCbox : Setting Filled centralPanel");
			cl.show(panelAModifier, panelPere.getSPaneFilled());
		}
		else if  (((String)cb.getSelectedItem()).equals("Infraccion")) {
			System.out.println("In ActionSelectCbox : Setting Empty centralPanel");
			cl.show(panelAModifier, panelPere.getSPaneEmpty());
		}
	}

}
