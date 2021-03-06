package po.list;

import java.io.Serializable;

import po.TimePO;
import util.ListState;
import util.ListType;

public class ListPO implements Serializable {
	private ListType listType;// 单据类型
	private long id;// 单据id
	private ListState lst;// 单据状态
	private TimePO time;// 提交时间

	public ListPO(ListType listType, long id, ListState lst, TimePO time) {
		super();
		this.listType = listType;
		this.id = id;
		this.lst = lst;
		this.time = time;
	}

	public ListType getListType() {
		return listType;
	}

	public void setListType(ListType listType) {
		this.listType = listType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ListState getLst() {
		return lst;
	}

	public void setLst(ListState lst) {
		this.lst = lst;
	}

	public ListType toListType(String m) {
		return listType;

	}

}
