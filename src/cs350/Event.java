package cs350;
import java.util.PriorityQueue;

public class Event implements Comparable<Event>  {
  
  public double time;
  public EventType type;
  
  
  //constructor for Event
  //parameter time indicates the time the event starts
  //parameter type indicates the type of this event
  
  public Event(double time, EventType type) {
    
    this.time=time;
    this.type=type;
    
  }
  
  //parameter schedule represents the scheduled future events
  //parameter state indicates the state of simulation
  //parameter track tracks the current time in the simulation
  
  public void func(PriorityQueue<Event> schedule, State state, double timestamp) {
    //execute this event so remove
    schedule.remove(this);
    
    switch(type) {
      case BIRTH:
        Request req = new Request(state.Next());
        req.arrival = timestamp;
        
        state.queue.add(req);
        int id=req.id;
        System.out.println("R"+id+" ARR: "+req.arrival+"\n");
        
        if(state.queue.size()==1) {
          req.start=timestamp;
          double timeDeath=nextDeath();
          Event event = new Event(timestamp+timeDeath,EventType.DEATH);
          schedule.add(event);
          System.out.println("R"+id+" START: "+timestamp+"\n");
        }
        
        double nextBirth = nextBirth();
        Event nextArr = new Event(timestamp+nextBirth,EventType.BIRTH);
        schedule.add(nextArr);
        break;
        
      case DEATH:
        //remove the head of the linked list in the state queue
        Request requ = state.queue.remove();
        requ.done=timestamp;
        state.totalTime += requ.getTq();
        state.timeinuse += requ.getTs(); 
        state.numCompleted +=1;
        System.out.println("R"+requ.id+" DONE: "+requ.done+"\n");
        
        if(state.queue.size()>0) {
          Request next = state.queue.peek();
          next.start=timestamp;
          System.out.println("R"+next.id+" START: "+next.start+"\n");
          Event nextDeath = new Event(timestamp+nextDeath(),EventType.DEATH);
          schedule.add(nextDeath);
        }
        break;
        
      case MONITOR:
        state.numMonitor += 1;
        state.qlen += state.queue.size();
        Event nextMonitor = new Event(timestamp + nextMonitor(),EventType.MONITOR);
        schedule.add(nextMonitor);
        break;
    } 
  }
  
  
  
  public int compareTo(Event e) {
    double diff = time-e.time;
    if(diff<0) {
      return -1;
    }
    else if (diff>0) {
      return 1;
    }
    else {
      return 0;
    }
  }
  
  public static double exp(double input) {
    return (- Math.log(1.0-Math.random())/input);
  }
  
  public static double nextBirth() {
    return exp(Simulator.lambda);
  }
  
  public static double nextDeath() {
    return exp(1.0/Simulator.Ts);
  }
  
  public static double nextMonitor() {
    return exp(Simulator.lambda);
  }
  
  

}
