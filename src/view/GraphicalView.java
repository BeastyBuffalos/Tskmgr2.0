package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.TaskMgrDriver;
import javafx.scene.layout.Border;

import java.util.ListIterator;
import model.Task;

public class GraphicalView {
	
	private TaskMgrDriver driver;
	
	private JFrame frame;
	
	public GraphicalView(TaskMgrDriver driver) {
		this.driver = driver;
		initialize();
	}
	
//	Drop Box Code for use when I can find a place to put it
//	String[] switchit = { "Main Menu", "New Task", "Edit Tasks"};
//	JComboBox changescreen = new JComboBox(switchit);
//	changescreen.setSelectedIndex(2);
//	changescreen.addActionListener(this);
	
//	 public void actionPerformed(ActionEvent e) {
//	        JComboBox cb = (JComboBox)e.getSource();
//	        String petName = (String)cb.getSelectedItem();
//	    }
	
	public void starterup(JFrame window) {
		
		JPanel windowpanel = new JPanel();
		windowpanel.setLayout(new BorderLayout());
				
		window.setSize(1200, 800);
		
		JPanel weltxt = new JPanel();
		
		JLabel welcome = new JLabel("Welcome to TaskManager 2.0!"
				+ "    What would you like to do today?");
		JLabel welcome2 = new JLabel("Create a new task, or view existing tasks?");
		welcome.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		welcome2.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		
		JPanel buttons = new JPanel();
		
		JButton newtask = new JButton("New Task");
		JButton existing = new JButton("Existing Tasks");
		
		newtask.setFont(newtask.getFont().deriveFont(Font.BOLD, 24));
		existing.setFont(existing.getFont().deriveFont(Font.BOLD, 24));
		
		window.setLayout(new CardLayout());
		
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		buttons.add(newtask);
		buttons.add(existing);
		
		weltxt.add(welcome);
		weltxt.add(welcome2);
		
		windowpanel.add(buttons, BorderLayout.SOUTH);
		windowpanel.add(weltxt, BorderLayout.CENTER);
		
		String[] switchit = { "Main Menu", "New Task", "Edit Tasks"};
		JComboBox changescreen = new JComboBox(switchit);
		changescreen.setSelectedIndex(0);
		//changescreen.addActionListener(this);
		
		JPanel dropdown = new JPanel();
		dropdown.setLayout(new GridLayout(0,8));
		
		JLabel blank = new JLabel("");
		JLabel blank1 = new JLabel("");
		JLabel blank2 = new JLabel("");
		JLabel blank3 = new JLabel("");
		JLabel blank4 = new JLabel("");
		JLabel blank5 = new JLabel("");
		JLabel blank6 = new JLabel("");
		JLabel blank7 = new JLabel("");
		
		dropdown.add(changescreen);
		dropdown.add(blank);
		dropdown.add(blank1);
		dropdown.add(blank2);
		dropdown.add(blank3);
		dropdown.add(blank4);
		dropdown.add(blank5);
		dropdown.add(blank6);
		dropdown.add(blank7);
		
		windowpanel.add(dropdown, BorderLayout.NORTH);
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
	
	public void exist( ) {
		
		JFrame window2 = new JFrame();
		JPanel windowpanel1 = new JPanel();
		
		windowpanel1.setLayout(new BoxLayout(windowpanel1, BoxLayout.Y_AXIS));
		
		window2.setSize(1200, 800);
		
		JPanel weltxt1 = new JPanel();
		
		JLabel welcome1 = new JLabel("Here are the Existing Tasks. Please choose which one "
				+ "you wish to view by typing in the task name in the given text box.");
		
		welcome1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JPanel buttons1 = new JPanel();
		
		window2.setLayout(new GridLayout(5,2));
		
		buttons1.setLayout(new GridLayout(16, 2));
		buttons1.setPreferredSize(new Dimension((int)1000000,1000000));
		
		weltxt1.add(welcome1);
		
		//try something like this
		int i = 0;
		int count = 0;
		for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext(); i++)
		{
			Task t = tasks.next();
			JLabel nt = new JLabel((i + 1) + ": " + t.getName(), SwingConstants.CENTER);
			nt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
			buttons1.add(nt);
			count++;
		}
		
		if (count == 0) {
			JLabel none = new JLabel("No tasks to display", SwingConstants.CENTER);
			buttons1.add(none);
			none.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		}
		
		JPanel tasks = new JPanel();
		
		tasks.setLayout(new BoxLayout(tasks, BoxLayout.Y_AXIS));
		
		tasks.add(buttons1);
		
		windowpanel1.add(weltxt1);
		
		JPanel textme = new JPanel();

		JButton removeAllTasks = new JButton("Remove All Tasks");
		removeAllTasks.setFont(removeAllTasks.getFont().deriveFont(Font.BOLD, 24));
		
		
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
		backtomenu.setFont(backtomenu.getFont().deriveFont(Font.BOLD, 24));
		
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
	
	
	

	public static void makingtask() {
		
		Font newTaskFont = new Font("Times New Roman", Font.PLAIN, 32);
		
		JFrame window2 = new JFrame();
		window2.setSize(1200, 800);
		
		JPanel windowpanel1 = new JPanel();
		windowpanel1.setLayout(new BorderLayout());

		JPanel tasktypes = new JPanel();
		tasktypes.setLayout(new GridLayout(6,2, 10, 40));
		tasktypes.setPreferredSize(new Dimension((int)10,10));
		
		// name
		JLabel name = new JLabel("The Name of the Task: ", SwingConstants.RIGHT);
		JTextField listen5 = new JTextField("");
				
		name.setFont(newTaskFont);
		listen5.setFont(newTaskFont);
				
		tasktypes.add(name);
		tasktypes.add(listen5);
		
		// title
		JLabel newtask = new JLabel("<html>Please input the specified information into the designated boxes. 	<br/> 	 "
				+ "<br/>		<html>", SwingConstants.CENTER);
		newtask.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		
		// due date
		JLabel dueDate = new JLabel("The Date Due: ", SwingConstants.RIGHT);
		JTextField listen1 = new JTextField("");
		
		dueDate.setFont(newTaskFont);
		listen1.setFont(newTaskFont);
		
		tasktypes.add(dueDate);
		tasktypes.add(listen1);
		
		// difficulty
		JLabel difficulty = new JLabel("How You Would Rate It's Difficulty: ", SwingConstants.RIGHT);
		JTextField listen2 = new JTextField("");
		
		difficulty.setFont(newTaskFont);
		listen2.setFont(newTaskFont);

		tasktypes.add(difficulty);
		tasktypes.add(listen2);
		
		
		// hours
		JLabel hours = new JLabel("How Many Hours It Will Take To Finish: ", SwingConstants.RIGHT);
		JTextField listen3 = new JTextField("");
		
		hours.setFont(newTaskFont);
		listen3.setFont(newTaskFont);
		
		tasktypes.add(hours);
		tasktypes.add(listen3);
	
		
		// type
		JLabel type = new JLabel("The Type of Task: ", SwingConstants.RIGHT);
		JTextField listen4 = new JTextField("");
		
		type.setFont(newTaskFont);
		listen4.setFont(newTaskFont);
		
		tasktypes.add(type);
		tasktypes.add(listen4);
		
		
		// space for right side of window
		JLabel space = new JLabel("   				", SwingConstants.RIGHT);
		space.setFont(new Font("Times New Roman", Font.PLAIN, 90));
		
		
		// buttons
		JPanel enter = new JPanel();
		
		JButton enterbutton = new JButton("Enter");
		JButton backtomenu = new JButton("Back");
		
		enter.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 5));
		
