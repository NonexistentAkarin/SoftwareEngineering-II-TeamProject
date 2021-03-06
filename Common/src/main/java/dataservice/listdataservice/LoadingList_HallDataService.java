package dataservice.listdataservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.list.LoadingListPO;

public interface LoadingList_HallDataService extends Remote {

	public void insert(LoadingListPO po) throws RemoteException;

	public LoadingListPO find(long id) throws RemoteException, FileNotFoundException;

	public LoadingListPO findlast() throws RemoteException, IOException;

	public String readLastLine(File file, String charset) throws RemoteException, IOException;

	public void init() throws RemoteException;

	public ArrayList<LoadingListPO> findallLoadingHall() throws RemoteException, IOException;

	public ArrayList<LoadingListPO> findNoneReviewed() throws RemoteException, IOException;
}