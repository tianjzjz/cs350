package HW3;

public class Request {
  
  int id;
  double arrival;
  double start;
  double done;
  int serveid;
  double Ts;
  
  public Request(int id){
    this.id=id;
    this.Ts=Simulator.Ts1;
  }
  
  public double getTq() {
    return this.done-this.arrival;
  }
  
  public double getTs() {
    return this.done-this.start;
  }
  
  public void setServeid(int id) {
    this.Ts=Simulator.Ts2;
    serveid=id;
  }
}
