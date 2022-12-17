
package pl.jade;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

/**
 * This class is a JADE Agent, it is a "worker" class. It will first register the service computeService
 * TestParrallelAgent will call it then and pass a part of the integration calculus
 * ComputeAgent will calculate its part of the work, then return it to TestParralAgent
 *
 * @author HOUARI Mourtada KASMI Ghilas
 */
public class ComputeAgent extends Agent {

	protected Function f;

  protected void setup() {
	
	  // Register service
	  ServiceDescription serviceDescription = new ServiceDescription();
	  serviceDescription.setType("computeService");
	  serviceDescription.setName(getLocalName());
	  try {
		register(serviceDescription);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  addBehaviour(new CyclicBehaviour() {
		
		@Override
		public void action() {
			
			ACLMessage message = receive();
			if ( message == null ) {
				block();
			}else {
				String[] attributes = message.getContent().split(":");
				double min = Double.valueOf(attributes[0]);
				double max = Double.valueOf(attributes[1]);
				double delta = Double.valueOf(attributes[2]);
				
				ReverseFunction reverseFunction = new ReverseFunction(min, max, delta);
				// compute result and send response
				ACLMessage response = message.createReply();
				response.setPerformative(ACLMessage.INFORM);
				try{
					response.setContent(String.valueOf(reverseFunction.eval()));
				}catch(Exception e){
					e.printStackTrace();
					response.setContent("0");
				}
				send(response);
			}
		}
	});
  }
  
  protected void takeDown() {
	  
	  try {
		DFService.deregister(this);
	} catch (FIPAException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

	/**
	 * Method which registers a service, once added this agent becomes "callable" to perform this service
	 * @param sd : The description of the service which the agent can perform
	 * @throws FIPAException
	 */
	void register(ServiceDescription sd) throws FIPAException {
	  DFAgentDescription dfAgentDescription = new DFAgentDescription();
	  dfAgentDescription.setName(getAID());
	  dfAgentDescription.addServices(sd);
	  
	  DFService.register(this, dfAgentDescription);
  }
  
}
