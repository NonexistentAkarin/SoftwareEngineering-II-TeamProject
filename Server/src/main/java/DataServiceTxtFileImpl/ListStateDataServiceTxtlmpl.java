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
import java.util.ArrayList;

import dataservice.reviewdataservice.ListStateDataService;
import po.BaccountPO;
import po.GaragePlacePO;
import po.TimePO;
import po.WarePO;
import po.list.ArrivaListPO;
import po.list.DeliveryListPO;
import po.list.LoadingListPO;
import po.list.MoneyInListPO;
import po.list.MoneyOutListPO;
import po.list.OrderListPO;
import po.list.TranscenterArrivalListPO;
import po.list.WareInListPO;
import po.list.WareOutListPO;
import util.City;
import util.DeliverType;
import util.Entry;
import util.GoodState;
import util.ListState;
import util.ListType;
import util.Vehicle;

public class ListStateDataServiceTxtlmpl extends UnicastRemoteObject implements ListStateDataService {

	public ListStateDataServiceTxtlmpl() throws RemoteException, RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<ArrivaListPO> findallArrival() throws RemoteException, IOException {
		// TODO Auto-generated method stub
		ArrayList<ArrivaListPO> result = new ArrayList<ArrivaListPO>();
		FileReader fr = new FileReader("TxtData/ArrivalList.txt");
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");
			String t[] = output[1].split("-");

			ArrivaListPO po = new ArrivaListPO(ListType.toListType(output[0]),
					new TimePO(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]), 0, 0, 0),
					Long.parseLong(output[2]), City.toCity(output[3]), GoodState.toState(output[4]),
					ListState.toState(output[5]), Long.parseLong(output[6]));
			result.add(po);
			Line = br.readLine();
		}
		return result;
	}

	@Override
	public ArrayList<ArrivaListPO> findNoneReviewd() throws RemoteException, IOException {
		ArrayList<ArrivaListPO> temp = new ArrayList<ArrivaListPO>();
		ArrayList<ArrivaListPO> result = new ArrayList<ArrivaListPO>();
		temp = findallArrival();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getLst().equals(ListState.SUBMITTED)) {
				result.add(temp.get(i));
			} else {
				;
			}
		}

		return result;
	}

	@Override
	public ArrayList<DeliveryListPO> findallDelivery() throws RemoteException, IOException {
		ArrayList<DeliveryListPO> result = new ArrayList<DeliveryListPO>();
		FileReader fr = new FileReader("TxtData/DeliveryList.txt");
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");

			DeliveryListPO po = new DeliveryListPO(Long.parseLong(output[0]), TimePO.toTime(output[1]),
					Long.parseLong(output[2]), output[3], ListState.toState(output[4]));

			result.add(po);
			Line = br.readLine();
		}
		return result;
	}

	@Override
	public ArrayList<DeliveryListPO> findNoneReviewed() throws RemoteException, IOException {
		ArrayList<DeliveryListPO> temp = new ArrayList<DeliveryListPO>();
		ArrayList<DeliveryListPO> result = new ArrayList<DeliveryListPO>();
		temp = findallDelivery();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getLst().equals(ListState.SUBMITTED)) {
				result.add(temp.get(i));
			} else {
				;
			}
		}

		return result;
	}

	@Override
	public ArrayList<OrderListPO> findallOrder() throws RemoteException, IOException {
		ArrayList<OrderListPO> result = new ArrayList<OrderListPO>();
		FileReader fr = new FileReader("TxtData/orderlist.txt");
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");
			String t[] = output[12].split(",");

			String time[] = t[7].split("-");
			WarePO ware = new WarePO(Double.parseDouble(t[0]), Integer.parseInt(t[1]), Double.parseDouble(t[2]), t[3],
					t[4], DeliverType.toType(t[5]), Double.parseDouble(t[6]), TimePO.toTime(t[7]), City.toCity(t[8]),
					City.toCity(t[9]));
//			System.out.println(output[1]);
			OrderListPO po = new OrderListPO(ListType.toListType(output[1]), output[2], output[3], output[4], output[5],
					output[6], output[7], output[8], output[9], output[10], output[11], ware, output[0],
					ListState.toState(output[13]), output[14]);
			result.add(po);
			Line = br.readLine();
		}
		return result;
	}

	@Override
	public ArrayList<LoadingListPO> findallLoading() throws RemoteException, IOException {
		ArrayList<LoadingListPO> result = new ArrayList<LoadingListPO>();
		FileReader fr = new FileReader("TxtData/loadinglist.txt");
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");

			String t[] = output[2].split("-");
			String l[] = output[6].split("-");
			ArrayList<Long> list = new ArrayList<Long>();
			for (int i = 0; i < l.length; i++) {
				list.add(Long.parseLong(l[i]));

			}

			LoadingListPO po = new LoadingListPO(Long.parseLong(output[0]), ListType.toListType(output[1]),
					new TimePO(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]), 0, 0, 0),
					Long.parseLong(output[3]), City.toCity(output[4]), City.toCity(output[5]), list, output[7],
					output[8], Double.parseDouble(output[9]), ListState.toState(output[10]));

			result.add(po);
			Line = br.readLine();
		}
		return result;
	}

	@Override
	public ArrayList<LoadingListPO> findNoneReviewedLoading() throws RemoteException, IOException {
		// TODO Auto-generated method stub
		ArrayList<LoadingListPO> temp = new ArrayList<LoadingListPO>();
		ArrayList<LoadingListPO> result = new ArrayList<LoadingListPO>();
		temp = findallLoading();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getLst().equals(ListState.SUBMITTED)) {
				result.add(temp.get(i));
			} else {
				;
			}
		}

		return result;
	}

	@Override
	public ArrayList<LoadingListPO> findallHallLoading() throws RemoteException, IOException {
		ArrayList<LoadingListPO> result = new ArrayList<LoadingListPO>();
		FileReader fr = new FileReader("TxtData/loadinglist_Hall.txt");
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");

			String t[] = output[2].split("-");
			String l[] = output[6].split("-");

			ArrayList<Long> list = new ArrayList<Long>();
			for (int i = 0; i < l.length; i++) {
				list.add(Long.parseLong(l[i]));

			}
			LoadingListPO po = new LoadingListPO(Long.parseLong(output[0]), ListType.toListType(output[1]),
					new TimePO(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]), 0, 0, 0),
					Long.parseLong(output[3]), City.toCity(output[4]), City.toCity(output[5]), list, output[7],
					output[8], Double.parseDouble(output[9]), ListState.toState(output[10]));

			result.add(po);
			Line = br.readLine();
		}
		return result;
	}

	@Override
	public ArrayList<LoadingListPO> findNoneReviewedLoadingHall() throws RemoteException, IOException {
		// TODO Auto-generated method stub
		ArrayList<LoadingListPO> temp = new ArrayList<LoadingListPO>();
		ArrayList<LoadingListPO> result = new ArrayList<LoadingListPO>();
		temp = findallHallLoading();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getLst().equals(ListState.SUBMITTED)) {
				result.add(temp.get(i));
			} else {
				;
			}
		}

		return result;
	}

	@Override
	public ArrayList<MoneyInListPO> findallMoneyIn() throws RemoteException, IOException {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public ArrayList<MoneyOutListPO> findallMoneyOut() throws RemoteException, IOException {
		// TODO Auto-generated method stub
		ArrayList<MoneyOutListPO> result = new ArrayList<MoneyOutListPO>();
		FileReader fr = new FileReader("TxtData/MoneyOutList.txt");
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");
			BaccountPO bapo = new BaccountPO(output[4], "111111", "99999");
			MoneyOutListPO po = new MoneyOutListPO(Long.parseLong(output[0]), TimePO.toSpeccialTime(output[1]),
					Double.parseDouble(output[2]), output[3], bapo, Entry.toEntry(output[5]), output[6],
					ListState.toState(output[7]));
			result.add(po);
			Line = br.readLine();
		}
		return result;
	}

	@Override
	public ArrayList<WareInListPO> findallWareIn() throws RemoteException, IOException {
		ArrayList<WareInListPO> result = new ArrayList<WareInListPO>();
		FileReader fr = new FileReader("TxtData/warein.txt");
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");
			String t[] = output[3].split("-");

			WareInListPO po = new WareInListPO(Long.parseLong(output[0]), TimePO.toTime(output[1]),
					City.toCity(output[2]),
					new GaragePlacePO(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]),
							Integer.parseInt(t[3])),
					ListState.toState(output[4]), Long.parseLong(output[5]), Vehicle.toVehicle(output[6]));
			result.add(po);
			Line = br.readLine();
		}
		return result;
	}

	@Override
	public ArrayList<WareOutListPO> findallWareOut() throws RemoteException, IOException {
		// TODO Auto-generated method stub
		ArrayList<WareOutListPO> result = new ArrayList<WareOutListPO>();
		FileReader fr = new FileReader("TxtData/wareout.txt");
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");
			String t[] = output[3].split("-");

			WareOutListPO po = new WareOutListPO(Long.parseLong(output[0]), TimePO.toTime(output[1]),
					Vehicle.toVehicle(output[2]), City.toCity(output[3]), Long.parseLong(output[4]),
					ListState.toState(output[5]), Long.parseLong(output[6]));
			result.add(po);
			Line = br.readLine();
		}
		return result;
	}

	@Override
	public ArrayList<Object> findall() throws RemoteException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Object> result = new ArrayList<Object>();
		ArrayList<ArrivaListPO> arrival = findallArrival();
		ArrayList<DeliveryListPO> delivery = findallDelivery();
		ArrayList<OrderListPO> order = findallOrder();
		ArrayList<LoadingListPO> loading = findallLoading();
		ArrayList<LoadingListPO> loadinghall = findallHallLoading();
		ArrayList<WareInListPO> warein = findallWareIn();
		ArrayList<WareOutListPO> wareout = findallWareOut();
		ArrayList<MoneyOutListPO> moneyout = findallMoneyOut();
		result.add(arrival);
		result.add(delivery);
		result.add(order);
		result.add(loading);
		result.add(loadinghall);
		result.add(warein);
		result.add(wareout);
		result.add(moneyout);
		return result;
	}

	@Override
	public void updateArrival(ArrivaListPO po) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		long id = po.getid();
		deleteArrival(id);
		insertArrival(po);
	}

	public void deleteArrival(long id) throws RemoteException, IOException {

		File accounttempfile = new File("TxtData/ArrivalTemp.txt");
		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(accounttempfile, true), "UTF-8");

		try {
			File f5 = new File("TxtData/ArrivalTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}
		FileReader fr = null;
		fr = new FileReader("TxtData/ArrivalList.txt");
		String Line = null;
		String temp = null;
		BufferedReader br = null;
		br = new BufferedReader(fr);
		Line = br.readLine();

		while (Line != null) {
			String output[] = Line.split(":");

			if (Long.parseLong(output[2]) != id) {

				itemWriter.write(Line);
				itemWriter.write("\r\n");

			}
			if (Long.parseLong(output[2]) == id) {
				;
			}

			Line = br.readLine();
		}
		itemWriter.close();

		FileReader fr2 = null;
		fr2 = new FileReader("TxtData/ArrivalTemp.txt");
		String Line2 = null;

		BufferedReader br2 = null;
		br2 = new BufferedReader(fr2);
		Line2 = br2.readLine();
		init("TxtData/ArrivalList.txt");
		File financetempfile2 = new File("TxtData/ArrivalList.txt");
		OutputStreamWriter itemWriter2 = new OutputStreamWriter(new FileOutputStream(financetempfile2, true), "UTF-8");
		while (Line2 != null) {
			itemWriter2.write(Line2);
			itemWriter2.write("\r\n");
			Line2 = br2.readLine();
		}
		itemWriter2.close();
		try {
			File f5 = new File("TxtData/ArrivalTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}

//		// System.out.println("DELETE SUCCESS!");
	}

	@Override
	public void insertArrival(ArrivaListPO po) throws RemoteException {
		File Arrivalfile = new File("TxtData/ArrivalList.txt");
		if (po == null) {
			System.out.println("ARRIVALLIST IS NOTHING");
		}
		if (po != null) {
			try {
				OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(Arrivalfile, true),
						"UTF-8");
				itemWriter.write(po.getType() + "");
				itemWriter.write(":");
				itemWriter.write(po.getTime() + "");
				itemWriter.write(":");
				itemWriter.write(po.getid() + "");
				itemWriter.write(":");
				itemWriter.write(po.getStartCity() + "");
				itemWriter.write(":");
				itemWriter.write(po.getState() + "");
				itemWriter.write(":");
				itemWriter.write(po.getLst().toString());
				itemWriter.write(":");
				itemWriter.write(po.getCode() + "");
				itemWriter.write(":");
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
	public void init(String Txtaddress) throws RemoteException {
		try {
			File f5 = new File(Txtaddress);
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}
//		System.out.println("INIT SUCCESS");

	}

	@Override
	public void updateDelivery(DeliveryListPO po) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		long id = po.getId();
		deleteDelivery(id);
		insertDelivery(po);
	}

	@Override
	public void deleteDelivery(long id) throws RemoteException, IOException {
		File accounttempfile = new File("TxtData/DeliveryTemp.txt");
		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(accounttempfile, true), "UTF-8");

		try {
			File f5 = new File("TxtData/DeliveryTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}
		FileReader fr = null;
		fr = new FileReader("TxtData/DeliveryList.txt");
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
			if (Long.parseLong(output[2]) == id) {
				;
			}

			Line = br.readLine();
		}
		itemWriter.close();

		FileReader fr2 = null;
		fr2 = new FileReader("TxtData/DeliveryTemp.txt");
		String Line2 = null;

		BufferedReader br2 = null;
		br2 = new BufferedReader(fr2);
		Line2 = br2.readLine();
		init("TxtData/DeliveryList.txt");
		File financetempfile2 = new File("TxtData/DeliveryList.txt");
		OutputStreamWriter itemWriter2 = new OutputStreamWriter(new FileOutputStream(financetempfile2, true), "UTF-8");
		while (Line2 != null) {
			itemWriter2.write(Line2);
			itemWriter2.write("\r\n");
			Line2 = br2.readLine();
		}
		itemWriter2.close();
		try {
			File f5 = new File("TxtData/DeliveryTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}

//		// System.out.println("DELETE SUCCESS!");
	}

	@Override
	public void insertDelivery(DeliveryListPO po) throws RemoteException {
		File Deliveryfile = new File("TxtData/DeliveryList.txt");
		if (po == null) {
			System.out.println("INSERT NOTHING");
			;
		}
		if (po != null) {
			try {
				OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(Deliveryfile, true),
						"UTF-8");
				itemWriter.write(po.getId() + "");
				itemWriter.write(":");
				itemWriter.write(po.getTime() + "");
				itemWriter.write(":");
				itemWriter.write(po.getCode() + "");
				itemWriter.write(":");
				itemWriter.write(po.getName());
				itemWriter.write(":");
				itemWriter.write(po.getLst().toString());
				itemWriter.write(":");
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

		//  System.out.println("insertOK");
	}

	@Override
	public void updateOrder(OrderListPO po) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		String id = po.getId();
		deleteOrder(id);
		insertOrder(po);
	}

	@Override
	public void deleteOrder(String id) throws RemoteException, IOException {
		File accounttempfile = new File("TxtData/orderlistTemp.txt");
		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(accounttempfile, true), "UTF-8");

		try {
			File f5 = new File("TxtData/orderlistTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}
		FileReader fr = null;
		fr = new FileReader("TxtData/orderlist.txt");
		String Line = null;
		String temp = null;
		BufferedReader br = null;
		br = new BufferedReader(fr);
		Line = br.readLine();

		while (Line != null) {
			String output[] = Line.split(":");

			if (!output[0].equals(id)) {

				itemWriter.write(Line);
				itemWriter.write("\r\n");

			}
			if (output[0].equals(id)) {
				;
			}

			Line = br.readLine();
		}
		itemWriter.close();

		FileReader fr2 = null;
		fr2 = new FileReader("TxtData/orderlistTemp.txt");
		String Line2 = null;

		BufferedReader br2 = null;
		br2 = new BufferedReader(fr2);
		Line2 = br2.readLine();
		init("TxtData/orderlist.txt");
		File financetempfile2 = new File("TxtData/orderlist.txt");
		OutputStreamWriter itemWriter2 = new OutputStreamWriter(new FileOutputStream(financetempfile2, true), "UTF-8");
		while (Line2 != null) {
			itemWriter2.write(Line2);
			itemWriter2.write("\r\n");
			Line2 = br2.readLine();
		}
		itemWriter2.close();
		try {
			File f5 = new File("TxtData/orderlistTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}

		// System.out.println("DELETE SUCCESS!");
	}

	@Override
	public void insertOrder(OrderListPO po) throws RemoteException {
		File financefile = new File("TxtData/orderlist.txt");
		if (po == null) {
		} else
			try {
				OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(financefile, true),
						"UTF-8");
				itemWriter.write(po.getId() + "");
				itemWriter.write(":");
				itemWriter.write(po.getListtype() + "");
				itemWriter.write(":");
				itemWriter.write(po.getSenderName() + "");
				itemWriter.write(":");
				itemWriter.write(po.getSenaderAddress() + "");
				itemWriter.write(":");
				itemWriter.write(po.getSenderOrganization() + "");
				itemWriter.write(":");
				itemWriter.write(po.getSenderTphone() + "");
				itemWriter.write(":");
				itemWriter.write(po.getSenderCphone() + "");
				itemWriter.write(":");
				itemWriter.write(po.getReceiverName() + "");
				itemWriter.write(":");
				itemWriter.write(po.getReceiverAddress() + "");
				itemWriter.write(":");
				itemWriter.write(po.getReceiverOrganization() + "");
				itemWriter.write(":");
				itemWriter.write(po.getReceiverTphone() + "");
				itemWriter.write(":");
				itemWriter.write(po.getReceiverCphone() + "");
				itemWriter.write(":");
				itemWriter.write(po.getWare().getweight() + "," + po.getWare().getamount() + ","
						+ po.getWare().getvolume() + "," + po.getWare().getpackag() + "," + po.getWare().getname() + ","
						+ po.getWare().gettype() + "," + po.getWare().getcost() + ","
						+ po.getWare().gettime().toString() + "," + po.getWare().getDepartPlace().toString() + ","
						+ po.getWare().getDestination().toString());
				itemWriter.write(":");
				itemWriter.write(po.getLst() + "");
				itemWriter.write(":");
				itemWriter.write(po.getAccount() + "");
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

	@Override
	public void updateLoading(LoadingListPO po) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		long id = po.getId();
		deleteLoading(id);
		insertLoading(po);
	}

	@Override
	public void deleteLoading(long id) throws RemoteException, IOException {
		File accounttempfile = new File("TxtData/loadinglistTemp.txt");
		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(accounttempfile, true), "UTF-8");

		try {
			File f5 = new File("TxtData/loadinglistTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}
		FileReader fr = null;
		fr = new FileReader("TxtData/loadinglist.txt");
		String Line = null;
		String temp = null;
		BufferedReader br = null;
		br = new BufferedReader(fr);
		Line = br.readLine();

		while (Line != null) {
			String output[] = Line.split(":");

			if (!output[0].equals(String.valueOf(id))) {

				itemWriter.write(Line);
				itemWriter.write("\r\n");

			}
			if (output[0].equals(String.valueOf(id))) {
				;
			}

			Line = br.readLine();
		}
		itemWriter.close();

		FileReader fr2 = null;
		fr2 = new FileReader("TxtData/loadinglistTemp.txt");
		String Line2 = null;

		BufferedReader br2 = null;
		br2 = new BufferedReader(fr2);
		Line2 = br2.readLine();
		init("TxtData/loadinglist.txt");
		File financetempfile2 = new File("TxtData/loadinglist.txt");
		OutputStreamWriter itemWriter2 = new OutputStreamWriter(new FileOutputStream(financetempfile2, true), "UTF-8");
		while (Line2 != null) {
			itemWriter2.write(Line2);
			itemWriter2.write("\r\n");
			Line2 = br2.readLine();
		}
		itemWriter2.close();
		try {
			File f5 = new File("TxtData/loadinglistTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}

		// System.out.println("DELETE SUCCESS!");
	}

	@Override
	public void insertLoading(LoadingListPO po) throws RemoteException {

		File loginfile = new File("TxtData/loadinglist.txt");
		if (po == null) {
			System.out.println("LOADINGLIST NONE EXIST!");
		} else {
			try {
				OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(loginfile, true), "UTF-8");
				itemWriter.write(po.getId() + "");
				itemWriter.write(":");
				itemWriter.write(po.getType() + "");
				itemWriter.write(":");
				itemWriter.write(po.getLoadDate() + "");
				itemWriter.write(":");
				itemWriter.write(po.getTransNum() + "");
				itemWriter.write(":");
				itemWriter.write(po.getDepartPlace() + "");
				itemWriter.write(":");
				itemWriter.write(po.getDestination() + "");
				itemWriter.write(":");
				ArrayList<Long> list = po.getWaybillNumList();
				for (int i = 0; i < list.size(); i++) {
					if (i == list.size() - 2)
						itemWriter.write(list.get(i) + "-");
					else {
						itemWriter.write(list.get(i) + "");
					}
				}
				itemWriter.write(":");
				itemWriter.write(po.getLoadMonitor() + "");
				itemWriter.write(":");
				itemWriter.write(po.getLoadPerformer() + "");
				itemWriter.write(":");
				itemWriter.write(po.getFreight() + "");
				itemWriter.write(":");
				itemWriter.write(po.getLst() + "");
				itemWriter.write("\r\n");
				itemWriter.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("INSERT SUCCESS!!");
		}
	}

	@Override
	public void updateLoadingHall(LoadingListPO po) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		long id = po.getId();
		deleteLoadingHall(id);
		insertLoadingHall(po);
	}

	@Override
	public void deleteLoadingHall(long id) throws RemoteException, IOException {
		File accounttempfile = new File("TxtData/loadinglistTemp.txt");
		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(accounttempfile, true), "UTF-8");

		try {
			File f5 = new File("TxtData/loadinglistTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}
		FileReader fr = null;
		fr = new FileReader("TxtData/Loadinglist_Hall.txt");
		String Line = null;
		String temp = null;
		BufferedReader br = null;
		br = new BufferedReader(fr);
		Line = br.readLine();

		while (Line != null) {
			String output[] = Line.split(":");

			if (!output[0].equals(String.valueOf(id))) {

				itemWriter.write(Line);
				itemWriter.write("\r\n");

			}
			if (output[0].equals(String.valueOf(id))) {
				;
			}

			Line = br.readLine();
		}
		itemWriter.close();

		FileReader fr2 = null;
		fr2 = new FileReader("TxtData/loadinglistTemp.txt");
		String Line2 = null;

		BufferedReader br2 = null;
		br2 = new BufferedReader(fr2);
		Line2 = br2.readLine();
		init("TxtData/Loadinglist_Hall.txt");
		File financetempfile2 = new File("TxtData/Loadinglist_Hall.txt");
		OutputStreamWriter itemWriter2 = new OutputStreamWriter(new FileOutputStream(financetempfile2, true), "UTF-8");
		while (Line2 != null) {
			itemWriter2.write(Line2);
			itemWriter2.write("\r\n");
			Line2 = br2.readLine();
		}
		itemWriter2.close();
		try {
			File f5 = new File("TxtData/loadinglistTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}

		// System.out.println("DELETE SUCCESS!");
	}

	@Override
	public void insertLoadingHall(LoadingListPO po) throws RemoteException {
		// TODO Auto-generated method stub

		File loginfile = new File("TxtData/loadinglist_Hall.txt");
		if (po == null) {
			System.out.println("LOADINGLIST NONE EXIST!");
		} else {
			try {
				OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(loginfile, true), "UTF-8");
				itemWriter.write(po.getId() + "");
				itemWriter.write(":");
				itemWriter.write(po.getType() + "");
				itemWriter.write(":");
				itemWriter.write(po.getLoadDate() + "");
				itemWriter.write(":");
				itemWriter.write(po.getTransNum() + "");
				itemWriter.write(":");
				itemWriter.write(po.getDepartPlace() + "");
				itemWriter.write(":");
				itemWriter.write(po.getDestination() + "");
				itemWriter.write(":");
				ArrayList<Long> list = po.getWaybillNumList();
				for (int i = 0; i < list.size(); i++) {
					if (i == list.size() - 2)
						itemWriter.write(list.get(i) + "-");
					else {
						itemWriter.write(list.get(i) + "");
					}
				}
				itemWriter.write(":");
				itemWriter.write(po.getLoadMonitor() + "");
				itemWriter.write(":");
				itemWriter.write(po.getLoadPerformer() + "");
				itemWriter.write(":");
				itemWriter.write(po.getFreight() + "");
				itemWriter.write(":");
				itemWriter.write(po.getLst() + "");
				itemWriter.write("\r\n");
				itemWriter.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("INSERT SUCCESS!!");
		}

	}

	@Override
	public void updateMoneyIn(String id, ArrayList<MoneyInListPO> list, String toAdd) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			deleteMoneyIn(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insertMoneyIn(list, toAdd);

	}

	@Override
	public void deleteMoneyIn(String id) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<String>();

		FileReader fr = null;
		try {
			fr = new FileReader("TxtData/MoneyInList.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");

			if (!output[3].equals(id)) {
				temp.add(Line);
			}
			Line = br.readLine();
		}
		if (Line == null) {
			try {
				File f5 = new File("TxtData/MoneyInList.txt");
				FileWriter fw5 = new FileWriter(f5);
				BufferedWriter bw1 = new BufferedWriter(fw5);
				bw1.write("");
			} catch (Exception e) {

			}
		}

		File Arrivalfile = new File("TxtData/MoneyInList.txt");

		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(Arrivalfile, true), "UTF-8");
		for (int i = 0; i < temp.size(); i++) {
			itemWriter.write(temp.get(i));
			itemWriter.write("\r\n");
		}
		itemWriter.close();
	}

	@Override
	public void insertMoneyIn(ArrayList<MoneyInListPO> list, String toAdd) throws RemoteException {
		// TODO Auto-generated method stub
		File loginfile = new File("TxtData/MoneyInList.txt");
		if (list != null) {
			try {
				MoneyInListPO po1 = list.get(0);
				OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(loginfile, true), "UTF-8");

				double total = 0;
				String idStr = "";

				for (MoneyInListPO po : list) {
					total = total + po.getMoney();
					idStr = idStr + po.getId() + ";";
				}

				String add[] = toAdd.split(":");

				total += Double.parseDouble(add[add.length - 3]);
				idStr += add[add.length - 2];

				itemWriter.write(po1.getAccount().getUsername());
				itemWriter.write(":");
				itemWriter.write(po1.getTime().toSpecicalString());
				itemWriter.write(":");
				itemWriter.write(total + "");
				itemWriter.write(":");
				itemWriter.write(idStr);
				itemWriter.write(":");
				itemWriter.write("未审批");
				itemWriter.write(":");
				itemWriter.write(po1.getBaccount());
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
	public ArrayList<OrderListPO> findNoneReviewedOrder() throws RemoteException, IOException {
		ArrayList<OrderListPO> temp = new ArrayList<OrderListPO>();
		ArrayList<OrderListPO> result = new ArrayList<OrderListPO>();
		temp = findallOrder();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getLst().equals(ListState.SUBMITTED)) {
				result.add(temp.get(i));
			} else {
				;
			}
		}
		return result;
	}

	public ArrayList<TranscenterArrivalListPO> findallTrans() throws RemoteException, IOException {
		ArrayList<TranscenterArrivalListPO> result = new ArrayList<TranscenterArrivalListPO>();
		FileReader fr = new FileReader("TxtData/TransCenterArrival.txt");
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");
			String t[] = output[1].split("-");

			TranscenterArrivalListPO po = new TranscenterArrivalListPO(output[0],
					new TimePO(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]), 0, 0, 0),
					Long.parseLong(output[2]), City.toCity(output[3]), GoodState.toState(output[4]),
					ListState.toState(output[5]), Long.parseLong(output[6]));

			result.add(po);
			Line = br.readLine();
		}
		return result;
	}

	@Override
	public ArrayList<TranscenterArrivalListPO> findNoneReviewedTrans() throws RemoteException, IOException {
		ArrayList<TranscenterArrivalListPO> temp = new ArrayList<TranscenterArrivalListPO>();
		ArrayList<TranscenterArrivalListPO> result = new ArrayList<TranscenterArrivalListPO>();
		temp = findallTrans();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getLst().equals(ListState.SUBMITTED)) {
				result.add(temp.get(i));
			} else {
				;
			}
		}
		return result;
	}

	@Override
	public void updateTrans(TranscenterArrivalListPO po) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		String id = po.getTranscenterID();
		deleteTrans(id);
		insertTrans(po);
	}

	@Override
	public void deleteTrans(String id) throws RemoteException, IOException {
		File accounttempfile = new File("TxtData/loadinglistTemp.txt");
		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(accounttempfile, true), "UTF-8");

		try {
			File f5 = new File("TxtData/loadinglistTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}
		FileReader fr = null;
		fr = new FileReader("TxtData/TransCenterArrival.txt");
		String Line = null;
		String temp = null;
		BufferedReader br = null;
		br = new BufferedReader(fr);
		Line = br.readLine();

		while (Line != null) {
			String output[] = Line.split(":");

			if (!output[2].equals(id)) {

				itemWriter.write(Line);
				itemWriter.write("\r\n");

			}
			if (output[2].equals(id)) {
				;
			}

			Line = br.readLine();
		}
		itemWriter.close();

		FileReader fr2 = null;
		fr2 = new FileReader("TxtData/loadinglistTemp.txt");
		String Line2 = null;

		BufferedReader br2 = null;
		br2 = new BufferedReader(fr2);
		Line2 = br2.readLine();
		init("TxtData/TransCenterArrival.txt");
		File financetempfile2 = new File("TxtData/TransCenterArrival.txt");
		OutputStreamWriter itemWriter2 = new OutputStreamWriter(new FileOutputStream(financetempfile2, true), "UTF-8");
		while (Line2 != null) {
			itemWriter2.write(Line2);
			itemWriter2.write("\r\n");
			Line2 = br2.readLine();
		}
		itemWriter2.close();
		try {
			File f5 = new File("TxtData/loadinglistTemp.txt");
			FileWriter fw5 = new FileWriter(f5);
			BufferedWriter bw1 = new BufferedWriter(fw5);
			bw1.write("");
		} catch (Exception e) {

		}

		// System.out.println("DELETE SUCCESS!");
	}

	@Override
	public void insertTrans(TranscenterArrivalListPO po) throws RemoteException {

		File loginfile = new File("TxtData/TransCenterArrival.txt");
		try {
			OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(loginfile, true), "UTF-8");
			itemWriter.write(po.getTranscenterID() + "");
			itemWriter.write(":");
			itemWriter.write(po.getArrivatime() + "");
			itemWriter.write(":");
			itemWriter.write(po.getTranscenterID() + "");
			itemWriter.write(":");
			itemWriter.write(po.getStartCity() + "");
			itemWriter.write(":");
			itemWriter.write(po.getState() + "");
			itemWriter.write(":");
			itemWriter.write(po.getLst() + "");
			itemWriter.write(":");
			itemWriter.write(po.getCode() + "");
			itemWriter.write("\r\n");
			itemWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("INSERT SUCCESS!!");
	}

	@Override
	public void deleteWareIn(long id) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<String>();

		FileReader fr = null;
		try {
			fr = new FileReader("TxtData/warein.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");

			if (!output[0].equals(String.valueOf(id))) {
				temp.add(Line);
			}
			Line = br.readLine();
		}
		if (Line == null) {
			init("TxtData/warein.txt");
		}

		File Arrivalfile = new File("TxtData/warein.txt");

		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(Arrivalfile, true), "UTF-8");
		for (int i = 0; i < temp.size(); i++) {
			itemWriter.write(temp.get(i));
			itemWriter.write("\r\n");
		}
		itemWriter.close();
	}

	public static void main(String[] args) throws IOException {
		try {
			ListStateDataService imp = new ListStateDataServiceTxtlmpl();
			imp.deleteMoneyOut(1949031443);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insertWareIn(WareInListPO po) throws RemoteException {
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
				itemWriter.write(":");
				itemWriter.write(po.getState().toString());
				itemWriter.write(":");
				itemWriter.write(po.getTranscenterid() + "");
				itemWriter.write(":");
				itemWriter.write(po.getVehicle().toString());
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
	public void updateWareIn(WareInListPO po) throws IOException {
		// TODO Auto-generated method stub
		deleteWareIn(po.getId());
		insertWareIn(po);
	}

	@Override
	public void deleteWareOut(long id) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<String>();

		FileReader fr = null;
		try {
			fr = new FileReader("TxtData/wareout.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");

			if (!output[0].equals(String.valueOf(id))) {
				temp.add(Line);
			}
			Line = br.readLine();
		}
		if (Line == null) {
			init("TxtData/wareout.txt");
		}

		File Arrivalfile = new File("TxtData/wareout.txt");

		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(Arrivalfile, true), "UTF-8");
		for (int i = 0; i < temp.size(); i++) {
			itemWriter.write(temp.get(i));
			itemWriter.write("\r\n");
		}
		itemWriter.close();
	}

	@Override
	public void insertWareOut(WareOutListPO po,GaragePlacePO place) throws RemoteException {
		// TODO Auto-generated method stub
		File Arrivalfile = new File("TxtData/wareout.txt");
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
				itemWriter.write(po.getVehicle().toString());
				itemWriter.write(":");
				itemWriter.write(po.getDestination().toString() + "");
				itemWriter.write(":");
				itemWriter.write(po.getTransid() + "");
				itemWriter.write(":");
				itemWriter.write(po.getState().toString());
				itemWriter.write(":");
				itemWriter.write(po.getTranscenterid() + "");
				itemWriter.write(":");
				itemWriter.write(place.getQu() + "-" + place.getPai() + "-" + place.getJia() + "-" + place.getWei());
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
	public void updateWareOut(WareOutListPO po) throws IOException {
		// TODO Auto-generated method stub
		GaragePlacePO gpo=getPlace(po.getId());
		deleteWareOut(po.getId());
		insertWareOut(po,gpo);
	}
	
	private GaragePlacePO getPlace(long id){
		GaragePlacePO po=null;
		FileReader fr = null;
		try {
			fr = new FileReader("TxtData/wareout.txt");
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
				String port[]=output[7].split("-");
				po=new GaragePlacePO(Integer.parseInt(port[0]), Integer.parseInt(port[1]),
						Integer.parseInt(port[2]), Integer.parseInt(port[3]));
				return po;
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
		
		return null;	
	}

	@Override
	public ArrayList<WareInListPO> findallNoneReviewedWareIn() throws RemoteException, IOException {
		ArrayList<WareInListPO> temp = new ArrayList<WareInListPO>();
		ArrayList<WareInListPO> result = new ArrayList<WareInListPO>();
		temp = findallWareIn();
		for (int i = 0; i < temp.size(); i++) {
			if (!temp.get(i).getState().equals(ListState.REVIEWED)) {
				result.add(temp.get(i));
			} else {
				;
			}
		}
		return result;
	}

	@Override
	public ArrayList<WareOutListPO> findallNoneReviewedWareOut() throws RemoteException, IOException {
		ArrayList<WareOutListPO> temp = new ArrayList<WareOutListPO>();
		ArrayList<WareOutListPO> result = new ArrayList<WareOutListPO>();
		temp = findallWareOut();
		for (int i = 0; i < temp.size(); i++) {
			if (!temp.get(i).getState().equals(ListState.REVIEWED)) {
				result.add(temp.get(i));
			} else {
				;
			}
		}
		return result;
	}

	@Override
	public void updateMoneyOut(MoneyOutListPO po) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		deleteMoneyOut(po.getId());
		insertMoneyOut(po);

	}

	@Override
	public void deleteMoneyOut(long id) throws RemoteException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<String>();

		FileReader fr = null;
		try {
			fr = new FileReader("TxtData/MoneyOutList.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = null;
		br = new BufferedReader(fr);
		String Line = br.readLine();
		while (Line != null) {
			String output[] = Line.split(":");

			if (!output[0].equals(String.valueOf(id))) {
				temp.add(Line);
			}
			Line = br.readLine();
		}
		if (Line == null) {
			init("TxtData/MoneyOutList.txt");
		}

		File Arrivalfile = new File("TxtData/MoneyOutList.txt");

		OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(Arrivalfile, true), "UTF-8");
		for (int i = 0; i < temp.size(); i++) {
			itemWriter.write(temp.get(i));
			itemWriter.write("\r\n");
		}
		itemWriter.close();
	}

	@Override
	public void insertMoneyOut(MoneyOutListPO po) throws RemoteException {
		// TODO Auto-generated method stub
		File MoneyInfile = new File("TxtData/MoneyOutList.txt");
		if (po == null) {
			System.out.println("MONEYOUTLIST IS NOTHING");
		}
		if (po != null) {
			try {
				OutputStreamWriter itemWriter = new OutputStreamWriter(new FileOutputStream(MoneyInfile, true),
						"UTF-8");
				itemWriter.write(po.getId() + "");
				itemWriter.write(":");
				itemWriter.write(po.getTime() + "");
				itemWriter.write(":");
				itemWriter.write(po.getMoney() + "");
				itemWriter.write(":");
				itemWriter.write(po.getName());
				itemWriter.write(":");
				itemWriter.write(po.getAccount().getName());
				itemWriter.write(":");
				itemWriter.write(po.getEntry().toString());
				itemWriter.write(":");
				itemWriter.write(po.getNote() + "");
				itemWriter.write(":");
				itemWriter.write(po.getLst().toString());
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
	public ArrayList<MoneyOutListPO> findallNoneMoneyOut() throws RemoteException, IOException {
		ArrayList<MoneyOutListPO> temp = new ArrayList<MoneyOutListPO>();
		ArrayList<MoneyOutListPO> result = new ArrayList<MoneyOutListPO>();
		temp = findallMoneyOut();
		for (int i = 0; i < temp.size(); i++) {
			if (!temp.get(i).getLst().equals(ListState.REVIEWED)) {
				result.add(temp.get(i));
			} else {
				;
			}
		}
		return result;
	}
}
