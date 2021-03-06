package dataservice.listdataservice;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.AccountPO;
import po.list.MoneyInListPO;

public interface MoneyInListDataService extends Remote {
	public void init() throws RemoteException;

	public boolean MoneyInUpdate(ArrayList<MoneyInListPO> po) throws RemoteException, IOException;

	public ArrayList<MoneyInListPO> findAll(AccountPO po) throws RemoteException, IOException;

	public ArrayList<MoneyInListPO> findAllWithNoDel() throws RemoteException, IOException;

	public ArrayList<String> delete() throws RemoteException, IOException;

	public void insert(ArrayList<MoneyInListPO> po) throws RemoteException;

	public void update(ArrayList<MoneyInListPO> po) throws RemoteException;

	public ArrayList<AccountPO> findAllCourier(AccountPO po) throws RemoteException, IOException;

	public String delete(ArrayList<MoneyInListPO> po) throws RemoteException, IOException;

	public ArrayList<MoneyInListPO> findAllExist(AccountPO po) throws RemoteException, IOException;

	public ArrayList<String> findAllBAccount() throws RemoteException, IOException;

	public ArrayList<AccountPO> findAllCourierNoPo() throws RemoteException, IOException;
}
