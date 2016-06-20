import javax.swing.JOptionPane;

     
    public class WaitingQueue<E>
{
       
        private Node first; // the first object on the linked list
        private Node last; // the last object on the linked list
        
        /**
         * This method constructs a LinkedListQueue object
         */
	public WaitingQueue(){first = last = null ;}
	
	/**
	 * This method checks if the queue is empty.
	 * @return true if the queue is empty, false if not empty
	 */
	public boolean isEmpty(){return first == null ;}
	
	/**
	 * Add an object to the end of the queue.
     * @param anAppt
	 */
	public void enqueue(Appointment anAppt)
	{
		Node temp = new Node() ;// create new node
                temp.info = anAppt;// new node gets the value of anAppt.
		if ( isEmpty() ){first = temp;}// if empty this is the first Node.
		else{last.next = temp ;}// just keep adding them. 
                last = temp;
	}

	/**
	 * Remove the object at the front of the queue and return it
	 * @return the object at the front of the queue
	 **/
	public Appointment dequeue()
	{
		Appointment add = first.info ;// add object at the front of queue
		first = first.next ;// remove it from queue
                if(isEmpty()){ last = null;}
		return add ;		
	}
	
        /**
         * This method checks if an appointment is available for someone in the waiting list to snatch one at a time.
         * @param list  The Linked List to be traverse.
         */
        public void fillAvailableAppointment(LinkedList list){
            
       ListIterator myIterator = list.listIterator();
       
            if ( isEmpty()){JOptionPane.showMessageDialog(null, "There are no appointments in the waiting list");}
            else
            {
                Appointment appt = dequeue();

                // Attempt to make an appt.
                boolean removeFromQueue = myIterator.makeAppointment(appt);

                // If make appt returns false, put patient in back of the queue.
                if(!removeFromQueue)
                {
                    enqueue(appt);
                    String message = "The desired time slot for " + appt.toString() + " is NOT available.\n" + appt.getFirstName() + " " + appt.getLastName() + " will be added to the end of the waiting list.";
                    JOptionPane.showMessageDialog(null,message);
                }
                else{
                    String message = "The desired time slot for " + appt.toString() + " is available.\n" + appt.getFirstName() + " " + appt.getLastName() + " will be added to the Appointment list.";
                    JOptionPane.showMessageDialog(null,message);
                }
            }
        }
        /**
         * This method returns all the objects in the queue as Strings.
         * @return Returns all the objects in the queue as strings.
         */
        public String toString()
	{
		if ( isEmpty()){return "queue is empty!" ;}
                
		String print = "" ;
                Node temp = first;
		
		while (temp != null)// while there is more objects in the queue
		{
                    Appointment appointment = temp.info;
                    
                    String lastName = appointment.getLastName();// get the last name of the patient

                    String firstName = appointment.getFirstName();// get the first name of the patient

                    int year = appointment.getYear(); // get the year of the appointment

                    int month = appointment.getMonth();// the month..

                    int day = appointment.getDay();// the day..

                    int time = appointment.getTime();// the time in military..

                    String conc = firstName + " " + lastName + " "  + " " + year + " " + month + " " + day + " at " + time; // make string for all of it..
                    
                    print = conc + "\n" + print;
                    
                    temp = temp.next ;// next
		}
		return print ;
	}
        
    class Node
    {
       Appointment info ;
       Node next ;
       
       
    }  
}
