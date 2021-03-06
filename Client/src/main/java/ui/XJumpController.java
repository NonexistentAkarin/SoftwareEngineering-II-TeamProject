package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import bl.account.AccountManger;
import bl.finance.BAccountManage;
import bl.finance.FinanceMIBL;
import bl.finance.FinanceMOBL;
import bl.inquire.Inquire;
import bl.list.ArrivaListBL;
import bl.list.DeliveryListBL;
import bl.list.MoneyInListBL;
import bl.list.OrdersInputBL;
import bl.list.ReceiveCourierListBL;
import bl.review.BeginningSetupBL;
import bl.review.DistanceConstantBL;
import bl.review.InstituteManager;
import bl.review.ListReviewBL;
import bl.review.LogBL;
import bl.review.ManagerSetRewardBL;
import bl.review.PriceConstantBL;
import bl.review.StaffManager;
import bl.trans.CarBL;
import bl.trans.DriverBL;
import bl.trans.TransCenterArriveBL;
import bl.warehouse.LoadingList;
import bl.warehouse.WareInBLserviceImpl;
import bl.warehouse.WareOutBLserviceImpl;
import blservice.accountblservice.AccountBLService;
import blservice.financeblservice.BAccountBLService;
import blservice.financeblservice.FinanceMIBLService;
import blservice.financeblservice.FinanceMOBLService;
import blservice.inquireblservice.InquireBLService;
import blservice.listblservice.MoneyInListBLService;
import blservice.listblservice.OrdersInputBLService;
import blservice.listblservice.ReceiveCourierListBLService;
import blservice.listblservice.arrivaList_HallBLService;
import blservice.listblservice.delivery_HallBLService;
import blservice.reviewblservice.BeginningSetupBLService;
import blservice.reviewblservice.CarBLservice;
import blservice.reviewblservice.ConstantBLService;
import blservice.reviewblservice.DriverBLservice;
import blservice.reviewblservice.InstituteBLService;
import blservice.reviewblservice.ListReviewBLServive;
import blservice.reviewblservice.LogBLService;
import blservice.reviewblservice.ManagerSetRewardBLService;
import blservice.reviewblservice.StaffBLService;
import blservice.transblservice.LoadingListBLService;
import blservice.transblservice.TransCenterArriveBLService;
import blservice.warehouseblservice.WareInBLservice;
import blservice.warehouseblservice.WareOutBLservice;
import po.AccountPO;
import ui.page.BAccountManageView;
import ui.page.BeginningSetupView;
import ui.page.CarView;
import ui.page.CenterAcceptView;
import ui.page.Chart1View;
import ui.page.Chart2View;
import ui.page.DistanceConstantView;
import ui.page.DriverView;
import ui.page.FinanceMIView;
import ui.page.FinanceMO_CommissionView;
import ui.page.FinanceMO_DriverView;
import ui.page.FinanceMO_FreigntView;
import ui.page.FinanceMO_RentView;
import ui.page.FinanceMO_RewardView;
import ui.page.FinanceMO_SalaryView;
import ui.page.InquireView;
import ui.page.InstituteManageView;
import ui.page.ListReviewView;
import ui.page.LoadingListInputView;
import ui.page.LoadingListInputView_Hall;
import ui.page.LogView;
import ui.page.LoginAcocuntMangerView;
import ui.page.ManagerSetRewardView;
import ui.page.MoneyInView_Hall;
import ui.page.OrdersInputView;
import ui.page.PriceConstantView;
import ui.page.ReceiveInputView;
import ui.page.StaffInfView;
import ui.page.StaffManageView;
import ui.page.WareChangeView;
import ui.page.WareInView;
import ui.page.WareOutView;
import ui.page.WareShowView;
import ui.page.WareStockTakeView;
import ui.page.deliveryview_Hall;
import ui.page.reciveview_Hall;
import ui.tab.XTabPage;
import util.Permission;

public class XJumpController {
	private OrdersInputBLService obl;
	private arrivaList_HallBLService abl;
	private delivery_HallBLService dbl;
	private LoadingListBLService lbl;
	private AccountBLService accountBl;
	private InstituteBLService ibl;
	private ReceiveCourierListBLService rcBL;
	private MoneyInListBLService mibl;
	private TransCenterArriveBLService tbl;
	private FinanceMIBLService fbl;
	private FinanceMOBLService fobl;
	private StaffBLService sbl;
	private WareInBLservice wbl;
	private WareOutBLservice wobl;
	private ManagerSetRewardBLService msbl;
	private WareOutBLservice wobl2;
	private BAccountBLService babl;
	private AccountPO po;
	private BeginningSetupBLService bsbl;
	private LogBLService lobl;
	private CarBLservice cbl;
	private DriverBLservice drbl;
	private InquireBLService iqbl;
	private ListReviewBLServive lrbl;
	private ConstantBLService pcobl;
	private ConstantBLService dcobl;

