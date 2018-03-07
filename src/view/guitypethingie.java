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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class guitypethingie {

	public static void starterup(JFrame window) {
		
		JPanel windowpanel = new JPanel();
		windowpanel.setLayout(new BorderLayout());
		
		
		window.setSize(1200, 800);
		
		JPanel weltxt = new JPanel();
		
		JLabel welcome = new JLabel("Welcome to TaskManager 2.0!"
				+ "    What would you like to do today?");
		JLabel welcome2 = new JLabel("Create a new task, or view existing tasks?");
		
		JPanel buttons = new JPanel();
		
		JButton newtask = new JButton("New Task");
		JButton existing = new JButton("Existing Tasks");
		
		window.setLayout(new CardLayout());
		
		buttons.setLayout(new FlowLayout());
		
		buttons.add(newtask);
		buttons.add(existing);
		
		weltxt.add(welcome);
		weltxt.add(welcome2);
		
		windowpanel.add(buttons, BorderLayout.SOUTH);
		windowpanel.add(weltxt, BorderLayout.CENTER);
		
		window.add(windowpanel);
		
		
		window.setVisible(true);
		
		newtask.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				window.setVisible(false);
				makingtask();
			
			}
		
		
		});
		
		existing.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				window.setVisible(false);
				exist();
			
			}
		
		
		});
		
		
	}
	
	public static void main(String[] args) {
	
		JFrame window = new JFrame("Task Manager 2.0");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		starterup(window);
	}
	
	public static void exist( ) {
		
		JFrame window2 = new JFrame();
		JPanel windowpanel1 = new JPanel();
		
		windowpanel1.setLayout(new BoxLayout(windowpanel1, BoxLayout.Y_AXIS));
		
		window2.setSize(1200, 800);
		
		JPanel weltxt1 = new JPanel();
		
		JLabel welcome1 = new JLabel("Here are the Existing Tasks. Please choose which one you wish to view.");
		
		JPanel buttons1 = new JPanel();
		
		window2.setLayout(new BorderLayout());
		
		buttons1.setLayout(new GridLayout(16, 1));
		buttons1.setPreferredSize(new Dimension((int)10,10));
		
		addAButton("Task 1", buttons1);
		addLabel("", buttons1);
		addAButton("Task 2", buttons1);
		addLabel("", buttons1);
		addAButton("Task 3", buttons1);
		addLabel("", buttons1);
		addAButton("Task 4", buttons1);
		addLabel("", buttons1);
		addAButton("Task 5", buttons1);
		addLabel("", buttons1);
		addAButton("Task 6", buttons1);
		addLabel("", buttons1);
		addAButton("Task 7", buttons1);
		addLabel("", buttons1);
		addAButton("Task 8", buttons1);
		weltxt1.add(welcome1);
		
		windowpanel1.add(weltxt1);
		windowpanel1.add(buttons1);
		
		JPanel back = new JPanel();
		
		JButton backtomenu = new JButton();
		
		back.add(backtomenu);
		
		window2.add(windowpanel1, BorderLayout.CENTER);
		window2.add(back, BorderLayout.NORTH);
		
		backtomenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				window2.setVisible(false);
				
				starterup(window2);
			
			}
		
		
		});
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setVisible(true);
		
	}
	
	private static void addAButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

	private static void addLabel(String text, Container container) {
        JLabel field = new JLabel(text);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(field);
    }
	
	private static void addATextField(String text, Container container) {
        JTextField button = new JTextField(text, 60);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }
	
	public static void makingtask() {
		
		JFrame window2 = new JFrame();
		window2.setSize(1200, 800);
		
		JPanel windowpanel1 = new JPanel();
		windowpanel1.setLayout(new BorderLayout());

		JPanel tasktypes = new JPanel();
		tasktypes.setLayout(new GridLayout(5,2));
		tasktypes.setPreferredSize(new Dimension((int)10,10));
		
		JLabel newtask = new JLabel("Please input the specified information into the designated boxes.");
		
		addLabel("The Date Due: ", tasktypes);
		addATextField("", tasktypes);
		addLabel("How You Would Rate It's Difficulty: ", tasktypes);
		addATextField("", tasktypes);
		addLabel("How Many Hours You Suspect It Will Take: ", tasktypes);
		addATextField("", tasktypes);
		addLabel("The Type of Task: ", tasktypes);
		addATextField("", tasktypes);
		addLabel("The Name of the Task: ", tasktypes);
		addATextField("", tasktypes);
		
		windowpanel1.add(tasktypes, BorderLayout.CENTER);
		windowpanel1.add(newtask, BorderLayout.NORTH);
		
		
		window2.add(windowpanel1);
		
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setVisible(true);
		
		
	}
	
}
