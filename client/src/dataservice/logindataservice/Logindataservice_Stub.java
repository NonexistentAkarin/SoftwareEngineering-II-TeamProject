package dataservice.logindataservice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.AccountPO;
import po.list.OrderListPO;
import util.*;

public class Logindataservice_Stub implements LoginDataService {

	
	
	@Override
	public AccountPO find(String username) throws IOException {
		// TODO Auto-generated method stub
		AccountPO po=null;
		FileReader fr=new FileReader("TxtData/login.txt");
		BufferedReader br = null;
		 br = new BufferedReader(fr);
		 String Line = br.readLine();
		while(Line!=null){
			String output[]=Line.split(":");
			if(output[2].equals(String.valueOf(username))){
				 po=new AccountPO(Long.parseLong(output[0]),Permission.toPermission(output[1]),output[2],output[3]);
			
				break;
		}
			else{
				Line = br.readLine();
			}
		}
		if(Line==null){
			System.out.println("ID NOT EXIST");
		}
		
		return po;
	}



	@Override
	public void insert(AccountPO po) throws RemoteException {
		File loginfile=new File("TxtData/login.txt");
		if(po==null){
			;
		}if(po!=null){
		try {				
			   OutputStreamWriter itemWriter = new OutputStreamWriter(
				new FileOutputStream(loginfile,true),"UTF-8"); 
			    itemWriter.write(po.getid()+"");
	            itemWriter.write(":");
	            itemWriter.write(po.getPermission().toString());
	            itemWriter.write(":");
	            itemWriter.write(po.getUsername().toString());
	            itemWriter.write(":");
	            itemWriter.write(po.getPassword().toString());
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
	}

	@Override
	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		try 
		   {    
		 File f5 = new File("TxtData/login.txt");
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
		System.out.println("FINISH SUCCESS");
	}



	@Override
	public boolean login(String username, String password) throws IOException {
		// TODO Auto-generated method stub
	
		FileReader fr=new FileReader("TxtData/login.txt");
		BufferedReader br = null;
		 br = new BufferedReader(fr);
		 String Line = br.readLine();
		while(Line!=null){
			String output[]=Line.split(":");
			if(output[2].equals(username)&&output[3].equals(password)){
				System.out.println("login success");
				return true;
			}
			else{
				Line = br.readLine();
			}
		}
		if(Line==null){
			System.out.println("login failure");
		}
		
		return false;	
	}
	public String readLastLine(File file, String charset) throws IOException {
		// TODO Auto-generated method stub
		  if (!file.exists() || file.isDirectory() || !file.canRead()) {  
			    return null;  
			  }  
			  RandomAccessFile raf = null;  
			  try {  
			    raf = new RandomAccessFile(file, "r");  
			    long len = raf.length();  
			    if (len == 0L) {  
			      return "";  
			    } else {  
			      long pos = len - 1;  
			      while (pos > 0) {  
			        pos--;  
			        raf.seek(pos);  
			        if (raf.readByte() == '\n') {  
			          break;  
			        }  
			      }  
			      if (pos == 0) {  
			        raf.seek(0);  
			      }  
			      byte[] bytes = new byte[(int) (len - pos)];  
			      raf.read(bytes);  
			      if (charset == null) {  
			        return new String(bytes);  
			      } else {  
			        return new String(bytes, charset);  
			      }  
			    }  
			  } catch (FileNotFoundException e) {  
			  } finally {  
			    if (raf != null) {  
			      try {  
			        raf.close();  
			      } catch (Exception e2) {  
			      }  
			    }  
			  }  
			  return null;  
			}  


	@Override
	public ArrayList<AccountPO> findAll() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public AccountPO findlast() throws IOException {
		// TODO Auto-generated method stub
		AccountPO po=null;
		FileReader fr = null;
	File file = new File("TxtData/login.txt");
		
		String Line = readLastLine(file, "UTF-8");
		
		String[] output=Line.split(":");
		po=find(output[2]);
		return po;
		
	}



	@Override
	public void delete(long id) throws IOException {
		
			
					
					File logintempfile=new File("TxtData/loginTemp.txt");
					 OutputStreamWriter itemWriter = new OutputStreamWriter(
								new FileOutputStream(logintempfile,true),"UTF-8"); 
					 
					 try 
					   {    
					 File f5 = new File("TxtData/loginTemp.txt");
					       FileWriter fw5 = new FileWriter(f5);
					       BufferedWriter bw1 = new BufferedWriter(fw5);
					       bw1.write("");
					   }
					   catch (Exception e)
					   {
						   
					   }
					FileReader fr = null;
					fr = new FileReader("TxtData/login.txt");
					String Line=null;
					String temp=null;
					BufferedReader br = null;
					 br = new BufferedReader(fr);
					 Line=br.readLine();
					
					 while(Line!=null){
						String output[]=Line.split(":");
						
						if(Long.parseLong(output[0])!=id){
							
							itemWriter.write(Line);
							itemWriter.write("\r\n");
				      
						}if(Long.parseLong(output[0])==id){
							;
						}
						
						Line=br.readLine();
					}
					itemWriter.close();
					
					FileReader fr2 = null;
					fr2 = new FileReader("TxtData/loginTemp.txt");
					String Line2=null;
				
					BufferedReader br2 = null;
					 br2 = new BufferedReader(fr2);
					 Line2=br2.readLine();
					init();
					File financetempfile2=new File("TxtData/login.txt");
					 OutputStreamWriter itemWriter2 = new OutputStreamWriter(
								new FileOutputStream(financetempfile2,true),"UTF-8"); 
					while(Line2!=null){
						itemWriter2.write(Line2);
						itemWriter2.write("\r\n");
						Line2=br2.readLine();
					}
					itemWriter2.close();
					 try 
					   {    
					 File f5 = new File("TxtData/loginTemp.txt");
					       FileWriter fw5 = new FileWriter(f5);
					       BufferedWriter bw1 = new BufferedWriter(fw5);
					       bw1.write("");
					   }
					   catch (Exception e)
					   {
						   
					   }
					
					
					System.out.println("DELETE SUCCESS!");
		
	


		
		
}



	@Override
	public void update(AccountPO po) throws IOException {
		// TODO Auto-generated method stub
	long id=po.getid();
	delete(id);
	insert(po);
	
	}

}
