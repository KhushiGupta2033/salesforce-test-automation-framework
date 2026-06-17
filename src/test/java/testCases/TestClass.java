package testCases;

import genericUtilities.BaseClass;
import genericUtilities.DateTime;
import genericUtilities.ExcelFileUtility;
import objectRepository.AccountsPage;
import objectRepository.CampaignsPage;
import objectRepository.ContactsPage;
import objectRepository.FilesPage;
import objectRepository.LeadsPage;
import objectRepository.NewAccountPage;
import objectRepository.OpportunityPage;
import objectRepository.TaskPage;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.*;

public class TestClass extends BaseClass{
	
	@Test
	public void verifyAllObjects() {

		OpportunityPage op= new OpportunityPage(driver);
		LeadsPage lp= new LeadsPage(driver);
		TaskPage tp= new TaskPage(driver);
		FilesPage fp= new FilesPage(driver);
		AccountsPage ap= new AccountsPage(driver);
		ContactsPage cp= new ContactsPage(driver);
		CampaignsPage camp= new CampaignsPage(driver);
		
		System.out.println("Opportunity Object Verificatrion Started");
		op.OppObjectVerification();
		
		System.out.println("Lead Object Verificatrion Started");
		lp.leadObjVerification();
		
		System.out.println("Task Object Verificatrion Started");
		tp.taksObjectVerification();
		
		System.out.println("Files Object Verificatrion Started");
		fp.fileObjectVerification();
		
		System.out.println("Accounts Objcet Verfication Started");
		ap.accountsObjectVerfication();
		
		System.out.println("Contacts Objcet Verfication Started");
		cp.contactsObjectVerification();
		
		System.out.println("Campaigns Object Verification Stareted");
		camp.campaignObjectVerification();
		
		System.out.println("Test Case-1 completed");
		
	}
	
	@Test
	public void createAccount() throws EncryptedDocumentException, IOException {
		AccountsPage ap= new AccountsPage(driver);
		NewAccountPage np= new NewAccountPage(driver);
		ExcelFileUtility ex= new ExcelFileUtility("./resources/Salesforce.xlsx");
		DateTime dt= new DateTime();
		
		ap.clickAccounts();
		
		Map<String, Object> map=ex.getDataFromSingleRowAsMap("Salesforce", "Tc_01", 0);
		String name=(String) ex.getValueFromMap(map, "Account_Name");
		String website=(String) ex.getValueFromMap(map, "Webiste");
		String type=(String) ex.getValueFromMap(map, "Type");
		String industry=(String) ex.getValueFromMap(map, "Industry");
		String street=(String) ex.getValueFromMap(map, "Street");
		String city=(String) ex.getValueFromMap(map, "City");
		String code=(String) ex.getValueFromMap(map, "Code");
		String state=(String) ex.getValueFromMap(map, "State");
		String country=(String) ex.getValueFromMap(map, "Country");
		name=name+dt.currentDateTime();
		np.createAccount(name, website, type, industry, street, city, code, state, country);
		
        
	}
	
	

}
