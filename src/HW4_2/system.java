package HW4_2;

import java.util.LinkedList;
import java.util.Random;

public class system{

 double time = 0; // 1.max time to run simulation
 double lambda = 0.0; // 2. average arrival rate of requests at the system λ;
 double m0 = 0;// 3. average service time μ0 at S0;
 double m1 = 0;// 4. average service time μ1 at S1;
 double m2 = 0;// 5. average service time μ2 at S2;
 double t1 = 0;// 6. service time t1 at S3;
 double p1 = 0;// 7. probability p1 of service time t1 at S3;
 double t2 = 0;// 8. service time t2 at S3;
 double p2 = 0;// 9. probability p2 of service time t2 at S3;
 double t3 = 0;// 10. service time t3 at S3;
 double p3 = 0;// 11. probability p3 of service time t3 at S3;
 int K = 0;// 12. K2 maximum length of the queue expressed in number of requests at S2;
 double p01 = 0;// 13. routing probability p0,1 that a request will go from S0 to S1;
 double p02 = 0;// 14. routing probability p0,2 that a request will go from S0 to S2;
 double pout = 0;// 15. routing probability p3,out that a request will exit the system from S3;
 double p31 = 0;// 16. routing probability p3,1 that a request will go from S3 back to S1;
 double p32 = 0;// 17. routing probability p3,2 that a request will go from S3 back to S2;

 int N = 0;
 int[] vacant;
 int[] numP;
 int ran = 0;
 double[] accutil = new double[3];
 double debutS0[] = new double[100000] ;
 double debutS1[] = new double[100000] ;
 double debutS2[] = new double[100000] ;
 double debutS3[] = new double[100000] ;
 double Ts = 0.0; // time of service

 LinkedList<Integer> S1 = new LinkedList<Integer>();
 LinkedList<Integer> S2 = new LinkedList<Integer>();
 LinkedList<Integer> S3 = new LinkedList<Integer>();

 // <utilization>
 double UTIL0 = 0;
 double UTIL2 = 0;
 double UTIL3 = 0;
 // <avg. queue length>
 double QLEN0 = 0;
 double QLEN1 = 0;
 double QLEN2 = 0;
 double QLEN3 = 0;
 double QTOT = 0 ;
 
 // <avg. response time of requests>
 double TRESP0 = 0;
 double TRESP1 = 0;
 double TRESP2 = 0;
 double TRESP3 = 0;
 
 // temp utilization
 double tUTIL0 = 0; 
 double tUTIL2 = 0; 
 double tUTIL3 = 0; 
 // temp response time of requests
 double dT0 = 0;
 double dT1 = 0;
 double dT2 = 0;
 double dT3 = 0;
 double dTtot = 0 ;

 double clock = 0;// current time

 double newIAT = 0;// generated time for new task
 double service = 0;// generated time for waiting
 int numA = 0;// birth event #
 int numS = 0; // start event #
 // death event #
 int numD0 = 0;
 int numD1 = 0;
 int numD2 = 0;
 int numD3 = 0;
 int queue0 = 0;// event numbers in the queue
 int queue1 = 0;
 int queue2 = 0;
 int queue3 = 0;
 double squeue0 = 0 ;
 double squeue1 = 0;
 double squeue2 = 0;
 double squeue3 = 0;
 double Ts0 = 0;
 double Ts1 = 0;
 double Ts2 = 0;
 double Ts3 = 0;
 Event toDep0;
 Event toDep1;
 Event toDep2;
 Event toDep3;


 double startTime = 0;// time when service begin
 double arrTime = 0;// time when event arrive at system

 double depTime = 0;
 double Mclock = 0;// interval of monitors
 int numM = 1;// number of monitors
 double tTime = 0;// temp time
 double q = 0;
 double Tq = 0;
 double sumTq = 0;

 int dropped = 0;
 int accepted = 0;
 int finished = 0 ;

 double[] probabilities = new double[3];
 double[] outcomes = new double[3];
 double[] pRouting = new double [3] ;
 double[] routing = new double [3] ;

 Random r = new Random();

 double rho = 0;// current accumulated UTIL

 // parameters for calculation
 double PSk = 0;
 double Lambda1 = 0;


 Event head;
 Schedule schedule = new Schedule(new Event("S0", "M", Mclock, -1));

