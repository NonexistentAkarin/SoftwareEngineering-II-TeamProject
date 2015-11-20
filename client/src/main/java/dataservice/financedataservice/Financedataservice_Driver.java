package dataservice.financedataservice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;

import po.*;
import util.*;
import dataservice.financedataservice.FinanceDataService;
public class Financedataservice_Driver {
	public void driver(FinanceDataService financedataservice) throws FileNotFoundException, IOException{
		StaffPO staff=new StaffPO(123459,"JOHN",OrgType.HALL,Permission.MANAGER);
		InstitutePO institute=new InstitutePO(City.BEIJING,OrgType.HALL,12345);
		TimePO time=new TimePO(2015,1,1,1,1,1);
		ReceiptPO po=new ReceiptPO(staff,  institute, 100.21, time);
	    long m=123458;
	    StaffPO staff2=new StaffPO(123457,"LEBRON",OrgType.HALL,Permission.MANAGER);
	    ReceiptPO p=new ReceiptPO(staff2,  institute, 100.21, time);
	    
	
	    
	    financedataservice.delete(po);
	    
	   
	  
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		FinanceDataService service=new Financedataservice_Stub();
		Financedataservice_Driver driver=new Financedataservice_Driver();
		driver.driver(service);
	}
	

}