        enterbutton.setFont(enterbutton.getFont().deriveFont(Font.BOLD, 30));
		backtomenu.setFont(backtomenu.getFont().deriveFont(Font.BOLD, 30));
		
		enter.add(enterbutton);
		enter.add(backtomenu);
		
		
		String[] switchit = { "Main Menu", "New Task", "Edit Tasks"};
		JComboBox changescreen = new JComboBox(switchit);
		changescreen.setSelectedIndex(0);
		//changescreen.addActionListener(this);
		
		
		
		JPanel dropdown = new JPanel();
		dropdown.setLayout(new GridLayout(0,2));
		
//		JLabel blank = new JLabel("");
//		JLabel blank1 = new JLabel("");
//		JLabel blank2 = new JLabel("");
//		JLabel blank3 = new JLabel("");
//		JLabel blank4 = new JLabel("");
//		JLabel blank5 = new JLabel("");
//		JLabel blank6 = new JLabel("");
//		JLabel blank7 = new JLabel("");
//		
		dropdown.add(changescreen);
		
		//changescreen.setBounds(0, 0, 1, 1);
		
		Dimension preferredSize = new Dimension(1,1);
//		changescreen.setPreferredSize(preferredSize);
		
		changescreen.setMaximumSize(preferredSize);
//		blank.setFont(blank.getFont().deriveFont(Font.BOLD, 1));
//		blank1.setFont(blank1.getFont().deriveFont(Font.BOLD, 1));
//		blank2.setFont(blank2.getFont().deriveFont(Font.BOLD, 1));
//		dropdown.add(blank);
//		dropdown.add(blank1);
//		dropdown.add(blank2);
		
//		dropdown.add(blank4);
//		dropdown.add(blank5);
//		dropdown.add(blank6);
//		dropdown.add(blank7);
		
