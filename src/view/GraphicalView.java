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
	
	private JPanel mainmenu = new JPanel();
	{
		mainmenu.setLayout(new BorderLayout());
		//finalization code
		mainmenu.add(makeDropDownMenu(), BorderLayout.NORTH);
		mainmenu.add(makeWelcomeText(), BorderLayout.CENTER);
		mainmenu.add(makeMainButtons(), BorderLayout.SOUTH);
		mainmenu.validate();
	}
	
	private JPanel newtasks = makeTaskCreation();
	
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
		welcomePane(null);
		
		//finalization code
		frame.setVisible(true);
	}

	private void welcomePane(ActionEvent ae)
	{
		//TODO
		//initalization code
		frame.setVisible(false);
		frame.setContentPane(mainmenu);
		frame.setVisible(true);
	}
	
	private void taskCreationPane(ActionEvent ae) 
	{
		//initialization code
		frame.setVisible(false);
		frame.setContentPane(newtasks);
		frame.setVisible(true);
	}

	private JPanel makeDropDownMenu()
	{
		//dropdown menu code
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
		return dropdown;
	}
	
	private JPanel makeWelcomeText()
	{
		//welcome text
		JPanel weltxt = new JPanel();
		
		JLabel welcome = new JLabel("Welcome to TaskManager 2.0!"
				+ "    What would you like to do today?");
		JLabel welcome2 = new JLabel("Create a new task, or view existing tasks?");
		welcome.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		welcome2.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		weltxt.add(welcome);
		weltxt.add(welcome2);
		return weltxt;
	}
	
	private JPanel makeMainButtons()
	{
		//buttons code
		JPanel buttons = new JPanel();
		
		JButton newtask = new JButton("New Task");
		JButton existing = new JButton("Existing Tasks");
		
		newtask.setFont(newtask.getFont().deriveFont(Font.BOLD, 24));
		existing.setFont(existing.getFont().deriveFont(Font.BOLD, 24));
		
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		buttons.add(newtask);
		buttons.add(existing);
		newtask.addActionListener(this::taskCreationPane);
		existing.addActionListener(this::makeExistingTasksPanel);
		return buttons;
	}
	
	private JPanel makeTaskCreation()
	{
		//content pane code
		JPanel newtaskPanel = new JPanel();
		newtaskPanel.setLayout(new BorderLayout());
		
		// title
		JLabel newtask = new JLabel("<html>Please input the specified information into the designated boxes. 	<br/> 	 "
				+ "<br/>		<html>", SwingConstants.CENTER);
		newtask.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		
		//TASK FIELDS
		JPanel tasktypes = new JPanel();
		tasktypes.setLayout(new GridLayout(6,2, 10, 40));
		tasktypes.setPreferredSize(new Dimension((int)10,10));
		
		// name
		JLabel name = new JLabel("The Name of the Task: ", SwingConstants.RIGHT);
		JTextField nameField = new JTextField("");
				
		Font newTaskFont = new Font("Times New Roman", Font.PLAIN, 32);
		name.setFont(newTaskFont);
		nameField.setFont(newTaskFont);
				
		tasktypes.add(name);
		tasktypes.add(nameField);		
		
		// due date
		JLabel dueDate = new JLabel("The Date Due: ", SwingConstants.RIGHT);
		JTextField dueField = new JTextField("");
		
		dueDate.setFont(newTaskFont);
		dueField.setFont(newTaskFont);
		
		tasktypes.add(dueDate);
		tasktypes.add(dueField);
		
		// difficulty
		JLabel difficulty = new JLabel("How You Would Rate It's Difficulty: ", SwingConstants.RIGHT);
		JTextField diffField = new JTextField("");
		
		difficulty.setFont(newTaskFont);
		diffField.setFont(newTaskFont);	
		
		tasktypes.add(difficulty);
		tasktypes.add(diffField);
		
		
		// hours
		JLabel hours = new JLabel("How Many Hours It Will Take To Finish: ", SwingConstants.RIGHT);
		JTextField hoursField = new JTextField("");
		
		hours.setFont(newTaskFont);
		hoursField.setFont(newTaskFont);
		
		tasktypes.add(hours);
		tasktypes.add(hoursField);
	
		
		// type
		JLabel type = new JLabel("The Type of Task: ", SwingConstants.RIGHT);
		JTextField typeField = new JTextField("");
		
		type.setFont(newTaskFont);
		typeField.setFont(newTaskFont);
		
		tasktypes.add(type);
		tasktypes.add(typeField);
		
		
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
		
		
		
		
		enterbutton.addActionListener((event) ->
		{
				//use the getText to get the text for the new task
				//TODO make this smoother somehow? fix issues with allowed input types
				try {
					int duedate = Integer.valueOf(dueField.getDocument().getText(0, dueField.getDocument().getLength()));
					int diff = Integer.valueOf(diffField.getDocument().getText(0, diffField.getDocument().getLength()));
					int hourst = Integer.valueOf(hoursField.getDocument().getText(0, hoursField.getDocument().getLength()));
					String typet = typeField.getDocument().getText(0, typeField.getDocument().getLength());
					String namet = nameField.getDocument().getText(0, nameField.getDocument().getLength());
					driver.addTask(namet, typet, duedate, hourst, false, diff);
					
					dueField.setText("");
					diffField.setText("");
					hoursField.setText("");
					typeField.setText("");
					nameField.setText("");
					
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
		
		
		});
		
		backtomenu.addActionListener(this::welcomePane);
		
		
		//finalization code
		newtaskPanel.add(tasktypes, BorderLayout.CENTER);
		newtaskPanel.add(newtask, BorderLayout.NORTH);
		newtaskPanel.add(enter, BorderLayout.SOUTH);
		newtaskPanel.add(space, BorderLayout.EAST);
		newtaskPanel.validate();
		return newtaskPanel;
	}
	
	private void makeExistingTasksPanel(ActionEvent ae) {
			//initialization code
			frame.setVisible(false);
			
			//contentpane code
			JPanel contentpane = new JPanel();
			contentpane.setLayout(new BoxLayout(contentpane, BoxLayout.Y_AXIS));
			
			
			JPanel weltxt1 = new JPanel();
			
			JLabel welcome1 = new JLabel("Here are the Existing Tasks. Please choose which one "
					+ "you wish to view by typing in the task name in the given text box.");
			
			welcome1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
			
			JPanel buttons1 = new JPanel();
			
			contentpane.setLayout(new GridLayout(5,2));
			
			buttons1.setLayout(new GridLayout(16, 2));
			buttons1.setPreferredSize(new Dimension((int)1000000,1000000));
			
			weltxt1.add(welcome1);
			
			//TODO update task list without recreating this panel
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
				//TODO no, the next line is bad
				makeExistingTasksPanel(null);
			});
			
			JPanel back = new JPanel();
			
			JButton backtomenu = new JButton("Back To Main Menu");
			backtomenu.setFont(backtomenu.getFont().deriveFont(Font.BOLD, 24));
			
			back.add(backtomenu);
			
			contentpane.add(back);
			
			contentpane.add(tasks);
			contentpane.add(textme);
			
			backtomenu.addActionListener(this::welcomePane);
			
			//finalization code
			contentpane.validate();
			frame.setContentPane(contentpane);
			frame.setVisible(true);
		}

	private void edittasks(TaskMgrDriver driver2) {
		
		
		JFrame tester = new JFrame();
		
		//for( int i = 0; i < driver2.getTasks().)
		
//		String name = new String("Task Name");
//		
//		JLabel name1 = new JLabel(name);
		
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
		//contentpane.add(windowpanel1);
		
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
					
				//	driver.editTask(driver, name, typet, duedate, hourst, false, diff);
					
					listen1.setText("");
					listen2.setText("");
					listen3.setText("");
					listen4.setText("");
					listen5.setText("");
					
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
		
		
		});
		
//		backtomenu.addActionListener((event) ->
//		{
//				contentpane.setVisible(false);
//				JFrame mainmenu = new JFrame();
//				starterup(mainmenu);
//		});
		
		
	}
		
	
}
