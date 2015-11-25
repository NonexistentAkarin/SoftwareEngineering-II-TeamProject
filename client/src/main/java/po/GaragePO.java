package po;

import java.io.Serializable;

public class GaragePO implements Serializable {

	private  int qu;/*区数量*/
	private  int pai;
	private  int jia;
	private  int wei;
	private garageware[][][][] place=new garageware[qu][pai][jia][wei];
	public GaragePO(int qu, int pai, int jia, int wei, garageware[][][][] place) {
		super();
		this.qu = qu;
		this.pai = pai;
		this.jia = jia;
		this.wei = wei;
		this.place = place;
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
	public garageware[][][][] getPlace() {
		return place;
	}
	public void setPlace(garageware[][][][] place) {
		this.place = place;
	}
	
}
