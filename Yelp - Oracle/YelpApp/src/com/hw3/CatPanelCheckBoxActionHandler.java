package com.hw3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;

//public class CatPanelCheckBoxActionHandler implements ActionListener {
//	
//	private ArrayList<String> categoriesSelected = null;
//	
//	
//	
//	public CatPanelCheckBoxActionHandler() {
//		this.categoriesSelected = new ArrayList<String>();
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent event) {
//		// TODO Auto-generated method stub
//		JCheckBox cb = (JCheckBox) event.getSource();
//        if (cb.isSelected()) {
//            System.out.println("Checkbox "+cb.getText()+" is checked");
//            //this.getCategoriesSelected().add(cb.getText());
//            categoriesSelected.add(cb.getText());
//            System.out.println(categoriesSelected.size());
//        } else {
//        	System.out.println("Checkbox "+cb.getText()+" is unchecked");
//        	this.getCategoriesSelected().remove(cb.getText());
//        }
//	}
//
//	/**
//	 * @return the categoriesSelected
//	 */
//	public ArrayList<String> getCategoriesSelected() {
//		return this.categoriesSelected;
//	}
//
//	/**
//	 * @param categoriesSelected the categoriesSelected to set
//	 */
//	public void setCategoriesSelected(ArrayList<String> categoriesSelected) {
//		this.categoriesSelected = categoriesSelected;
//	}
//	
//	public void pane2Query(){
//		System.out.println(categoriesSelected);
//	}
//
//}
