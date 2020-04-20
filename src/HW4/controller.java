package HW4;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;


public class controller {
  
  LinkedList<Integer> q0 = new LinkedList<Integer>();
  LinkedList<Integer> q1 = new LinkedList<Integer>();
  LinkedList<Integer> q2 = new LinkedList<Integer>();
  LinkedList<Integer> q3 = new LinkedList<Integer>();

   int numT=0;
   double TT=0;
  
  double time=0;
  double lambda=0;
   double Ts0=0;
  double  Tq1=0;
  double  Tq2=0;
   double t1=0;
  double  p1=0;
  double  t2=0;
  double  p2=0;
   double t3=0;
  double  p3=0;
   int K=0;
   double p01=0;
   double p02=0;
   double p30=0;
  double  p31=0;
   double p32=0;
   
   double timestamp=0;
   int N=0;
   int[] vac;
   int[] numP;
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
   
   double Ts=0;
   
   double ql0=0;
   double ql1=0;
   double ql2=0;
   double ql3=0;
   
   
   double tql0=0;
   double tql1=0;
   double tql2=0;
   double tql3=0;
   
   
   double u0=0;
   double u1=0;
   double u2=0;
   double u3=0;
   
   double n0=0;
   double n1=0;
   double n2=0;
   double n3=0;

   
   double tq0=0;
   double tq1=0;
   double tq2=0;
   double tq3=0;


   double[] acc=new double[3];

   
   
   int[] iDeath = new int[] {0,0,0,0};
   double Tsd0=0;
   double Tsd1=0;
   double Tsd2=0;
   double Tsd3=0;

   
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
   
   double start=0;
   double arr=0;
   double dt=0;
   double Mt=0;

   
   double[]probs = new double[3];
   double[]out=new double[3];
   double[] track=new double[3];
   double[]result=new double[3];
   Random r = new Random();
   int rm;
   double newarr;
   double service;
   
   double t;
   int id;
   int numM=0;
   
   Event head;
   schedule schedule = new schedule(new Event("S4","M",Mt,-1));
   

   


   public static double exp(double input) {
     Random RandomGenerator = new Random();
     double numRan = RandomGenerator.nextDouble();

     double res = -(1 / input) * Math.log(numRan);
     return res;   }
   
  public void initiate() {
    
    curr=0;
    numP = new int[100000];
    N=3;
    vac=new int [N];
    head=schedule.head();
    newarr=exp(lambda);
    schedule.add(head,new Event("S0","A",newarr,0));
  }
  
  public void execute(double time) {
    while(curr<time) {
      newarr=exp(lambda);
      Tsd0=exp(1.0/Ts0);
      Tsd1=exp(1.0/Tq1);
      Tsd2=exp(1.0/Tq2);
      
      if(head.next!=null) {
        if(curr<time) {
          func(head);
        }
        head=head.next;
        curr=head.time;
      }

    }
    
    double util0= u0/head.time;
    double avglen0= tql0/ numM;
    double avgTq0=  tq0/ n0;  
    System.out.println("S0 UTIL: "+util0);
    System.out.println("S0 QLEN: "+avglen0);
    System.out.println("S0 TRESP: "+avgTq0);
    System.out.println();
    
    
   // System.out.println("count1 is "+ count1);
   // System.out.println("count2 is "+ count2);

   // System.out.println("q1,2 busytime is "+ busytime[1]);
    //System.out.println("q1,2 sumtime is "+ sumtime[1]);



    double util11= acc[0]/head.time;
    double util12= acc[1]/head.time;
    double avglen1= tql1/ numM;
    double avgTq1=  tq1/ n1;
    System.out.println("q1,1 UTIL: "+util11);
    System.out.println("q1,2 UTIL: "+util12);
    System.out.println("q1 QLEN: "+avglen1);
    System.out.println("q1 TRESP: "+avgTq1);
    System.out.println();

    //System.out.println("count3 is "+ count3);
    //System.out.println("count4 is "+ count4);

        
    double util2= u2/head.time;
    double avglen2= tql2/ numM;
    double avgTq2=  tq2/ n2;
    System.out.println("q2 UTIL: "+util2);
    System.out.println("q2 QLEN: "+avglen2);
    System.out.println("q2 TRESP: "+avgTq2);
    System.out.println("q2 DROPPED: "+ numD);
    System.out.println();



    double util3= u3/head.time;
    double avglen3= tql3/ numM;
    double avgTq3=  tq3/ n3;
    System.out.println("q3 UTIL: "+util3);
    System.out.println("q3 QLEN: "+avglen3);
    System.out.println("q3 TRESP: "+avgTq3);
    System.out.println();

    

    double avglen=avglen0+avglen1+avglen2+avglen3;
    double avgTq= TT/ numT;
    System.out.println("QTOT: "+avglen);
    System.out.println("TRESP: "+avgTq);


  }
  private void arr(String sid, Event toDep) {
    if (toDep != null && toDep.next != null) {
     if (toDep.next.type == "A" && head.svid == sid) {
      toDep = toDep.next;
     } 
    else {
      while (toDep.next.type != "A") 
      {

       toDep = toDep.next;
      }
      toDep = toDep.next;
     } // end else
    } // end if
   }
  
