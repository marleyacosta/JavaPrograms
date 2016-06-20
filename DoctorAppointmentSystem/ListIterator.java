/**
 A list iterator allows access to a position in a linked list.
 This interface contains a subset of the methods of the
 standard java.util.ListIterator interface. The methods for
 backward traversal are not included.
 */
 public interface ListIterator
 {
 /**
 Moves the iterator past the next element.
 @return the traversed element
 */
 Appointment next();

 /**
 Tests if there is an element after the iterator position.
 @return true if there is an element after the iterator position
 */
 boolean hasNext();
 
 boolean makeAppointment(Appointment anAppt);

 /**
 Adds an element before the iterator position
 and moves the iterator past the inserted element.
 @param anAppointment the element to add
 */
 void add(Appointment anAppointment);
 /**
  * Find the appointment
  * @param element The appointment
  * @return The appointment
  */
 Appointment find(Appointment element);
 /**
  * Finds the appointment using only first and last name.
  * @param first First Name
  * @param last Last Name
  * @return The appointment
  */
 Appointment finder(String first,String last);
 /**
  * This method replaces an Appointment.
  * @param old The old Appointment
  * @param news The new Appointment
  * @return True if the old Appointment was replaced.
  */
 boolean replaceAppointment(Appointment old, Appointment news);
 /**
  * This method checks if the slot is within the approved hours.
  * @param anAppt The Appointment.
  * @return True if the hours are approved.
  */
 boolean validateAppointment(Appointment anAppt);
 /**
  * This method removes an Appointment.
  * @param anAppt  The Appointment
  */
 void removeAppointment(Appointment anAppt);
 /**
 Removes the last traversed element. This method may
 only be called after a call to the next method.
 */
 void remove();

 /**
 Sets the last traversed element to a different value.
 @param element the element to set
*/
 void set(Object element);
 }