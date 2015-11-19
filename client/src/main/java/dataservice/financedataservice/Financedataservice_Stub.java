package dataservice.financedataservice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;

import po.InstitutePO;
import po.ReceiptPO;
import po.StaffPO;
import po.TimePO;
import util.City;
import util.OrgType;
import util.Permission;
public class Financedataservice_Stub implements FinanceDataService {
	public ReceiptPO find(long id) throws RemoteException{
		StaffPO staff=null;
		InstitutePO institute=null;
		TimePO time=null;
		ReceiptPO po=null;
		FileReader fr = null;
		
		try {
			fr = new FileReader("TxtData/finance.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			String d[]= output[0].split(",");
			String inst[] = output[1].split(",");
			if(d[0].equals(String.valueOf(id))){
			
				StaffPO staff2=new StaffPO(Long.parseLong(d[0]),d[1],OrgType.toOrgType(d[2]),Permission.toPermission(d[3]));
				
				InstitutePO ins = new InstitutePO(City.toCity(inst[0]),OrgType.toOrgType(inst[1]),Long.parseLong(inst[2]));
				String t[] = output[3].split("y|m|d|h|s");
				TimePO time2=new TimePO(Integer.parseInt(t[0]),Integer.parseInt(t[1]),Integer.parseInt(t[2]),Integer.parseInt(t[3]),Integer.parseInt(t[4]),Integer.parseInt(t[5]));
				
				 po = new ReceiptPO(staff2,ins,Double.parseDouble(output[2]),time2);
				
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
			return null;
		}
		
		return po;
		
	}
	//在数据库中增加po记录
	public void insert(ReceiptPO po) throws RemoteException {
		File financefile=new File("TxtData/finance.txt");
		if(po==null){
			
		}else
		try {				
			   OutputStreamWriter itemWriter = new OutputStreamWriter(
				new FileOutputStream(financefile,true),"UTF-8"); 
	            itemWriter.write(po.getStaff().getId()+","+po.getStaff().getName()+","+po.getStaff().getOrgType()+","+po.getStaff().getPermission());
	            itemWriter.write(":");
	            itemWriter.write(po.getInstitute().getCity()+","+po.getInstitute().getOrg()+","+po.getInstitute().getId());
	            itemWriter.write(":");
	            itemWriter.write(po.getMoney()+"");
	            itemWriter.write(":");
	            itemWriter.write(po.getTime()+"");
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
		System.out.println("INSERT SUCCESS!");
	}
	//删除一个po
	public void delete(ReceiptPO po) throws RemoteException{
		System.out.println("DELETE SUCCESS!");
	}
	//更新一个po
	public void update(ReceiptPO po) throws RemoteException{
		System.out.println("UPDATE SUCCESS!");
	}
	//初始化持久化数据库
	public void init() throws RemoteException{
		System.out.println("INIT SUCCESS!");
	}
	//结束持久化数据库
	public void finish() throws RemoteException{
		System.out.println("FINISH SUCCESS!");
	}
	
	
}
