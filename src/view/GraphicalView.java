package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.TaskMgrDriver;
import java.util.ListIterator;
import model.Task;
import model.TaskList;

public class GraphicalView {

	private TaskMgrDriver driver;	
	private JFrame frame;

	private JPanel mainmenu = new JPanel();
	{
		mainmenu.setLayout(new BorderLayout());
		//finalization code
		mainmenu.add(makeWelcomeText(), BorderLayout.CENTER);
		mainmenu.add(makeMainButtons(), BorderLayout.SOUTH);
		mainmenu.validate();
	}

	private JPanel newtasks = makeTaskCreation();

	public GraphicalView(TaskMgrDriver driver) {
		this.driver = driver;
		initialize();
	}


	private void initialize()
	{
		frame = new JFrame("Task Manager 2.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		frame.setSize(1600, 900);

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



	private JPanel makeWelcomeText()
	{
		//welcome text
		JPanel weltxt = new JPanel();
		weltxt.setLayout(new BoxLayout(weltxt, BoxLayout.Y_AXIS));

		JLabel welcome = new JLabel("TaskManager 2.0", SwingConstants.CENTER);
		JLabel welcome3 = new JLabel("Would you like to create a new task, or view existing tasks?", SwingConstants.CENTER);
		JLabel men = new JLabel("2.0", SwingConstants.RIGHT);
		JLabel spaces = new JLabel(" 		");

		welcome.setFont(new Font("Times New Roman", Font.BOLD, 42));
		welcome3.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		spaces.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		men.setFont(new Font("Times New Roman", Font.PLAIN, 400));

		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		men.setAlignmentX(Component.CENTER_ALIGNMENT);
		spaces.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcome3.setAlignmentX(Component.CENTER_ALIGNMENT);

		men.setForeground(new Color(10, 10, 10, 20));

		weltxt.add(welcome);
		weltxt.add(spaces);
		weltxt.add(welcome3);
		weltxt.add(men);
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

		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

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

		//TASK FIELDS
		JPanel tasktypes = new JPanel();
		tasktypes.setLayout(new GridLayout(6,2, 1, 60));
		tasktypes.setPreferredSize(new Dimension((int)10,10));

		// title
		JLabel newtask = new JLabel("<html>Please input the specified inf"
				+ "<html>", SwingConstants.RIGHT);
		newtask.setFont(new Font("Times New Roman", Font.BOLD, 33));

		tasktypes.add(newtask);

		JLabel newtask2 = new JLabel("<html>ormation into the designated boxes. "
				+ "<html>", SwingConstants.LEFT);
		newtask2.setFont(new Font("Times New Roman", Font.BOLD, 33));


		tasktypes.add(newtask2);

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

		enter.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 20));

		enterbutton.setFont(enterbutton.getFont().deriveFont(Font.BOLD, 30));
		backtomenu.setFont(backtomenu.getFont().deriveFont(Font.BOLD, 30));

		enter.add(enterbutton);
		enter.add(backtomenu);		

		JPanel dropdown = new JPanel();
		dropdown.setLayout(new FlowLayout());
		
		JLabel taskAdded = new JLabel(" ");
		taskAdded.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		dropdown.add(taskAdded);


		
		enterbutton.addActionListener((event) ->
		{
			//use the getText to get the text for the new task
			try {
				int duedate = Integer.valueOf(dueField.getDocument().getText(0, dueField.getDocument().getLength()));
				int diff = Integer.valueOf(diffField.getDocument().getText(0, diffField.getDocument().getLength()));
				int hourst = Integer.valueOf(hoursField.getDocument().getText(0, hoursField.getDocument().getLength()));
				String typet = typeField.getDocument().getText(0, typeField.getDocument().getLength());
				String namet = nameField.getDocument().getText(0, nameField.getDocument().getLength());
				int i = 0;
				boolean isOriginal = true;
				for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext(); i++){
					Task t = tasks.next();
					if (namet.equals(t.getName()))
						isOriginal = false;
					taskAdded.setText("Error. Name already exists");
					nameField.setText("");
				}
				if (isOriginal){
					driver.addTask(namet, typet, duedate, hourst, false, diff);
					taskAdded.setText(namet + " added");
					dueField.setText("");
					diffField.setText("");
					hoursField.setText("");
					typeField.setText("");
					nameField.setText("");
					
				}

				
				
				int delay = 3000;
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						taskAdded.setText(" ");
					}
				};
				Timer time = new Timer(delay, taskPerformer);
				time.start();
				

			} catch (BadLocationException e) {
				e.printStackTrace();
			}


		});

		backtomenu.addActionListener(this::welcomePane);


		//finalization code
		newtaskPanel.add(tasktypes, BorderLayout.CENTER);
		newtaskPanel.add(dropdown, BorderLayout.NORTH);
		newtaskPanel.add(enter, BorderLayout.SOUTH);
		newtaskPanel.add(space, BorderLayout.EAST);
		newtaskPanel.validate();
		return newtaskPanel;
	}

	private JComboBox renderDrop(int currentTask){
		String[] switchit = new String[100];
		int i = 0;
		for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext(); i++)
		{
			Task t = tasks.next();
			switchit[i] = t.getName();
		}
		JComboBox changetask = new JComboBox(switchit);
		changetask.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		((JLabel)changetask.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		changetask.setSelectedIndex(currentTask);
		return changetask;
	}
	private void makeExistingTasksPanel(ActionEvent ae) {

		//initialization code
		frame.setVisible(false);

		//contentpane code
		JPanel contentpane = new JPanel();
		contentpane.setLayout(new BorderLayout());

		//TASK FIELDS
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension((int)10,10));
		
		JPanel fields = new JPanel();
		fields.setLayout(new GridLayout(5,2, 20, 40));
		
		// name
		JLabel name = new JLabel("The Name of the Task: ", SwingConstants.RIGHT);
		JTextField nameField = new JTextField("", 50);
		nameField.setMaximumSize(nameField.getPreferredSize());

		Font newTaskFont = new Font("Times New Roman", Font.PLAIN, 32);
		name.setFont(newTaskFont);
		nameField.setFont(newTaskFont);
		
		fields.add(name);
		fields.add(nameField);
		

		// due date	
		JLabel dueDate = new JLabel("The Date Due: ", SwingConstants.RIGHT);
		JTextField dueField = new JTextField("", 50);
		dueField.setMaximumSize(dueField.getPreferredSize());

		dueDate.setFont(newTaskFont);
		dueField.setFont(newTaskFont);

		fields.add(dueDate);
		fields.add(dueField);

		// difficulty
		JLabel difficulty = new JLabel("How You Would Rate It's Difficulty: ", SwingConstants.RIGHT);
		JTextField diffField = new JTextField("", 50);
		diffField.setMaximumSize(diffField.getPreferredSize());

		difficulty.setFont(newTaskFont);
		diffField.setFont(newTaskFont);	
		
		fields.add(difficulty);
		fields.add(diffField);


		// hours
		JLabel hours = new JLabel("How Many Hours It Will Take To Finish: ", SwingConstants.RIGHT);
		JTextField hoursField = new JTextField("", 50);
		hoursField.setMaximumSize(hoursField.getPreferredSize());

		hours.setFont(newTaskFont);
		hoursField.setFont(newTaskFont);
		
		fields.add(hours);
		fields.add(hoursField);


		// type
		JLabel type = new JLabel("The Type of Task: ", SwingConstants.RIGHT);
		JTextField typeField = new JTextField("", 50);
		typeField.setMaximumSize(typeField.getPreferredSize());

		type.setFont(newTaskFont);
		typeField.setFont(newTaskFont);
		
		fields.add(type);
		fields.add(typeField);


		// space for right side of window
		JLabel space = new JLabel("   				", SwingConstants.RIGHT);
		space.setFont(new Font("Times New Roman", Font.PLAIN, 90));

		JCheckBox movepos = new JCheckBox("Manually Assign The Position For This Task?");

		JTextField typewhere = new JTextField("", 5);
		
		
		
		JButton delete = new JButton("Delete Task");
		delete.setFont(delete.getFont().deriveFont(Font.BOLD, 24));
		
		typewhere.setEnabled(false);

		JComboBox changetask = renderDrop(-1);
		changetask.addActionListener((ActionEvent e) -> {
			Task chosentask = null;
			int j = 0;
			Object item = changetask.getSelectedItem();
			for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext(); j++)
			{
				Task t = tasks.next();
				if (t.getName() == item){
					chosentask = t;
					diffField.setText(Integer.toString(chosentask.getRealDiff()));
					nameField.setText(chosentask.getName());
					hoursField.setText(Integer.toString(chosentask.getRealHrs()));
					dueField.setText(Integer.toString(chosentask.getRealDue()));
					typeField.setText(chosentask.getType());
					if (chosentask.isOverride()){
						
						movepos.setSelected(true);
						typewhere.setEnabled(true);
					}
					break;
				}
			}
		});

		panel.add(changetask);


		movepos.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		typewhere.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		typewhere.setMaximumSize(typewhere.getPreferredSize());
		typewhere.setEnabled(false);

		
		movepos.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel move = new JPanel();
		move.setLayout(new BoxLayout(move, BoxLayout.X_AXIS));
		
		JLabel spacer = new JLabel("spac");
		spacer.setFont(newTaskFont);
		spacer.setForeground(new Color(1, 1, 1, 0));

		
		move.add(spacer);
		move.add(movepos);
		move.add(typewhere);
		
		JPanel move2 = new JPanel();
		move2.setLayout(new BorderLayout());
		move2.add(move, BorderLayout.WEST);
		
				
		
		movepos.addActionListener((ActionEvent e) -> {
			
			if( movepos.isSelected()) {
				typewhere.setEnabled(true);
			
			} else {
				typewhere.setEnabled(false);
				
			}
		
		});
		
		delete.addActionListener((e) -> {
				Task chosentask = null;
				Object item = changetask.getSelectedItem();
				int j = 0;
				for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext(); j++){
					Task t = tasks.next();
					if (t.getName() == item){
						chosentask = t;
						break;
					}
				}
				driver.removeTask(chosentask);
				
				nameField.setText("");
				dueField.setText("");
				hoursField.setText("");
				diffField.setText("");
				typeField.setText("");
				makeExistingTasksPanel(null);
		});
		
		JButton enterbutton = new JButton("Enter");
		enterbutton.addActionListener((ActionEvent e) -> {
			try {
				Task chosentask = null;
				Object item = changetask.getSelectedItem();
				int j = 0;
				for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext(); j++){
					Task t = tasks.next();
					if (t.getName() == item){
						chosentask = t;
						break;
					}
				}
				int duedate = Integer.valueOf(dueField.getDocument().getText(0, dueField.getDocument().getLength()));
				int diff = Integer.valueOf(diffField.getDocument().getText(0, diffField.getDocument().getLength()));
				int hourst = Integer.valueOf(hoursField.getDocument().getText(0, hoursField.getDocument().getLength()));
				String typet = typeField.getDocument().getText(0, typeField.getDocument().getLength());
				String namet = nameField.getDocument().getText(0, nameField.getDocument().getLength());
				if(movepos.isSelected() && !typewhere.getText().equals("")) {
					chosentask.setOverride(true);
					int wheres = Integer.parseInt(typewhere.getDocument().getText(0, typewhere.getDocument().getLength()));
					driver.overrideTask(wheres, chosentask);
				}
				driver.editTask(chosentask, namet, typet, duedate, hourst, false, diff);
/*
				if( delete.isSelected() ) {
					driver.deleteTask(chosentask);
					for(ListIterator<Task> tasks = driver.getTasks(); tasks.hasNext(); j++){
						Task t = tasks.next();
						System.out.println(t.getName());
						}
				}
					*/
				makeExistingTasksPanel(null);
			} catch (BadLocationException f) {
				f.printStackTrace();
			}

		});
		JPanel change = new JPanel();
		change.setLayout(new BoxLayout(change, BoxLayout.X_AXIS));
		JLabel spaces = new JLabel("spacer				", SwingConstants.RIGHT);
		spaces.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		spaces.setForeground(new Color(1, 1, 1, 0));
		
		change.add(spaces);
		change.add(changetask);
		
		JPanel but = new JPanel();
		but.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 20));
		enterbutton.setFont(enterbutton.getFont().deriveFont(Font.BOLD, 24));
		but.add(enterbutton);
		
		Font spaceFont = new Font("Times New Roman", Font.PLAIN, 40);
		
		JLabel space1 = new JLabel("   ");
		JLabel space7 = new JLabel("   ");
		JLabel space8 = new JLabel("   ");
		JLabel space9 = new JLabel("   ");
		
		space1.setFont(spaceFont);
		space7.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		space8.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		space9.setFont(spaceFont);


		JLabel tasklistlist = new JLabel("Task List");
		tasklistlist.setFont(new Font("Times New Roman", Font.BOLD, 30));

		
		panel.add(space8);
		panel.add(tasklistlist);
		panel.add(space7);
		panel.add(change);
		panel.add(space9);
		panel.add(space1);		
		panel.add(fields);
		panel.add(move2);
		

		JPanel textme = new JPanel();
		textme.setLayout(new BorderLayout());
		

		JButton removeAllTasks = new JButton("Remove All Tasks");
		removeAllTasks.setFont(removeAllTasks.getFont().deriveFont(Font.BOLD, 24));


		textme.add(removeAllTasks, BorderLayout.CENTER);

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
		
		
		but.add(makeBackButton());
		but.add(textme);
		but.add(delete);
		but.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(but);
		

		contentpane.add(panel, BorderLayout.CENTER);
		contentpane.add(space, BorderLayout.EAST);

		//finalization code
		contentpane.validate();
		frame.setContentPane(contentpane);
		frame.setVisible(true);
		
	
	}

	private JPanel makeBackButton()
	{
		JPanel back = new JPanel();

		JButton backtomenu = new JButton("Back To Main Menu");
		backtomenu.setFont(backtomenu.getFont().deriveFont(Font.BOLD, 24));
		backtomenu.addActionListener(this::welcomePane);
		back.add(backtomenu);
		return back;
	}

}