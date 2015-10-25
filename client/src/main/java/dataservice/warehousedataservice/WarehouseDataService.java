/**
 *仓库管理数据层接口
 *@author parkdc
 *@date  2015/10/25
 */
package dataservice.warehousedataservice;
import java.rmi.RemoteException;

public class WarehouseDataService {
	//按ID进行查找返回相应的WareHousePO结果
	Public WareHousePO find(long id) throws RemoteException;
	//在数据库中增加po记录
	public void insert(WareHousePO po) throws RemoteException;
	//删除一个po
	public void delete(WareHousePO po) throws RemoteException;
	//更新一个po
	public void update(WareHousePO po) throws RemoteException;
	//初始化持久化数据库
	public void init() throws RemoteException;
	//结束持久化数据库
	public void finish() throws RemoteException;


}