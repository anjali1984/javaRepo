package com.optum.tops.J5427HC1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.concurrency.CallableClaimTask;
import com.optum.tops.J5427HC1.concurrency.OneClaimTask;
import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;

//Single Service that relies on all other services to process the incoming request and sends a HC1Response to the Controller
@Service
public class RequestProcessor{

	@Autowired 
	CheckCOBClaim cobclaimcheck ; //Service that checks if a claim is a COB claim, NYSTATECOB, Penny Process claim

	@Autowired
	OpsHcfaService opshcfacheck ; //Service for checking OPS_HCFA

	@Autowired
	COBLN2121Service cobln2121 ; 

	@Autowired
	COBLN2131Service cobln2131; 

	@Autowired 
	InstlReduction2140Service instlRed2140; //A Service that utilizes another service RedProcessor for getting DP835RED Data

	@Autowired 
	LoadCobLnLineAmtsService2150 instlLoad2150; 
	
	@Autowired
	ProfReduction2160Service profRed2160;
	
	@Autowired
	LoadSumForReductService2170 profLoad2170;
	
	
	
	public HC1Response process (Hc1Request request){
		boolean threadPool = false ; 
		
		if (!threadPool){ //Each claim in the incoming request will get a new thread of its own (Basic Design) 
			HC1Response response = new HC1Response() ; //to be sent back to the HC1Controller
			List<ReqClaimEntry> claims_to_be_serviced = request.getClaimEntries() ; 
			
			List<Thread> threadList = new ArrayList<>(); 
			
			//For Each ReqClaimEntry service it by creating a V5427HC1 instance, put it in the response_list_all_claims field of HC1Response
			for(ReqClaimEntry individual_claim : claims_to_be_serviced){
				OneClaimTask task = new OneClaimTask(individual_claim, response, claims_to_be_serviced.indexOf(individual_claim), cobclaimcheck, opshcfacheck,cobln2121,cobln2131,instlRed2140,instlLoad2150,profRed2160,profLoad2170) ;
				Thread t = new Thread(task); 
				t.setName("" + claims_to_be_serviced.indexOf(individual_claim));
				threadList.add(t);
				t.start();
			}
	
			System.out.println("Waiting for Child Threads to die");
	
		    for (Thread thread : threadList) {
		        try {
		            thread.join();
		            System.out.println(thread.getName() + " Finished its job");             
		        } catch (InterruptedException e) {
		            System.out.println("Interrupted Exception thrown by : "
		                    + thread.getName());                
		        }
		    }
		    System.out.println("All Child Threads Finished their Job");
			System.out.println("Response size is " + response.getResponse_map_all_claims().size());
			return response ;
		}
		else{ //Design of Thread pools, So all the claims will be served from the threadPool of fixed size
			
			System.out.println("Doing it the Threadpool way ??????$$$$$$$$$$&&&&&&&&&&&&&&&&&&&&&&&");
			HC1Response response = new HC1Response() ; //to be sent back to the HC1Controller
			List<ReqClaimEntry> claims_to_be_serviced = request.getClaimEntries() ; 
			
			ExecutorService executor = Executors.newFixedThreadPool(15);
			
			List<Future<V5427HC1>> futuresList = new ArrayList<Future<V5427HC1>>(); //In here all the returned claims will be added. 
			
			for(ReqClaimEntry individual_claim : claims_to_be_serviced){
				CallableClaimTask task = new CallableClaimTask(individual_claim, response, claims_to_be_serviced.indexOf(individual_claim), cobclaimcheck, opshcfacheck,cobln2121,cobln2131,instlRed2140,instlLoad2150,profRed2160,profLoad2170) ;
				Future<V5427HC1> f = executor.submit(task); //Submits a value-returning task for execution and returns a Future representing the pending results of the task.
				futuresList.add(f);
			}
			
			/*boolean all_done =false ;  
			while(all_done != true){
				for(Future<V5427HC1> future : futuresList){
					all_done &= future.isDone(); // check if future is done
				}
			}
			
			*/
			
			// No more threads can be submitted to the executor service!
			executor.shutdown();
			
			// Blocks until all submitted threads tasks have finished!
		    try {
				executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("All Child Threads Finished their Job");
			System.out.println("Response size is " + response.getResponse_map_all_claims().size());
			return response ;
		}
	}
}


