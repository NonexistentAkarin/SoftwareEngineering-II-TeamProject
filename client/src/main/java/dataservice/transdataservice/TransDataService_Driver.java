package dataservice.transdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CarPO;
import po.DriverPO;
import po.LoadingPO;
import po.TimePO;
import po.WarePO;
import util.DeliverType;
import util.Vehicle;

public class TransDataService_Driver {
	public void drive(TransDataService transDataService) throws RemoteException{
		WarePO po1=transDataService.findWare(1);
		System.out.println(po1.toString());
		transDataService.insertWare(new WarePO(1, 1, 1, "HaHa", "HaHa", DeliverType.ECO, 1, new TimePO(2015, 1, 1, 1, 1, 1)));
		transDataService.deleteWare(new WarePO(1, 1, 1, "HaHa", "HaHa", DeliverType.ECO, 1, new TimePO(2015, 1, 1, 1, 1, 1)));
	
		DriverPO po2=transDataService.findDriver(1);
		System.out.println(po2.toString());
		transDataService.insertDriver(new DriverPO(1, "HaHa", new TimePO(2015, 1, 1, 1, 1, 1), "1", "10000000000", "301", "Male", "1"));
		transDataService.deleteDriver(new DriverPO(1, "HaHa", new TimePO(2015, 1, 1, 1, 1, 1), "1", "10000000000", "301", "Male", "1"));

		CarPO po3=transDataService.findCar(1);
		System.out.println(po3.toString());
		transDataService.insertCar(new CarPO(Vehicle.CAR, "苏A HAHAH", 1, "Ha1s", 1, "2015-1-1", "2015-1-1", null));
		transDataService.deleteCar(new CarPO(Vehicle.CAR, "苏A HAHAH", 1, "Ha1s", 1, "2015-1-1", "2015-1-1", null));
		
		LoadingPO po4=transDataService.findLoading(1);
		System.out.println(po4.toString());
		transDataService.insertLoading(new LoadingPO(new TimePO(2015, 1, 1, 1, 1, 1), 1, "301", "NJU", new ArrayList<>(), "HaHa", "HaHa", 1));
		transDataService.deleteLoading(new LoadingPO(new TimePO(2015, 1, 1, 1, 1, 1), 1, "301", "NJU", new ArrayList<>(), "HaHa", "HaHa", 1));
		
		transDataService.init();
		transDataService.finish();
	}
	
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		TransDataService transDataService=new TransDataService_Stub(1, 1, 1, "HaHa", "HaHa", DeliverType.ECO, 1, new TimePO(2015, 1, 1, 1, 1, 1), 1, 
				"HaHa", new TimePO(2015, 1, 1, 1, 1, 1), "1", "10000000000", "301", "Male", "1", Vehicle.CAR, "苏A HAHAH", 1, "Ha1s", 1, "2015-1-1", 
				"2015-1-1", null, new TimePO(2015, 1, 1, 1, 1, 1), 1, "301", "NJU", new ArrayList<>(), "HaHa", "HaHa", 1);
		TransDataService_Driver driver=new TransDataService_Driver();
		driver.drive(transDataService);
	}

}