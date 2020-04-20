package cs350;

public class Request{
  
  int id;
  double arrival;
  double start;
  double done;
  
  public Request(int id){
    this.id=id;
  }
  
  public double getTq() {
    return done-arrival;
  }
  
  public double getTs() {
    return done-start;
  }
  
  
}
