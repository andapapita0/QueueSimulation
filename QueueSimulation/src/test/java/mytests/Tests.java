package mytests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;
import model.Client;
import model.Queue;
import model.Simulation;

public class Tests extends TestCase {
	int nrQueues, nrClients;
	ArrayList<Client> allC = new ArrayList<Client>(nrClients);
	Client c1 = new Client(2, 18);
	Client c2 = new Client(4, 12);
	Client c3 = new Client(10, 20);
	Client c4 = new Client(11, 5);
	Client c5 = new Client(7, 10);
	ArrayList<Queue> q = new ArrayList<Queue>(nrQueues);
			
	@Before
	public void setUp() {
		nrQueues = 3;
		nrClients = 5;
		allC.add(c1);
		allC.add(c2);
		allC.add(c3);
		allC.add(c4);
		allC.add(c5);
	}
	
	@Test
	public void testSimulation() {
		for(int i = 0; i < nrQueues; i++) {
	   		q.add(new Queue());
	   		q.get(i).start();
	   	}
		Simulation sim = new Simulation(nrQueues, q, nrClients, allC);
		sim.start();
	}
	
	
}