		dropdown.add(changescreen);
		dropdown.add(newtask);
		
		
		windowpanel1.add(tasktypes, BorderLayout.CENTER);
		windowpanel1.add(dropdown, BorderLayout.NORTH);
		windowpanel1.add(enter, BorderLayout.SOUTH);
		windowpanel1.add(space, BorderLayout.EAST);
		
		
		window2.add(windowpanel1);
		
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setVisible(true);
		
		enterbutton.addActionListener((event) ->
		{
				//use the getText to get the text for the new task
				//System.out.println(listen1.getText());
				try {
					int duedate = Integer.valueOf(listen1.getDocument().getText(0, listen1.getDocument().getLength()));
					int diff = Integer.valueOf(listen2.getDocument().getText(0, listen2.getDocument().getLength()));
					int hourst = Integer.valueOf(listen3.getDocument().getText(0, listen3.getDocument().getLength()));
					String typet = listen4.getDocument().getText(0, listen4.getDocument().getLength());
					String namet = listen5.getDocument().getText(0, listen5.getDocument().getLength());
					//driver.addTask(namet, typet, duedate, hourst, false, diff);
					
					listen1.setText("");
					listen2.setText("");
					listen3.setText("");
					listen4.setText("");
					listen5.setText("");
					
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
		
		
		});
		
		backtomenu.addActionListener((event) ->
		{
				window2.setVisible(false);
				JFrame mainmenu = new JFrame();
				//starterup(mainmenu);
		});
		
		
	}
	
