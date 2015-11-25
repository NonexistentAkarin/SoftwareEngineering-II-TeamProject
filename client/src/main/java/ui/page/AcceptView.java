package ui.page;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ui.XContorlUtil;
import ui.XLabel;

public class AcceptView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField centerNumField;
	private JTextField transSheetNumField;
	private JTextField arriveDateField;
	private JComboBox arriveStatusBox;
	private JComboBox departPlaceBox;
	
	private DefaultTableModel acceptInputModel;
	private JTable acceptInputTable;

	public AcceptView(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		init();
		
		this.validate();
	}
	
	
	private void init(){
		//第一行
		XLabel centerNumLabel = new XLabel("中转中心编号");
		centerNumField=new JTextField();
		centerNumLabel.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		centerNumField.setPreferredSize(new Dimension(215,26));
		XLabel transSheetNumLabel = new XLabel("中转单编号");
		transSheetNumField=new JTextField();
		transSheetNumLabel.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		transSheetNumField.setPreferredSize(new Dimension(215,26));
		JPanel acceptNumPanel = new JPanel();
		acceptNumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		acceptNumPanel.add(centerNumLabel);
		acceptNumPanel.add(centerNumField);
		acceptNumPanel.add(transSheetNumLabel);
		acceptNumPanel.add(transSheetNumField);
		
		this.add(acceptNumPanel);
		
		//第二行
		XLabel arriveDateLabel = new XLabel("到达日期");
		arriveDateField=new JTextField();
		arriveDateLabel.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		arriveDateField.setPreferredSize(new Dimension(215,26));
		
		XLabel arriveStatusLabel = new XLabel("货物到达状态");
		arriveStatusLabel.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		initBox();		
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.add(arriveDateLabel);
		panel2.add(arriveDateField);
		panel2.add(arriveStatusLabel);
		panel2.add(arriveStatusBox);
		
		this.add(panel2);
		//第三行
		XLabel dateLabel = new XLabel("出发地");
		dateLabel.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel3.add(dateLabel);
		panel3.add(departPlaceBox);
		
		//添加按钮
		JButton addButton = new JButton("添加");
//		JPanel addButtonPanel = new JPanel();
//		addButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel3.add(addButton);
		this.add(panel3);
		
		initTable();
		
		//确定按钮
		JButton confirmButton = new JButton("确定");
		JPanel confirmButtonPanel = new JPanel();
		confirmButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		confirmButtonPanel.add(confirmButton);
		this.add(confirmButtonPanel);
	}
	
	private void initTable(){
		JScrollPane scrollPane = new JScrollPane();
		
		//表头
		Vector<String> vColumns = new Vector<String>();
		vColumns.add("中转中心编号");
		vColumns.add("中专单编号");
		vColumns.add("到达日期");
		vColumns.add("货物到达状态");
		vColumns.add("出发地");
		
//		//模型
		   acceptInputModel = new DefaultTableModel(null, vColumns);
//		//表格
		   acceptInputTable = new JTable(acceptInputModel){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		 JTableHeader tableH=acceptInputTable.getTableHeader();
	
//		 tableH.setBackground(XContorlUtil.OUTLOOK_CONTAINER_COLOR);
		 tableH.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		 tableH.setFont(XContorlUtil.FONT_14_BOLD);
		 acceptInputTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 acceptInputTable.setShowVerticalLines(false);
		 acceptInputTable.setShowHorizontalLines(false);
		scrollPane.getViewport().add(acceptInputTable);
		acceptInputTable.setFillsViewportHeight(true);
		this.add(scrollPane);	
	}	
	
	private void initBox(){
		arriveStatusBox=new JComboBox();
		arriveStatusBox.addItem("完好");
		arriveStatusBox.addItem("损坏");
		arriveStatusBox.addItem("部分损坏");
		arriveStatusBox.addItem("遗失");
		arriveStatusBox.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
		
		departPlaceBox=new JComboBox();
		departPlaceBox.addItem("北京");
		departPlaceBox.addItem("上海");
		departPlaceBox.addItem("南京");
		departPlaceBox.addItem("广州");
		departPlaceBox.setForeground(XContorlUtil.DEFAULT_PAGE_TEXT_COLOR);
	}
}
