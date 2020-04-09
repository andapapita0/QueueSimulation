package main;

import java.util.ArrayList;

import controller.Controller;
import model.Queue;
import view.View;
/**
 * the main class which just initializes the simulation
 * @author anda
 *
 */
public class Main {

	public static void main(String[] args) {
		View v = new View();
		
		int nr_cozi = 3;
		
		ArrayList<Queue> q = new ArrayList<Queue>(nr_cozi);
		
		for(int i = 0; i < nr_cozi; i++) {
			q.add(new Queue());
			q.get(i).start();
		}
		
		Controller c = new Controller(v);
		v.setVisible(true);
	}

}
