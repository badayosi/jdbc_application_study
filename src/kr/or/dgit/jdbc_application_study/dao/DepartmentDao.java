package kr.or.dgit.jdbc_application_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.jdbc_application_study.dto.Department;
import kr.or.dgit.jdbc_application_study.jdbc.DBCon;

public class DepartmentDao implements SqlDao<Department> {
	private static final DepartmentDao instance = new DepartmentDao();

	public DepartmentDao() {}

	public static DepartmentDao getInstance() {
		return instance;
	}

	@Override
	public void insertItem(Department item) throws SQLException{
		String sql = "INSERT INTO department VALUES(?, ?, ?)";
		Connection con = DBCon.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, item.getDeptNo());
			pstmt.setString(2, item.getDeptName());
			pstmt.setInt(3, item.getFloor());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void deleteItem(Department item) throws SQLException{
		String sql = "DELETE FROM department where deptno = ?";
		Connection con = DBCon.getInstance().getConnection();
		//PreparedStatement는 Auto Close가 구현되어있기 때문에 try에서 자동으로 열고 닫는것이 가능하다.
		try(PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, item.getDeptNo());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void updateItem(Department item) throws SQLException{
		String sql = "UPDATE department set deptname = ?, floor = ? where deptno = ?";
		Connection con = DBCon.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, item.getDeptName());
			pstmt.setInt(2, item.getFloor());
			pstmt.setInt(3, item.getDeptNo());
			pstmt.executeUpdate();
		}
	}

	// 중복 Try Tip..! 중요하다.
	// Query 날리기 전 Set을 한다. 
	@Override
	public Department selectItemByNo(Department item) throws SQLException{
		String sql = "SELECT deptno, deptname, floor from department where deptno = ?";
		Department dept = null;
		try(PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);){
			pstmt.setInt(1, item.getDeptNo());
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()){
					dept = getDepartment(rs);
				}
			}
		}
		return dept;
	}

	@Override
	public List<Department> selectItemByAll() throws SQLException{
		List<Department> lists = new ArrayList<>();
		String sql = "SELECT deptno, deptname, floor from department";
		try(PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
			while(rs.next()){
				lists.add(getDepartment(rs));
			}
		}
		return lists;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt(1);
		String deptName = rs.getString(2);
		int floor = rs.getInt(3);
		return new Department(deptNo, deptName, floor);
	}

}
