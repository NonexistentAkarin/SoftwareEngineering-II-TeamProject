package blservice.financeblservice;

import po.BaccountPO;
import po.CostchartPO;
import po.ReceiptPO;
import po.StatisticalchartPO;
import util.ResultMessage;

public interface FinanceBLService {
	// 结算管理
	// 系统更新结算管理相关的信息
	public ResultMessage BalanceMan(ReceiptPO po);

	// 系统更新成本管理相关的账户，成本收益表及租金等数据信息
	public ResultMessage CostMan(BaccountPO ba, CostchartPO po);

	// 系统更新经营情况表
	public ResultMessage StatisticalChart(StatisticalchartPO po);

	// 显示账户信息
	public ResultMessage BaccountReview(BaccountPO ba);

	// 系统初始化账户信息
	public ResultMessage BaccountInit(BaccountPO po);

	public void endFinanace();

}