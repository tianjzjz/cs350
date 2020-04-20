package HW4_2;

public class Schedule {

  int length;
  Event head;
  Event tail;

  public Schedule(Event first) {
   head = first;
   tail = first;
   length = 1;
  }

  /**
   * Add newEvent to list based on its time.
   * 
   * @param newEvent
   */
     public void add(Event head, Event new_event) 
     { 
          Event current; 
   
          /* Special case for head node */
          if (head == null || head.time > new_event.time) 
          { 
             new_event.next = head; 
             head = new_event; 
          } 
          else { 
   
             /* Locate the node before point of insertion. */
             current = head; 
   
             while (current.next != null && 
                    current.next.time < new_event.time) 
                   current = current.next; 
   
             new_event.next = current.next; 
             current.next = new_event; 
          } 
      } 
  public Event gethead() {
   return head;
  }

 }