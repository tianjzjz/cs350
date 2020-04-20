package HW4;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

//initializes the state of the simulated system
//keeps track of time
//calls the functions to update the events

public class controller {
  
  LinkedList<Integer> q0 = new LinkedList<Integer>();
  LinkedList<Integer> q1 = new LinkedList<Integer>();
  LinkedList<Integer> q2 = new LinkedList<Integer>();
  LinkedList<Integer> q3 = new LinkedList<Integer>();

   int numT;
   double TT;
  
  double simTime;
  double lambda;
   double Ts0;
  double  Ts1;
  double  Ts2;
   double t1;
  double  p1;
  double  t2;
  double  p2;
   double t3;
  double  p3;
   int K;
   double p01;
   double p02;
   double p30;
  double  p31;
   double p32;
   
   double Mt=0;
   
   int[] busy;
   int[] iServer;
   double[] st0=new double[100000];
   double[] st1=new double[100000];
   double[] st2=new double[100000];
   double[] st3=new double[100000];
   double[] d0=new double[100000];
   double[] d1=new double[100000];
   double[] d2=new double[100000];
   double[] d3=new double[100000];
   double[] a0=new double[100000];
   double[] a1=new double[100000];
   double[] a2=new double[100000];
   double[] a3=new double[100000];
   
   int[] iDeath = new int[] {0,0,0,0};
   double Tsd0;
   double Tsd1;
   double Tsd2;
   double Tsd3;

   
   double curr=0;
   int[] numC=new int[] {0,0,0,0};
   int numD=0;
   int[] qlen=new int[] {0,0,0,0};
   int[] tlen=new int[] {0,0,0,0};

   double[]  runtime = new double[]{0,0,0,0};
   double[] timeinuse = new double[]{0,0,0,0};
   double[] busytime = new double[]{0,0};


   Event s0;
   Event s1;
   Event s2;
   Event s3;
   
   double start;
   
   double[]probs = new double[3];
   double[]out=new double[3];
   int[] track=new int[3];
   double[]result=new double[3];
   Random r = new Random();
   int rm;
   
   Event head;
   schedule schedule = new schedule(new Event(4,Mt,-1,"M"));
   
   double Arr;
   
   double t;
   int id;
   int numM;
   

  //initialize a priority queue to record 
  //the schedule of event to be valued
  

  
 /* public static  double exp(double lambda) {
     Random RandomGenerator = new Random();
     double numRan = RandomGenerator.nextDouble();

     double res = -(1 / lambda) * Math.log(numRan);
     return res;
    }
   
   public static double nextBirth() {
     return exp(Simulator.lambda);
   }
   */
   
   public static double exp(double input) {
     return (- Math.log(1.0-Math.random())/input);
   }
   
  public void initiate(double lambda) {
    
    curr=0;
    iServer = new int[100000];
    busy=new int [] {0,0};
    head=schedule.head();
    Arr=exp(lambda);
    schedule.add(head,new Event(0,Arr,0,"A"));
  }
  
  public void execute(double time) {
    while(curr<time) {
      Arr=exp(lambda);
      Tsd0=exp(1.0/Ts0);
      Tsd1=exp(1.0/Ts1);
      Tsd2=exp(1.0/Ts2);
      
      if(head.next!=null) {
        if(curr<time) {
          func(head);
        }
        head=head.next;
        curr=head.time;
      }

    }
    
    double util0= timeinuse[0]/time;
    double avglen0= qlen[0]/ numM;
    double avgTq0=  runtime[0]/ numC[0];  
    System.out.println("S0 UTIL: "+util0);
    System.out.println("S0 QLEN: "+avglen0);
    System.out.println("S0 TRESP: "+avgTq0);
    System.out.println();
    
    
   // System.out.println("count1 is "+ count1);
   // System.out.println("count2 is "+ count2);

   // System.out.println("s1,2 busytime is "+ busytime[1]);
    //System.out.println("s1,2 sumtime is "+ sumtime[1]);



    double util11= busytime[0]/time;
    double util12= busytime[1]/time;
    double avglen1= qlen[1]/ numM;
    double avgTq1=  runtime[1]/ numC[1];
    System.out.println("S1,1 UTIL: "+util11);
    System.out.println("S1,2 UTIL: "+util12);
    System.out.println("S1 QLEN: "+avglen1);
    System.out.println("S1 TRESP: "+avgTq1);
    System.out.println();

    //System.out.println("count3 is "+ count3);
    //System.out.println("count4 is "+ count4);

        
    double util2= timeinuse[2]/time;
    double avglen2= qlen[2]/ numM;
    double avgTq2=  runtime[2]/ numC[2];
    System.out.println("S2 UTIL: "+util2);
    System.out.println("S2 QLEN: "+avglen2);
    System.out.println("S2 TRESP: "+avgTq2);
    System.out.println("S2 DROPPED: "+ numD);
    System.out.println();



    double util3= timeinuse[3]/time;
    double avglen3= qlen[3]/ numM;
    double avgTq3=  runtime[3]/ numC[3];
    System.out.println("S3 UTIL: "+util3);
    System.out.println("S3 QLEN: "+avglen3);
    System.out.println("S3 TRESP: "+avgTq3);
    System.out.println();

    

    double avglen=avglen0+avglen1+avglen2+avglen3;
    double avgTq= TT/ numT;
    System.out.println("QTOT: "+avglen);
    System.out.println("TRESP: "+avgTq);


  }
  
