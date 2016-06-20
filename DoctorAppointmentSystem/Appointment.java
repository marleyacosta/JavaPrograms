
public class Appointment {
    
    private String lastName;// last name of the patient.
    private String firstName;// first name of the patient   
    private int year;// year of the appointment
    private int month;// month of the appointment
    private int day;// day of the appointment
    private int time;
   
    /**
     * Constructor for appointment object.
     * @param lastName last name of the patient.
     * @param firstName first name of the patient 
     * @param year year of the appointment
     * @param month month of the appointment
     * @param day day of the appointment
     * @param time time of the appointment in military time
     */
    public Appointment(String lastName, String firstName,int year, int month, int day, int time ){
        
        this.lastName = lastName;
        this.firstName = firstName;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;       
    }

    /** 
     * This method returns the last name.
     * @return the lastName
     */
    public String getLastName() {return lastName;}

    /**
     * This method returns the first name.
     * @return the firstName
     */
    public String getFirstName() {return firstName;}

    /**
     * This method returns the year.
     * @return the year
     */
    public int getYear() {return year;}

    /**
     * This method returns the month.
     * @return the month
     */
    public int getMonth() {return month;}

    /**
     * This method returns the day.
     * @return the day
     */
    public int getDay() {return day;}
    /**
     * This method returns the time as a String.
     * @return the time
     */
    public int getTime(){return time;}
    /**
     * This method returns the Appointment as a String
     * @return the Appointment as a String.
     */
    public String toString(){return lastName + " " + firstName + " " + year + "." + month + "." + day + " at " + time;}
}
