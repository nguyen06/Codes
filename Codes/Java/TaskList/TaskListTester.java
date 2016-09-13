import java.io.*;
import java.util.*;
public class TaskListTester{
	public static void main(String[] args){
		/*
			test for insert several tasks 
		*/
		TaskListRefBased l = new TaskListRefBased();

		l.insert(new Task(10, 212));
		l.insert(new Task(12, 100));
		l.insert(new Task(10, 198));
		l.insert(new Task(3, 104));
		l.insert(new Task(4, 108));
		l.insert(new Task(15, 108));
		TaskListNodeAlt curr = l.getHead();
		//Task cur = curr.next;
		// while(curr.next != null){
		// 	System.out.println(curr.task.priority + " " +curr.task.number);
		// 	curr = curr.next;
		// }
		// System.out.println(curr.task.priority + " " + curr.task.number);

		// see the output 
		toFile(curr);

		// test to remove head
		l.removeHead();
		curr = l.getHead();
		toFile(curr);

		// test of remove Task(4,108)

		l.remove(new Task(4,108));
		curr = l.getHead();
		toFile(curr);

		// test of retreive a task
		Task a = l.retrieve(3);
		System.out.println(a.priority + " " + a.number);

	}
	public static void toFile(TaskListNodeAlt l){

		BufferedWriter output = null;
		try{
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out1.txt"),"utf-8"));
			if(l == null){
				output.write("[]");
			}else{
				//StringBuilder b = new StringBuilder("[(");
				TaskListNodeAlt curr = l;
				Task currTask = curr.task;
				output.write("(" + currTask.priority + "," + currTask.number + ")");
				//curr = curr.next;
				while(curr.next != null){
					curr = curr.next;
					currTask = curr.task;
					output.write("(" + currTask.priority + "," + currTask.number + ")");
				}
				// TaskListNodeAlt cur = curr.next;
				// Task c = cur.task;
				// output.newLine();
				// output.write("(" + c.priority + "," + c.number + ")");
				// while(cur.next != null){
				// 	System.out.println("not done");
				// }
				// //b.append(curr.task.priority).append(",").append(curr.task.number).append(")");
				//curr = curr.next;
				// while(curr.next != null){
				// 	output.write("(" + currTask.priority + "," + currTask.number + ")");
				// }
				
				
				
			}
		}
		catch (IOException ex) {
            // report
         } finally {
            	try {output.close();} catch (Exception ex) {/*ignore*/}
         }

	}
}