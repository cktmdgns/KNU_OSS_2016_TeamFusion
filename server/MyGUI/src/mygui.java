import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class mygui extends JFrame implements ActionListener {
	
	private JTextField textField_1;
	
	private JPanel panel_main;

	private JButton button_view_goods;
	private JButton button_view_event;
	private JButton button_search;
	private JButton button_add;
	
	private JComboBox comboBox_corner_search;
	
	private JTextField textField_name_search;
	
	private JLabel label_view_top;
	private JLabel label_top_name;
	private JScrollPane total_List_Scroll;
	private JList list;

	

	public mygui() {
		
		panel_main = new JPanel();
		panel_main.setLayout(null);
		panel_main.setBounds(0, 0, 500, 500);
		getContentPane().add(panel_main);


		label_top_name = new JLabel("마트안내 프로그램");
		label_top_name.setBounds(194, 10, 135, 30);
		panel_main.add(label_top_name);

		button_view_goods = new JButton("물품정보");
		button_view_goods.setBounds(464,70,96,30);
		button_view_goods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_main.add(button_view_goods);

		button_view_event= new JButton("행사정보");
		button_view_event.setBounds(464,110,96,30);
		button_view_event.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panel_main.add(button_view_event);


		JLabel label_corner_check = new JLabel("코너");
		label_corner_check.setBounds(26,58,37,23);
		panel_main.add(label_corner_check);
		
		String[] corner_category={"생선/해산","정육/계란","과일","채소/건나물","쌀/잡곡","김/미역/건어",
				"유제품/냉장음료","두부/김치/소스","냉동식품/아이스크림","음료/생수","커피/차",
				"과자","라면","식용유/조미료/밀가루","통조림/캔",
				"장류/케찹/소스","세제/화장지","헤어/세안/바디","랩/호일/고무장갑","조리/주방용품/그릇"};		
		comboBox_corner_search = new JComboBox(corner_category);
		comboBox_corner_search.setBounds(59,58,96,23);
		panel_main.add(comboBox_corner_search);
		
		JLabel label_name_check = new JLabel("이름");
		label_name_check.setBounds(170,58,37,23);
		panel_main.add(label_name_check);
		
		textField_name_search = new JTextField();
		textField_name_search.setBounds(204,55,150,30);
		panel_main.add(textField_name_search);
		
		

		button_search = new JButton("검색");
		button_search.setBounds(366,53,72,33);
		button_search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String str = textField_name_search.getText();

			}
		});
		panel_main.add(button_search);
		
		
		
		
		JButton button_add = new JButton("추가");
		button_add.setBounds(366,96,72,30);
		button_add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				addd addd1 = new addd();
				addd1.createD();

			}
		});
		panel_main.add(button_add);
		
		JLabel label_top_goods = new JLabel(" 이름               무게               가격               위치      ");
		label_top_goods.setBounds(86,135,285,26);
		panel_main.add(label_top_goods);
		

		total_List_Scroll = new JScrollPane();
		total_List_Scroll.setBounds(26, 171, 397, 320);
		GridBagConstraints gbc_total_List_Scroll = new GridBagConstraints();
		gbc_total_List_Scroll.gridheight = 2;
		gbc_total_List_Scroll.gridy = 0;
		panel_main.add(total_List_Scroll, gbc_total_List_Scroll);
		
		list = new JList();
		total_List_Scroll.setViewportView(list);

		setSize(600,700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
	

}




