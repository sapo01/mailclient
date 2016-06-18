package model;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class MessageList extends AbstractListModel<String>{
	
	int size = 2;

	@Override
	public String getElementAt(int index) {
		return "Test" + index;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	public void remove(){
		if(size>0){
			size--;
			fireIntervalRemoved(this,size,size);
		}
	}

}