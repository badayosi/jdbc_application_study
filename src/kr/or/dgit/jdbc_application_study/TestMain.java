package kr.or.dgit.jdbc_application_study;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import kr.or.dgit.jdbc_application_study.common.TextFieldComponent;
import kr.or.dgit.jdbc_application_study.content.DepartmentContent;
import kr.or.dgit.jdbc_application_study.content.TitleContent;
import kr.or.dgit.jdbc_application_study.dao.DepartmentDao;
import kr.or.dgit.jdbc_application_study.dao.EmployeeDao;
import kr.or.dgit.jdbc_application_study.dao.TitleDao;
import kr.or.dgit.jdbc_application_study.dto.Department;
import kr.or.dgit.jdbc_application_study.dto.Employee;
import kr.or.dgit.jdbc_application_study.dto.Title;
import kr.or.dgit.jdbc_application_study.jdbc.DBCon;
import kr.or.dgit.jdbc_application_study.jdbc.jdbcUtil;

public class TestMain {

	public static void main(String[] args) {
		//testDBCon();
		//testDepartmentDao();
		//testTitleDao();
		//testEmployeeDao();
		
		//testTextFieldComponent();
		//testDepartmentComponent();
		//testTitleComponent();
		
		
	}

	private static void testTitleComponent() {
		TitleContent tfc = new TitleContent();
		tfc.setContent(new Title(10,"이사"));

		
		JButton btn = new JButton("확인");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tfc.isEmptyCheck();
					Title title = tfc.getContent();
					JOptionPane.showMessageDialog(null, title);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}				
			}
		});
		
		testContent(tfc, btn);
	}

	private static void testDepartmentComponent() {
		DepartmentContent tfc = new DepartmentContent();
		tfc.setContent(new Department(1, "개발", 10));
		
		JButton btn = new JButton("확인");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tfc.isEmptyCheck();
					Department dept = tfc.getContent();
					JOptionPane.showMessageDialog(null, dept);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}				
			}
		});
		
		testContent(tfc, btn);
	}

	private static void testTextFieldComponent() {
		TextFieldComponent tfc = new TextFieldComponent("테스트");
		
		JButton btn = new JButton("확인");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tfc.isEmptyCheck();
					JOptionPane.showMessageDialog(null, tfc.getTextField());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}				
			}
		});
		
		testContent(tfc, btn);
	}

	private static void testContent(JPanel panel, JButton btn) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setBounds(10, 10, 200, 150);
		jf.add(panel);
		jf.add(btn, BorderLayout.SOUTH);
		jf.setVisible(true);
	}

	private static void testEmployeeDao() {
		Employee test = new Employee(9999,"가나다",new Title(4),new Employee(4377),1000000,new Department(3));
		
		testEmpInsert(test);
		testEmpSelectAll();
		System.out.println("---------------");
		test.setEmpName("마바사");
		testEmpUpdate(test);
		testEmpSelectNo(test);
		System.out.println("---------------");
		testEmpDelete(test);
		testEmpSelectAll();
	}

	private static void testEmpSelectNo(Employee test) {
		try {
			System.out.println(EmployeeDao.getInstance().selectItemByNo(test));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testEmpSelectAll() {
		try {
			List<Employee> eAll = EmployeeDao.getInstance().selectItemByAll();
			for(Employee e : eAll){
				System.out.println(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testEmpUpdate(Employee test) {
		try {
			EmployeeDao.getInstance().updateItem(test);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testEmpDelete(Employee test) {
		try {
			EmployeeDao.getInstance().deleteItem(test);
			JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다.");
			e.printStackTrace();
		}
	}

	private static void testEmpInsert(Employee test) {
		try {
			EmployeeDao.getInstance().insertItem(test);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testTitleDao() {
		Title tit = new Title(7,"이사");
		
		testTitleInsert(tit);
		testTitleShowAll();
		System.out.println("---------------");
		tit.setTitleName("퇴사");
		try {
			TitleDao.getInstance().updateItem(tit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		testTitleShowByNo(tit);
		System.out.println("---------------");
		testTitleDelete(tit);
		testTitleShowAll();
	}

	private static void testTitleShowByNo(Title tit) {
		try {
			Title tTitle = TitleDao.getInstance().selectItemByNo(tit);
			System.out.println(tTitle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testTitleShowAll() {
		try {
			List<Title> tLists = TitleDao.getInstance().selectItemByAll();
			for(Title tTest : tLists)
				System.out.println(tTest);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testTitleDelete(Title tit) {
		try {
			TitleDao.getInstance().deleteItem(tit);
			JOptionPane.showMessageDialog(null, "직급삭제에 성공 하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testTitleInsert(Title tit) {
		try {
			TitleDao.getInstance().insertItem(tit);
			JOptionPane.showMessageDialog(null, "직급추가에 성공 하였습니다.");
		} catch (SQLException e) {
			System.err.printf("%s - %s\n", e.getErrorCode(), e.getMessage());
			if(e.getErrorCode() == 1062){
				JOptionPane.showMessageDialog(null, "직급번호가 중복 되었습니다.");
			}
		}
	}

	private static void testDepartmentDao() {
		Department dept = new Department(4, "마케팅", 10);
		
		testInsert(dept);
		testListAll();
		System.out.println("----------");
		dept.setDeptName("마케팅2");
		testUpdate(dept);
		testSearch(dept);
		System.out.println("----------");
		testDelete(dept);
		testListAll();
	}

	private static void testUpdate(Department dept) {
		try {
			DepartmentDao.getInstance().updateItem(dept);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testSearch(Department dept) {
		try {
			Department searchDept = DepartmentDao.getInstance().selectItemByNo(dept);
			System.out.println(searchDept);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testListAll() {
		try {
			List<Department> lists = DepartmentDao.getInstance().selectItemByAll();
			for(Department dept : lists)
				System.out.println(dept);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testDelete(Department dept) {
		try {
			DepartmentDao.getInstance().deleteItem(dept);
			JOptionPane.showMessageDialog(null, "부서가 삭제 되었습니다.");
		} catch (SQLException e) {
			System.err.printf("%s - %s\n", e.getErrorCode(), e.getMessage());
			JOptionPane.showMessageDialog(null, "부서 삭제에 실패하였습니다.");
		}
	}

	private static void testInsert(Department dept) {
		try {
			DepartmentDao.getInstance().insertItem(dept);
			JOptionPane.showMessageDialog(null, "부서번호가 추가 되었습니다.");
		} catch (SQLException e) {
			System.err.printf("%s - %s\n", e.getErrorCode(), e.getMessage());
			if(e.getErrorCode() == 1062){
				JOptionPane.showMessageDialog(null, "부서번호가 중복 되었습니다.");
			}
		}
	}

	private static void testDBCon() {
		DBCon dbCon = DBCon.getInstance();
		Connection connection = dbCon.getConnection();
		System.out.println(connection);
		
		jdbcUtil.close(connection);
	}
}
