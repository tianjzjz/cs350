package cs350;
import java.util.LinkedList;

public class State {
  

  public LinkedList<Request> queue;
  public int next;
  public double totalTime;
  public double numCompleted;
  public double qlen;
  public double timeinuse;
  public double numMonitor;

  
  
  public State() {
    
    queue = new LinkedList<Request>();
    next=0;
    totalTime=0;
    numCompleted=0;
    qlen=0;
    numMonitor=0;
    timeinuse=0;
 
    
  }
  
  public int Next() {
    return next++;
  }

}
