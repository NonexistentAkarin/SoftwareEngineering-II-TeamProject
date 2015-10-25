//枚举所有城市
package util;

public enum City {
	BEIJING, // 北京
	SHANGHAI, // 上海
	NANJING, // 南京
	GUANGZHOU; // 广州

	public String toString() {
		switch (this) {
		case BEIJING:
			return "北京";
		case SHANGHAI:
			return "上海";
		case NANJING:
			return "南京";
		case GUANGZHOU:
			return "广州";
		default:
			return null;
		}
	}

}
