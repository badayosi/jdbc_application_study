package kr.or.dgit.jdbc_application_study.content;

import javax.swing.JPanel;
import java.awt.GridLayout;
import kr.or.dgit.jdbc_application_study.common.TextFieldComponent;

@SuppressWarnings("serial")
public class EmployeeContent extends JPanel {

	public EmployeeContent() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		TextFieldComponent pEmpNo = new TextFieldComponent("사원번호");
		add(pEmpNo);
		
		TextFieldComponent pEmpName = new TextFieldComponent("사원명");
		add(pEmpName);
		
		JPanel pTitleNo = new JPanel();
		add(pTitleNo);
		
		JPanel pManager = new JPanel();
		add(pManager);
		
		JPanel pSalary = new JPanel();
		add(pSalary);
		
		JPanel pDeptNo = new JPanel();
		add(pDeptNo);

	}

}
