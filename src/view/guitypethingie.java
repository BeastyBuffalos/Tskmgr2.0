package view;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class guitypethingie {

	public static void main(String[] args) {
	
		JFrame window = new JFrame();
		JPanel windowpanel = new JPanel();
		windowpanel.setLayout(new BorderLayout());
		
		
		window.setSize(900, 400);
		
		JPanel weltxt = new JPanel();
		
		JLabel welcome = new JLabel("Welcome to TaskManager 2.0!"
				+ " What would you like to do today?");
		
		JPanel buttons = new JPanel();
		
		JButton newtask = new JButton("New Task");
		JButton existing = new JButton("Existing Tasks");
		
		window.setLayout(new CardLayout());
		
		buttons.setLayout(new FlowLayout());
		
		buttons.add(newtask);
		buttons.add(existing);
		
		weltxt.add(welcome);
		
		windowpanel.add(buttons, BorderLayout.SOUTH);
		windowpanel.add(weltxt, BorderLayout.CENTER);
		
		window.add(windowpanel);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		newtask.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				exist(window);
			
			}
		
		
		});
		
		
		
	}
	
	public static void exist( JFrame window) {
		
		//JFrame window1 = new JFrame();
		JPanel windowpanel1 = new JPanel();
		windowpanel1.setLayout(new BorderLayout());
		
		
		window.setSize(900, 400);
		
		JPanel weltxt1 = new JPanel();
		
		JLabel welcome1 = new JLabel("its changed");
		
		JPanel buttons1 = new JPanel();
		
		JButton newtask1 = new JButton("New Task");
		JButton existing1 = new JButton("Existing Tasks");
		
		window.setLayout(new CardLayout());
		
		buttons1.setLayout(new FlowLayout());
		
		buttons1.add(newtask1);
		buttons1.add(existing1);
		
		weltxt1.add(welcome1);
		
		windowpanel1.add(buttons1, BorderLayout.SOUTH);
		windowpanel1.add(weltxt1, BorderLayout.CENTER);
		
		window.add(windowpanel1);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}
	
}
