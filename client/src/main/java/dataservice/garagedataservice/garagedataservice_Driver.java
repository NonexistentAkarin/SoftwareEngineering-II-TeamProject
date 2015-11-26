package dataservice.garagedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GarageBodyPO;
import po.GaragePO;
import po.GaragePlacePO;
import po.TimePO;
import po.garageitem;



public class garagedataservice_Driver {
public void drive(garagedataservice service) throws RemoteException{
		
	garageitem item=new garageitem(new TimePO(1,1,1,1,1,1), 123457);
	garageitem item2=new garageitem(new TimePO(1,1,1,1,1,1), 1234527);
	Garage g=service.init();
	service.insert(g,item);
	service.insert(g,item);
	service.insert(g,item);
	service.insert(g,item);
	service.insert(g, item2);
	 service.insert(g,item);
    service.delete(g,item2);
	System.out.println(g.getTemp());
	System.out.println(g.getSize());
	System.out.println(service.rate(g));
	 service.show(g);
	
	}
	
	public static void main(String[] args) throws RemoteException {
		garagedataservice service=new garagedataservice_Stub();
		garagedataservice_Driver driver=new garagedataservice_Driver();
		driver.drive(service);
	}
}
