

public class TaskListRefBased implements TaskList{

	// initalize a link list for tasks
	private TaskListNodeAlt head;
	// initialize the size of the link list is 0
	private int size = 0;
	public TaskListRefBased(){
		// initial the head is null
		head = null;

	}
	public TaskListNodeAlt getHead(){
		return head;
	}
	public int getLength(){
		return size;
	}
	public boolean isEmpty(){
		if(head == null) return true;
		else return false;
	}
	public Task removeHead(){

		// if the task list is empty, we return null
		if(head == null) return null;

		// get the head's task and poiter to the next element
		Task rtask = head.task;
		head = head.next;

		// decrease the size of the list tasks
		size --;

		// return the head's Task
		return rtask;
	}

	// remove a given task from list task
	public Task remove(Task t){

		// if the list tasks now is empty, return null
		if (head == null){
			
			return null;
		}

		// create two TaskListNodeAlt so that can can traversal 
		TaskListNodeAlt curr = head, prev = null;

		// traversal to find the task not match with given task
		while(curr != null){

			// check the task node if match with the given nodes
			Task currTask = curr.task;
			if(t.priority == currTask.priority && t.number == currTask.number)
			{
				// if they match, but in case at the head of link list
				if(prev == null){
					// just set the head to the next, remove the head
					head = head.next;
				}else{
					// otherwise, remove the node
					prev.next = curr.next;

				}
				// decrease the size of link list
				size --;
				// return the delete node
				return currTask;

			}
			else{
				// if nor match, we move on
				prev = curr;
				curr = curr.next;
			}
		}
		System.out.println("Echo: Remove Task(" + t.priority + " " + t.number +")");
		return null;
	}
	public void insert(Task t){
		// if the link list is empty, insert in the head
		if(head == null){
			head = new TaskListNodeAlt(t);
			//increase the size of link list
			System.out.println("ECHO insert(Task(" + t.priority + " " + t.number + "))");
			size ++;
			return; // done
		}

		// otherwise, we have to traversal to find appropriate possition
		TaskListNodeAlt curr = head, prev = null;
		while(curr != null){
			Task currTask = curr.task;
			// traversal instill we meet the node with priority is equal or greater
			// than the given task
			if(t.priority <= currTask.priority){
				prev = curr;
				curr = curr.next;
				continue;
			}
			// otherwise, we find the appropriate possition, then we break the loop
			break;
		}

		// now we can insert the task into the linklist
		// if the link task only has a node, head, and the priority of head is 
		// lower than the given task
		if(prev == null){
			// create a new node and assign head to it
			TaskListNodeAlt oldTask = head;
			//System.out.println("hello");
			// assign the new task to the head 
			head = new TaskListNodeAlt(t);
			//System.out.println("ECHO insert(Task(" + t.priority + " " + t.number + "))");
			// set the next note to the oldTask
			head.next = oldTask;
		}else{
			// otherwise, insert into the other possition
			// create a new node
			TaskListNodeAlt newnode = new TaskListNodeAlt(t);
			newnode.next = prev.next;
			prev.next = newnode;
		}
		// increase the size
		size ++;
		System.out.println("ECHO insert(Task(" + t.priority + " " + t.number + "))");
    	return;

	}
	public Task retrieve(int i){

		// traversal to find the appropriate task
		TaskListNodeAlt curr = head;
		for(;i >0; i--){
			curr = curr.next;
		}

		// now we find the task, return it
		return curr.task;

	}

	
}