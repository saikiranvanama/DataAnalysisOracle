package com.hw3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class hw3 {

	private JFrame frmCecsYelp;
	private JPanel MainCategoryPanel;
	private CatPanelCheckBoxActionHandler catPanelCheckBoxActionHandler;
	private CatPanelCheckBoxActionHandler SubcatPanelCheckBoxActionHandler;
	private CatPanelCheckBoxActionHandler AttributePanelCheckBoxActionHandler;
	private JComboBox comboBoxWeekDay;
	private JComboBox comboBoxFrom;
	private JComboBox comboBoxTo;
	private JComboBox comboBoxSearchType;
	private JScrollPane DisplaySetPanel;
	private JPanel AttributePanel;
	private JPanel SubCategoryPanel;
	private JButton Search_Button;
	private JButton CloseButton;
	private JButton CategorySelectButton;
	private JTable table1;
	private JTable table_review;
	private JScrollPane Review_pane;
	private static Connection con = null;
	/**
	 * Launch the application.
	 */
	public static void InvokeUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw3 window = new hw3();
					window.frmCecsYelp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public hw3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public static void main(String[] args) {
		Connection dbConnection = hw3.connectDatabase();
		
		if (dbConnection != null) {
			try {
				hw3.InvokeUI();
			}catch (Exception e) {
				System.out.println("Conection not established" + e);;
			}
		} else{
			System.out.println("Conection not established");
		}
	}
	public static Connection connectDatabase() {
		String DriverClassName = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:yelpDatabase";
		String userName = "C##SAIKIRAN";
		String pwd = "oracle";
		try {
			Class.forName(DriverClassName);
			con = DriverManager.getConnection(url, userName, pwd);
			if (con != null) {
				System.out.println("Connection Established !!");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not found");
		} catch (SQLSyntaxErrorException e) {
			System.out.println("Table not found " + e);
		} catch (SQLException e) {
			System.out.println("Connection Not established" + e);
		} catch (Exception e) {

		}
		return con;
	}
	private void initialize() {
		frmCecsYelp = new JFrame();
		frmCecsYelp.getContentPane().setForeground(new Color(255, 255, 255));
		frmCecsYelp.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\programWorkspace\\eclipse java\\Yelp\\YelpApp\\src\\yelp.png"));
		frmCecsYelp.setTitle("CECS 521 Yelp DataSet");
		frmCecsYelp.getContentPane().setBackground(new Color(0, 0, 102));
		frmCecsYelp.setBounds(0, 0, 1100, 650);
		frmCecsYelp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		DisplaySetPanel = new JScrollPane();
		DisplaySetPanel.setBackground(new Color(240, 230, 140));
		
		JLabel lblNewLabel = new JLabel("Day of the week");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 17));
		
		comboBoxWeekDay = new JComboBox();
		comboBoxWeekDay.setModel(new DefaultComboBoxModel(new String[] {"ANY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"}));
		
		JLabel lblNewLabel_1 = new JLabel("From");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		
		comboBoxFrom = new JComboBox();
		comboBoxFrom.setModel(new DefaultComboBoxModel(new String[] {"09:00", "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"}));
		
		JLabel lblNewLabel_2 = new JLabel("To");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
		
		comboBoxTo = new JComboBox();
		comboBoxTo.setModel(new DefaultComboBoxModel(new String[] {"23:30", "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00"}));
		
		JLabel lblNewLabel_3 = new JLabel("Search for");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 15));
		
		comboBoxSearchType = new JComboBox();
		comboBoxSearchType.setModel(new DefaultComboBoxModel(new String[] {"NONE", "All atrributes"}));
		
		Search_Button = new JButton("SEARCH");
		Search_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Search_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					PopulatePannel4();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Search_Button.setMaximumSize(new Dimension(65, 23));
		Search_Button.setMinimumSize(new Dimension(65, 23));
		Search_Button.setPreferredSize(new Dimension(65, 23));
		
		CloseButton = new JButton("CLOSE");
		CloseButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				frmCecsYelp.dispose();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCecsYelp.dispose();
				
			}
		});
		
		CategorySelectButton = new JButton("Select Category");
		CategorySelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<String> arrList = new ArrayList<String>();
				arrList = catPanelCheckBoxActionHandler.getCategoriesSelected();
				String whereClause = " in (";
				int i;
				for(i = 0; i<arrList.size()-1; i++){
					whereClause = whereClause +"'"+arrList.get(i)+"',";
				}
				whereClause += "'"+arrList.get(i)+"')";
				String queryPart1 = "select distinct d.categoryname from businesscategories d join(select business_id from businesscategories  where categoryname" + whereClause + ") b on d.business_id = b.business_id  where d.categoryname not in (select categorypk from business28category) order by d.categoryname"; 
				System.out.println(queryPart1);
				PopulatePannel2(queryPart1);
				String queryPart2="Select distinct d.attributekeys from businessattributes d join (select business_id from businesscategories  where categoryname"+  whereClause +") b on d.business_id = b.business_id order by d.attributekeys";
				PopulatePanel3(queryPart2);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmCecsYelp.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(CategorySelectButton)
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(comboBoxWeekDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(62)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addComponent(comboBoxSearchType, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Search_Button, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addComponent(CloseButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(37))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addComponent(DisplaySetPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(18))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(DisplaySetPanel, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(Search_Button, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(CloseButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
							.addGap(32))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBoxFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBoxTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBoxSearchType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(CategorySelectButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel)
												.addComponent(lblNewLabel_1)
												.addComponent(lblNewLabel_2))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(comboBoxWeekDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(29)))
					.addGap(2))
		);
		
		AttributePanel = new JPanel();
		AttributePanel.setBackground(new Color(153, 255, 153));
		scrollPane_2.setViewportView(AttributePanel);
		
		SubCategoryPanel = new JPanel();
		SubCategoryPanel.setBackground(new Color(153, 255, 153));
		scrollPane_1.setViewportView(SubCategoryPanel);
		

		MainCategoryPanel = new JPanel();
		MainCategoryPanel.setBackground(new Color(153, 255, 153));
		scrollPane.setViewportView(MainCategoryPanel);
		frmCecsYelp.getContentPane().setLayout(groupLayout);
		populateCategoriesPanel();
	}
	public JPanel getPanel() {
		return MainCategoryPanel;
	}
	public JComboBox getComboBox() {
		return comboBoxWeekDay;
	}
	public JComboBox getComboBox_1() {
		return comboBoxFrom;
	}
	public JComboBox getComboBox_2() {
		return comboBoxTo;
	}
	public JComboBox getComboBox_3() {
		return comboBoxSearchType;
	}
	public JScrollPane getScrollPane_3() {
		return DisplaySetPanel;
	}
	public JPanel getPanel_2() {
		return AttributePanel;
	}
	public JPanel getPanel_1() {
		return SubCategoryPanel;
	}
	public JButton getBtnNewButton() {
		return Search_Button;
	}
	public JButton getBtnNewButton_1() {
		return CloseButton;
	}
	
	public void populateCategoriesPanel() {

		// Call database to get the master categories
		Connection dbConnection = hw3.connectDatabase();
		JCheckBox chckbxNewCheckBox;
		String query = "select categorypk from business28category";
		ResultSet resultSet;
		Statement statement = null;
		int totalRows = 0;
		try {
			statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			totalRows = resultSet.getRow();
			resultSet.beforeFirst();
			
			System.out.println("Total Rows : " + totalRows);
			resultSet = statement.executeQuery(query);
			this.catPanelCheckBoxActionHandler = new CatPanelCheckBoxActionHandler();

			// Populate the panel with check boxes
			while (resultSet.next()) {
				String catName = resultSet.getString("CATEGORYPK");
				System.out.println("Cat Name : " + catName);
				chckbxNewCheckBox = new JCheckBox(catName);
				
				chckbxNewCheckBox.addActionListener(catPanelCheckBoxActionHandler);
				MainCategoryPanel.add(chckbxNewCheckBox);
			}
			
		} catch (SQLException exec) {
			System.out.println(exec);
		}
		
		MainCategoryPanel.setLayout(new GridLayout(totalRows, 1));
		
	}
	public JButton getBtnNewButton_2() {
		return CategorySelectButton;
	}
	public void PopulatePannel2(String queryPart){
		// Call database to get the master categories
		Connection dbConnection = hw3.connectDatabase();
		JCheckBox chckbxNewCheckBox;
		SubCategoryPanel.removeAll();
		ResultSet resultSet;
		Statement statement = null;
		int totalRows = 0;
		int trows=0;
		try {
			statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(queryPart);
			resultSet.last();
			totalRows = resultSet.getRow();
			resultSet.beforeFirst();
			
			System.out.println("Total Rows : " + totalRows);
			
			resultSet = statement.executeQuery(queryPart);
			this.SubcatPanelCheckBoxActionHandler = new CatPanelCheckBoxActionHandler();

			// Populate the panel with check boxes
			while (resultSet.next()) {
				String catName = resultSet.getString("categoryname");
				System.out.println("Cat Name : " + catName);
				chckbxNewCheckBox = new JCheckBox(catName);
				
				chckbxNewCheckBox.addActionListener(SubcatPanelCheckBoxActionHandler);
				SubCategoryPanel.add(chckbxNewCheckBox);
			}
			
		} catch (SQLException exec) {
			System.out.println(exec);
		}
		
		trows=trows+totalRows;
		SubCategoryPanel.setLayout(new GridLayout(trows, 1));
		SubCategoryPanel.revalidate();
		SubCategoryPanel.repaint();
	}
	

	public void PopulatePanel3(String queryPart){
		// Call database to get the master categories
		Connection dbConnection = hw3.connectDatabase();
		JCheckBox chckbxNewCheckBox;
		ResultSet resultSet;
		AttributePanel.removeAll();
		Statement statement = null;
		int totalRows = 0;
		try {
			statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(queryPart);
			resultSet.last();
			totalRows = resultSet.getRow();
			resultSet.beforeFirst();
			
			System.out.println("Total Rows : " + totalRows);
			resultSet = statement.executeQuery(queryPart);
			this.AttributePanelCheckBoxActionHandler = new CatPanelCheckBoxActionHandler();

			// Populate the panel with check boxes
			while (resultSet.next()) {
				String catName = resultSet.getString("attributekeys");
				System.out.println("Cat Name : " + catName);
				chckbxNewCheckBox = new JCheckBox(catName);
				
				chckbxNewCheckBox.addActionListener(AttributePanelCheckBoxActionHandler);
				AttributePanel.add(chckbxNewCheckBox);
			}
			
		} catch (SQLException exec) {
			System.out.println(exec);
		}
		
		AttributePanel.setLayout(new GridLayout(totalRows, 1));
		AttributePanel.revalidate();
		AttributePanel.repaint();
	}
	
