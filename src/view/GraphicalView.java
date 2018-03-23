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

import controller.TaskMgrDriver;
import java.util.ListIterator;
import model.Task;

public class GraphicalView {
	
	private static TaskMgrDriver driver;

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
	
	public GraphicalView(TaskMgrDriver driver) {
		this.driver = driver;
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
		
		weltxt1.add(welcome1);
		
		//TODO
		//try something like this
		for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext();)
		{
			Task t = tasks.next();
			addLabel(t.getName(), buttons1);
		}
		
		
		JPanel tasks = new JPanel();
		
		tasks.setLayout(new BoxLayout(tasks, BoxLayout.Y_AXIS));
		
		tasks.add(buttons1);
		
		windowpanel1.add(weltxt1);
		
		JPanel textme = new JPanel();
		
//		addATextField("", textme);

		JButton removeAllTasks = new JButton("Remove All Tasks");
		textme.add(removeAllTasks);
		removeAllTasks.addActionListener( (ActionEvent e) -> {
			ListIterator<Task> tasklist = driver.getTasks();
			while(tasklist.hasNext())
			{
				Task t = tasklist.next();
				tasklist = driver.removeTask(t);
			}
			window2.setVisible(false);
			exist();
		});
		
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
		int i = 0;
		String s = "";
		
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
		
		int duedate = i;
		
		addLabel("How You Would Rate It's Difficulty: ", tasktypes);
		JTextField listen2 = addATextField("", tasktypes);
		addLabel("How Many Hours You Suspect It Will Take: ", tasktypes);
		JTextField listen3 = addATextField("", tasktypes);
		
		
		
		addLabel("The Type of Task: ", tasktypes);
		JTextField listen4 = addATextField("", tasktypes);
		
		
		
		addLabel("The Name of the Task: ", tasktypes);
		JTextField listen5 = addATextField("", tasktypes);
		
		
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
				try {
					int duedate = Integer.valueOf(listen1.getDocument().getText(0, listen1.getDocument().getLength()));
					int diff = Integer.valueOf(listen2.getDocument().getText(0, listen2.getDocument().getLength()));
					int hours = Integer.valueOf(listen3.getDocument().getText(0, listen3.getDocument().getLength()));
					String type = listen4.getDocument().getText(0, listen4.getDocument().getLength());
					String name = listen5.getDocument().getText(0, listen5.getDocument().getLength());
					driver.addTask(name, type, duedate, hours, false, diff);
					
					listen1.setText("");
					listen2.setText("");
					listen3.setText("");
					listen4.setText("");
					listen5.setText("");
					
				} catch (BadLocationException e) {
					
					e.printStackTrace();
				}
				
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