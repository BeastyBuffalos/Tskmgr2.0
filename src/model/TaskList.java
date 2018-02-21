package model;

public class TaskList {

	
	public TaskList() {
		//TODO
	}
	
	

	private class TaskWrapper
	{
		private Task t;
		private boolean moved; 
		public TaskWrapper(Task t)
		{
			this.t = t;
		}
		
		public Task get() { return t; }
		
		public void setMoved(boolean moved) { this.moved = moved; }
	}
}
