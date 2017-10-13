package kr.or.dgit.jdbc_application_study.common;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TextFieldComponent extends JPanel {
	private JTextField textField;

	public TextFieldComponent(String title) {
		setLayout(new GridLayout(1, 0, 10, 0));
		
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNewLabel);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);

	}
	
	public String getTextValue(){
		return textField.getText().trim();
	}
	
	public void setTextValue(String value){
		textField.setText(value);
	}

	public JTextField getTextField() {
		return textField;
	}
	
	public void isEmptyCheck() throws Exception{
		if(getTextValue().equals("")){
			textField.requestFocus();
			throw new Exception("공백이 존재합니다");
		}
	}
}
