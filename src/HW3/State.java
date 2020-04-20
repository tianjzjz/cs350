package HW3;

import java.util.LinkedList;

public class State {
  

  public LinkedList<Request> queue;
  public LinkedList<Request> Squeue;
  
  public int next;
  public double totalTime;
  public double numCompleted;
  public double qlen;
  public double qlen0;
  public double qlen1;
  double[] timeinuse;
  public double numMonitor;
  public double lenmax;
  public int numRedirected;
  public double timeinusebyone;
  public int numDropped;
  public int numSimulated;
  
  
  
  public State() {
    
    queue = new LinkedList<Request>();
    Squeue = new LinkedList<Request>();
    next=0;
    totalTime=0;
    numCompleted=0;
    qlen0=0.0;
    qlen1=0.0;
    numMonitor=0;
    timeinuse= new double[2];
    timeinuse[0]=0.0;
    timeinuse[1]=0.0;
    timeinusebyone = 0;
    numRedirected=0;
    qlen=0;
    numDropped=0;
    numSimulated=0;
 
    
  }
  
  public int Next() {
    return next++;
  }
  
  public void updatelen() {
    double len1=queue.size();
    double len2=Squeue.size();
    qlen0+=len1;
    qlen1+=len2;

  }


}
