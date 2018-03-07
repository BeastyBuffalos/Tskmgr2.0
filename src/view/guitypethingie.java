package view;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class guitypethingie {

	public static void main(String[] args) {
	
		JFrame window = new JFrame("Task Manager 2.0");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		
		window.setVisible(true);
		
		
		newtask.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				makingtask(window);
			
			}
		
		
		});
		
		existing.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				exist(window);
			
			}
		
		
		});
		
		
	}
	
	public static void exist( JFrame window ) {
		
		JFrame window2 = window;
		JPanel windowpanel1 = new JPanel();
		windowpanel1.setLayout(new BorderLayout());
		
		
		window2.setSize(900, 400);
		
		JPanel weltxt1 = new JPanel();
		
		JLabel welcome1 = new JLabel("Here are the Existing Tasks. Please choose which one you wish to view.");
		
		JPanel buttons1 = new JPanel();
		
		window.setLayout(new CardLayout());
		
		buttons1.setLayout(new BoxLayout(buttons1, BoxLayout.Y_AXIS));
		
		addAButton("Task 1", buttons1);
		addAButton("Task 2", buttons1);
		addAButton("Task 3", buttons1);
		addAButton("Task 4", buttons1);
		addAButton("Task 5", buttons1);
		addAButton("Task 6", buttons1);
		addAButton("Task 7", buttons1);
		addAButton("Task 8", buttons1);
		weltxt1.add(welcome1);
		
		windowpanel1.add(buttons1, BorderLayout.CENTER);
		windowpanel1.add(weltxt1, BorderLayout.NORTH);
		
		window2.add(windowpanel1);
		
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setVisible(true);
		
	}
	
	private static void addAButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

	private static void addATextField(String text, Container container) {
        JTextField field = new JTextField(text, 60);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(field);
    }
	
	public static void makingtask(JFrame window) {
		
		JFrame window2 = window;
		
		JPanel windowpanel1 = new JPanel();
		windowpanel1.setLayout(new BorderLayout());
		
		window2.setSize(900, 400);
		
		JPanel tasktypes = new JPanel();
		
		JLabel newtask = new JLabel("Please input the specified information into the designated boxes.");
		
		addATextField("The Date Due:", tasktypes);
		addATextField("How You Would It's Difficulty:", tasktypes);
		addATextField("How Many Hours You Suspect It Will Take:", tasktypes);
		addATextField("The Type of Task:", tasktypes);
		addATextField("The Name of the Task:", tasktypes);
	
		windowpanel1.add(tasktypes, BorderLayout.CENTER);
		windowpanel1.add(newtask, BorderLayout.NORTH);
		
		
		window2.add(windowpanel1);
		
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setVisible(true);
		
		
	}
	
}
