package kr.or.dgit.jdbc_application_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.jdbc_application_study.dto.Title;
import kr.or.dgit.jdbc_application_study.jdbc.DBCon;

public class TitleDao implements SqlDao<Title> {
	private static final TitleDao instance = new TitleDao();
	
	public static TitleDao getInstance() {
		return instance;
	}

	public TitleDao() {}

	@Override
	public void insertItem(Title item) throws SQLException {
		String sql = "INSERT INTO title VALUES(?, ?)";
		Connection con = DBCon.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, item.getTitleNo());
			pstmt.setString(2, item.getTitleName());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void deleteItem(Title item) throws SQLException {
		String sql = "DELETE FROM title WHERE titleno = ?";
		Connection con = DBCon.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, item.getTitleNo());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void updateItem(Title item) throws SQLException {
		String sql = "UPDATE title SET titlename = ? WHERE titleno =?";
		Connection con = DBCon.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, item.getTitleName());
			pstmt.setInt(2, item.getTitleNo());
			pstmt.executeUpdate();
		}
	}

	@Override
	public Title selectItemByNo(Title item) throws SQLException {
		String sql = "SELECT titleno, titlename FROM title WHERE titleno = ?";
		Title tit = null;
		try(PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);){
			pstmt.setInt(1, item.getTitleNo());
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()){
					tit = getTitle(rs);
				}
			}
		}
		return tit;
	}

	@Override
	public List<Title> selectItemByAll() throws SQLException {
		List<Title> lists = new ArrayList<>();
		String sql = "SELECT titleno, titlename FROM title";
		try(PreparedStatement pstmt = DBCon.getInstance().getConnection().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
			while(rs.next()){
				lists.add(getTitle(rs));
			}
		}
		return lists;
	}

	private Title getTitle(ResultSet rs) throws SQLException{
		int titleNo = rs.getInt(1);
		String titleName = rs.getString(2);
		return new Title(titleNo, titleName);
	}

/*	public String findTitle(int titleNo) throws SQLException{
		Title find = selectItemByNo(new Title(titleNo, ""));
		return find.getTitleNo();
	}*/
}
