package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import view.View;
/**
 * @class Queue is the class which contains the list of clients per queue; the attribute waitingTime
 * represents the total waiting time of one queue at a certain point in time
 * @author Anda
 *
 */
public class Queue extends Thread {
	private Vector<Client> clients;
	private int waitingTime;
	/**
	 * the constructor initializes the vector of clients and the waiting time
	 */
	public Queue() {
		setClients(new Vector());
		waitingTime = 0;
	}
	/**
	 * 
	 * @param p add this client to the vector of clients of the queue (at the end)
	 * @throws InterruptedException
	 */
	public synchronized void addClient(Client p) throws InterruptedException {
			getClients().addElement(p);
			notifyAll();
	}
	/**
	 * @method removes the first client in queue
	 * @throws InterruptedException
	 */
	public synchronized void removeClient() throws InterruptedException {
		while(getClients().size() == 0) {
			wait();
		}
		Client client = (Client)getClients().elementAt(0);
		getClients().removeElementAt(0);
		System.out.println(client.toString() + "is out");
		notifyAll();
		//return client;
	}
	/**
	 * 
	 * @return the length of the queue
	 * @throws InterruptedException
	 */
	public synchronized int queueLength() throws InterruptedException {
		notifyAll();
		int size = getClients().size();
		return size;
	}
	/**
	 * @method gets the final waiting time of a queue
	 * @return rez - the waiting time
	 */
	public int getWT() {
		int rez = 0;
		for(int i = 0; i < clients.size(); i++) {
			rez+=clients.elementAt(i).getServiceTime();
		}
		return rez;
	}
	/**
	 * @method gets the waiting time for each client in order to know where to add the next client
	 * @return the waiting time
	 */
	public int getWaitingTime() {
		if(getClients().size() == 1) {
			waitingTime = getClients().elementAt(0).getArrivalTime() + getClients().elementAt(0).getServiceTime();
		}
				for(int i = 1; i < getClients().size(); i++) {
					
					waitingTime += getClients().elementAt(i).getServiceTime();
			}
		System.out.println("waiting t: "+waitingTime);
		return waitingTime;
	}
	/**
	 * @method that turns the result of the queue into a proper format to be displayed
	 */
	public String toString(){
		String rez ="";
		int t1 = 0;
		int t2 = 0;
		for (int i = clients.size() - 1; i >= 0; i--){
			t1 += clients.elementAt(i).getServiceTime();
			t2 += clients.elementAt(i).getArrivalTime();
			rez += clients.elementAt(i).toString(t2, t1) + "  ";
		}
		return rez;
	}
	/**
	 * this method must be implemented due to the fact that this class extends the Thread class;
	 * All threads that will function on an object of type Queue will implement this method. It 
	 * will show the queue for a period of time equal with the sum of the arrival and service 
	 * time for each client. Them it will decrement the service time, thus the waiting time, for 
	 * each customer. If his service time is null, the client will leave (he will be removed).
	 */
	public void run() {
		try {
			while(true) {
				while(!clients.isEmpty()) {
					
					Client c = getClients().elementAt(0);
					sleep(c.getArrivalTime()+c.getServiceTime());
					//sleep(1000);
					int st = c.getServiceTime();
			
					if(st == 0) {
						removeClient();
					}
					c.setServiceTime(st - 1);
					}
			}
		}
		catch(Exception e) {
			System.out.println("intrerupere");
			System.out.println(e.toString());
		}
		
	}
	/**
	 *  @method getter of the list of clients
	 * @return the vector of clients of the queue
	 */
	public Vector<Client> getClients() {
		return clients;
	}
/**
 * @method sets the list of clients
 * @param clients list of clients
 */
	public void setClients(Vector<Client> clients) {
		this.clients = clients;
	}

}