	private void initialize()
	{
		frame = new JFrame("Task Manager 2.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		frame.setSize(1200, 800);
		
		//unsorted code
		Color backCol = new Color(200, 210, 230);
		frame.setLayout(new CardLayout());
		
		//seet up contents of the window
		makeWelcomePanel(null);
		
		//finalization code
		frame.setVisible(true);
	}

	private void makeWelcomePanel(ActionEvent ae)
	{
		//initalization code
		frame.setVisible(false);
<<<<<<< HEAD
		frame.setContentPane(mainmenu);
		frame.setVisible(true);
	}
	
	private JPanel makeDropDownMenu()
	{
		String[] switchit = { "Main Menu", "New Task", "Edit Tasks"};
		JComboBox changescreen = new JComboBox(switchit);
		changescreen.setSelectedIndex(0);
		//changescreen.addActionListener(this);
		
		JPanel dropdown = new JPanel();
		dropdown.setLayout(new GridLayout(0,13));
=======
>>>>>>> c3f7f4c298ef8f0bda03138e4bf166efbe541e2c
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		
<<<<<<< HEAD
		changescreen.addActionListener((event) ->
		{
			JComboBox cd = (JComboBox)event.getSource();
			Object menu = cd.getSelectedItem();
			if(menu.equals("Main Menu")) {
				
				makeWelcomeText();
				
			} else if ( menu.equals("New Task")) {
				
				makeTaskCreationPanel(null);
			
			} else if ( menu.equals("Edit Tasks")) {
			
				makeExistingTasksPanel(null);
			
			}
	
	
	
	});
	
		
		return dropdown;
	}
	
	
	private JPanel makeWelcomeText()
	{
=======
>>>>>>> c3f7f4c298ef8f0bda03138e4bf166efbe541e2c
		//welcome text
		JPanel weltxt = new JPanel();
		
		JLabel welcome = new JLabel("Welcome to TaskManager 2.0!"
				+ "    What would you like to do today?");
		JLabel welcome2 = new JLabel("Create a new task, or view existing tasks?");
		welcome.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		welcome2.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		
		JPanel buttons = new JPanel();
		
		JButton newtask = new JButton("New Task");
		JButton existing = new JButton("Existing Tasks");
		
		newtask.setFont(newtask.getFont().deriveFont(Font.BOLD, 24));
		existing.setFont(existing.getFont().deriveFont(Font.BOLD, 24));
		
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		buttons.add(newtask);
		buttons.add(existing);
		
		weltxt.add(welcome);
		weltxt.add(welcome2);
		
		contentPane.add(buttons, BorderLayout.SOUTH);
		contentPane.add(weltxt, BorderLayout.CENTER);
		
		String[] switchit = { "Main Menu", "New Task", "Edit Tasks"};
		JComboBox changescreen = new JComboBox(switchit);
		changescreen.setSelectedIndex(0);
		//changescreen.addActionListener(this);
		
		JPanel dropdown = new JPanel();
		dropdown.setLayout(new GridLayout(0,8));
		
		JLabel blank = new JLabel("");
		JLabel blank1 = new JLabel("");
		JLabel blank2 = new JLabel("");
		JLabel blank3 = new JLabel("");
		JLabel blank4 = new JLabel("");
		JLabel blank5 = new JLabel("");
		JLabel blank6 = new JLabel("");
		JLabel blank7 = new JLabel("");
		
		dropdown.add(changescreen);
		dropdown.add(blank);
		dropdown.add(blank1);
		dropdown.add(blank2);
		dropdown.add(blank3);
		dropdown.add(blank4);
		dropdown.add(blank5);
		dropdown.add(blank6);
		dropdown.add(blank7);
		
		newtask.addActionListener(this::makeTaskCreationPanel);
		existing.addActionListener(this::makeExistingTasksPanel);
		
		
		//finalization code
		contentPane.add(dropdown, BorderLayout.NORTH);
		contentPane.add(weltxt, BorderLayout.CENTER);
		contentPane.add(buttons, BorderLayout.SOUTH);
		contentPane.validate();
		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
	
	private void makeTaskCreationPanel(ActionEvent ae) {
		
		//initialization code
		frame.setVisible(false);
		
		//content pane code
		
		Font newTaskFont = new Font("Times New Roman", Font.PLAIN, 32);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		JPanel tasktypes = new JPanel();
		tasktypes.setLayout(new GridLayout(6,2, 10, 40));
		tasktypes.setPreferredSize(new Dimension((int)10,10));
		
		//TASK FIELDS
		
		// name
		JLabel name = new JLabel("The Name of the Task: ", SwingConstants.RIGHT);
		JTextField listen5 = new JTextField("");
				
		name.setFont(newTaskFont);
		listen5.setFont(newTaskFont);
				
		tasktypes.add(name);
		tasktypes.add(listen5);
		
		// title
		JLabel newtask = new JLabel("<html>Please input the specified information into the designated boxes. 	<br/> 	 "
				+ "<br/>		<html>", SwingConstants.CENTER);
		newtask.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		
		// due date
		JLabel dueDate = new JLabel("The Date Due: ", SwingConstants.RIGHT);
		JTextField listen1 = new JTextField("");
		
		dueDate.setFont(newTaskFont);
		listen1.setFont(newTaskFont);
		
		tasktypes.add(dueDate);
		tasktypes.add(listen1);
		
		// difficulty
		JLabel difficulty = new JLabel("How You Would Rate It's Difficulty: ", SwingConstants.RIGHT);
		JTextField listen2 = new JTextField("");
		
		difficulty.setFont(newTaskFont);
		listen2.setFont(newTaskFont);


		
		tasktypes.add(difficulty);
		tasktypes.add(listen2);
		
		
		// hours
		JLabel hours = new JLabel("How Many Hours It Will Take To Finish: ", SwingConstants.RIGHT);
		JTextField listen3 = new JTextField("");
		
		hours.setFont(newTaskFont);
		listen3.setFont(newTaskFont);
		
		tasktypes.add(hours);
		tasktypes.add(listen3);
	
		
		// type
		JLabel type = new JLabel("The Type of Task: ", SwingConstants.RIGHT);
		JTextField listen4 = new JTextField("");
		
		type.setFont(newTaskFont);
		listen4.setFont(newTaskFont);
		
		tasktypes.add(type);
		tasktypes.add(listen4);
		
		
		// space for right side of window
		JLabel space = new JLabel("   				", SwingConstants.RIGHT);
		space.setFont(new Font("Times New Roman", Font.PLAIN, 90));
		
		
		// BUTTONS
		JPanel enter = new JPanel();
		
		JButton enterbutton = new JButton("Enter");
		JButton backtomenu = new JButton("Back");
		
		enter.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 5));
		
        enterbutton.setFont(enterbutton.getFont().deriveFont(Font.BOLD, 30));
		backtomenu.setFont(backtomenu.getFont().deriveFont(Font.BOLD, 30));
		
		enter.add(enterbutton);
		enter.add(backtomenu);
				
		
<<<<<<< HEAD
		String[] switchit = { "Main Menu", "New Task", "Edit Tasks"};
		JComboBox changescreen = new JComboBox(switchit);
		changescreen.setSelectedIndex(0);
		//changescreen.addActionListener(this);
		
		JPanel dropdown = new JPanel();
		dropdown.setLayout(new GridLayout(0,8));
				
		JLabel blank = new JLabel("		");
		JLabel blank1 = new JLabel("			");
		JLabel blank2 = new JLabel("				");
		JLabel blank3 = new JLabel("");
		JLabel blank4 = new JLabel("");
		JLabel blank5 = new JLabel("");
		JLabel blank6 = new JLabel("");
		JLabel blank7 = new JLabel("");
		
		dropdown.add(changescreen);
		dropdown.add(blank);
		dropdown.add(blank1);
		dropdown.add(blank2);
//		dropdown.add(blank3);
//		dropdown.add(blank4);
//		dropdown.add(blank5);
//		dropdown.add(blank6);
//		dropdown.add(blank7);
		
		// Added task message
				JLabel taskAdded = new JLabel("");
				dropdown.add(taskAdded);

=======
		
>>>>>>> master
		
		enterbutton.addActionListener((event) ->
		{
				//use the getText to get the text for the new task
				//System.out.println(listen1.getText());
				try {
					int duedate = Integer.valueOf(listen1.getDocument().getText(0, listen1.getDocument().getLength()));
					int diff = Integer.valueOf(listen2.getDocument().getText(0, listen2.getDocument().getLength()));
					int hourst = Integer.valueOf(listen3.getDocument().getText(0, listen3.getDocument().getLength()));
					String typet = listen4.getDocument().getText(0, listen4.getDocument().getLength());
					String namet = listen5.getDocument().getText(0, listen5.getDocument().getLength());
					driver.addTask(namet, typet, duedate, hourst, false, diff);
					taskAdded.setText("Task Added");
					taskAdded.setFont(new Font("Times New Roman", Font.PLAIN, 22));
					
					listen1.setText("");
					listen2.setText("");
					listen3.setText("");
					listen4.setText("");
					listen5.setText("");
					
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
		
		
		});
		
		backtomenu.addActionListener(this::makeWelcomePanel);
		
		
		//finalization code
		contentPane.add(tasktypes, BorderLayout.CENTER);
		contentPane.add(newtask, BorderLayout.NORTH);
		contentPane.add(enter, BorderLayout.SOUTH);
		contentPane.add(space, BorderLayout.EAST);
		contentPane.validate();
		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
	
	private void makeExistingTasksPanel(ActionEvent ae) {
		//initialization code
		frame.setVisible(false);
		
		//contentpane code
		JPanel contentpane = new JPanel();
		contentpane.setLayout(new BoxLayout(contentpane, BoxLayout.Y_AXIS));
		
		
		JPanel weltxt1 = new JPanel();
		
<<<<<<< HEAD
		
		JPanel dropdown = makeDropDownMenu();
		
		
=======
>>>>>>> c3f7f4c298ef8f0bda03138e4bf166efbe541e2c
		JLabel welcome1 = new JLabel("Here are the Existing Tasks. Please choose which one "
				+ "you wish to view by typing in the task name in the given text box.");
		
		welcome1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JPanel buttons1 = new JPanel();
		
		contentpane.setLayout(new GridLayout(5,2));
		
		buttons1.setLayout(new GridLayout(16, 2));
		buttons1.setPreferredSize(new Dimension((int)1000000,1000000));
		
		weltxt1.add(welcome1);
		

		String[] switchit = new String[100];
		int i = 0;
		for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext(); i++)
		{
			Task t = tasks.next();
			System.out.println(t.getName());
			switchit[i] = t.getName();
		}

		JComboBox changetask = new JComboBox(switchit);
		changetask.setSelectedIndex(-1);
		changetask.addActionListener((ActionEvent e) -> {
			Task chosentask = null;
			int j = 0;
			Object item = changetask.getSelectedItem();
			for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext(); j++)
			{
				Task t = tasks.next();
				if (t.getName() == item){
					System.out.println(item + " item");
					chosentask = t;
					break;
				}
			}

			//ENTER NEW PAGE SWITCH INFO HERE FOR EDITING TASKS 
			//pass the chosentask variable as the argument for the task to edit
		});
		JPanel tasks = new JPanel();
		
		tasks.setLayout(new BoxLayout(tasks, BoxLayout.Y_AXIS));
		
		tasks.add(changetask);
		
		contentpane.add(weltxt1);
		
		JPanel textme = new JPanel();
		
//		addATextField("", textme);

		JButton removeAllTasks = new JButton("Remove All Tasks");
		removeAllTasks.setFont(removeAllTasks.getFont().deriveFont(Font.BOLD, 24));
		
		
		textme.add(removeAllTasks);
		
		removeAllTasks.addActionListener( (ActionEvent e) -> {
			ListIterator<Task> tasklist = driver.getTasks();
			while(tasklist.hasNext())
			{
				Task t = tasklist.next();
				tasklist = driver.removeTask(t);
			}
			//TODO no, the next two lines are bad. 
			frame.setVisible(false);
			exist();
		});
		
		JPanel back = new JPanel();
		
		JButton backtomenu = new JButton("Back To Main Menu");
		backtomenu.setFont(backtomenu.getFont().deriveFont(Font.BOLD, 24));
		
		back.add(backtomenu);
		
		contentpane.add(back);
		
		contentpane.add(tasks);
		contentpane.add(textme);
		
		backtomenu.addActionListener(this::makeWelcomePanel);
		
		//finalization code
		contentpane.validate();
		frame.setContentPane(contentpane);
		frame.setVisible(true);
	}

