/**
 * 账户管理业务逻辑接口
 */
package blservice.accountblservice;

import java.util.ArrayList;
import java.util.Iterator;

import po.AccountPO;
import util.Permission;
import vo.AccountVO;

public interface AccountBLService {
	// 输入职员id，显示该职员的账号信息
	public AccountPO accountRev(String username);

	// 输入的职员id,修改该账号的权限
	public boolean permissionUpate(ArrayList<AccountVO> vo);

	public Iterator<AccountVO> findAll();

	public AccountVO addAccount(Permission permission, String username, String password, String staffid);

	public boolean deleteAccount(long id);

	public void endAccount();

	public AccountPO getAccountPO();
}
