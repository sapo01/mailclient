package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class MainView {
	 
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		
		final MyListClass model = new MyListClass();
		JList<String> list = new JList<>(model);
		
		
		list.setCellRenderer(new DefaultListCellRenderer() {
			    Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
			        boolean isSelected, boolean cellHasFocus) {
			          			         
			        return this;
			    }
		});
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(list,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		JButton removeButton = new JButton("Delete");
		JButton settingsButton = new JButton("Settings");
		
		buttonPanel.add(removeButton);
		buttonPanel.add(settingsButton);
		
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		removeButton.addActionListener(event ->model.remove());
		settingsButton.addActionListener(event ->model.remove());
		
		JFrame frame = new JFrame("ListExample");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        frame.add(mainPanel, BorderLayout.CENTER);
        
        frame.setSize(250, 250);
        frame.setVisible(true);
	}
	
	
	
	
	@SuppressWarnings("serial")
	static class MyListClass extends AbstractListModel<String>{
		
		int size = 1;

		@Override
		public String getElementAt(int index) {
			return "#" + index;
		}

		@Override
		public int getSize() {
		return size;
		}
		
		public void add(){
			size++;
			fireIntervalAdded(this,size-1,size-1);
		}
		public void remove(){
			if(size>0){
				size--;
				fireIntervalRemoved(this,size,size);
			}
		}

	}
	     


}