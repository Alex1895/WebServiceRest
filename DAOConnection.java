package service_employee;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import service_file.conbd;
public class DAOConnection extends conbd { 
	public static void DAO(){ //select and do joins
		Statement statment = null;
		ResultSet rs = null ;
		try{
			statment = connect.createStatement();
			statment.executeQuery("USE , 'HCL_DB'");
			System.out.println(rs);
			/* Metodos de reglas de negocio */
			ArrayList<Employee> List_Emp_Result = new ArrayList<Employee>();
			final int bonus = 25;
			
			rs = statment.executeQuery("SELECT * FROM employee INNER JOIN empform ON employee.name = empform.name"); //Get all information about the both tables where the name are same 
			for( int i = 0; rs.next();i++){
			System.out.println(rs);  
			}
			// query para comparar las dos tablas si dijo que trabajaria el domingo y si fue a trabajar el dia que dijo
			rs = statment.executeQuery("SELECT * FROM employee INNER JOIN empform ON employee.days = empform.days");
			System.out.println(rs);
		while (rs.findColumn("days")== 7){ //if he work in sunday or not, also check the work time in sunday for bonus 
				List_Emp_Result.add((Employee) rs);
				System.out.println("he work in sunday pay 25% more"+ bonus);
			 if(rs.findColumn("horusin")== 13 && rs.findColumn("horusout")== 21){
				 List_Emp_Result.add((Employee) rs);
				 System.out.println("He arrive early"+bonus);
			}else if(rs.findColumn("horusin") > 13 ){
				 List_Emp_Result.add((Employee) rs);
				 int rtbn = bonus - 50;
				 System.out.println("time delay - 50%  bonus"+rtbn);
			}else if(rs.findColumn("horus") >= 8){
				List_Emp_Result.add((Employee) rs);
				System.out.println("pay more he work all sunday"+bonus);
			}else{
				System.out.println("no bonus");
			}
		}
		// query para hacer la regla de negocio SHIFT
			ArrayList<Employee> List_Emp_Shift = new ArrayList<Employee>();
			final int bonus_shift = 100;
			// dos inner join para comparar 
			rs = statment.executeQuery("SELECT * FROM employee INNER JOIN empform ON employee.days = empform.days" +
					"SELECT * FROM employee INNER JOIN empform ON employee.role = empform.role");
			System.out.println(rs);
		while ( rs.findColumn("role") == 1){ //1 == Shift 
			List_Emp_Shift.add((Employee) rs);
			System.out.println(List_Emp_Shift);	
			if(rs.findColumn("horusin")== 13 && rs.findColumn("horusout")== 21){
				 List_Emp_Result.add((Employee) rs);
				 System.out.println("He arrive early"+bonus_shift);
			}else if(rs.findColumn("horusin") > 13 ){
				int count = 0; //contador para contar las veces que llego tarde un empleado  
				for (int j = 0; j == 2; j++ ){
					if(count >= j && count == rs.findColumn("horusin")){// si el contador es igual que j y el contador que las veces que a llegado tarde 
						List_Emp_Result.add((Employee) rs);
						 int rtbn = bonus_shift - 50;
						 System.out.println("time delay - 50%  bonus"+rtbn);
					}	
				}
			}else{
						System.out.println("no bonus");
		}
		}
		
		/*
			rs = statment.executeQuery("SELECT id,name,horus,days FROM employee where horus = 8"); //GENERAL 
			if (rs.findColumn("horus")!= 8 && rs.findColumn("total_horus")!= 37){
				System.out.println();
			}else{
				List_Emp_Result.add((Employee) rs);
				System.out.println(List_Emp_Result);
			}
			
			rs = statment.executeQuery("DESCRIBE, employee");
			System.out.println(rs);
			rs = statment.executeQuery("DESCRIBE, empform");
			System.out.println(rs2);
			rs = statment.executeQuery("SELECT id,name,horus FROM employee");
			System.out.println(rs);
			rs = statment.executeQuery("SELECT * FROM empform");
			System.out.println(rs2);
			ArrayList<Employee> empList = new ArrayList<Employee>();
			ArrayList<Employee> List_Emp_M = new ArrayList<Employee>();
			
			if(empList.size() == 0 && List_Emp_M.size() == 0 ){
				System.out.println("array vacio");
			}else {
				empList.add((Employee) rs);
				System.out.println(empList);
				List_Emp_M.add((Employee)rs2);
				System.out.println(List_Emp_M);
			}*/		
		}catch(SQLException ex){
			System.out.println("Connection error"+ex);
		}
	}
	public static void main(String[] args) {
		DAO();
	}
}
