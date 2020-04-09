package controller;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Queue;
import model.Simulation;
import view.View;
/**
 * this class acts on both Simulation and View classes. It controls the data flow that goes into 
 * Simulation and updates the View whenever data changes. More precisely, it coordinates the 
 * button start which performs a specific task if the user chooses to press it
 * @author anda
 *
 */
public class Controller {
	private View v;
	int nrc;
	int nrq;
	/**
	 * it initializez the view and adds a view listener
	 * @param v the view
	 */
	public Controller(View v) {
		this.v = v;
		v.addStartListener(new StartListener());
	}
	/**
	 * class that implements the action performed when button "start" is pressed
	 * @author anda
	 *
	 */
	class StartListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int mina = Integer.parseInt(v.getMinArrival());
			int maxa = Integer.parseInt(v.getMaxArrival());
			int mins = Integer.parseInt(v.getMinService());
			int maxs = Integer.parseInt(v.getMaxService());
			int nrq = Integer.parseInt(v.getNrQueues());
			int nrc = Integer.parseInt(v.getClients());
			ArrayList<Queue> q = new ArrayList<Queue>(nrq);
			
			for(int i = 0; i < nrq; i++) {
				q.add(new Queue());
				q.get(i).start();
			}
			Simulation s = new Simulation(nrq, q, nrc, maxa, mina, maxs, mins);
			s.start();
		}
	}
	}
