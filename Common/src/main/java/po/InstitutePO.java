package po;

import java.io.Serializable;

import util.City;
import util.OrgType;

public class InstitutePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private City city;// 城市
	private OrgType org;// 机构类型
	private String id;// 机构编号

	public InstitutePO(City city, OrgType org, String id) {

		this.city = city;
		this.org = org;
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public OrgType getOrg() {
		return org;
	}

	public void setOrg(OrgType org) {
		this.org = org;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return city.toString() + org.toString();
	}
}
