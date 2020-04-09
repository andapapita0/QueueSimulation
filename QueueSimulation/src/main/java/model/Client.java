package model;
/**
 * this class represents the format of a client which comes in a queue. Each object of type Client 
 * will have as attributes the arrival time, which is the time the customer arrives for the 
 * service, and the service time, the time that the customer’s desired service requires
 * @author anda
 *
 */
public class Client {
	private int arrivalTime;
	private int serviceTime;
	/**
	 * constructor of the class
	 * @param a the arrival time
	 * @param s the service time
	 */
	public Client(int a, int s) {
		this.arrivalTime = a;
		this.serviceTime = s;
	}
	/**
	 * 
	 * @param timp1 the arrival time
	 * @param timp2the service time
	 * @return a string in an appropriate format that will be displayed for the user
	 */
	public String toString(int timp1, int timp2){
		String rez1 = "";
		rez1 += timp1;
		String rez2 = "";
		rez2 += timp2;
		return "C-" + rez1 + "-" + rez2 + " ";
	}
	
	@Override
	public String toString() {
		return "Client [arrivalTime=" + arrivalTime + ", serviceTime=" + serviceTime + "]";
	}
	
	/// the following methods are just getters and setters for the attributes
	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	public void printClient() {
		System.out.println(this.toString());
	}

}