	public XJumpController(AccountPO po) {
		this.po = po;

		if (po.getPermission() != Permission.SENDER) {
			obl = new OrdersInputBL(po);
			abl = new ArrivaListBL(po);
			dbl = new DeliveryListBL(po);
			lbl = new LoadingList(po);
			accountBl = new AccountManger(po);
			ibl = new InstituteManager(po);
			rcBL = new ReceiveCourierListBL(po);
			mibl = new MoneyInListBL(po);
			tbl = new TransCenterArriveBL(po);
			sbl = new StaffManager(po);
			msbl = new ManagerSetRewardBL(po);
			fbl = new FinanceMIBL(po);
			fobl = new FinanceMOBL(po);
			wbl = new WareInBLserviceImpl(po);
			wobl = new WareOutBLserviceImpl(po);
			wobl2 = new WareOutBLserviceImpl(po);
			babl = new BAccountManage(po);
			bsbl = new BeginningSetupBL(po);
			cbl = new CarBL(po);
			drbl = new DriverBL(po);
			lobl = new LogBL();
			lrbl = new ListReviewBL(po);
			pcobl = new PriceConstantBL(po);
			dcobl = new DistanceConstantBL(po);
		} else
			iqbl = new Inquire(po);
	}

	public List<XTabPage> getPageList(String command) {
		List<XTabPage> pageList = new ArrayList<XTabPage>();

		switch (command) {
		case "职员信息":
			pageList.add(createPage(new StaffInfView(po)));
			break;
		case "账号管理":
			pageList.add(createPage(new LoginAcocuntMangerView(accountBl)));
			break;
		case "中转接收":
			pageList.add(createPage(new CenterAcceptView(tbl)));
			break;
		case "装运管理":
			pageList.add(createPage(new LoadingListInputView(lbl)));
			break;
		case "统计报表":
			pageList.add(createPage(new Chart1View(fobl, mibl)));
			pageList.add(createPage(new Chart2View(fobl, mibl)));
			break;
		case "订单输入":
			pageList.add(createPage(new OrdersInputView(obl)));
			break;
		case "收件信息输入":
			pageList.add(createPage(new ReceiveInputView(rcBL)));
			break;
		case "车辆装车管理":
			pageList.add(createPage(new LoadingListInputView_Hall(po)));
			break;
		case "接收与派件":
			pageList.add(createPage(new reciveview_Hall(abl)));
			pageList.add(createPage(new deliveryview_Hall(dbl)));
			break;
		case "车辆司机信息管理":
			pageList.add(createPage(new DriverView(drbl)));
			pageList.add(createPage(new CarView(cbl)));
			break;
		case "收款单":
			pageList.add(createPage(new MoneyInView_Hall(mibl)));
			break;
		case "人员机构管理":
			pageList.add(createPage(new InstituteManageView(ibl)));
			pageList.add(createPage(new StaffManageView(sbl)));
			break;
		case "审批单据":
			pageList.add(createPage(new ListReviewView(lrbl)));
			break;
		case "查看统计分析":
			pageList.add(createPage(new Chart1View(fobl, mibl)));
			pageList.add(createPage(new Chart2View(fobl, mibl)));
			break;
		case "寄件信息查询":
			pageList.add(createPage(new InquireView(iqbl)));
			break;
		case "出库单生成":
			pageList.add(createPage(new WareInView(wbl)));
			break;
		case "结算管理":
			pageList.add(createPage(new FinanceMIView(fbl)));
			break;
		case "成本管理":
			pageList.add(createPage(new FinanceMO_SalaryView(fobl)));
			pageList.add(createPage(new FinanceMO_FreigntView(fobl)));
			pageList.add(createPage(new FinanceMO_RentView(fobl)));
			pageList.add(createPage(new FinanceMO_RewardView(fobl)));
			pageList.add(createPage(new FinanceMO_CommissionView(fobl, mibl)));
			pageList.add(createPage(new FinanceMO_DriverView(fobl)));
			break;
		case "入库单生成":
			pageList.add(createPage(new WareOutView(wobl)));
			break;
		case "库存管理":
			pageList.add(createPage(new WareShowView(wobl)));
			pageList.add(createPage(new WareStockTakeView(wobl2)));
			break;
		case "库存调整":
			pageList.add(createPage(new WareChangeView(wbl)));
			break;
		case "账户管理":
			pageList.add(createPage(new BAccountManageView(babl)));
			break;
		case "修改工资策略":
			pageList.add(createPage(new ManagerSetRewardView(msbl)));
			break;
		case "期初建账":
			pageList.add(createPage(new BeginningSetupView(bsbl)));
			break;
		case "日志":
			pageList.add(createPage(new LogView(lobl)));
			break;
		case "常量调整":
			pageList.add(createPage(new PriceConstantView(pcobl)));
			pageList.add(createPage(new DistanceConstantView(dcobl)));
			break;
		}
		return pageList;
	}

	private XTabPage createPage(JComponent pageContent) {
		XTabPage page = new XTabPage(pageContent);
		page.setName(pageContent.getName());
		return page;
	}

	public String getoutlookPanelXML(Permission permission) {
		switch (permission) {
		case COURIER:
			return "ui/outlook_courier.xml";
		case SENDER:
			return "ui/outlook_sender.xml";
		case HALLCLERK:
			return "ui/outlook_hallclerk.xml";
		case CENTERCLERK:
			return "ui/outlook_centerclerk.xml";
		case WAREKEEPER:
			return "ui/outlook_warekeeper.xml";
		case COUNTER:
			return "ui/outlook_counter.xml";
		case MANAGER:
			return "ui/outlook_manager.xml";
		case ADMINISTRATOR:
			return "ui/outlook_administrator.xml";
		case ICOUNTER:
			return "ui/outlook_icounter.xml";
		default:
			return null;
		}
	}
}
