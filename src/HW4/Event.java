package HW4;

public class Event   {
  
  double time;
  public Event next;
  String svid;
  String type;
  int id;

  
  //constructor for Event
  //parameter time indicates the time the event starts
  //parameter type indicates the type of this event
  
  public Event(String sv,String type, double time, int id) {
    
    this.time=time;
    this.type=type;
    this.id=id;
    this.svid=sv;
    
  }
  
  public boolean hasNext() {
    if(next==null) {
      return false;
    }
    else {
      return true;
    }
  }
  
  

  


  

  
  

}


