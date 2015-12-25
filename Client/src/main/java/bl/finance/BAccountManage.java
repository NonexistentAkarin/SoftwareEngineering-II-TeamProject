package bl.finance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import blservice.financeblservice.BAccountBLService;
import dataimpl.datafactory.DataFactory;
import dataservice.datafactoryservice.DataFactoryService;
import dataservice.financedataservice.BAccountManageDataService;
import po.BaccountPO;
import po.InstitutePO;
import util.City;
import vo.BaccountVO;
import vo.InstituteVO;
import vo.StaffVO;

public class BAccountManage implements BAccountBLService {
	private DataFactoryService dataFactory;
	private ArrayList<BaccountVO> voList;
	private BAccountManageDataService bad;

	@Override
	public ArrayList<BaccountVO> findAll() {
		// TODO Auto-generated method stub
		dataFactory = new DataFactory();
		bad = dataFactory.getBAccountManageData();
		ArrayList<BaccountVO> voList = new ArrayList<BaccountVO>();
		ArrayList<BaccountPO> poList = new ArrayList<BaccountPO>();
		try {
			poList = bad.findAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < poList.size(); i++) {
			BaccountPO po = poList.get(i);
			BaccountVO vo = new BaccountVO(po.getName(), po.getAccount(), po.getBalance());
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public BaccountVO addStaff(String id, City city) {
		// TODO Auto-generated method stub
		voList = new ArrayList<BaccountVO>();
		BaccountVO vo = new BaccountVO(city.toString(), id, 0 + "");
		voList.add(vo);
		BaccountPO po = new BaccountPO(city.toString(), id, 0 + "");
		try {
			bad.insert(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean deleteStaff(String id) {
		// TODO Auto-generated method stub
		try {
			bad.delete(id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update(ArrayList<BaccountVO> voList) {
		// TODO Auto-generated method stub
		try {
			bad.init();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < voList.size(); i++) {
			BaccountVO vo = voList.get(i);
			BaccountPO po = new BaccountPO(vo.getName(), vo.getAccount(), vo.getBalance());
			try {
				bad.insert(po);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

}
