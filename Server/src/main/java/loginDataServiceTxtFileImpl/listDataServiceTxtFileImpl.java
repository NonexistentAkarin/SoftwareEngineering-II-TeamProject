package loginDataServiceTxtFileImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import po.AccountPO;
import po.list.ListPO;
import util.ListType;
import util.Permission;
import dataservice.listdataservice.ListDataService;

public class listDataServiceTxtFileImpl extends UnicastRemoteObject implements ListDataService {

	protected listDataServiceTxtFileImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ListPO find(long id) throws RemoteException, FileNotFoundException {
		// TODO Auto-generated method stub
		ListPO po=new ListPO(ListType.ARRIVE, 1);
		FileReader fr=new FileReader("TxtData/list.txt");
		BufferedReader br = null;
		 br = new BufferedReader(fr);
		 String Line = null;
		try {
			Line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(Line!=null){
			String output[]=Line.split(":");
			if(output[1].equals(String.valueOf(id))){
				 po=new ListPO(ListType.toListType(output[0]),id);
				System.out.println("find!");
				break;
		}
			else{
				try {
					Line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(Line==null){
			System.out.println("ID NOT EXIST");
		}
		
		return po;
		
	}

	@Override
	public void insert(ListPO po) throws RemoteException {
		// TODO Auto-generated method stub
		File loginfile=new File("TxtData/list.txt");
		try {				
			   OutputStreamWriter itemWriter = new OutputStreamWriter(
				new FileOutputStream(loginfile,true),"UTF-8"); 
			    itemWriter.write(po.getListType()+"");
	            itemWriter.write(":");
	            itemWriter.write(po.getId()+"");
	        
	            itemWriter.write("\r\n");
	            itemWriter.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(ListPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ListPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		try 
		   {    
		 File f5 = new File("TxtData/list.txt");
		       FileWriter fw5 = new FileWriter(f5);
		       BufferedWriter bw1 = new BufferedWriter(fw5);
		       bw1.write("");
		   }
		   catch (Exception e)
		   {
			   
		   }
	}

	@Override
	public void finish() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