  public double max(double t1,double t2) {
    if(t1>=t2) {
      
      return t1;
    }
    return t2;
  }
  
  public void getServer(int N,int id) {
    int temp = 0;
    for (int i = 0; i < N; i++) {
     temp += vac[i];
    }
    if (temp != 0) {
     int[] rest = new int[temp];
     int p = 0;
     for (int i = 0; i < N; i++) {
      if (vac[i] == 1) {
       rest[p] = i;
       p++;
      }
     }
     rm = r.nextInt(temp);
     numP[id] = rest[rm];
    } else {
     numP[id] = r.nextInt(N);
    }
    vac[numP[id]] = 0;
    
  }
  
  public controller(double simTime,double lambda,double Ts0, double Tq1, double Tq2, double t1, double p1,double t2, double p2, double t3, double p3, int K,double p01, double p02, double p30,double p31, double p32) {
    this.time=simTime;
    this.lambda=lambda;
    this.Ts0=Ts0;
    this.Tq1=Tq1;
    this.Tq2=Tq2;
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
    initiate();
    execute(this.time);
  }
  
  //variable curr indicates the time when the event happens
  
  
  

  
  public void func(Event head) {

    if (head.svid == "S0") {
     // for Arrivals
     if (head.type == "A") {
      ql0++;

      arr = head.time;
      a0[head.id] = arr ;
      // output ARR
      System.out.println("R" + head.id + " " + "ARR" + ": " + arr);

      if (ql0 == 1) {

       s0 = head;

       System.out.println("R" + head.id + " " + "START" + " " + "S0" + ": " + arr);

       schedule.add(head, new Event("S0", "D", arr + Tsd0, head.id));



      }

      // plan for next arrival
      schedule.add(head, new Event("S0", "A", (arr + newarr), (head.id + 1)));

     }

     // for Departures
     if (head.type == "D") {
      if (ql0 <= 0) {
       return;
      } else {
       ql0--;
       n0++ ;
       timestamp = head.time;
       tq0 += timestamp - a0[head.id] ;
       System.out.println("R" + head.id + " " + "DONE" + " " + "S0" + ": " + timestamp);
       u0 += Ts0;
       

       Random r = new Random();
       double pos = r.nextDouble();
       if (pos < p01) {
        schedule.add(head, new Event("S1", "A", timestamp, head.id));
        q1.add(head.id);
        System.out.println("R" + head.id + " " + "FROM S0 TO S1" + ": " + timestamp);
       }

       else {
        schedule.add(head, new Event("S2", "A", timestamp, head.id));
        System.out.println("R" + head.id + " " + "FROM S0 TO S2" + ": " + timestamp);
       }

       if (ql0 <= 0)
        return;
       else {
        // plan for next departure
        arr("S0", s0);
        start = max(s0.time, timestamp);
        schedule.add(head, new Event("S0", "D", start + Tsd0, head.id + 1));

        System.out.println("R" + (head.id + 1) + " " + "START" + " " + "S0" + ": " + timestamp);
       }
      }
     }
     // for Monitor

    }
    if(head.svid=="S4") {
      if (head.type == "M") {
        tql0 += ql0;
        tql1 += ql1;
        tql2 += ql2;
        tql3 += ql3;
        numM++;
        Mt += exp(1);
        schedule.add(head, new Event("S4", "M", Mt, -1));
       }
    }
    if (head.svid == "S1") {

     // for Arrivals
     if (head.type == "A") {
      ql1++;

      arr = head.time;
      a1[head.id] = arr ;
      // output ARR

      if (ql1 <= 2) {

       s1 = head;
       getServer(2, head.id);
       System.out.println(
         "R" + head.id + " " + "START" + " " + "S1" + "," + numP[head.id] + ": " + arr);

       schedule.add(head, new Event("S1", "D", arr + Tsd1, head.id));


      }

     }

     // for Departures
     if (head.type == "D") {
      if (ql1 <= 0) {
       return;
      } else {
       ql1--;
       n1++ ;
      
       q1.removeFirst();
       t = head.time;
       tq1 += t - a1[head.id] ;
       System.out.println(
         "R" + head.id + " " + "DONE" + " " + "S1" + "," + numP[head.id] + ": " + t);
       schedule.add(head, new Event("S3", "A", t, head.id));
       System.out.println("R" + head.id + " " + "FROM S1 TO S3" + ": " + t);
       q3.add(head.id);
       acc[numP[head.id]] += Tsd1 ;

       if (ql1 < 2)
        return;
       else {
        // plan for next departure
        arr("S1", s1);
        t = max(s1.time, t);
        schedule.add(head, new Event("S1", "D", t + Tsd1, q1.getFirst()));
        int temp = 0;
        for (int i = 0; i < 2; i++) {
         temp += vac[i];
        }
        if (temp == 0) {
         numP[q1.getFirst()] = r.nextInt(3);
        } else {
         getServer(2, q1.getFirst());// ?
        }
        System.out.println("R" + q1.getFirst() + " " + "START" + " " + "S1" + "," + numP[q1.getFirst()]
          + ": " + t);
       }
      }
     }
    }

    if (head.svid == "S2") {

     if (head.type == "A") {

      if (ql2 >= K) {
       numD++;
       System.out.println("R" + head.id + " " + "DROP" + " " + "S2" + ": " + head.time);
       return ;
      
      } 
      else {
       q2.add(head.id);
       arr = head.time;
       a2[head.id] = arr ;
       ql2++;

      }
      if (ql2 == 1) {
       
       s2 = head;

       System.out.println("R" + head.id + " " + "START" + " " + "S2" + ": " + arr);

       schedule.add(head, new Event("S2", "D", arr + Tsd2, head.id));


      }
     }

     // for Departures
     if (head.type == "D") {
      if (ql2 <= 0) {
       return;
      } else {
       ql2--;
       n2++ ;
       t = head.time;
       tq2 += t - a2[head.id] ;
       
       System.out.println("R" + head.id + " " + "DONE" + " " + "S2" + ": " + t);
       q2.removeFirst();
       schedule.add(head, new Event("S3", "A", t, head.id));
       System.out.println("R" + head.id + " " + "FROM S2 TO S3" + ": " + t);
       q3.add(head.id);
       u2 += Tsd2;
       if (ql2 <= 0)
        return;
       else {
        // plan for next departure
        arr("S2", s2);
        t = max(s2.time, t);
        schedule.add(head, new Event("S2", "D", t + Tsd2, q2.getFirst()));

        System.out.println("R" + q2.getFirst() + " " + "START" + " " + "S2" + ": " + t);
       }
      }
     }

    }

    if (head.svid == "S3") {
     probs[0] = p1;
     probs[1] = p2;
     probs[2] = p3;
     out[0] = t1;
     out[1] = t2;
     out[2] = t3;

     Tsd3 = custom(out, probs);
     // for Arrivals

     if (head.type == "A") {
      ql3++;

      arr = head.time;
      a3[head.id] = arr ;
      // output ARR

      if (ql3 == 1) {

       s3 = head;

       System.out.println("R" + head.id + " " + "START" + " " + "S3" + ": " + arr);

       schedule.add(head, new Event("S3", "D", arr + Tsd3, head.id));



      }

     }

     // for Departures
     if (head.type == "D") {
      if (ql3 <= 0) {
       return;
      } else {
       result[0] = p30 ;
       result[1] = p31 ;
       result[2] = p32 ;
       for(int i =0 ; i < 3; i++)
        track[i] = i ;
       double key = custom(track, result) ;
       ql3--;
       n3++ ;
       t = head.time;
       tq3 += t - a3[head.id] ;
        
       q3.removeFirst();
       u3 += Tsd3;
       System.out.println("R" + head.id + " " + "DONE" + ": " + t);
       if(key == 0) {
        System.out.println("R" + head.id + " " + "FROM S3 TO OUT" + ": " + t);
        numT ++ ;
        TT += t - a0[head.id] ;
        
        
       }
       if(key == 1) {
        schedule.add(head, new Event("S1", "A", t, head.id));
        q1.add(head.id);
        System.out.println("R" + head.id + " " + "FROM S3 TO S1" + ": " + t);
       }
       if(key == 2) {
        schedule.add(head, new Event("S2", "A", t, head.id));
        System.out.println("R" + head.id + " " + "FROM S3 TO S2" + ": " + t);
        
       }
       if (ql3 <= 0)
        return;
       else {
        // plan for next departure
        arr("S3", s3);
        start = max(s3.time, t);
        schedule.add(head, new Event("S3", "D", start + Tsd3, q3.getFirst()));

        System.out.println("R" + q3.getFirst() + " " + "START" + " " + "S3" + ": " + start);
       }
      }
     }

    }
   }
  
  
 /* public void func(Event head) {
    
    
    if(head.svid==0) {
      if(head.type=="A") {
        
  
        ql0++;
        a0[head.id]=head.time;
        System.out.println("R"+head.id+" ARR: "+head.time+"\n");
        
        if(ql0==1) {
          
          s0=head;
          System.out.println("R"+head.id+" START S0: "+head.time+"\n");
          schedule.add(head, new Event(0,head.time+Tsd0,head.id,"D"));

        }
        schedule.add(head, new Event(0,(t+newarr),(head.id+1),"A"));
        
        

      }
      if(head.type=="D") {
        if(ql0<=0) {
          return;
        }
        else {
          ql0--;
          n0++;
          t=head.time;
           tq0+=t-a0[head.id];
           System.out.println("R"+head.id+" DONE S0: "+t+"\n");

           
          u0+=Tsd0;
          
          double a=r.nextDouble();
          if(a<p01) {
            schedule.add(head, new Event(1,t,head.id,"A"));
            q1.add(head.id);
            System.out.println("R"+head.id+" FROM S0 TO q1: "+t+"\n");

            
          }
          
          else {
            schedule.add(head, new Event(2,t,head.id,"A"));
            System.out.println("R"+head.id+" FROM S0 TO q2: "+t+"\n");      
          }
          if(ql0<=0) {
            return;
          }
          else {
            Arr(0,s0);
            start=max(s0.time,t);
            schedule.add(head, new Event(0,start+Tsd0,id+1,"D"));
            System.out.println("R"+(head.id+1)+" START S0: "+t+"\n");
          }
          
        }
        

      }
      
    }
    if(head.type=="M") {
      for(int i=0;i<4;i++) {
        tlen[i]+=qlen[i];
      }
      numM++;
      Mt+=exp(lambda);
      schedule.add(head, new Event(0,Mt,-1,"M"));
      
      
    }
    
    if(head.svid==1) {
      
      if(head.type=="A") {
        ql1++;
        t=head.time;
        
        
        a1[head.id]=t;
        
        if(ql1<=2) {
          q1=head;
          getServer(head.id);
          int b=iServer[head.id]+1;
          System.out.println("R" +head.id + " START q1,"+b+": " + t+"\n");
          schedule.add(head, new Event(1,head.time+Tsd1,head.id,"D"));

        }
        
      }
      
      if(head.type=="D") {
        if(ql1<=0) {
          return;
        }
        else {
          ql1--;
          n1++;
          q1.removeFirst();
          t=head.time;
          id=head.id;
          tq1+=t-a1[head.id];
          int c =iServer[id]+1;
          System.out.println("R"+head.id+" DONE q1,"+c+": "+t+"\n");
          schedule.add(head, new Event(3,t,head.id,"A"));
          System.out.println("R" + head.id+ " FROM q1 TO q3: " + t+"\n");

          q3.add(head.id);
          busytime[iServer[head.id]]+=Tsd1;
          
          if(ql1<2) {
          }
          else {
            Arr(1,q1);
            t=max(q1.time,t);
            id=q1.getFirst();

            schedule.add(head,  new Event(1,t+Tsd1,id,"D"));
            int temp=0;
            for(int i=0;i<2;i++) {
              temp+=vac[i];
            }
            if(temp==0) {
              iServer[id]=r.nextInt(2);
            }
            else {
              getServer(q1.getFirst());
            }
            int a=iServer[id]+1;
            System.out.println("R" + id+ " START q1,"+a+": " + t+"\n");

            
          }

        }
        
 
      }
    }
    if(head.svid==2) {
      if(head.type=="A") {
        id=head.id;
        t=head.time;
        if(ql2>=K) {
          numD++;
          System.out.println("R"+id+" DROP q2: "+t+"\n");

        }
        else {
          q2.add(id);
          a2[id]=t;
          ql2++;
        }
        
        if(ql2==1){
          q2=head;
          System.out.println("R"+id+" START q2: "+t+"\n");
          schedule.add(head, new Event(2,t+Tsd2,id,"D"));
          

        }

      }
      if(head.type=="D") {
        
        if(ql2>0) {
          
          ql2--;
          n2++;
          id=head.id;
          t=head.time;
          tq2+=t-a2[id];
          System.out.println("R"+id+" DONE q2: "+t+"\n");
          
          q2.removeFirst();
          schedule.add(head, new Event(3,t,id,"A"));
          System.out.println("R" + id+ " FROM q2 TO q3: " + t+"\n");
          
          q3.add(id);
          
          u2+=Tsd2;
          if(ql2>0) {
            Arr(2,q2);
            t=max(q2.time,t);
            id=q2.getFirst();
            schedule.add(head, new Event(2,t+Tsd2,id,"D"));
            System.out.println("R" + id + " START q2" + ": " + t+"\n");

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
        ql3++;
        t=head.time;
        id=head.id;
        a3[id]=t;
        if(ql3==1) {
          q3=head;
          System.out.println("R"+id+" START q3: "+t+"\n");
          schedule.add(head, new Event(3,t+Tsd3,id,"D"));

        }
      }
      
      if(head.type=="D") {
        
        if(ql3>0) {
          result[0]=p30;
          result[1]=p31;
          result[2]=p32;
          track = new int[] {0,1,2};
          double key = Custom.custom1(track, result);
          ql3--;
          n3++;
          t=head.time;
          id=head.id;
           tq3+=t-a3[id];
          q3.removeFirst();
          u3+=Tsd3;
          System.out.println("R" + id + " " + "DONE" + ": " + t+"\n");
          if(key==0) {
            System.out.println("R"+id+" FROM q3 TO OUT: "+t+"\n");
            numT++;
            TT+=t-a0[id];

            
          }
          
          if(key==1) {
            schedule.add(head, new Event(1,t,id,"A"));
            q1.add(id);
            System.out.println("R"+id+" FROM q3 TO q1: "+t+"\n");

          }
          
          if(key==2) {
            schedule.add(head, new Event(2,t,id,"A"));
            System.out.println("R"+id+" FROM q3 TO q2: "+t+"\n");

          }
          
          if(ql3>0) {
            Arr(3,q3);
            t=max(q3.time,t);
            id=q3.getFirst();
            schedule.add(head, new Event(3,t+Tsd3,id,"A"));
            System.out.println("R" + id + " START q3: " + t+"\n");

          }
          return;


        }
      }
      
    }
    
    
  }
  */
  
  public double custom(double[] outcomes, double[] probabilities) {
    Random random = new Random();
    double r = random.nextDouble();
    double sum = 0;
    double res = 0;
    int l = outcomes.length;
    for (int i = 0; i < l; i++) {
     if (r >= sum && r < sum + probabilities[i]) {
      res = outcomes[i];
     }
     sum += probabilities[i];
    }
    return res;
   }
  
}