  public double max(double t1,double t2) {
    if(t1>=t2) {
      return t1;
    }
    return t2;
  }
  
  public void getServer(int id) {
    int temp=0;
    for(int i=0;i<2;i++) {
      if(busy[i]==0)
      temp+=1;
    }
    if(temp!=0) {
      int[] available = new int[temp];
      int track=0;
      for(int i=0;i<2;i++) {
        if(busy[i]==0) {
          available[track]=i;
          track++;
        }
      }
      rm=r.nextInt(temp);
      
      iServer[id]=available[rm];
      busy[iServer[id]]=1;

    }
    else {
      iServer[id]=r.nextInt(2);
      busy[iServer[id]]=1;
    }
    
    
  }
  
  public controller(double simTime,double lambda,double Ts0, double Ts1, double Ts2, double t1, double p1,double t2, double p2, double t3, double p3, int K,double p01, double p02, double p30,double p31, double p32) {
    this.simTime=simTime;
    this.lambda=lambda;
    this.Ts0=Ts0;
    this.Ts1=Ts1;
    this.Ts2=Ts2;
    this.t1=t1;
    this.p1=p1;
    this.t2=t2;
    this.p2=p2;
    this.t3=t3;
    this.p3=p3;
    this.K=K;
    this.p01=p01;
    this.p02=p02;
    this.p30=p30;
    this.p31=p31;
    this.p32=p32;
    initiate(lambda);
    execute(simTime);
  }
  
  //variable curr indicates the time when the event happens
  
  
  