 // initialize all parameters before start
 public void init() {

  clock = 0;
  numP = new int[1000000];
  N = 3;
  vacant = new int[N];
  head = schedule.gethead();
  newIAT = exp(lambda);
  schedule.add(head, new Event("S0", "A", newIAT, 0));
 }

 // simulate(time)
 public void simulate(double time) {
  while (clock < time) {
   newIAT = exp(lambda);
   Ts0 = exp(1.0 / m0);
   Ts1 = exp(1.0 / m1);
   Ts2 = exp(1.0 / m2);
   if (head.next != null) {

    // only execute events scheduled before end of simulation
    if (clock < time) {
     execute(head);
    }
    head = head.next;
    clock = head.time;

   }
  }
  
  
  QLEN0 = squeue0 / numM ;
  QLEN1 = squeue1 / numM ;
  QLEN2 = squeue2 / numM ;
  QLEN3 = squeue3 / numM ;
  UTIL0 = tUTIL0 / head.time;
  UTIL2 = tUTIL2 / head.time;
  UTIL3 = tUTIL3 / head.time;
  TRESP0 = dT0 / numD0 ;
  TRESP1 = dT1 / numD1 ;
  TRESP2 = dT2 / numD2 ;
  TRESP3 = dT3 / numD3 ;
  Tq = dTtot / finished ;
  QTOT = QLEN0 + QLEN1 + QLEN2 + QLEN3 ; 
  System.out.println();
  System.out.println("S0 UTIL: " + UTIL0);
  System.out.println("S0 QLEN: " + QLEN0);
  System.out.println("S0 TRESP: " + TRESP0);
    System.out.println();
  for(int i = 0 ; i < 2 ; i++) {
   System.out.println("S1,"+ (i+1) + " UTIL: " + accutil[i]/head.time);
  }
  System.out.println("S1 QLEN: " + QLEN1);
  System.out.println("S1 TRESP: " + TRESP1);
    System.out.println();
  System.out.println("S2 UTIL: " + UTIL2);
  System.out.println("S2 QLEN: " + QLEN2);
  System.out.println("S2 TRESP: " + TRESP2);
  System.out.println("S2 DROPPED: " + dropped);
    System.out.println();
  System.out.println("S3 UTIL: " + UTIL3);
  System.out.println("S3 QLEN: " + QLEN3);
  System.out.println("S3 TRESP: " + TRESP3);
    System.out.println();
  System.out.println("QTOT: " + QTOT);
  System.out.println("TRESP: " + Tq);
  System.out.println();
 }

