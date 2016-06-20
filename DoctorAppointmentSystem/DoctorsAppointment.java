
import javax.swing.JOptionPane;

public class DoctorsAppointment {
    
    LinkedList myList; // list of appointments
    ListIterator myIterator; // iterator for appointment list
    WaitingQueue waitingList; // waiting list.
   
    public void DoctorsAppointment(){
         myList = new LinkedList();// list of appointments.  
        
        myIterator = myList.listIterator();// list of appointments iterator.
        
        waitingList = new WaitingQueue();// list of patients that are waiting for an appointment slot.
        //Create a new Appointment for John Smith at 3pm on a certain date
        Appointment firstAppt = new Appointment("Smith", "John",1996,01,27,15);
        createAppointment(firstAppt);

        // Create another Appointment for Mary Jones at 2pm  on a certain date
        Appointment secondAppt = new Appointment("Jones", "Mary",1996,01,27,14);
        createAppointment(secondAppt);
        
      
        //Create another Appointment for Jim Hernandez at 1pm  on a certain date
        Appointment thirdAppt = new Appointment("Hernandez", "Jim",1996,01,27,13);   
        createAppointment(thirdAppt);
        
        
        //Change the Appointment for Mary Jones from 3pm to 11:00am  on a certain date
        Appointment newSecondAppt = new Appointment("Jones","Mary",1996,01,27,11);
        myIterator.replaceAppointment(secondAppt,newSecondAppt);
        
        
        //Create another Appointment for Ron Stevens at 10am on a certain date
        Appointment fourthAppt = new Appointment("Stevens","Ron",1996,01,27,10);
        createAppointment(fourthAppt);
        
       
        // "Cancel" the Appointment for John Smith at 3pm on a certain date .
        myIterator.removeAppointment(firstAppt);
        
        //Create another Appointment for Nick Hernandez at 9am  on a certain date
        Appointment fifthAppt = new Appointment("Hernandez","Nick",1996,01,27,9);
        createAppointment(fifthAppt);
        
        
        //Attempt to create another Appointment for Joe Kline at 9am on same date as Nick (place Kline in the Waiting Queue for any cancellations)
        Appointment sixthAppt = new Appointment("Kline","Joe",1996,01,27,9);
        createAppointment(sixthAppt);
        
        
        //Create another Appointment for Maria Garcia at 11am on a certain date.
        Appointment seventhAppt = new Appointment("Garcia","Maria",1996,01,28,11);
        createAppointment(seventhAppt);
        
        //Create another Appointment for Jose Gonzalez at 11am on same date as Maria (place Gonzalez in the Waiting Queue for any cancellations)
        Appointment eighthAppt = new Appointment("Gonzalez","Jose",1996,01,28,11);
        createAppointment(eighthAppt);
        
        
        //Attempt to create another Appointment for Luis Leeds at 5pm on a certain date (error message)
        Appointment tenthAppt = new Appointment("Leeds","Luis",1996,01,28,5);
        createAppointment(tenthAppt);    
    } 
    /**
     * This method calls the fillAvailableAppointment method so that it can be accessed by the GUI.
     */
    public void fillProcess(){waitingList.fillAvailableAppointment(myList);}
    /**
     * This method searches for an Appointment by only using first and last name as keys.
     */
    public void search(){
        int value = JOptionPane.showConfirmDialog(null,"Would you like to search for an appointment?","Search",JOptionPane.YES_NO_OPTION);
        while(value == 0){
            String lastName =  JOptionPane.showInputDialog(null,"Enter Last Name","Search");
            String firstName =  JOptionPane.showInputDialog(null,"Enter first Name","Search");
            
            Appointment x = myIterator.finder(lastName,firstName);
            if(x != null){String eh = "Found it: " + x.toString();
                        JOptionPane.showMessageDialog(null,eh);}
            else{JOptionPane.showMessageDialog(null,"Sorry couldn't find it.");}
            value = JOptionPane.showConfirmDialog(null,"Would you like to search for another appointment?","Search",JOptionPane.YES_NO_OPTION);
        }
    }
    /**
     * This method returns a String of the list of Appointments.
     * @return 
     */
    public String printList(){return myIterator.toString();}
    /**
     * This method returns a String of the list of waiting Appointments.
     * @return 
     */
    public String printQueueList(){return waitingList.toString();}
    /**
     * This method cancels an Appointment in the GUI.
     */
    public void cancelAppointment(){
        int value = JOptionPane.showConfirmDialog(null,"Would you like to cancel an appointment?","Cancel Appointment",JOptionPane.YES_NO_OPTION);
        while(value == 0){
            String lastName =  JOptionPane.showInputDialog(null,"Enter Last Name","Cancel Appointment");
            String firstName =  JOptionPane.showInputDialog(null,"Enter first Name","Cancel Appointment");
            
            Appointment apt = myIterator.finder(lastName,firstName);
            
            myIterator.removeAppointment(apt);
            
            String eh = "The appointment " + apt.toString() + " was removed";
            JOptionPane.showMessageDialog(null,eh);
            
            value = JOptionPane.showConfirmDialog(null,"Would you like to cancel another appointment?","Cancel Appointment",JOptionPane.YES_NO_OPTION);
        }
     }
    /**
     * This method changes an Appointment.
     * @param current The new Appointment. 
     */
     public void changeAppointment(Appointment current){
         int value = 0;
        while(value == 0){
            String lastName =  JOptionPane.showInputDialog(null,"Enter Last Name","Cancel Appointment");
            String firstName =  JOptionPane.showInputDialog(null,"Enter first Name","Cancel Appointment");
            Appointment old = myIterator.finder(lastName,firstName);
            
            boolean x = myIterator.replaceAppointment(old,current);
            String eh = "The appointment " + old.toString() + " was replaced by " + current.toString();
            if(x){JOptionPane.showMessageDialog(null,eh);}
            
            else{JOptionPane.showMessageDialog(null,"Sorry couldn't change it");}
                        
            value = JOptionPane.showConfirmDialog(null,"Would you like to change another appointment?","Change Appointment",JOptionPane.YES_NO_OPTION);
        }
     }
     
    /**
     * This method properly adds an appointment either to the appointment list or the waiting list. 
     * As well as checks if the appointment is a duplicate or the time is not acceptable.
     * @param anAppt An Appointment.
     */
    public void createAppointment(Appointment anAppt){
         
         Appointment find = myIterator.find(anAppt);// check to see if the appointment is in the list.
         
         boolean validate = myIterator.validateAppointment(anAppt);// validate if the appointment time is correct.
         
         if(find == null){// if could not find the appointment 
             
             if(validate){//if correct time
                 boolean x = myIterator.makeAppointment(anAppt);//create the appointment.
                 if(x){
                     
                     JOptionPane.showMessageDialog(null, "The following appointment has been added to the appointment list: " + anAppt.toString());
                 }
                 else{JOptionPane.showMessageDialog(null, "The following appointment has been added to the waiting list: " + anAppt.toString() + "\n");
                 waitingList.enqueue(anAppt);
                 
                 }
             }
             else{// else not the correct time.
                 
                 JOptionPane.showMessageDialog(null,"Sorry, " + anAppt.getFirstName() + " " + anAppt.getLastName() + " the time you have requested for your appointment is not available.\n "
                         + "The only available times are : 9am, 10am, 11am, 1pm, 2pm, or 3pm\n");
             }             
         }
         
     } 
}