package HW3;
import java.util.PriorityQueue;

public class Event implements Comparable<Event>  {
  
  public double time;
  public EventType type;
  int Eid;
  int sid;
  
  //constructor for Event
  //parameter time indicates the time the event starts
  //parameter type indicates the type of this event
  
  public Event(double time, EventType type) {
    
    this.time=time;
    this.type=type;
    
  }
  public void func(PriorityQueue<Event> schedule, State state, double timestamp, int maxlen) {
    //execute this event so remove
    schedule.remove(this);

    switch(type) {
      
      case BIRTH:

        Request req = new Request(state.Next());
        req.arrival = timestamp;
        
        int id=req.id;
        req.serveid =0;
        state.numSimulated+=1;
        
        System.out.println("R"+id+" ARR: "+req.arrival+"\n");
        
        if(state.queue.size()== maxlen) {
          
          state.numRedirected+=1;
          System.out.println("R"+id+" REDIR: "+req.arrival+"\n");
          req.serveid=1;
          state.Squeue.add(req);
          if(state.Squeue.size()==1) {
            req.start=timestamp;
            double timeDeath=nextDeath(Simulator.Ts2);
            Event event = new Event(timestamp+timeDeath,EventType.DEATH);
            event.Eid=req.id;
            event.sid=1;
            schedule.add(event);
            System.out.println("R"+id+" START "+req.serveid+": "+req.start+"\n");
          }
          
          
        }
        else {
          state.queue.add(req);
          if(state.queue.size()==1) {
            req.start=timestamp;
            double timeDeath=nextDeath(Simulator.Ts1);
            Event event = new Event(timestamp+timeDeath,EventType.DEATH);
            event.Eid=req.id;
            event.sid=0;
            schedule.add(event);
            System.out.println("R"+id+" START "+req.serveid+": "+timestamp+"\n");
          }
        }

        
        double nextBirth = nextBirth(Simulator.lambda);
        Event nextArr = new Event(timestamp+nextBirth,EventType.BIRTH);
        schedule.add(nextArr);
        break;
        
      case DEATH:
        //remove the head of the linked list in the state queue
        Request requ;
        if (sid==0) {
          requ=state.queue.remove();        
          int serveid = requ.serveid;        
          requ.done=timestamp;
          state.totalTime += requ.getTq();
          state.timeinuse[serveid] += requ.getTs(); 
          state.numCompleted +=1;
          System.out.println("R"+requ.id+" DONE "+serveid+": "+requ.done+"\n");
          if(state.queue.size()>0) {
            Request reque=state.queue.peek();
            reque.start=timestamp;
            double nextDeathT=nextDeath(Simulator.Ts1);
            System.out.println("R"+reque.id+" START "+reque.serveid+": "+timestamp+"\n");
            Event nextDeath = new Event(timestamp+nextDeathT,EventType.DEATH);
            nextDeath.Eid=reque.id;
            nextDeath.sid=0;
            schedule.add(nextDeath);
          }
        }
        
        
        else {
          requ=state.Squeue.remove();
          int serveid = requ.serveid;        
          requ.done=timestamp;
          state.totalTime += requ.getTq();
          state.timeinuse[serveid] += requ.getTs(); 
          state.numCompleted +=1;
          System.out.println("R"+requ.id+" DONE "+serveid+": "+requ.done+"\n");
          if(state.Squeue.size()>0) {
            Request reque=state.Squeue.peek();
            reque.start=timestamp;
            double nextDeathT=nextDeath(Simulator.Ts2);
            System.out.println("R"+reque.id+" START "+reque.serveid+": "+timestamp+"\n");
            Event nextDeath = new Event(timestamp+nextDeathT,EventType.DEATH);
            nextDeath.Eid=reque.id;
            nextDeath.sid=1;
            schedule.add(nextDeath);
          }
        }
        
        

        break;
        
      case MONITOR:
        state.numMonitor += 1;
        state.updatelen();
        Event nextMonitor = new Event(timestamp + nextMonitor(Simulator.lambda),EventType.MONITOR);
        schedule.add(nextMonitor);
        break;
    } 
  }
  
  public void function(PriorityQueue<Event> schedule, State state, double timestamp,int maxlen) {
    schedule.remove(this);
    
    switch(type) {
      case BIRTH:
        Request req = new Request(state.Next());
        req.arrival = timestamp;
        int id=req.id;
        System.out.println("R"+id+" ARR: "+timestamp+"\n");
        
        if(state.queue.size()== maxlen) {
          System.out.println("R"+id+" DROP: "+timestamp+"\n");
          state.numDropped++;
          
        }
        
        else {
          state.queue.add(req);
          if(state.queue.size()==1) {
            req.start=timestamp;
            double timeDeath=nextDeath();
            Event event = new Event(timestamp+timeDeath,EventType.DEATH);
            schedule.add(event);
            System.out.println("R"+id+" START: "+timestamp+"\n");
          }
        }
        
        double nextBirth = nextBirth(SimulatorK.lambda);
        Event nextArr = new Event(timestamp+nextBirth,EventType.BIRTH);
        schedule.add(nextArr);
        break;
        
      case DEATH:
        //remove the head of the linked list in the state queue
        Request requ = state.queue.remove();
        requ.done=timestamp;
        state.totalTime += requ.getTq();
        state.timeinusebyone += requ.getTs(); 
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
        Event nextMonitor = new Event(timestamp + nextMonitor(SimulatorK.lambda),EventType.MONITOR);
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
  
  public static double nextBirth(double lambda) {
    return exp(lambda);
  }
  

  
  public static double nextDeath(double Ts) {
    return exp(1.0/Ts);
  }
  
  public static double nextDeath() {
    return exp(1.0/SimulatorK.Ts);
  }
  
  public static double nextMonitor(double lambda) {
    return exp(lambda);
  }

  
  

}


