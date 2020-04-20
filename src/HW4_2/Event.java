package HW4_2;

public class Event {

  double time; //time to execute
  String type; //Arrival ,Departure or Monitor 
  Event next;  //following event
  String server ;
  int num ;
  public boolean isEmpty(Event e) {
   if(e == null )
   return true;
   else return false ;
   
  }
  
  public Event(String server, String type, double time, int num)
  {
   this.server = server ;
   this.type = type;
   this.time = time;
   this.num = num ;
  }
  


  public boolean hasNext()
  {
   if(next == null){return false;}
   else{return true;}
  }
  

  
 }