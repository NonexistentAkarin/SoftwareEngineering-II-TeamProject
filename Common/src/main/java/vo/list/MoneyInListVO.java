package vo.list;

import java.io.Serializable;
import java.util.Vector;

import po.AccountPO;
import po.TimePO;

public class MoneyInListVO extends Vector<String> implements Serializable {
	private TimePO time;
	private double money;
	private AccountPO account;
	private long id;
	private boolean isApproved;
	private String baccount;

	public MoneyInListVO(TimePO time, double money, AccountPO account, long id, boolean isApproved, String baccount) {
		super();
		this.time = time;
		this.money = money;
		this.account = account;
		this.id = id;
		this.isApproved = isApproved;
		this.baccount = baccount;

		this.add(account.getUsername());
		this.add(time.toSpecicalString());
		this.add(id + "");
		this.add(money + "");
		if (isApproved)
			this.add("已核对");
		else
			this.add("未核对");
		this.add(baccount);
	}

	public boolean isApproved() {
		return isApproved;
	}

	public TimePO getTime() {
		return time;
	}

	public double getMoney() {
		return money;
	}

	public AccountPO getAccount() {
		return account;
	}

	public long getId() {
		return id;
	}

	public String getBaccount() {
		return baccount;
	}
}
