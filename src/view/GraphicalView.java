package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphicalView {

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
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		JLabel welcome1 = new JLabel("Here are the Existing Tasks. Please choose which one "
				+ "you wish to view by typing in the task name in the given text box.");
		
		JPanel buttons1 = new JPanel();
		
		window2.setLayout(new GridLayout(5,2));
		
		buttons1.setLayout(new GridLayout(16, 2));
		buttons1.setPreferredSize(new Dimension((int)1000000,1000000));
		
		//this is temporary
		addLabel("Task 1", buttons1);
		//addLabel("", buttons1);
		addLabel("Task 2", buttons1);
		//addLabel("", buttons1);
		addLabel("Task 3", buttons1);
		//addLabel("", buttons1);
		addLabel("Task 4", buttons1);
		//addLabel("", buttons1);
		addLabel("Task 5", buttons1);
		//addLabel("", buttons1);
		addLabel("Task 6", buttons1);
		//addLabel("", buttons1);
		addLabel("Task 7", buttons1);
		//addLabel("", buttons1);
		addLabel("Task 8", buttons1);
		weltxt1.add(welcome1);
		
		//try something like this
		/* going to have to call in the list of the tasks
		 * then do a for loop
		 * for( int i = 0; i < list.size; i++) {
		 * String name = list.get(i).getname;
		 * addAButton(name, buttons1);
		 * addLabel("", buttons1);
		 * }
		 */
		
		
		
		JPanel tasks = new JPanel();
		
		tasks.setLayout(new BoxLayout(tasks, BoxLayout.Y_AXIS));
		
		tasks.add(buttons1);
		
		windowpanel1.add(weltxt1);
		
		JPanel textme = new JPanel();
		
		addATextField("", textme);
		
		JPanel back = new JPanel();
		
		JButton backtomenu = new JButton("Back To Main Menu");
		
		back.add(backtomenu);
		
		window2.add(back);
		window2.add(windowpanel1);
		
		window2.add(tasks);
		window2.add(textme);
		
		backtomenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				window2.setVisible(false);
				
				JFrame mainmenu = new JFrame();
				starterup(mainmenu);
			
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
	
	private static JTextField addATextField(String text, Container container) {
		PlainDocument doc =  new PlainDocument();
		JTextField button = new JTextField(doc, text, 1);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        return button;
    }
	
	public static void makingtask() {
		
		String duedate = "";
		String name = "";
		String diff = "";
		String hours = "";
		String type = "";
		
		JFrame window2 = new JFrame();
		window2.setSize(1200, 800);
		
		JPanel windowpanel1 = new JPanel();
		windowpanel1.setLayout(new BorderLayout());

		JPanel tasktypes = new JPanel();
		tasktypes.setLayout(new GridLayout(6,2));
		tasktypes.setPreferredSize(new Dimension((int)10,10));
		
		JLabel newtask = new JLabel("Please input the specified information into the designated boxes.");
		
		addLabel("The Date Due: ", tasktypes);
		JTextField listen1 = addATextField("", tasktypes);
		try {
			duedate = listen1.getDocument().getText(0, listen1.getDocument().getLength());
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}
		
		
		addLabel("How You Would Rate It's Difficulty: ", tasktypes);
		JTextField listen2 = addATextField("", tasktypes);
		try {
			diff = listen2.getDocument().getText(0, listen2.getDocument().getLength());
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}
		
		
		addLabel("How Many Hours You Suspect It Will Take: ", tasktypes);
		JTextField listen3 = addATextField("", tasktypes);
		try {
		    hours = listen3.getDocument().getText(0, listen3.getDocument().getLength());
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}
		
		addLabel("The Type of Task: ", tasktypes);
		JTextField listen4 = addATextField("", tasktypes);
		try {
			type = listen4.getDocument().getText(0, listen4.getDocument().getLength());
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}
		
		
		addLabel("The Name of the Task: ", tasktypes);
		JTextField listen5 = addATextField("", tasktypes);
		try {
			name = listen5.getDocument().getText(0, listen5.getDocument().getLength());
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}
		
		JPanel enter = new JPanel();
		enter.setLayout(new BorderLayout());
		JButton enterbutton = new JButton("Enter");
        enterbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enter.add(enterbutton);
        
        JPanel back = new JPanel();
		
		JButton backtomenu = new JButton("Back To Main Menu");
		
		back.add(backtomenu);
		
		windowpanel1.add(tasktypes, BorderLayout.CENTER);
		windowpanel1.add(newtask, BorderLayout.NORTH);
		windowpanel1.add(enter, BorderLayout.SOUTH);
		windowpanel1.add(back, BorderLayout.EAST);
		
		
		window2.add(windowpanel1);
		
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setVisible(true);
		
		enterbutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				//use the getText to get the text for the new task
				//System.out.println(listen1.getText());
			
			}
		
		
		});
		
		backtomenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				window2.setVisible(false);
				
				JFrame mainmenu = new JFrame();
				starterup(mainmenu);
				
			}
		
		
		});
		
		
	}
	
}
