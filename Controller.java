package HW3;

import java.util.PriorityQueue;

//initializes the state of the simulated system
//keeps track of time
//calls the functions to update the events

public class Controller {
  
  
  //initialize a priority queue to record 
  //the schedule of event to be valued
  
  public static PriorityQueue<Event> initialize(double lambda){
    
    PriorityQueue<Event> schedule = new PriorityQueue<Event>();
    
    double timeBirth = Event.nextBirth(lambda);
    double timeMonitor = Event.nextMonitor(lambda);
    
    schedule.add(new Event(timeBirth,EventType.BIRTH));
    schedule.add(new Event(timeMonitor,EventType.MONITOR));
    
    return schedule;
    
  }
  
  //variable curr indicates the time when the event happens
  public static void simulation(double simTime, double lambda, int maxlen, double Ts1, double Ts2) {
    
    State state = new State();
    PriorityQueue<Event> schedule = initialize(Simulator.lambda);
    
    double curr=0;
    double end=simTime;
    
    while(curr<end) {
      Event event = schedule.remove();
      curr=event.time;
      event.func(schedule, state, curr, maxlen);
    }
   
    double utilization0=state.timeinuse[0]/simTime;
    double utilization1=state.timeinuse[1]/simTime;
    double avgQlen0=state.qlen0/state.numMonitor;
    double avgQlen1=state.qlen1/state.numMonitor;

    double avgResponseTime=state.totalTime/state.numCompleted;
    double num =state.numRedirected;
    
    System.out.println("UTIL 0: "+utilization0+"\n");
    System.out.println("UTIL 1: "+utilization1+"\n");
    System.out.println("QLEN 0: "+avgQlen0+"\n");
    System.out.println("QLEN 1: "+avgQlen1+"\n");

    System.out.println("TRESP: "+avgResponseTime+"\n");
    System.out.println("REDIRECTED: "+num+"\n");



  }
  public static void simulate(double simTime, double lambda, int maxlen,double Ts) {
    
    State state = new State();
    PriorityQueue<Event> schedule = initialize(SimulatorK.lambda);
    
    double curr=0;
    double end=simTime;
    
    while(curr<end) {
      Event event = schedule.remove();
      curr=event.time;
      event.function(schedule, state, curr, maxlen);
    }
   
    double utilization=state.timeinusebyone/simTime;
    double avgQlen=state.qlen/state.numMonitor;

    double avgResponseTime=state.totalTime/state.numCompleted;
    System.out.println("UTIL: "+utilization+"\n");
    System.out.println("QLEN: "+avgQlen+"\n");

    System.out.println("TRESP: "+avgResponseTime+"\n");
    System.out.println("DROPPED: "+state.numDropped+"\n");

  }

}
