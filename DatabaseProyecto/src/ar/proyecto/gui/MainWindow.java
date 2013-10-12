package ar.proyecto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ar.proyecto.controller.Controller;
import ar.proyecto.controller.RequestType;

public class MainWindow extends JFrame {
	
	//Para construir la ventana
	private JPanel panelTop;
	private int heightpt = 30;
	private JPanel panelMiddle;
	private int heightpm = 80;
	private JPanel panelBottom;
	private int heightpb = 490;
	private JPanel panelContent;
	private String title;
	private int width;
	private int height;
	
	//Para comunicar con el modelo
	private Controller controller;
	
	
	//-------------------------------------
	//Para construir y modificar la ventana
	//-------------------------------------
	//Constructor
	public MainWindow() {
		super();
		title = new String("BDD Proyecto");
		width = 800;
		height = 600;
		this.setTitle(title);
		this.setSize(new Dimension(width,height));
		this.setLocationRelativeTo(null);
		this.setBackground(Color.white);
		
		panelContent = new JPanel();
		panelContent.setPreferredSize(new Dimension(width, height));
		panelContent.setBackground(this.getBackground());
		panelContent.setLayout(new BorderLayout());

		
		panelTop = new TopPanel(this);
		panelTop.setPreferredSize(new Dimension(width,heightpt));
		panelMiddle = new MiddlePanel(this);
		panelMiddle.setPreferredSize(new Dimension(width,heightpm));
		panelBottom = new BottomPanelString();
		panelBottom.setPreferredSize(new Dimension(width,heightpb));

		panelContent.add(panelTop,BorderLayout.NORTH);
		panelContent.add(panelMiddle,BorderLayout.CENTER);
		panelContent.add(panelBottom,BorderLayout.SOUTH);
		
		
		this.setContentPane(panelContent);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	//Para cambiar el panel del medio
	public void setMiddlePanel(MiddlePanel panel) {
		this.remove(panelMiddle);
		panelMiddle.removeAll();
		panel.setGui(this);
		panelMiddle = panel;
		panelMiddle.setPreferredSize(new Dimension(width,heightpm));
		this.add(panelMiddle,BorderLayout.CENTER);
		panelMiddle.revalidate();
		System.out.println("En MainWindow : panel del medio agregado");
	}
	//Para cambiar el panel del medio
	public void setMiddlePanel(RequestType type) {
		switch (type) {
		case NONE :
			setMiddlePanel(new MiddlePanel(this));
			break;
		case SELECT :
			setMiddlePanel(new MiddlePanelSelect(this));
			break;
		case INSERT :
			setMiddlePanel(new MiddlePanelInsert(this));
			break;
		case DELETE :
			setMiddlePanel(new MiddlePanelDelete(this));
			break;
		}
			
	}
	
	//Para mostrar un resultado en el panel de abajo
	public void setResult(String result) {
		this.remove(panelBottom);
		panelBottom.removeAll();
		panelBottom = new BottomPanelString(result);
		panelBottom.setPreferredSize(new Dimension(width,heightpb));
		this.add(panelBottom,BorderLayout.SOUTH);
		panelBottom.revalidate();
		System.out.println("En MainWindow : panel de abajo agregado");
	}
	
	//Para mostrar un resultado en forma de una tablita
	public void setResult(ResultSet result) throws SQLException {
		this.remove(panelBottom);
		panelBottom.removeAll();
		panelBottom = new BottomPanelTable(result);
		panelBottom.setPreferredSize(new Dimension(width,heightpb));
		this.add(panelBottom,BorderLayout.SOUTH);
		panelBottom.revalidate();
		System.out.println("En MainWindow : panel de abajo agregado");
	}
	
	//----------------------------
	//Para comunicar con el modelo
	//----------------------------
	public void setController(Controller ctrllr) {
		this.controller = ctrllr;
	}
	
	public void sendRequestToController(String request, RequestType type) {
		controller.sendRequestToModelAndUpdateGui(request, type);
	}
	
}
