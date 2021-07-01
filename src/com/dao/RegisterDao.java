package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bean.DiseaseBean;
import com.bean.LoginBean;
import com.bean.Registrationbean;

public class RegisterDao {
	static Connection con;

	public int saveData(Registrationbean reg){
		con=MyConnection.getConnection();

		PreparedStatement ps=null;
		int i=0;
		try {
			ps=con.prepareStatement("Insert into HCRegister values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,reg.getRegFname());
			ps.setString(2,reg.getRegMname());
			ps.setString(3,reg.getRegLname());
			ps.setString(4,reg.getDob());
			ps.setString(5,reg.getGender());
			ps.setString(6,reg.getState());
			ps.setString(7, reg.getCity());
			ps.setString(8,reg.getEmail());
			ps.setString(9,reg.getMobno());
			ps.setString(10,reg.getPass());
			ps.setString(11,reg.getHealthid());
			i=ps.executeUpdate();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public boolean ValidateUser(LoginBean l){
		boolean b=false;
		PreparedStatement ps=null;
		con=MyConnection.getConnection();
		try {
			ps=con.prepareStatement("select * from hcregister where email=? and pass=?");
			ps.setString(1,l.getUsername());
			ps.setString(2,l.getPassword());
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return b;
	}
	
	public Registrationbean DisplayUser(String user_name){
		boolean b=false;
		PreparedStatement ps=null;
		Registrationbean reg=new Registrationbean();
		List<Registrationbean> ls=null;
		con=MyConnection.getConnection();
		try {
			ps=con.prepareStatement("select * from hcregister where email=?");
			ps.setString(1,user_name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				reg.setRegFname(rs.getString(1));
				reg.setRegMname(rs.getString(2));
				reg.setRegLname(rs.getString(3));
				reg.setDob(rs.getString(4));
				reg.setGender(rs.getString(5));
				reg.setState(rs.getString(6));
				reg.setCity(rs.getString(7));
				reg.setEmail(rs.getString(8));
				reg.setMobno(rs.getString(9));
				reg.setPass(rs.getString(10));		
				reg.setHealthid(rs.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reg;		
	}
	
	public int save_disease_Rec_Data(DiseaseBean mb){
		con=MyConnection.getConnection();
		PreparedStatement ps=null;
		int i=0;
		try {
			ps=con.prepareStatement("Insert into diseaseinfo values(?,?,?,?,?,?,?)");
			ps.setString(1,mb.getDNAME());
			ps.setString(2,mb.getDDATE());
			ps.setString(3,mb.getDSYMP());
			ps.setString(4,mb.getDMED());
			ps.setString(5,mb.getDLOC());
			ps.setString(6,mb.getDHEIR());
			ps.setString(7, mb.getDEMAIL());
			i=ps.executeUpdate();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
