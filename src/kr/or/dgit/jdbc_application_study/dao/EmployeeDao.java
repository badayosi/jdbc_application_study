package kr.or.dgit.jdbc_application_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.jdbc_application_study.dto.Department;
import kr.or.dgit.jdbc_application_study.dto.Employee;
import kr.or.dgit.jdbc_application_study.dto.Title;
import kr.or.dgit.jdbc_application_study.jdbc.DBCon;

public class EmployeeDao implements SqlDao<Employee> {
	private static final EmployeeDao instance = new EmployeeDao();

	public static EmployeeDao getInstance() {
		return instance;
	}

	public EmployeeDao() {}

	@Override
	public void insertItem(Employee item) throws SQLException {
		String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)";
		Connection con = DBCon.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, item.getEmpNo());
			pstmt.setString(2, item.getEmpName());
			pstmt.setInt(3, item.getTitle().getTitleNo());
			pstmt.setInt(4, item.getManager().getEmpNo());
			pstmt.setInt(5, item.getSalary());
			pstmt.setInt(6, item.getDno().getDeptNo());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void deleteItem(Employee item) throws SQLException {
		String sql = "DELETE FROM employee WHERE empno = ?";
		Connection con = DBCon.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, item.getEmpNo());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void updateItem(Employee item) throws SQLException {
		String sql = "UPDATE employee SET empname = ? WHERE empno =?";
		Connection con = DBCon.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, item.getEmpName());
			pstmt.setInt(2, item.getEmpNo());
			pstmt.executeUpdate();
		}
	}

	@Override
	public Employee selectItemByNo(Employee item) throws SQLException {
		String sql = "SELECT * FROM employee WHERE empno = ?";
		Employee emp = null;
		try(PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);){
			pstmt.setInt(1, item.getEmpNo());
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()){
					emp = getEmployee(rs);
				}
			}
		}
		return emp;
	}

	

	@Override
	public List<Employee> selectItemByAll() throws SQLException {
		List<Employee> lists = new ArrayList<>();
		String sql = "SELECT * FROM employee";
		try(PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
			while(rs.next()){
				lists.add(getEmployee(rs));
			}
		}
		return lists;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException{
		int empNo = rs.getInt(1);
		String empName = rs.getString(2);
		Title title = TitleDao.getInstance().selectItemByNo(new Title(rs.getInt(3)));
		Employee manager;
		if(empNo != 4377)
			manager = EmployeeDao.getInstance().selectItemByNo(new Employee(rs.getInt(4)));
		else
			manager = new Employee(4377);
		int salary = rs.getInt(5);
		Department dno = DepartmentDao.getInstance().selectItemByNo(new Department(rs.getInt(6)));
		return new Employee(empNo, empName, title, manager, salary, dno);
	}
}
