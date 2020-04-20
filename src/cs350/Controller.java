package cs350;

import java.util.PriorityQueue;

//initializes the state of the simulated system
//keeps track of time
//calls the functions

public class Controller {
  
  public static PriorityQueue<Event> initialize(){
    
    PriorityQueue<Event> schedule = new PriorityQueue<Event>();
    
    double timeBirth = Event.nextBirth();
    double timeMonitor = Event.nextMonitor();
    
    schedule.add(new Event(timeBirth,EventType.BIRTH));
    schedule.add(new Event(timeMonitor,EventType.MONITOR));
    
    return schedule;
    
  }
  
  //variable curr indiactes the time when the event happens
  public static void simulation(double simTime, double lambda, double Ts) {
    State state = new State();
    PriorityQueue<Event> schedule = initialize();
    double curr=0;
    double end=simTime;
    while(curr<end) {
      Event event = schedule.remove();
      curr=event.time;
      event.func(schedule, state, curr);
    }
    double utilization=state.timeinuse/simTime;
    double avgQlen=state.qlen/state.numMonitor;
    double avgResponseTime=state.totalTime/state.numCompleted;
    System.out.println("UTIL: "+utilization+"\n");
    System.out.println("QLEN: "+avgQlen+"\n");
    System.out.println("TRESP: "+avgResponseTime+"\n");

  }
}
