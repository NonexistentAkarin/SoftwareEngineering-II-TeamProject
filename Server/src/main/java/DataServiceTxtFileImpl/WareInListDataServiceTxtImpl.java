package DataServiceTxtFileImpl;

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

import dataservice.listdataservice.WareInListDataService;
import po.GaragePlacePO;
import po.TimePO;
import po.list.ArrivaListPO;
import po.list.WareInListPO;
import util.City;
import util.GoodState;
import util.ListState;
import util.ListType;

public class WareInListDataServiceTxtImpl extends UnicastRemoteObject implements WareInListDataService {

	public WareInListDataServiceTxtImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws RemoteException{
		// TODO Auto-generated method stub
		try {
			File f5 = new File("TxtData/warein.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}
	}

	@Override
	public void insert(WareInListPO po) throws RemoteException {
		// TODO Auto-generated method stub
		File Arrivalfile = new File("TxtData/warein.txt");
		if (po == null) {
			System.out.println("WAREINLIST IS NOTHING");
		}
		if (po != null) {
			try {
				OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(Arrivalfile, true),
						"UTF-8");
				itemWriter.write(po.getId() + "");
				itemWriter.write(":");
				itemWriter.write(po.getTime() + "");
				itemWriter.write(":");
				itemWriter.write(po.getDestination().toString());
				itemWriter.write(":");
				itemWriter.write(po.getPlace().getQu() + "");
				itemWriter.write("-");
				itemWriter.write(po.getPlace().getPai() + "");
				itemWriter.write("-");
				itemWriter.write(po.getPlace().getJia() + "");
				itemWriter.write("-");
				itemWriter.write(po.getPlace().getWei() + "");

				itemWriter.write("\r\n");
				itemWriter.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public WareInListPO find(long id) throws RemoteException, IOException {
		WareInListPO po = null;
		FileReader fr = null;
		try {
			fr = new FileReader("TxtData/warein.txt");
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
		while (Line != null) {
			String output[] = Line.split(":");
			if (output[0].equals(String.valueOf(id))) {
				String t[] = output[3].split("-");

				po = new WareInListPO(Long.parseLong(output[0]),
						TimePO.toTime(output[1]), City.toCity(output[2]), new GaragePlacePO(Integer.parseInt(t[0]),
								Integer.parseInt(t[1]), Integer.parseInt(t[2]), Integer.parseInt(t[3])),
						ListState.SUBMITTED);

				break;
			} else {
				try {
					Line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (Line == null) {
			System.out.println("WAREIN NOT EXIST");
		}

		return po;
	}

	@Override
	public void delete(long id) throws RemoteException, IOException {

		File accounttempfile = new File("TxtData/wareinTemp.txt");
		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(accounttempfile, true), "UTF-8");

		try {
			File f5 = new File("TxtData/wareinTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}
		FileReader fr = null;
		fr = new FileReader("TxtData/warein.txt");
		String Line = null;
		String temp = null;
		BufferedReader br = null;
		br = new BufferedReader(fr);
		Line = br.readLine();

		while (Line != null) {
			String output[] = Line.split(":");

			if (Long.parseLong(output[0]) != id) {

				itemWriter.write(Line);
				itemWriter.write("\r\n");

			}
			if (Long.parseLong(output[0]) == id) {
				;
			}

			Line = br.readLine();
		}
		itemWriter.close();

		FileReader fr2 = null;
		fr2 = new FileReader("TxtData/wareinTemp.txt");
		String Line2 = null;

		BufferedReader br2 = null;
		br2 = new BufferedReader(fr2);
		Line2 = br2.readLine();
		init();
		File financetempfile2 = new File("TxtData/warein.txt");
		OutputStreamWriter itemWriter2 = new OutputStreamWriter(new FileOutputStream(financetempfile2, true), "UTF-8");
		while (Line2 != null) {
			itemWriter2.write(Line2);
			itemWriter2.write("\r\n");
			Line2 = br2.readLine();
		}
		itemWriter2.close();
		try {
			File f5 = new File("TxtData/wareinTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}

		System.out.println("DELETE SUCCESS!");

	}

}