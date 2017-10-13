package kr.or.dgit.jdbc_application_study.content;

import javax.swing.JPanel;
import java.awt.GridLayout;
import kr.or.dgit.jdbc_application_study.common.TextFieldComponent;
import kr.or.dgit.jdbc_application_study.dto.Title;

@SuppressWarnings("serial")
public class TitleContent extends JPanel {
	private TextFieldComponent pTitleNo;
	private TextFieldComponent pTitleName;

	public TitleContent() {
		setLayout(new GridLayout(0, 1, 5, 5));
		
		pTitleNo = new TextFieldComponent("직책번호");
		add(pTitleNo);
		
		pTitleName = new TextFieldComponent("직책이름");
		add(pTitleName);
	}
	
	public Title getContent(){
		int titleNo = Integer.parseInt(pTitleNo.getTextValue());
		String titleName = pTitleName.getTextValue();
		return new Title(titleNo, titleName);
	}
	
	public void setContent(Title title){
		pTitleNo.setTextValue(title.getTitleNo()+"");
		pTitleName.setTextValue(title.getTitleName());
	}
	
	public void isEmptyCheck() throws Exception{
		pTitleNo.isEmptyCheck();
		pTitleName.isEmptyCheck();
	}
}
