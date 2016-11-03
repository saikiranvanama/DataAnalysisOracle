package com.hw3;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.populate;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;

public class UI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel catPanel;
	private JCheckBox chckbxNewCheckBox;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JPanel subCatPanel;
	private JPanel attribPanel;
	private JPanel tablePanel;
	private JScrollPane scrollPane_3;
	private CatPanelCheckBoxActionHandler catPanelCheckBoxActionHandler;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;

	public void populateCategoriesPanel() {

		// Call database to get the master categories
		Connection dbConnection = populate.connectDatabase();

		String query = "select distinct d.categoryname \n" + "from businesscategories d \n"
				+ "join(select business_id from businesscategories  where categoryname = 'Restaurants') b on d.business_id = b.business_id \n"
				+ " order by d.categoryname";
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			statement = dbConnection.createStatement();
			resultSet = statement.executeQuery(query);

			// Get the countof resultset
			int totalRows = 0;
			while (resultSet.next()) {
				totalRows++;
			}

			GridLayout gridLayout = new GridLayout(totalRows, 1);
			catPanel.setLayout(gridLayout);

			System.out.println("Total Rows : " + totalRows);

			resultSet = statement.executeQuery(query);

			// Populate the panel with checkboxes
			while (resultSet.next()) {
				String catName = resultSet.getString("CATEGORYNAME");
				System.out.println("Cat Name : " + catName);
				chckbxNewCheckBox = new JCheckBox(catName);
				this.catPanelCheckBoxActionHandler = new CatPanelCheckBoxActionHandler();
				chckbxNewCheckBox.addActionListener(catPanelCheckBoxActionHandler);
				catPanel.add(chckbxNewCheckBox);
			}
		} catch (SQLException exec) {
			System.out.println(exec);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void invokeUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		scrollPane = new JScrollPane();
		panel.add(scrollPane);

		catPanel = new JPanel();
		scrollPane.setViewportView(catPanel);
		catPanel.setLayout(new GridLayout(0, 1, 0, 0));

		scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1);

		subCatPanel = new JPanel();
		scrollPane_1.setViewportView(subCatPanel);

		scrollPane_2 = new JScrollPane();
		panel.add(scrollPane_2);

		attribPanel = new JPanel();
		scrollPane_2.setViewportView(attribPanel);

		scrollPane_3 = new JScrollPane();
		panel.add(scrollPane_3);

		tablePanel = new JPanel();
		scrollPane_3.setViewportView(tablePanel);

		table = new JTable();
		table.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		tablePanel.add(table);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		
		comboBox = new JComboBox();
		panel_1.add(comboBox);
		
		comboBox_1 = new JComboBox();
		panel_1.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		panel_1.add(comboBox_2);
		
		btnNewButton_1 = new JButton("New button");
		panel_1.add(btnNewButton_1);

		populateCategoriesPanel();
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JScrollPane getScrollPane_1() {
		return scrollPane;
	}

	public JPanel getCatPanel() {
		return catPanel;
	}

	public JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
	}

	public JScrollPane getScrollPane_2() {
		return scrollPane_1;
	}

	public JScrollPane getScrollPane_3() {
		return scrollPane_2;
	}

	public JPanel getSubCatPanel() {
		return subCatPanel;
	}

	public JTable getTable() {
		return table;
	}

	public JPanel getAttribPanel() {
		return attribPanel;
	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public JScrollPane getScrollPane_4() {
		return scrollPane_3;
	}
}
