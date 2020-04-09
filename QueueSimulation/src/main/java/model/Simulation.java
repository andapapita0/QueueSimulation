package model;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Vector;

import view.View;

/**
 * this class contains a list with all the clients that will come to stand in queues, a list of 
 * all the queues, the number of clients and the number of queues. It also extends the Thread 
 * class. It represents the class that will implement the way a customer goes to the line with 
 * the minimum waiting time.
 * @author anda
 *
 */
public class Simulation extends Thread{
	private ArrayList<Queue> queues;
	private ArrayList<Client> allClients;
	private int nr_case;
	private int nr_clienti;
	/**
	 * constructor
	 * @param nr_case number of queues
	 * @param q queues list
	 * @param nr_clienti number of clients
	 * @param allc list with all the clients
	 */
	public Simulation(int nr_case, ArrayList<Queue> q, int nr_clienti, ArrayList<Client> allc) {
		this.nr_case = nr_case;
		queues = new ArrayList<Queue>(nr_case);
		
		this.nr_clienti = nr_clienti;
		allClients = new ArrayList<Client>(nr_clienti);
		
		for(int j = 0; j < nr_clienti; j++) {
			this.allClients.add(allc.get(j));
		}
		
		Collections.sort(allClients, new Comparator<Client>(){
			public int compare(Client m1, Client m2) {
				return Integer.compare(m1.getArrivalTime(), m2.getArrivalTime());
			}
		});
		
		for(int i = 0; i < nr_case; i++) {
			this.queues.add(q.get(i));
		}
	}
	/**
	 * constructor that puts random clients in the list of all the clients
	 * @param nr_case number of queues
	 * @param q list of queues
	 * @param nr_clienti number of clients
	 * @param a maximum value for arrival time
	 * @param b minimum value for arrival time
	 * @param c maximum value for service time
	 * @param d minimum value for service time
	 */
	public Simulation(int nr_case, ArrayList<Queue> q, int nr_clienti, int a, int b, int c, int d) {
		this.nr_case = nr_case;
		queues = new ArrayList<Queue>(nr_case);
		
		this.nr_clienti = nr_clienti;
		allClients = new ArrayList<Client>(nr_clienti);
		
		for(int j = 0; j < nr_clienti; j++) {
			this.allClients.add(new Client(randomGenerator(a, b), randomGenerator(c, d)));
		}
		
		Collections.sort(allClients, new Comparator<Client>(){
			public int compare(Client m1, Client m2) {
				return Integer.compare(m1.getArrivalTime(), m2.getArrivalTime());
			}
		});
		
		for(int i = 0; i < nr_case; i++) {
			this.queues.add(q.get(i));
		}
	}
	/**
	 * 
	 * @return the index of the queue with the smallest waiting time
	 */
	private int minWT() {
		int index = 0;
		try {
			int min = queues.get(0).getWaitingTime();
			for(int i = 1 ; i < nr_case; i++) {
				int wt = queues.get(i).getWaitingTime();
				if(wt < min) {
					min = wt;
					index = i;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return index;
	}
	/**
	 * 
	 * @param max maximum value
	 * @param min minimum value
	 * @return a random value between the two parameters
	 */
	public int randomGenerator(int max, int min) {
		Random rg = new Random();
		int val = rg.nextInt(max) + min;
		return val;
	}
	/**
	 * this is the most important of the class, as it generates the way the whole simulation 
	 * works. This method calls for the main thread. Again, the thread will repeat itself in the 
	 * amount of the sum of the specified times of a client. The first client in the list of all 
	 * clients will be inserted in the queue with the minimum waiting time. The reason the first 
	 * client is chosen is because the list of clients is sorted based on each client’s arrival 
	 * time. Moreover, it contains the way the information is displayed in the graphic interface
	 */
	public void run() {
	try {
		int sum = 0;
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		String s = "";
		String s1 = "";
		String s2 = "";
		int i = 0;
		while(i < nr_clienti) {
			
			Client c = allClients.get(i);
			int m = minWT();
			queues.get(m).addClient(c);
			s = "QUEUE " + m + ": ";
			s += c.toString(c.getArrivalTime(), c.getServiceTime());
			v.addElement(s);
			System.out.println(c.toString() + "adaugat la casa " + Integer.toString(m));
			View.qs[m].setListData(v);
			i++;
			sleep(c.getArrivalTime()+c.getServiceTime());
		}
		int max = queues.get(0).getWT();
		for(int j = 0; j < queues.size(); j++) {
			int wt = queues.get(j).getWT();
			sum += wt;
			if(sum > max) {
				max = sum;
			}
			s1 = "queue " + j + "has the total waiting time of: ";
			s1 += wt;
			v1.addElement(s1);
			View.list[j].setListData(v1);
		}
		sum = sum/queues.size();
		s2 = "Average waiting time: " + sum;
		View.avg.setText(s2);
		}
		catch(InterruptedException e) {
			System.out.println(e.toString());
		}
	}
}
