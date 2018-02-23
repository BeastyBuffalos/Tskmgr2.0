package model;
public class Weighting {
	static Task task;
	
	public static double alg(int difficulty, double duedate, double time, String tasktype, boolean isComplete){
		difficulty = task.getDifficulty();
		duedate = task.getDue();
		time = task.getHours();
		tasktype = task.getType();
		isComplete = task.getComplete();
		if (task.getComplete()){
			return 0;
		}

		//Temporary weighting, to be adjusted at a later date or possibly by users convienience
		return (difficulty * 2) + (10 / duedate) + (time * 2);
	}
}