package ui.page;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import blservice.accountblservice.AccountBLService;
import blservice.reviewblservice.LogBLService;
import po.TimePO;
import ui.XButton;
import ui.XContorlUtil;
import ui.XLabel;
import util.Permission;
import vo.AccountVO;

public class LoginAcocuntMangerView extends JPanel {
	private DefaultTableModel accountModel;
	private JTable accountTable;
	private AccountBLService bl;
	private Iterator<AccountVO> voListIterator;
	private ArrayList<AccountVO> voUpdateList;
	private JComboBox permissionCombobox;
	private JComboBox permissionInCombobox;
	private JTextField accountField;
	private JTextField passwordField;
	private JTextField staffidField;
	private JTextField orgidField;
	private String permission;
	private String staffid;
	private int selectedRow;

	public LoginAcocuntMangerView(AccountBLService bl) {
		this.setName("账号管理");

		this.bl = bl;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		voListIterator = null;
		voUpdateList = new ArrayList<AccountVO>();
		permissionCombobox = new JComboBox();
		permissionCombobox.addItem("快递员");
		permissionCombobox.addItem("营业厅业务员");
		permissionCombobox.addItem("中转中心业务员");
		permissionCombobox.addItem("中转中心仓库管理人员");
		permissionCombobox.addItem("财务人员");
		permissionCombobox.addItem("总经理");
		permissionCombobox.addItem("司机");
		permissionCombobox.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		permissionInCombobox = new JComboBox();
		permissionInCombobox.addItem("快递员");
		permissionInCombobox.addItem("营业厅业务员");
		permissionInCombobox.addItem("中转中心业务员");
		permissionInCombobox.addItem("中转中心仓库管理人员");
		permissionInCombobox.addItem("财务人员");
		permissionInCombobox.addItem("总经理");
		permissionInCombobox.addItem("司机");
		permissionInCombobox.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		initInputField();
		// 初始化账号列表界面
		initWareListTable();

		// 初始化按钮
		initButton();

		this.validate();
	}