	private static void edittasks(TaskMgrDriver driver2) {
		
		
		JFrame tester = new JFrame();
		
		
		Font newTaskFont = new Font("Times New Roman", Font.PLAIN, 32);
		
		//JPanel contentpane = (JPanel) frame.getContentPane();
		
		JPanel windowpanel1 = new JPanel();
		windowpanel1.setLayout(new BorderLayout());

		JPanel tasktypes = new JPanel();
		tasktypes.setLayout(new GridLayout(6,2, 10, 40));
		tasktypes.setPreferredSize(new Dimension((int)10,10));
		
		//TASK FIELDS
		
		// name
		JLabel name2 = new JLabel("The Name of the Task: ", SwingConstants.RIGHT);
		JTextField listen5 = new JTextField("The Name Of The Task");
				
		name2.setFont(newTaskFont);
		listen5.setFont(newTaskFont);
						
		tasktypes.add(name2);
		tasktypes.add(listen5);
				
		
		// title
		JLabel newtask = new JLabel("<html>Please change the information, that you wish to edit, in the appropriate boxes. 	<br/> 	 "
				+ "<br/>		<html>", SwingConstants.CENTER);
		newtask.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		
		// due date
		JLabel dueDate = new JLabel("The Date Due: ", SwingConstants.RIGHT);
		JTextField listen1 = new JTextField("The Current Date It Is Due");
		
		dueDate.setFont(newTaskFont);
		listen1.setFont(newTaskFont);
		
		tasktypes.add(dueDate);
		tasktypes.add(listen1);
		
		// difficulty
		JLabel difficulty = new JLabel("How You Would Rate It's Difficulty: ", SwingConstants.RIGHT);
		JTextField listen2 = new JTextField("The Current Difficulty Rating");
		
		difficulty.setFont(newTaskFont);
		listen2.setFont(newTaskFont);


		
		tasktypes.add(difficulty);
		tasktypes.add(listen2);
		
		
		// hours
		JLabel hours = new JLabel("How Many Hours It Will Take To Finish: ", SwingConstants.RIGHT);
		JTextField listen3 = new JTextField("The Current Amount of Hours It Will Take");
		
		hours.setFont(newTaskFont);
		listen3.setFont(newTaskFont);
		
		tasktypes.add(hours);
		tasktypes.add(listen3);
	
		
		// type
		JLabel type = new JLabel("The Type of Task: ", SwingConstants.RIGHT);
		JTextField listen4 = new JTextField("The Current Type of Task");
		
		type.setFont(newTaskFont);
		listen4.setFont(newTaskFont);
		
		tasktypes.add(type);
		tasktypes.add(listen4);
		
		JLabel space = new JLabel("   				", SwingConstants.RIGHT);
		space.setFont(new Font("Times New Roman", Font.PLAIN, 90));
		
		// BUTTONS
		JPanel enter = new JPanel();
		
		JButton enterbutton = new JButton("Enter");
		JButton backtomenu = new JButton("Back");
		
		enter.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 5));
		
        enterbutton.setFont(enterbutton.getFont().deriveFont(Font.BOLD, 30));
		backtomenu.setFont(backtomenu.getFont().deriveFont(Font.BOLD, 30));
		
		enter.add(enterbutton);
		enter.add(backtomenu);
		
		
		windowpanel1.add(tasktypes, BorderLayout.CENTER);
		windowpanel1.add(newtask, BorderLayout.NORTH);
		windowpanel1.add(enter, BorderLayout.SOUTH);
		windowpanel1.add(space, BorderLayout.EAST);
		
		tester.add(windowpanel1);
		
		tester.setVisible(true);
		tester.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		enterbutton.addActionListener((event) ->
		{
				
				try {
					int duedate = Integer.valueOf(listen1.getDocument().getText(0, listen1.getDocument().getLength()));
					int diff = Integer.valueOf(listen2.getDocument().getText(0, listen2.getDocument().getLength()));
					int hourst = Integer.valueOf(listen3.getDocument().getText(0, listen3.getDocument().getLength()));
					String typet = listen4.getDocument().getText(0, listen4.getDocument().getLength());
					String namet = listen5.getDocument().getText(0, listen5.getDocument().getLength());
					
					listen1.setText("");
					listen2.setText("");
					listen3.setText("");
					listen4.setText("");
					listen5.setText("");
					
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
		
		
		});
		
		
	}


	
		
	
}
