package cn.zch.ssm.po;

import java.util.List;

public class QueryVo {

	private Items items;
	
	private List<Items> itemsList;

	/**
	 * @return the items
	 */
	public Items getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Items items) {
		this.items = items;
	}

	/**
	 * @return the itemsList
	 */
	public List<Items> getItemsList() {
		return itemsList;
	}

	/**
	 * @param itemsList the itemsList to set
	 */
	public void setItemsList(List<Items> itemsList) {
		this.itemsList = itemsList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryVo [" + (items != null ? "items=" + items + ", " : "")
				+ (itemsList != null ? "itemsList=" + itemsList : "") + "]";
	}
	
	
}
