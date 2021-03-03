package f21as_coursework;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateOrders {
	public static void CreateOrders(){
		JButton but1 = new JButton("cake £7.60");
		but1.addActionListener(new ActionListener() {
			
			CustomerList customer = new CustomerList();
			
			@Override
			public void actionPerformed(ActionEvent a) {
				String m = JOptionPane.showInputDialog("Please enter a existing customer id or click new customer");
						if (OrderList.existingID(m));
						else{JOptionPane.showMessageDialog(null,"That Customer does not exist please re-enter or create a new customer");;
						}
							try {
								System.out.println(OrderList.getNumberOfOrders()+1 +"," + OrderList.findById(m)+","+
										CurrentTime.getCurrentTime()+","+"[FOOD101]");
								
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							};							
									//customer.getCustomer(String.valueOf(m)).getDetails());
									
				//OrderList.addDetails(OrderList.getNumberOfOrders(), 7, ,);
				JOptionPane.showMessageDialog(null, "Added Cake to the order");
			}
		});
		JButton but2 = new JButton("pasta £3.20");
		but2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent b) {
				JOptionPane.showMessageDialog(null, "Added pasta to the order");
				
			}
		});
		JButton but3 = new JButton("Not Coke a Cola £2.50");
		but3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent c) {
				JOptionPane.showMessageDialog(null, "Added Not Coke a Cola to the order");
				}
		});
		JButton but4 = new JButton("Sandwich");
		but4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent d) {
				JOptionPane.showMessageDialog(null, "Added Sandwhich to the order");
				
			}
		});JButton but5 = new JButton("Coffe");
		but5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Added Coffe to the order");
				
			}
		});
		JButton but6 = new JButton("Tea");
		but6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent f) {
				JOptionPane.showMessageDialog(null, "Added Tea to the order");
				
			}
			
		});
		JButton name = new JButton("Enter Name");
		name.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent f) {
				JOptionPane.showMessageDialog(null, "Added Tea to the order");
				
			}
			
		});
		
		JLabel inputlabel = new JLabel("Enter Name");
		JTextField texinput = new JTextField(10); // accepts 20 characters
		
		JPanel input = new JPanel();
		input.add(inputlabel);
		input.add(texinput);
		input.add(name);
		
		JPanel west = new JPanel();
		west.add(but1);
		west.add(but4);
		
		JPanel center = new JPanel();
		center.add(but2);
		center.add(but5);
		
		JPanel east = new JPanel();
		east.add(but3);
		east.add(but6);
		
		JFrame frame = new JFrame("New Order");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500,200);
		frame.getContentPane().add(BorderLayout.NORTH,texinput);
		frame.getContentPane().add(BorderLayout.WEST,west);
		frame.getContentPane().add(BorderLayout.CENTER,center);
		frame.getContentPane().add(BorderLayout.EAST,east);
		frame.setVisible(true);
	}
}