	private void initInputField() {
		// TODO Auto-generated method stub
		permission = "快递员";
		permissionInCombobox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if (evt.getStateChange() == ItemEvent.SELECTED) {
					permission = (String) permissionInCombobox.getSelectedItem();
				}
			}
		});
		XLabel permissionLabel = new XLabel("权限：");
		XLabel accountLabel = new XLabel("账号：");
		accountField = new JTextField();
		accountLabel.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		accountField.setPreferredSize(new Dimension(100, 26));
		XLabel passwordLabel = new XLabel("密码：");
		passwordField = new JTextField();
		passwordLabel.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		passwordField.setPreferredSize(new Dimension(100, 26));

		XLabel staffidLabel = new XLabel("职员编号：");
		staffidField = new JTextField();
		staffidLabel.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		staffidField.setPreferredSize(new Dimension(100, 26));
		XLabel sLabel = new XLabel("  -  ");
		sLabel.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		orgidField = new JTextField();
		orgidField.setPreferredSize(new Dimension(100, 26));

		XButton addItemButton = new XButton("添加");
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addItem();
			}
		});

		JPanel inputPanel = new JPanel();
		JPanel inputPanel0 = new JPanel();
		JPanel inputPanel1 = new JPanel();
		// inputPanel.setBackground(XContorlUtil.MENUITEM_BACKGROUND);
		inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		inputPanel.add(permissionLabel);
		inputPanel.add(permissionInCombobox);
		inputPanel.add(accountLabel);
		inputPanel.add(accountField);
		inputPanel.add(passwordLabel);
		inputPanel.add(passwordField);
		inputPanel0.setLayout(new FlowLayout(FlowLayout.LEFT));
		inputPanel0.add(staffidLabel);
		inputPanel0.add(orgidField);
		inputPanel0.add(sLabel);
		inputPanel0.add(staffidField);
		inputPanel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		inputPanel1.add(addItemButton);

		this.add(inputPanel);
		this.add(inputPanel0);
		this.add(inputPanel1);

	}

	private void initButton() {
		// TODO Auto-generated method stub
		XButton submitButton = new XButton("修改");
		submitButton.addActionListener(new ActionListener() {
			// //修改选中表格的数据
			public void actionPerformed(ActionEvent e) {
				voUpdateList = new ArrayList<AccountVO>();
				int col = accountModel.getColumnCount();
				int row = accountModel.getRowCount();
				for (int i = 0; i < row; i++) {
					AccountVO vo = null;
					String[] inf = new String[5];
					for (int j = 0; j < col; j++) {
						inf[j] = (String) accountModel.getValueAt(i, j);
					}
					vo = new AccountVO(Long.parseLong(inf[0]), Permission.toPermission(inf[1]), inf[2], inf[3], inf[4]);
					voUpdateList.add(vo);

				}

				boolean result = bl.permissionUpate(voUpdateList);
				if (result == true) {
					JOptionPane.showMessageDialog(null, "修改成功！", "", JOptionPane.INFORMATION_MESSAGE);
					LogBLService.insert(TimePO.getNowTimePO(),
							bl.getAccountPO().getPermission().toString() + bl.getAccountPO().getUsername() + "修改了账号信息");
				} else {
					JOptionPane.showMessageDialog(null, "修改失败！", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		XButton deleteButton = new XButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteItem();
			}
		});

		JPanel submitPanel = new JPanel();
		submitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		submitPanel.add(deleteButton);
		submitPanel.add(submitButton);
		this.add(submitPanel);
	}

	private void initWareListTable() {
		// TODO Auto-generated method stub
		JScrollPane scrollPane = new JScrollPane();

		// 表头
		Vector<String> vColumns = new Vector<String>();
		vColumns.add("ID");
		vColumns.add("权限");
		vColumns.add("用户名");
		vColumns.add("密码");
		vColumns.add("职员id");

		Vector<AccountVO> vData = new Vector<AccountVO>();
		// //模型
		accountModel = new DefaultTableModel(vData, vColumns);
		// //表格
		accountTable = new JTable(accountModel) {
			private int myRow = -1;
			TableCellEditor myEditor;
			private static final long serialVersionUID = 1L;

			// //设置第一行第一列不可编辑
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				}
				if (row == 0) {
					return false;
				}
				return true;
			}

		};

		JTableHeader tableH = accountTable.getTableHeader();
		TableColumn tableColumn = accountTable.getColumn("权限");
		tableColumn.setCellEditor(new DefaultCellEditor(permissionCombobox));
		// tableH.setBackground(XContorlUtil.OUTLOOK_CONTAINER_COLOR);
		tableH.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		tableH.setFont(XContorlUtil.FONT_14_BOLD);
		accountTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accountTable.setShowVerticalLines(false);
		accountTable.setShowHorizontalLines(false);
		scrollPane.getViewport().add(accountTable);
		accountTable.setFillsViewportHeight(true);
		accountTable.addMouseListener(new MouseAdapter() { // 鼠标事件
			public void mouseClicked(MouseEvent e) {
				selectedRow = accountTable.getSelectedRow(); // 获得选中行索引
			}
		});
		this.add(scrollPane);
		voListIterator = bl.findAll();
		for (;voListIterator.hasNext();) {
			AccountVO vo = voListIterator.next();
			accountModel.addRow(vo);
		}

	}

	protected void addItem() {
		// TODO Auto-generated method stub
		try {
			AccountVO account = bl.addAccount(Permission.toPermission(permission), accountField.getText(),
					passwordField.getText(), (orgidField.getText() + "-" + staffidField.getText()));
			accountModel.addRow(account);
			LogBLService.insert(TimePO.getNowTimePO(), bl.getAccountPO().getPermission().toString()
					+ bl.getAccountPO().getUsername() + "添加了账号：" + accountField.getText());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "无对应职员信息", "", JOptionPane.ERROR_MESSAGE);
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "请正确输入", "", JOptionPane.ERROR_MESSAGE);
		}
		accountField.setText("");
		passwordField.setText("");
		LoginAcocuntMangerView.this.validate();
	}

	protected void deleteItem() {
		// TODO Auto-generated method stub
		long id = Long.parseLong((String) accountModel.getValueAt(selectedRow, 0));
		if (!accountModel.getValueAt(selectedRow, 1).equals("管理员")) {
			bl.deleteAccount(id);
			accountModel.removeRow(selectedRow);
			LoginAcocuntMangerView.this.validate();
		} else {
			JOptionPane.showMessageDialog(null, "管理员账号无法被删除", "", JOptionPane.ERROR_MESSAGE);
		}
	}
}