private void Table_4(String query) throws SQLException{
	Connection dbConnection = hw3.connectDatabase();
	Statement st = dbConnection.createStatement();
	ResultSet rs = st.executeQuery(query);
	if(rs != null){
		table1 = new JTable(buildTableModel(rs));
		table1.removeColumn(table1.getColumnModel().getColumn(1));
		DisplaySetPanel.add(table1);
		DisplaySetPanel.setViewportView(table1);
		DisplaySetPanel.revalidate();
		DisplaySetPanel.repaint();
		table1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table1.rowAtPoint(e.getPoint());
				String business_id = table1.getModel().getValueAt(row, 1).toString();
				if (row>=0)
				{
					JFrame review = new JFrame();
					review.setSize(500, 500);
					review.setTitle("Reviews - Works Slow. Be Patient.");
					review.setVisible(true);
					String ReviewQuery = "select REVIEW_DATE, STARS, TEXT, USER_ID, USEFUL from YELP_REVIEW where  BUSINESS_ID = '"+business_id+"'";
					try {
						ResultSet reviewResults = st.executeQuery(ReviewQuery);
						table_review = new JTable(buildTableModel(reviewResults));
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					Review_pane = new JScrollPane();
					review.add(Review_pane);
					Review_pane.add(table_review);
					Review_pane.setViewportView(table_review);
					Review_pane.revalidate();
					Review_pane.repaint();
					review.revalidate();
					review.repaint();				
				}
		}
	});
	}
	else{
		System.out.println("No Result");
		JOptionPane.showMessageDialog(null,
			    "No Result",
			    "Search Warning",
			    JOptionPane.WARNING_MESSAGE);
	}
}

