import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class del extends JFrame implements ActionListener{

	public del() {}
	
	private JPanel paneldel;
	
	JTextField textField;
	JTextField textField_1;
	JButton btn;
	JButton btn_1;
		
	public void createD_1(){
			
		paneldel= new JPanel();
		paneldel.setLayout(null);
		getContentPane().add(paneldel);
		   
		this.setTitle("물품 삭제");
		
		JPanel paneldel = new JPanel();
		getContentPane().add(paneldel, BorderLayout.CENTER);
		paneldel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setBounds(31, 54, 62, 28);
		paneldel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(73, 57, 116, 21);
		paneldel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("가격");
		lblNewLabel_1.setBounds(31, 103, 57, 15);
		paneldel.add(lblNewLabel_1);		
		
		textField_1 = new JTextField();
		textField_1.setBounds(73, 100, 116, 21);
		paneldel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btn = new JButton("삭제");
		btn.setBounds(12, 152, 97, 23);
		paneldel.add(btn);
		
		
		btn.addActionListener(this);
		
		JButton btn_1 = new JButton("확인");
		btn_1.setBounds(159, 152, 97, 23);
		paneldel.add(btn_1);
			
		
		setVisible(true);
		setSize(280, 280);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn){
			
			//창닫기 및 정보전달
			DTO dto=getViewData_1();
			DAO dao = new DAO();
			boolean ok = dao.update(dto);
			
			if(ok){
				JOptionPane.showMessageDialog(this, "삭제되었습니다");
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "삭제 실패");
			}
			
			}
		}
		
		public DTO getViewData_1(){
			DTO dto = new DTO();
			String name = textField.getText();
			String price = textField_1.getText();

			//dto에 담음
			dto.setname(name);
			dto.setprice(price);
			
			return dto;
		}
}