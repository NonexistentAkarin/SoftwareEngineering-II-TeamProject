package blservice.reviewblservice;

import java.util.ArrayList;
import java.util.Iterator;

import po.AccountPO;
import po.TimePO;
import util.Vehicle;
import vo.CarVO;

public interface CarBLservice {
	public Iterator<CarVO> findAll();

	public CarVO addCar(Vehicle vehicle, String name, long engine, String carNum, long basenumber, TimePO buytime,
			TimePO usetime);

	public boolean deleteCar(String name);

	public boolean Upate(ArrayList<CarVO> vo);

	public AccountPO getPo();

}