  public void Arr(int sid, Event A) {
    if(A!=null && A.next!=null) {
      if(A.next.type=="A"&&head.svid==sid) {
          A=A.next;
        }
        else {
          while(A.next.type!="A") {
            A=A.next;
          }
          A=A.next;
        }
      }
    
  }
  
  
  public void func(Event head) {
    
    
    if(head.svid==0) {
      if(head.type=="A") {
        
        id=head.id;
        t=head.time;
        qlen[0]++;
        a0[head.id]=t;
        System.out.println("R"+head.id+" ARR: "+t+"\n");
        
        if(qlen[0]==1) {
          
          s0=head;
          System.out.println("R"+head.id+" START S0: "+t+"\n");
          schedule.add(head, new Event(0,t+Tsd0,head.id,"D"));

        }
        schedule.add(head, new Event(0,t+Arr,head.id+1,"A"));

        
      }
      if(head.type=="D") {
        
        if(qlen[0]>0) {
          qlen[0]--;
          numC[0]++;
          t=head.time;
          id=head.id;
           runtime[0]+=t-a0[0];
          timeinuse[0]+=Tsd0;
          
          double a=r.nextDouble();
          if(a<p01) {
            schedule.add(head, new Event(1,t,id,"A"));
            q1.add(id);
            System.out.println("R"+id+" FROM S0 TO S1: "+t+"\n");

            
          }
          
          else {
            schedule.add(head, new Event(2,t,id,"A"));
            System.out.println("R"+id+" FROM S0 TO S2: "+t+"\n");      
          }
          
          if(qlen[0]>0) {
            Arr(0,s0);
            schedule.add(head, new Event(0,t+Tsd0,id+1,"D"));
            System.out.println("R"+id+" FROM S0 TO S1: "+t+"\n");
          }
          
        }
        
        
      }
      
    }
    if(head.type=="M") {
      for(int i=0;i<4;i++) {
        tlen[i]+=qlen[i];
      }
      numM++;
      Mt+=exp(1);
      schedule.add(head, new Event(0,Mt,-1,"M"));
      
      
    }
    
    if(head.svid==1) {
      
      if(head.type=="A") {
        qlen[1]++;
        id=head.id;
        t=head.time;
        a1[head.id]=t;
        if(qlen[1]<=2) {
          s1=head;
          getServer(head.id);
          int b=iServer[head.id]+1;
          System.out.println("R" +head.id + " START S1,"+b+": " + t+"\n");
          schedule.add(head, new Event(1,t+Tsd1,head.id,"D"));

        }
        
      }
      
      if(head.type=="D") {
        
        if(qlen[1]>0) {
          qlen[1]--;
          numC[1]--;
          q1.removeFirst();
          t=head.time;
          id=head.id;
           runtime[1]+=t-a1[id];
          int c =iServer[id]+1;
          System.out.println("R"+head.id+" DONE S1,"+c+": "+t+"\n");
          schedule.add(head, new Event(3,t,head.id,"A"));
          System.out.println("R" + head.id+ " FROM S1 TO S3: " + t+"\n");

          q3.add(head.id);
          busytime[iServer[id]]+=Tsd1;
          
          if(qlen[1]<2) {
            return;
          }
          else {
            id=q1.getFirst();
            Arr(1,s1);
            start=max(s1.time,t);
            schedule.add(head,  new Event(1,start+Tsd1,id,"D"));
            int temp=0;
            for(int i=0;i<2;i++) {
              if(busy[i]==0) {
                temp+=1;
              }
            }
            if(temp==0) {
              iServer[id]=r.nextInt(3);
            }
            else {
              getServer(q1.getFirst());
            }
            int a=iServer[id]+1;
            System.out.println("R" + id+ " START S1,"+a+": " + t+"\n");

            
          }

        }
        
      }
    }
    if(head.svid==2) {
      if(head.type=="A") {
        id=head.id;
        t=head.time;
        if(qlen[2]>=K) {
          numD++;
          System.out.println("R"+id+" DROP S2: "+t+"\n");
          return;

        }
        else {
          q2.add(id);
          a2[id]=t;
          qlen[2]++;
        }
        
        if(qlen[2]==1){
          s2=head;
          System.out.println("R"+id+" START S2: "+t+"\n");
          schedule.add(head, new Event(2,t+Tsd2,id,"D"));
          

        }
        
      }
      if(head.type=="D") {
        
        if(qlen[2]>0) {
          
          qlen[2]--;
          numC[2]++;
          id=head.id;
          t=head.time;
           runtime[2]+=t-a2[id];
          System.out.println("R"+id+" DONE S2: "+t+"\n");
          q2.removeFirst();
          schedule.add(head, new Event(3,t,id,"A"));
          System.out.println("R" + id+ " FROM S2 TO S3: " + t+"\n");
          q3.add(id);
          
          timeinuse[2]+=Tsd2;
          if(qlen[2]>0) {
            Arr(2,s2);
            t=max(s2.time,t);
            id=q2.getFirst();
            schedule.add(head, new Event(2,t+Tsd2,id,"D"));
            System.out.println("R" + id + " START S2" + ": " + t+"\n");

          }


        }
        
      }

    }
    
    if(head.svid==3) {
      
      probs[0]=p1;
      probs[1]=p1;
      probs[2]=p3;
      out[0]=t1;
      out[1]=t2;
      out[2]=t3;
      
      Tsd3=Custom.custom(out, probs);
      
      if(head.type=="A") {
        qlen[3]++;
        t=head.time;
        id=head.id;
        a3[id]=t;
        if(qlen[3]==1) {
          s3=head;
          System.out.println("R"+id+" START S3: "+t+"\n");
          schedule.add(head, new Event(3,t+Tsd3,id,"D"));

        }
      }
      
      if(head.type=="D") {
        
        if(qlen[3]>0) {
          result[0]=p30;
          result[1]=p31;
          result[2]=p32;
          track = new int[] {0,1,2};
          double key = Custom.custom1(track, result);
          qlen[3]--;
          numC[3]++;
          t=head.time;
          id=head.id;
           runtime[3]+=t-a3[id];
          q3.removeFirst();
          timeinuse[3]+=Tsd3;
          System.out.println("R" + id + " " + "DONE" + ": " + t+"\n");
          if(key==0) {
            System.out.println("R"+id+" FROM S3 TO OUT: "+t+"\n");
            numT++;
            TT+=t=a0[id];

            
          }
          
          if(key==1) {
            schedule.add(head, new Event(1,t,id,"A"));
            q1.add(id);
            System.out.println("R"+id+" FROM S3 TO S1: "+t+"\n");

          }
          
          if(key==2) {
            schedule.add(head, new Event(2,t,id,"A"));
            System.out.println("R"+id+" FROM S3 TO S2: "+t+"\n");

          }
          
          if(qlen[3]>0) {
            Arr(3,s3);
            t=max(s3.time,t);
            id=q3.getFirst();
            schedule.add(head, new Event(3,t+Tsd3,id,"A"));
            System.out.println("R" + id + " START S3: " + t+"\n");

          }

        }
      }
      
    }
    
    
  }
  
  



}