 public void execute(Event head) {

  if (head.server == "S0") {
   // for Arrivals
   if (head.type == "A") {
    queue0++;

    arrTime = head.time;
    debutS0[head.num] = arrTime ;
    // output ARR
    System.out.println("R" + head.num + " " + "ARR" + ": " + arrTime);

    if (queue0 == 1) {

     toDep0 = head;

     System.out.println("R" + head.num + " " + "START" + " " + "S0" + ": " + arrTime);

     schedule.add(head, new Event("S0", "D", arrTime + Ts0, head.num));



    }

    // plan for next arrival
    schedule.add(head, new Event("S0", "A", (arrTime + newIAT), (head.num + 1)));

   }

   // for Departures
   if (head.type == "D") {
    if (queue0 <= 0) {
     return;
    } else {
     queue0--;
     numD0++ ;
     depTime = head.time;
     dT0 += depTime - debutS0[head.num] ;
     System.out.println("R" + head.num + " " + "DONE" + " " + "S0" + ": " + depTime);
     tUTIL0 += Ts0;
     

     Random r = new Random();
     double pos = r.nextDouble();
     if (pos < p01) {
      schedule.add(head, new Event("S1", "A", depTime, head.num));
      S1.add(head.num);
      System.out.println("R" + head.num + " " + "FROM S0 TO S1" + ": " + depTime);
     }

     else {
      schedule.add(head, new Event("S2", "A", depTime, head.num));
      System.out.println("R" + head.num + " " + "FROM S0 TO S2" + ": " + depTime);
     }

     if (queue0 <= 0)
      return;
     else {
      // plan for next departure
      advanceArrival("S0", toDep0);
      startTime = max(toDep0.time, depTime);
      schedule.add(head, new Event("S0", "D", startTime + Ts0, head.num + 1));

      System.out.println("R" + (head.num + 1) + " " + "START" + " " + "S0" + ": " + startTime);
     }
    }
   }
   // for Monitor
   if (head.type == "M") {
    squeue0 += queue0;
    squeue1 += queue1;
    squeue2 += queue2;
    squeue3 += queue3;
    numM++;
    Mclock += exp(1);
    schedule.add(head, new Event("S0", "M", Mclock, -1));
   }
  }
  if (head.server == "S1") {

   // for Arrivals
   if (head.type == "A") {
    queue1++;

    arrTime = head.time;
    debutS1[head.num] = arrTime ;
    // output ARR

    if (queue1 <= 2) {

     toDep1 = head;
     processor(2, head.num);
     System.out.println(
       "R" + head.num + " " + "START" + " " + "S1" + "," + numP[head.num] + ": " + arrTime);

     schedule.add(head, new Event("S1", "D", arrTime + Ts1, head.num));


    }

   }

   // for Departures
   if (head.type == "D") {
    if (queue1 <= 0) {
     return;
    } else {
     queue1--;
     numD1++ ;
    
     S1.removeFirst();
     depTime = head.time;
     dT1 += depTime - debutS1[head.num] ;
     System.out.println(
       "R" + head.num + " " + "DONE" + " " + "S1" + "," + numP[head.num] + ": " + depTime);
     schedule.add(head, new Event("S3", "A", depTime, head.num));
     System.out.println("R" + head.num + " " + "FROM S1 TO S3" + ": " + depTime);
     S3.add(head.num);
     accutil[numP[head.num]] += Ts1 ;

     if (queue1 < 2)
      return;
     else {
      // plan for next departure
      advanceArrival("S1", toDep1);
      startTime = max(toDep1.time, depTime);
      schedule.add(head, new Event("S1", "D", startTime + Ts1, S1.getFirst()));
      int temp = 0;
      for (int i = 0; i < 2; i++) {
       temp += vacant[i];
      }
      if (temp == 0) {
       numP[S1.getFirst()] = r.nextInt(3);
      } else {
       processor(2, S1.getFirst());// ?
      }
      System.out.println("R" + S1.getFirst() + " " + "START" + " " + "S1" + "," + numP[S1.getFirst()]
        + ": " + startTime);
     }
    }
   }
  }

  if (head.server == "S2") {

   if (head.type == "A") {

    if (queue2 >= K) {
     dropped++;
     System.out.println("R" + head.num + " " + "DROP" + " " + "S2" + ": " + arrTime);
     return ;
    
    } else {
     S2.add(head.num);
     arrTime = head.time;
     debutS2[head.num] = arrTime ;
     queue2++;

    }
    if (queue2 == 1) {
     
     toDep2 = head;

     System.out.println("R" + head.num + " " + "START" + " " + "S2" + ": " + arrTime);

     schedule.add(head, new Event("S2", "D", arrTime + Ts2, head.num));


    }
   }

   // for Departures
   if (head.type == "D") {
    if (queue2 <= 0) {
     return;
    } else {
     queue2--;
     numD2++ ;
     depTime = head.time;
     dT2 += depTime - debutS2[head.num] ;
     
     System.out.println("R" + head.num + " " + "DONE" + " " + "S2" + ": " + depTime);
     S2.removeFirst();
     schedule.add(head, new Event("S3", "A", depTime, head.num));
     System.out.println("R" + head.num + " " + "FROM S2 TO S3" + ": " + depTime);
     S3.add(head.num);
     tUTIL2 += Ts2;
     if (queue2 <= 0)
      return;
     else {
      // plan for next departure
      advanceArrival("S2", toDep2);
      startTime = max(toDep2.time, depTime);
      schedule.add(head, new Event("S2", "D", startTime + Ts2, S2.getFirst()));

      System.out.println("R" + S2.getFirst() + " " + "START" + " " + "S2" + ": " + startTime);
     }
    }
   }

  }

  if (head.server == "S3") {
   probabilities[0] = p1;
   probabilities[1] = p2;
   probabilities[2] = p3;
   outcomes[0] = t1;
   outcomes[1] = t2;
   outcomes[2] = t3;

   Ts3 = c(outcomes, probabilities);
   // for Arrivals

   if (head.type == "A") {
    queue3++;

    arrTime = head.time;
    debutS3[head.num] = arrTime ;
    // output ARR

    if (queue3 == 1) {

     toDep3 = head;

     System.out.println("R" + head.num + " " + "START" + " " + "S3" + ": " + arrTime);

     schedule.add(head, new Event("S3", "D", arrTime + Ts3, head.num));



    }

   }

   // for Departures
   if (head.type == "D") {
    if (queue3 <= 0) {
     return;
    } else {
     pRouting[0] = pout ;
     pRouting[1] = p31 ;
     pRouting[2] = p32 ;
     for(int i =0 ; i < 3; i++)
      routing[i] = i ;
     double key = c(routing, pRouting) ;
     queue3--;
     numD3++ ;
     depTime = head.time;
     dT3 += depTime - debutS3[head.num] ;
      
     S3.removeFirst();
     tUTIL3 += Ts3;
     System.out.println("R" + head.num + " " + "DONE" + ": " + depTime);
     if(key == 0) {
      System.out.println("R" + head.num + " " + "FROM S3 TO OUT" + ": " + depTime);
      finished ++ ;
      dTtot += depTime - debutS0[head.num] ;
      
      
     }
     if(key == 1) {
      schedule.add(head, new Event("S1", "A", depTime, head.num));
      S1.add(head.num);
      System.out.println("R" + head.num + " " + "FROM S3 TO S1" + ": " + depTime);
     }
     if(key == 2) {
      schedule.add(head, new Event("S2", "A", depTime, head.num));
      System.out.println("R" + head.num + " " + "FROM S3 TO S2" + ": " + depTime);
      
     }
     if (queue3 <= 0)
      return;
     else {
      // plan for next departure
      advanceArrival("S3", toDep3);
      startTime = max(toDep3.time, depTime);
      schedule.add(head, new Event("S3", "D", startTime + Ts3, S3.getFirst()));

      System.out.println("R" + S3.getFirst() + " " + "START" + " " + "S3" + ": " + startTime);
     }
    }
   }

  }
 }

