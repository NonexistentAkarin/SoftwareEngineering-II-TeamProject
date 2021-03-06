package po;

import java.io.Serializable;

public class GaragePlacePO implements Serializable {
	private int qu;
	private int pai;
	private int jia;
	private int wei;

	public GaragePlacePO(int qu, int pai, int jia, int wei) {
		super();
		this.qu = qu;
		this.pai = pai;
		this.jia = jia;
		this.wei = wei;
	}

	public int getQu() {
		return qu;
	}

	public void setQu(int qu) {
		this.qu = qu;
	}

	public int getPai() {
		return pai;
	}

	public void setPai(int pai) {
		this.pai = pai;
	}

	public int getJia() {
		return jia;
	}

	public void setJia(int jia) {
		this.jia = jia;
	}

	public int getWei() {
		return wei;
	}

	public void setWei(int wei) {
		this.wei = wei;
	}

	public void showplace() {
		System.out.println("当前位置为" + qu + "区" + pai + "排" + jia + "架" + wei + "位");
	}

}
