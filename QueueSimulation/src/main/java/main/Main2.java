package main;

import java.util.ArrayList;

import model.Client;
import model.Queue;
import model.Simulation;
/**
 * this is just a tester class with an example provided by me; not to be considered
 * @author anda
 *
 */
public class Main2 {
	public static void main(String[] args) {
		int nrQueues = 3, nrClients = 5;
		ArrayList<Client> allC = new ArrayList<Client>(nrClients);
		Client c1 = new Client(2, 18);
		Client c2 = new Client(4, 12);
		Client c3 = new Client(10, 20);
		Client c4 = new Client(20, 5);
		Client c5 = new Client(7, 10);
		ArrayList<Queue> q = new ArrayList<Queue>(nrQueues);
		
		allC.add(c1);
		allC.add(c2);
		allC.add(c3);
		allC.add(c4);
		allC.add(c5);
	   	for(int i = 0; i < nrQueues; i++) {
	   		q.add(new Queue());
	   		q.get(i).start();
	   	}
	   	Simulation sim = new Simulation(nrQueues, q, nrClients, allC);
	   	sim.start();
	}
}
