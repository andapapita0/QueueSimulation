package view;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * This class creates the interface the user works with regarding to monitoring the queue
 * evolution. It extends the JFrame class. Its attributes are labels, text fields, panels
 * buttons and two JList components
 * @author anda
 *
 */
public class View extends JFrame{
	public JPanel main = new JPanel(new GridBagLayout());

	private JTextField min_arrival_int = new JTextField(10);
	private JTextField max_arrival_int = new JTextField(10);
	private JTextField min_service_time = new JTextField(10);
	private JTextField max_service_time = new JTextField(10);
	private JTextField no_clients = new JTextField(10);
	private JTextField no_queues = new JTextField(10);
	
	private JLabel arrival = new JLabel("MIN and MAX arrival interval");
	private JLabel service = new JLabel("MIN and MAX service time");
	private JLabel sim = new JLabel("Number of clients");
	private JLabel queues = new JLabel("Number of queues in use");
	
	private JButton start_button = new JButton("Start");
	
	public static JTextField avg = new JTextField(25);
	public static JList[] qs;
	public static JList[] list;
	/**
	 * the constructor
	 */
	public View() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 700);
		this.setTitle("queue simulator");
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(arrival, c);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 0;
		main.add(min_arrival_int, c);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 2;
		c.gridy = 0;
		main.add(max_arrival_int, c);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 1;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(service, c);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 1;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(min_service_time, c);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 2;
		c.gridy = 1;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(max_service_time, c);
		
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 2;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(sim, c);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 2;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(no_clients, c);
	
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 3;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(queues, c);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 3;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(no_queues, c);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		main.add(start_button, c);
		
		JPanel ops = new JPanel();
		ops.setBorder(BorderFactory.createTitledBorder("Queues (maximum 6)"));
		BoxLayout lt = new BoxLayout(ops, BoxLayout.Y_AXIS);
		ops.setLayout(lt);
		
		qs = new JList[6];
		for(int i = 0; i < 6; i++) {
			qs[i] = new JList();
			qs[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			ops.add(qs[i]);
		}
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 5;
		main.add(ops, c);
		
		JPanel ops1 = new JPanel();
		ops1.setBorder(BorderFactory.createTitledBorder("Waiting time for queues"));
		BoxLayout lt1 = new BoxLayout(ops1, BoxLayout.Y_AXIS);
		ops1.setLayout(lt1);
		
		list = new JList[6];
		for(int i = 0; i < 6; i++) {
			list[i] = new JList();
			list[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			ops1.add(list[i]);
		}
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		main.add(ops1, c);
		
		JPanel ops2 = new JPanel();
		ops2.setBorder(BorderFactory.createTitledBorder("Results"));
		BoxLayout lt2 = new BoxLayout(ops2, BoxLayout.Y_AXIS);
		ops2.setLayout(lt2);
		ops2.add(avg);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		main.add(ops2, c);
		
		this.add(main);
		
	}
	/**
	 * method that helps to find errors
	 * @param errMessage
	 */
	public void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}
	/**
	 * method that creates an event for the start button
	 * @param start
	 */
	public void addStartListener(ActionListener start) {
		this.start_button.addActionListener(start);
		}
	//these methods get the user input from the text fields
	public String getMinArrival() {
		return min_arrival_int.getText();
	}
	
	public String getMaxArrival() {
		return this.max_arrival_int.getText();
	}
	
	public String getMinService() {
		return this.min_service_time.getText();
	}
	
	public String getMaxService() {
		return this.max_service_time.getText();
	}
	
	public String getNrQueues() {
		return this.no_queues.getText();
	}
	
	public String getClients() {
		return this.no_clients.getText();
	}
}
