package model;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class MessageList extends AbstractListModel<String>{
	
	int size = 1;

	@Override
	public String getElementAt(int index) {
		return "Index: " + index;
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