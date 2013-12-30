package logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



import javax.swing.table.DefaultTableModel;

public class Util {

	public static boolean isNumber(String str) {
		String exp = "\\d*";
		return str.matches(exp);
	}

	public static boolean isDecimal(String str) {
		String exp = "[0-9.]+";
		return str.matches(exp);
	}

	public static boolean isValidInt(int x, int lowerbound, int upperbound) {
		if (x >= lowerbound && x <= upperbound)
			return true;
		return false;

	}

	public static DefaultTableModel CreateTableHeader(String[] columnNames) {
		DefaultTableModel model = new DefaultTableModel();
		for (String str : columnNames) {
			model.addColumn((Object) str);
		}
		return model;
	}
	
	public static void SaveContacts(ArrayList<Contact> list){
		
		try{
			FileOutputStream fileOut = new FileOutputStream("contact.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(list);
			out.close();
			fileOut.close();
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static ArrayList<Contact> LoadContacts(){
		try{
			FileInputStream fileIn = new FileInputStream("contact.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList<Contact> list = (ArrayList<Contact>)in.readObject();
			in.close();
			fileIn.close();
			return list;
		}
		
		catch(Exception ex){
			ex.printStackTrace();
			return new ArrayList<Contact>();
		}
	}

}
