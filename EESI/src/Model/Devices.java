package Model;

public class Devices {

	public String itemID;
	public String itemType;
	public String capacity;
	public String description;
	
	public String gItemID()
	{
		return itemID;
	}
	public String gItemType()
	{
		return itemType;
	}
	public String gCapacity()
	{
		return capacity;
	}
	public String gDescription()
	{
		return description;
	}
	public void sItemID(String itemID)
	{
		this.itemID=itemID;
	}
	public void sItemType(String itemType)
	{
		this.itemType=itemType;
	}
	public void sCapacity(String capacity)
	{
		this.capacity=capacity;
	}
	public void sDescription(String description)
	{
		this.description=description;
	}
}