public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException{
  ResultSetMetaData rsmd = rs.getMetaData();
 
  //column names
  Vector<String> columnNames = new Vector<String>();
  int columnCount = rsmd.getColumnCount();
  for (int column = 1; column <= columnCount; column++){
      columnNames.add(rsmd.getColumnName(column));
  }
 
  //data of the table
  Vector<Vector<Object>>data = new Vector<Vector<Object>>();
  while(rs.next()){
      Vector<Object> vector = new Vector<Object>();
      for(int columnIndex = 1; columnIndex<=columnCount; columnIndex++){
          vector.add(rs.getObject(columnIndex));
      }
      data.add(vector);
  }
  return new DefaultTableModel(data,columnNames);
}
	
	public void PopulatePannel4() throws SQLException{
		String queryFinal;
		
		ArrayList<String> arrList_panel1 = new ArrayList<String>();
		arrList_panel1 = catPanelCheckBoxActionHandler.getCategoriesSelected();
		if((comboBoxSearchType.getSelectedItem().toString() == "All atrributes")? true:false)
		{
			
			ArrayList<String> arrList_panel2 = new ArrayList<String>();
			ArrayList<String> arrList_panel3 = new ArrayList<String>();
			
			String whereClause = " where x.business_id in (";
			int i;
			if(SubcatPanelCheckBoxActionHandler!= null && AttributePanelCheckBoxActionHandler!= null){
				arrList_panel2 = SubcatPanelCheckBoxActionHandler.getCategoriesSelected();
				arrList_panel3 = AttributePanelCheckBoxActionHandler.getCategoriesSelected();
				for(int j=0; j< arrList_panel2.size(); j++){
					whereClause += " select business_id from businesscategories  where categoryname = '"+arrList_panel2.get(j)+"' union";
				}
				for(int j=0; j< arrList_panel3.size(); j++){
					whereClause += " select business_id from businessattributes  where attributeKeys = '"+arrList_panel3.get(j)+"' and attributeValue in ('true', 'yes') intersect";
				}
			}		
			for(i = 0; i<arrList_panel1.size()-1; i++){
				whereClause += " select business_id from businesscategories  where categoryname = '"+arrList_panel1.get(i)+"' union";
			}		
			whereClause += " select business_id from businesscategories  where categoryname = '"+arrList_panel1.get(i)+"')";

			String day = comboBoxWeekDay.getSelectedItem().toString();
			String openTime = comboBoxFrom.getSelectedItem().toString();
			String closeTime = comboBoxTo.getSelectedItem().toString();
			String TimeQuery;
			if(day.contains("ANY")){
				TimeQuery = "SELECT distinct business_id FROM businesstimings WHERE (to_date(to_char(cast(MondayOpen as date),'hh24:mi'), 'hh24:mi') >= to_date(' " + openTime + "','HH24:MI') and to_date(to_char(cast(MondayClose as date),'hh24:mi'), 'hh24:mi') <= TO_DATE(' " + closeTime + "', 'HH24:MI')) or (to_date(to_char(cast(TuesdayOpen as date),'hh24:mi'), 'hh24:mi') >= to_date(' " + openTime + "','HH24:MI') and to_date(to_char(cast(TuesdayClose as date),'hh24:mi'), 'hh24:mi') <= TO_DATE(' " + closeTime + "', 'HH24:MI')) or (to_date(to_char(cast(WednesdayOpen as date),'hh24:mi'), 'hh24:mi') >= to_date(' " + openTime + "','HH24:MI') and to_date(to_char(cast(WednesdayClose as date),'hh24:mi'), 'hh24:mi') <= TO_DATE(' " + closeTime + "', 'HH24:MI')) or(to_date(to_char(cast(ThursdayOpen as date),'hh24:mi'), 'hh24:mi') >= to_date(' " + openTime + "','HH24:MI') and to_date(to_char(cast(ThursdayClose as date),'hh24:mi'), 'hh24:mi') <= TO_DATE(' " + closeTime + "', 'HH24:MI')) or(to_date(to_char(cast(FridayOpen as date),'hh24:mi'), 'hh24:mi') >= to_date(' " + openTime + "','HH24:MI') and to_date(to_char(cast(FridayClose as date),'hh24:mi'), 'hh24:mi') <= TO_DATE(' " + closeTime + "', 'HH24:MI')) or(to_date(to_char(cast(SaturdayOpen as date),'hh24:mi'), 'hh24:mi') >= to_date(' " + openTime + "','HH24:MI') and to_date(to_char(cast(SaturdayClose as date),'hh24:mi'), 'hh24:mi') <= TO_DATE(' " + closeTime + "', 'HH24:MI')) or(to_date(to_char(cast(SundayOpen as date),'hh24:mi'), 'hh24:mi') >= to_date(' " + openTime + "','HH24:MI') and to_date(to_char(cast(SundayClose as date),'hh24:mi'), 'hh24:mi') <= TO_DATE(' " + closeTime + "', 'HH24:MI'))";;
			}else {
				TimeQuery = "SELECT business_id FROM businesstimings WHERE (to_date(to_char(cast(" + day + "Open" + " as date),'hh24:mi'), 'hh24:mi') >= to_date(' " + openTime + "','HH24:MI') and	to_date(to_char(cast(" + day + "Close" + " as date),'hh24:mi'), 'hh24:mi') <= TO_DATE(' " + closeTime + "', 'HH24:MI'))";
			}
			
			queryFinal = "select x.businessName, x.business_id, x.city, x.state, x.stars from yelpbusiness x join (" + TimeQuery + ") y on x.business_id = y.business_id"+whereClause;
		} else{
			String whereClause = " where x.business_id in (";
			int i;
			for(i = 0; i<arrList_panel1.size()-1; i++){
				whereClause += " select business_id from businesscategories  where categoryname = '"+arrList_panel1.get(i)+"' union";
			}		
			whereClause += " select business_id from businesscategories  where categoryname = '"+arrList_panel1.get(i)+"')";
			queryFinal = "select x.businessName, x.business_id, x.city, x.state, x.stars from yelpbusiness x"+whereClause;
		}
		queryFinal += " order by x.businessName";
		System.out.println(queryFinal);
		Table_4(queryFinal);
		
//		System.out.println(TimeQuery);
		
	}
	

}


class CatPanelCheckBoxActionHandler implements ActionListener {
	
	 ArrayList<String> categoriesSelected = null;
	
	
	
	public CatPanelCheckBoxActionHandler() {
		this.categoriesSelected = new ArrayList<String>();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		JCheckBox cb = (JCheckBox) event.getSource();
        if (cb.isSelected()) {
            System.out.println("Checkbox "+cb.getText()+" is checked");
            //this.getCategoriesSelected().add(cb.getText());
            categoriesSelected.add(cb.getText());
            System.out.println(categoriesSelected.size());
        } else {
        	System.out.println("Checkbox "+cb.getText()+" is unchecked");
        	this.getCategoriesSelected().remove(cb.getText());
        }
	}

	/**
	 * @return the categoriesSelected
	 */
	public ArrayList<String> getCategoriesSelected() {
		return this.categoriesSelected;
	}

	/**
	 * @param categoriesSelected the categoriesSelected to set
	 */
	public void setCategoriesSelected(ArrayList<String> categoriesSelected) {
		this.categoriesSelected = categoriesSelected;
	}
	
	public void pane2Query(){
		System.out.println(categoriesSelected);
	}

}
