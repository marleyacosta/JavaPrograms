import java.util.NoSuchElementException;
import javax.swing.JOptionPane;

 /**
 A linked list is a sequence of nodes with efficient
 element insertion and removal. This class
 contains a subset of the methods of the standard
 java.util.LinkedList class.
 */
 public class LinkedList{
     
    private Node first;// first Node
  

    /**
    Constructs an empty linked list.
    */
    public LinkedList(){first = null;}
    
    public int size(){
        int count = 0;
        Node temp = first;
        while(temp != null){
        count++;
        temp = temp.next;
        }
      return count;
    }
    /**
    Returns the first element in the linked list.
    @return the first element in the linked list
    */
    public Object getFirst()
    {
       if (first == null) { 
           throw new NoSuchElementException(); }
       return first.data;
    }

    /**
    Removes the first element in the linked list.
    @return the removed element
    */
    public Appointment removeFirst()
    {
       if (first == null) { throw new NoSuchElementException(); }
       Appointment element = first.data;

       first = first.next;
       return element;
    }
 

    /**
    Adds an element to the front of the linked list.
    @param element the element to add
    */
    public void addFirst(Appointment element)
    {
       Node newAppointment = new Node();
       newAppointment.data = element;
       newAppointment.next = first;
       first = newAppointment;
    }

    /**
    Returns an iterator for iterating through this list.
    @return an iterator for iterating through this list
    */
    public ListIterator listIterator(){
        
        return new LinkedListIterator();
    }

 class Node
 {
    public Appointment data;
    public Node next;
 }
//******************************************************************************
    class LinkedListIterator implements ListIterator
    {
       private Node position;
       private Node previous;
       private boolean isAfterNext;
       WaitingQueue waitingList; // waiting list.
      

    /**
     Constructs an iterator that points to the front
    of the linked list.
    */
       public LinkedListIterator()
       {
          waitingList = new WaitingQueue();
          position = null;
          previous = null;
          isAfterNext = false;
       }
       
       
    /** Moves the iterator past the next element.
    @return the traversed element
    */
       public Appointment next()
       {
           if (!hasNext()) {throw new NoSuchElementException();} 
           previous = position; // Remember for remove
           isAfterNext = true;

           if (position == null){position = first;}
           else{position = position.next;}

           return position.data;
       }

    /**
    Tests if there is an element after the iterator position.
    @return true if there is an element after the iterator position
    */
       public boolean hasNext(){
           if (position == null){return first != null;}
           else{return position.next != null;}
           }

    /**
    Adds an element before the iterator position
    and moves the iterator past the inserted element.
    @param element the element to add
    */
       public void add(Appointment anAppointment)
       {
           if (position == null)
               {
                   addFirst(anAppointment);
                   position = first;
               }
           else
               {
                   Node newNode = new Node();
                   newNode.data = anAppointment;
                   newNode.next = position.next;
                   position.next = newNode;
                   position = newNode;
               }

           isAfterNext = false;
       }
       /**
        * Method which receives an Appointment object, and traverses through the 
        * linkedList starting from the beginning, using the LinkedListIterator.  
        * @param anAppt The Appointment
        * @return It will return the Appointment object if it was found, or null otherwise.
        */
       @Override
       public Appointment find(Appointment polo){
           LinkedListIterator marco = new LinkedListIterator();
          
            while(marco.hasNext()){// while there are more Appointments
                
                if(marco.next().equals(polo)){// if the Appointment is equal
                    return polo;}}// return the Appointment because we found it.
           return null;// Not found
       }
       /**
        * which uses the find(Apointment element) method to find the appointment object, 
        * remove it, and then adds a new one at the new date and time. 
        * A boolean will be returned from replaceAppointment,
        * true if it was successful and false if it was not and the new appointment needs to be added to the waiting queue.  
        * Note:  replaceAppointment calls find(), remove(), and makeAppointment() methods.
        * @param old The original Appointment
        * @param current The new Appointment
        * @return true if the appointment was replaced.
        */
        public boolean replaceAppointment(Appointment old, Appointment current){
            
            removeAppointment(old);// remove the old Appointment.
            return makeAppointment(current); // create the new Appointment   
        }
    /**
    Removes the last traversed element. This method may
    only be called after a call to the next method.
    */
       @Override
       public void remove()
       {
           if (!isAfterNext) { throw new IllegalStateException(); }

           if (position == first){removeFirst();}
           else{previous.next = position.next;}
           position = previous;
           isAfterNext = false;
       }

    /**
    Sets the last traversed element to a different value.
    @param element the element to set
   172 */
       public void set(Object element)
       {
           if (!isAfterNext) { throw new IllegalStateException(); }
           position.data =  (Appointment) element;
       }

       @Override//************needs work*******************
        public boolean makeAppointment(Appointment appt) {
 
           boolean duplicate = false; // Checks if duplicate
            boolean added = false; // Checks if appt is added
            Node locate = null; // temp Node
           
                // If there is nothing in the list
                if (first == null){addFirst(appt);added = true;} 
                
                // Traverse through the list. If the times are equal,
                // as well with the year, month, and day.
                else 
                {
                    position = null;
                    while (hasNext()) 
                    {
                        next();
                        if (appt.getYear() > position.data.getYear()){locate = position;} 
                        else if (appt.getYear() < position.data.getYear() && position == first) 
                        {
                            // Add Appointment if it's the first in the list and the
                            // list is not empty
                            addFirst(appt);
                            duplicate = true;
                            added = true;
                            break;
                        } 
                        else if (appt.getYear() < position.data.getYear()){locate = previous;} 
                        else if (appt.getYear() == position.data.getYear()) 
                        {
                            if (appt.getMonth() > position.data.getMonth()){locate = position;} 
                            else if (appt.getMonth() < position.data.getMonth() && position == first) 
                            {
                                addFirst(appt);
                                duplicate = true;
                                added = true;
                                break;
                            } 
                            else if (appt.getMonth() < position.data.getMonth()) {locate = previous;} 
                            else if (appt.getMonth() == position.data.getMonth()) 
                            {
                                if (appt.getDay() > position.data.getDay()){locate = position;} 
                                else if (appt.getDay() < position.data.getDay() && position == first) 
                                {
                                    addFirst(appt);
                                    duplicate = true;
                                    added = true;
                                    break;
                                } 
                                else if (appt.getDay() < position.data.getDay()){locate = previous;} 
                                else if (appt.getDay() == position.data.getDay()) 
                                {
                                    if (appt.getTime() > position.data.getTime()){locate = position;} 
                                    else if (appt.getTime() < position.data.getTime() && position == first) 
                                    {
                                        addFirst(appt);
                                        duplicate = true;
                                        added = true;
                                        break;
                                    } 
                                    else if (appt.getTime() < position.data.getTime()){locate = previous;} 
                                    else if (appt.getTime() == position.data.getTime()) 
                                    {
                                        duplicate = true;      
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    // if duplicate is true, add element to waiting list.
                   if (!duplicate){added = true;position = locate;add(appt);}
                }
            return added;
   }
    
        /**
         * which will ensure the appointment time is either 9am, 10am, 11am, 1pm, 2pm, or 3pm - in military time.  
         * If the validateAppointment(Appointment element) does not return true, 
         * then do not proceed with making an appointment.  
         * Instead, send an error message to the user stating the times that are possible.  
         * @param element 
         */
       @Override
        public boolean validateAppointment(Appointment anAppt){
            
            double time  = anAppt.getTime();
            
            if( time == 9 || time == 10 || time == 11 || time == 13 || time == 14 || time == 15){return true;}
            else{return false;}
        }
        
        /**
         * This method removes an appointment from the linked list.
         * @param anAppt an Appointment object.
         * @return true if the appointment was removed.
         */
       @Override
        public void removeAppointment(Appointment anAppt){
            
                position = null;
                //Traverse thru list until the Appointment is found and the remove.
                while (hasNext()){next();if (position.data.equals(anAppt)){remove();break;}}
            }

        /**
         * traverses through the linked list, 
         * and creates a String of all the appointment times with last name and
         * first name all concatenated together, with a "\n" after each appoointment.
         * @return 
         */
       @Override
        public String toString(){
            
           LinkedListIterator marco = new LinkedListIterator(); // create iterator object.
           
           String concatenate = " "; // you know...
           
           while(marco.hasNext()){ // while there are items in the list...

               Appointment appointment = marco.next();   // get an appointment object
               
               String lastName = appointment.getLastName();// get the last name of the patient
               
               String firstName = appointment.getFirstName();// get the first name of the patient
               
               int year = appointment.getYear(); // get the year of the appointment
               
               int month = appointment.getMonth();// the month..
               
               int day = appointment.getDay();// the day..
               
               int time = appointment.getTime();// the time in military..
               
               String conc = lastName + " " + firstName + " " + year + "." + month + "." + day + " at " + time; // make string for all of it..
               
               concatenate =   concatenate + "\n" + conc  ;   // concatenate followed by a new line.   
           }
          return concatenate + " " ;
        }   
        public Appointment finder(String lastName1,String firstName1){
            LinkedListIterator marco = new LinkedListIterator(); // create iterator object.
            while(marco.hasNext()){
                Appointment appointment = marco.next();   // get an appointment object
               
               String lastName = appointment.getLastName();// get the last name of the patient
               
               String firstName = appointment.getFirstName();// get the first name of the patient
               
               if(lastName1.equals(lastName)){// if the last name equal
                   
                    if(firstName1.equals(firstName)){return appointment;}//if the first name equal.
                }
            }
            return null;// couldn't find it
        }
    }
}
 