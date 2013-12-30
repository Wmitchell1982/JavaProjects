package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ContactDAO {

	private ArrayList<Contact> list;
	
	
	public ArrayList<Contact> getList() {
		return list;
	}

	public void setList(ArrayList<Contact> list) {
		this.list = list;
	}

	public ContactDAO(){
		list = new ArrayList<Contact>();
	}//sets up arraylist from start
	
	public ArrayList<Contact> GetAll(){
		return list;
	}
	
	public void AddContact(Contact obj){
		list.add(obj);

	}
	
	
	// Save data to output file
    public void saveDataToFile(String sFileName) {
        try {

            FileWriter writer = new FileWriter(sFileName, true);

            for (Contact entry : list) {
            	writer.append(Integer.toString(entry.getiD()));
            	writer.append(',');
                writer.append(entry.getFirstName());
                writer.append(',');
                writer.append(entry.getLastName());
                writer.append(',');
                writer.append(entry.getEmailAddress());
                writer.append(',');
                writer.append(Integer.toString(entry.getAge()));
                writer.append(',');   
                writer.append(entry.getCellPhone());
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read data from output file
    public void readDataFromFile(String sFileName) {
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sFileName));
            String str;
            while ((str = reader.readLine()) != null) {
                String[] parts = str.split(",");
                Contact entry = new Contact();
                entry.setiD(Integer.parseInt(parts[0]));
                entry.setFirstName(parts[1]);
                entry.setLastName(parts[2]);
                entry.setEmailAddress(parts[3]);
                entry.setAge(Integer.parseInt(parts[4]));
                entry.setCellPhone(parts[5]);
                list.add(entry);
            }
            reader.close();
        } catch (IOException e) {
          
        } 
    
    
    }
	
	}


