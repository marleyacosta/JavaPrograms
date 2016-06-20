import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*; 
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public  class DoctorGUI extends JFrame {
    
    LinkedList myList = new LinkedList();// list of appointments.  
        
    ListIterator myIterator = myList.listIterator();// list of appointments iterator.
        
    WaitingQueue waitingList = new WaitingQueue();// list of patients that are waiting for an appointment slot. // waiting list.
    
    private final int WINDOW_WIDTH = 800; // Width of the window 
    private final int WINDOW_HEIGHT = 160; // Height of the window
    
    JPanel panel;// The panel 
    
    // All the labels.
    JLabel enterLastName;
    JLabel enterFirstName;
    JLabel enterYear;
    // All the text fields.
    JTextField lastName;
    JTextField firstName;
    JTextField year;
    // Months Combo Box
    JComboBox month;
    String [] months = new String[12];
    // Days Combo Box
    JComboBox day;
    int [] days = new int[31];
    // Time Combo Box
    JComboBox time;
    int [] times = new int[6];
    //All the buttons.
    JButton makeAppointment;
    JButton findAppointment;
    JButton cancelAppointment;
    JButton changeAppointment;
    JButton viewAppointmentList;
    JButton viewWaitingList;
    JButton fillAvailableAppointment;
    // Initialize.
    DoctorsAppointment doc;
    
    public DoctorGUI(){
        myList = new LinkedList();// List of appointments.
       ListIterator myIterator = myList.listIterator();// list of appointments iterator.
       waitingList = new WaitingQueue();// list of patients that are waiting for an appointment slot. // waiting list.
         
       doc = new DoctorsAppointment();
       doc.DoctorsAppointment();
        
        setTitle("Doctor's Appointment System"); // Set the title      
        
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);// dimensioons of the window
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Exit by clicking red 'x'.
        
        setLayout(new BorderLayout());       

        buildPanel();
        
        add(panel);
        
        JOptionPane.showMessageDialog(null,"Welcome to DrYourLastName Appointment System.\n You will have 7 options:"
                + "\n 1. Make Appointment: Create an appointment by simply filling out the info and clicking the 'Make Appointment Button." +
                "\n 2. Find Appointment: Click on the 'Find Appointment' button and follow the prompts." + 
                "\n 3. Change Appointment: FIRST fill out the info and THEN click on the 'Change Appointment' button and follow the prompts." +
                "\n 4. View Appointment List: Click on 'View Appointment List' and the Appointments will be display." +
                "\n 5. View Waiting List: Click on 'Waiting List and the list of waiting Appointments will be display." +
                "\n 6. Cancel Appointment: Click on 'Cancel Appointment' and follow the prompts." +
                "\n 7. Waiting List Process: Click on  'Waiting List Process' ONCE to process ONE waiting lister at a time.");
        setVisible(true);// show window
    }
    
    private void buildPanel(){
        //Construct the labels and textfields.
        enterLastName = new JLabel("Enter Last Name: ");
        lastName = new JTextField(10);
        
        enterFirstName = new JLabel("Enter First Name: ");
        firstName = new JTextField(10);
        
        enterYear = new JLabel("Enter year: ");
        year = new JTextField(3);
        
        // Construct all the ComboBoxes
        month = new JComboBox();
        createMonthComboBox(month,months);
        
        day = new JComboBox();
        createDayComboBox(day);
        
        time = new JComboBox();
        createTimeComboBox(time);
        
        
        // Construct all the buttons.
        makeAppointment = new JButton("Make Appointment");
        makeAppointment.addActionListener(new makeAppointmentButtonListener());
        
        findAppointment = new JButton("Find Appointment");
        findAppointment.addActionListener(new findAppointmentButtonListener());
        
        changeAppointment = new JButton("Change Appointment");
        changeAppointment.addActionListener(new changeAppointmentButtonListener());
        
        cancelAppointment = new JButton("Cancel Appointment");
        cancelAppointment.addActionListener(new cancelAppointmentButtonListener());
        
        viewAppointmentList = new JButton("View Appointment List");
        viewAppointmentList.addActionListener(new viewAppointmentListener());
        
        viewWaitingList = new JButton("View Waiting List");
        viewWaitingList.addActionListener(new viewWaitingListListener());
        
        fillAvailableAppointment = new JButton("Waiting List Process");
        fillAvailableAppointment.addActionListener(new viewFillAvailableAppointment());
        
        panel = new JPanel();
        //Add all the features
        panel.add(enterLastName);
        panel.add(lastName);
        panel.add(enterFirstName);
        panel.add(firstName);
        panel.add(enterYear);
        panel.add(year);
        panel.add(month);
        panel.add(day);
        panel.add(time);
        panel.add(makeAppointment);   
        panel.add(findAppointment);
        panel.add(changeAppointment);
        panel.add(viewAppointmentList);
        panel.add(viewWaitingList);
        panel.add(cancelAppointment);
        panel.add(fillAvailableAppointment);
    }
    /**
     * This method creates month options.
     * @param x Months ComboBox
     * @param months Array of Months in Strings.
     */
    public void createMonthComboBox(JComboBox x, String [] months){
        
        months[0] = "1 - January";
        months[1] = "2 - February";
        months[2] = "3 - March";
        months[3] = "4 - April";
        months[4] = "5 - May";
        months[5] = "6 - June";
        months[6] = "7 - July";
        months[7] = "8 - August";
        months[8] = "9 - September";
        months[9] = "10 - October";
        months[10] = "11 - November";
        months[11] = "12 - December";
        
        for(int i = 0; i < months.length;i++){ x.addItem(months[i]);}
    }
    /**
     * This method creates 31 day optiosn.
     * @param x 
     */
    public void createDayComboBox(JComboBox x){

        int count = 1;
        for(int i = 0; i < days.length;i++){days[i] = count;x.addItem(days[i]);count++;}
    }
    /**
     * This method creates time options.
     * @param x Time ComboBox
     */
    public void createTimeComboBox(JComboBox x){
        
        times[0] = 9;
        times[1] = 10;
        times[2] = 11;
        times[3] = 13;
        times[4] = 14;
        times[5] = 15;
        for(int i = 0; i < times.length;i++){x.addItem(times[i]);}
    }
    
    /**
     * This method checks if a spot is available for patients in waiting list.
     */
    public class viewFillAvailableAppointment implements ActionListener{@Override public void actionPerformed(ActionEvent e){doc.fillProcess();} }
    //CalcButtonListener is an action listener class for the Calculate button.
   public class makeAppointmentButtonListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {  
         String lastName0 = lastName.getText();
         
         String firstName0 = firstName.getText();
         
         String get = year.getText();
         int years = Integer.parseInt(get);
         
         String m = month.getSelectedItem().toString();
         
         m = m.substring(0,2);
         m = m.replaceAll("\\s","");// for double digits.

         int m0 = Integer.parseInt(m);
             
         String d = day.getSelectedItem().toString();
         
         int d0= Integer.parseInt(d);
          
          String t = time.getSelectedItem().toString();
          int t0 = Integer.parseInt(t);
         
         Appointment appt = new Appointment(lastName0,firstName0,years,m0,d0,t0);

         doc.createAppointment(appt);  
      }
   } // End of CalcButtonListener class

   /**
    * Button Listener for find Appointment method.
    */
   public class findAppointmentButtonListener implements ActionListener{
 
       @Override
      public void actionPerformed(ActionEvent e)
      {  
         String lastName0 = lastName.getText();
         
         String firstName0 = firstName.getText();

         doc.search();
      }
   }
   
   private class changeAppointmentButtonListener implements ActionListener{
       
       @Override
      public void actionPerformed(ActionEvent e)
      {  
          String lastName0 = lastName.getText(); // get the last name
         
         String firstName0 = firstName.getText();// get the first name
         
         String get = year.getText();// get year
         int years = Integer.parseInt(get);// turn it into int.
         
         String m = month.getSelectedItem().toString();
         m = m.substring(0,2);
         m = m.replaceAll("\\s",""); // for double digit
         int m0 = Integer.parseInt(m);
             
         String d = day.getSelectedItem().toString();// get day
         int d0= Integer.parseInt(d);
          
          String t = time.getSelectedItem().toString();
          int t0 = Integer.parseInt(t);
         
         Appointment appt = new Appointment(lastName0,firstName0,years,m0,d0,t0);// make appointment
         
          JOptionPane.showMessageDialog(null,"Now look up the appointment you want to replace the new appointment with.");
 
          doc.changeAppointment(appt);// replace method.
      }
  }
   private class viewAppointmentListener implements ActionListener 
        {
         private JFrame frame = new JFrame("Appointments List");
         @Override
        public void actionPerformed(ActionEvent e)
     {
       
      JTextArea textArea = new JTextArea(6, 25);
      textArea.setText(doc.printList());
      textArea.setEditable(true);
       
      //wrap a scrollpane around it
      JScrollPane scrollPane = new JScrollPane(textArea);
       
      // display them in a message dialog
     JOptionPane.showMessageDialog(frame, scrollPane);
    }
   }
   private class viewWaitingListListener implements ActionListener{
       private JFrame frame = new JFrame("Waiting List");
         @Override
        public void actionPerformed(ActionEvent e)
     {
         JTextArea textArea = new JTextArea(6, 25);
         
         textArea.setText(doc.printQueueList());
        textArea.setEditable(true);
       
      //wrap a scrollpane around it
      JScrollPane scrollPane = new JScrollPane(textArea);
       
      // display them in a message dialog
     JOptionPane.showMessageDialog(frame, scrollPane);
     }
   }
   // Cancel Appointment Button Listener
   private class cancelAppointmentButtonListener implements ActionListener{@Override public void actionPerformed(ActionEvent e){doc.cancelAppointment();}}   
}