 public system(double time, double lambda, double m0, double m1, double m2, double t1, double p1, double t2,
   double p2, double t3, double p3, int K, double p01, double p02, double pout, double p31, double p32) {
  this.lambda = lambda;
  this.time = time;
  this.m0 = m0;
  this.m1 = m1;
  this.m2 = m2;
  this.t1 = t1;
  this.p1 = p1;
  this.t2 = t2;
  this.p2 = p2;
  this.t3 = t3;
  this.p3 = p3;
  this.K = K;
  this.p01 = p01;
  this.p02 = p02;
  this.pout = pout;
  this.p31 = p31;
  this.p32 = p32;
  init();
  simulate(time);

 }

 // Helpers
 // HELPER EXP GENERATOR
 public double exp(double lambda) {
  Random RandomGenerator = new Random();
  double numRan = RandomGenerator.nextDouble();

  double res = -(1 / lambda) * Math.log(numRan);
  return res;
 }

 // HELPER MAX
 private double max(double time1, double time2) {
  // TODO Auto-generated method stub
  if (time1 >= time2)
   return time1;
  else
   return time2;
 }
 //HELPER ASSIGN PROCESSORS 
 public void processor(int N, int numE) {
  int temp = 0;
  for (int i = 0; i < N; i++) {
   temp += vacant[i];
  }
  if (temp != 0) {
   int[] rest = new int[temp];
   int p = 0;
   for (int i = 0; i < N; i++) {
    if (vacant[i] == 1) {
     rest[p] = i;
     p++;
    }
   }
   ran = r.nextInt(temp);
   numP[numE] = rest[ran];
  } else {
   numP[numE] = r.nextInt(N);
  }
  vacant[numP[numE]] = 0;
 }
 //HELPER ARRANGE NEXT DEPARTURE
 private void advanceArrival(String server, Event toDep) {
  if (toDep != null && toDep.next != null) {
   if (toDep.next.type == "A" && head.server == server) {
    toDep = toDep.next;
   } 
  else {
    while (toDep.next.type != "A") 
    {
//     System.out.println(head.server);
//     System.out.println(toDep.server);
//     System.out.println(toDep.next);
     toDep = toDep.next;
    }
    toDep = toDep.next;
   } // end else
  } // end if
 }
 //HELPER GENERATE RESULT OF PMF
 public double c(double[] outcomes, double[] probabilities) {
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
