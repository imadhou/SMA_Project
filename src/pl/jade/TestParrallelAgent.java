// $Id: HelloAgent.java 2112 2018-12-05 07:10:09Z phil $

package pl.jade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

/**
 * This class is a JADE Agent, it will compute the integral of a function using simpson method
 * it takes three parameters : the first two min and max represents the lower and upper bound of the integration area, the other argument : delta is the step of integration
 * <br>
 * The agent works in two differents ways, it first compute the result in monothread mode using itself as the only working agent
 * then it search for agents which can do the job using their service description, it splits the computation into equals parts, waits for the results and sum them to get the final answer
 * <br>
 * Finally, it print the result and the execution time in both modes.
 *
 * @author HOUARI Mourtada KASMI Ghilas
 */
public class TestParrallelAgent extends Agent {
  
	protected double total;
	protected long timestamp;
	
	private double min;
	private double max;
	private double delta;

  protected void setup() {
	  
	  // Retrieve user arguments
	  String[] args = (String[]) getArguments();
	  if ( args!= null && args.length == 3 ) {
		  min = Double.parseDouble( args[0] );
		  max = Double.parseDouble( args[1] );
		  delta = Double.parseDouble( args[2] );
	  } else {
		  System.out.println("The program expected three argumens, found a different number");
		  System.out.println("The arguments were set with the following default values : ");
		  System.out.println(" min = 1 \n max = 10 \n delta = 0.000001");
		  min = 1;
		  max = 10;
		  delta = 0.000001;
	  }
	  
	  // Compute integral by TestParrallelAgent
	  long startTime = System.currentTimeMillis();
	  ReverseFunction reverseFunctionMono = new ReverseFunction(min, max, delta);
	  double result = 0;
	  try{
	  result = reverseFunctionMono.eval();
	  }catch(Exception e){
			e.printStackTrace();
	  }
	  System.out.println("*********************************************************");
	  System.out.println("Result when computed with TestParrallelAgent : " + result);
	  long endTime = System.currentTimeMillis();
	  System.out.println("Execution time TestParrallelAgent : " + (endTime - startTime) + " ms");
	  System.out.println("*********************************************************");
	  
	  // Compute integral by ComputeAgent
	  
	  // Get List of matching agent services
	  // Create description
	  DFAgentDescription dfAgentDescription = new DFAgentDescription();
	  ServiceDescription serviceDescription = new ServiceDescription();
	  serviceDescription.setType("computeService");
	  dfAgentDescription.addServices(serviceDescription);
	  
	  ArrayList<AID> compatibleAgents = new ArrayList<AID>();
	  
	  // Search for compatible agents
	  try {
		DFAgentDescription[] agentsDescriptions = DFService.search(this, dfAgentDescription);
		
		if ( agentsDescriptions.length > 0 ) {
			for ( DFAgentDescription description : agentsDescriptions )
			compatibleAgents.add(description.getName());
		} else {
			System.err.println("No compatible Agents found !");
		}
	  
	  } catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  // Divide the works into equals parts
	  double part = (max - min) / compatibleAgents.size();
	  
	  double begin = min;
	  double end;
	  long startComputeTime = System.currentTimeMillis();
	  // send the computation for the compatible agents
	  for ( AID agent : compatibleAgents ) {
		  end = begin + part;
		  ACLMessage message = new ACLMessage(ACLMessage.INFORM);
		  message.addReceiver(agent);
		  message.setContent(min + ":" + max + ":" + delta);
		  send(message);
		  begin = end;
	  }
	  
	  // sum the results retourned by the agents
	  addBehaviour(new SimpleBehaviour() {
		
		private double result = 0;
		private int nbDoneAgent = 0;
		  
		@Override
		public boolean done() {
			// All the compatible agents finished their jobs
			if ( nbDoneAgent >= compatibleAgents.size() ) {
				System.out.println("*********************************************************");
				System.out.println("Result Compute agent : "+ result);
				long endTime = System.currentTimeMillis();
				System.out.println("Execution time ComputeAgent : " + (endTime - startComputeTime)+ " ms");
				System.out.println("*********************************************************");
				return true;
			}
			return false;
		}
		
		@Override
		public void action() {
			
			
			ACLMessage message = receive();
			if ( message == null ) {
				block();
			}else{

			// sum the returned result
			result += Double.parseDouble(message.getContent());
			nbDoneAgent++;
			}
			
		}
	});
  }
  
}
