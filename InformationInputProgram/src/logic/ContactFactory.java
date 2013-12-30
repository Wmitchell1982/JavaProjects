package logic;

public class ContactFactory {

public static Contact Create(int Id, String fName, String lName, String email, int age, String cPhone){
		
		Contact newContact = null;
		
		newContact = new Contact();
		newContact.setiD(Id);
		newContact.setFirstName(fName);
		newContact.setLastName(lName);
		newContact.setEmailAddress(email);
		newContact.setAge(age);
		newContact.setCellPhone(cPhone);
				
		return newContact;
		
	}

}
