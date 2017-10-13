package kr.or.dgit.jdbc_application_study.common;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import kr.or.dgit.jdbc_application_study.dao.TitleDao;
import kr.or.dgit.jdbc_application_study.dto.Title;

@SuppressWarnings("serial")
public class ComboBoxComponent extends JPanel {

	public ComboBoxComponent(String title) {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		//콤보박스 생성형태 예시 참고
		//comboBox.setModel(new DefaultComboBoxModel(TitleDao.getInstance().selectItemByAll()));
		List<Title> arrTitle = new ArrayList<>();  
		try {
			arrTitle = TitleDao.getInstance().selectItemByAll();
			for(int i=0 ; i<arrTitle.size() ; i++){
				arrTitle.get(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(comboBox);
	}
	
	public void setModelByTitle(){
			
		
	}

}
