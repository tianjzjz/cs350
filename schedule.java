package HW4;

public class schedule {
  
  int len;
  Event head;
  Event tail;
  
  public schedule(Event first) {
    head=first;
    tail=first;
    len=1;
  }
  
  public void add(Event head, Event add) {
    Event curr;
    
    if(head==null||head.time>add.time) {
      add.next=head;
      head = add;
    }
    
    else {
      curr=head;
      while(curr.next!=null && add.time>curr.next.time) {
        curr=curr.next;
      }
      add.next=curr.next;
      curr.next=add;
    }
  }
  
  public Event head() {
    return head;
  }

}


