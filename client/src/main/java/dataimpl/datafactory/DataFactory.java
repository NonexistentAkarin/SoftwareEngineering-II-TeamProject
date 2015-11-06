package dataimpl.datafactory;

import dataimpl.listDataServiceTxtFileImpl.OrdersDataServiceTxtFileImpl;
import dataservice.datafactoryservice.DataFactoryService;
import dataservice.listdataservice.OrdersDataService;



public class DataFactory implements DataFactoryService{

	@Override
	public OrdersDataService getWareData() {
		// TODO Auto-generated method stub
		OrdersDataService od = new OrdersDataServiceTxtFileImpl();  
		return od;
	}

}