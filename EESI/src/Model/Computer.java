package Model;

public class Computer {

	public String pcID;
	public String CType;
	public String processor;
	public String RAM;
	public String VRAM;
	public String display;
	public String HDD;
	public String ROM;
	
	public String gPCID()
	{
		return pcID;
	}
	public String gCType()
	{
		return CType;
	}
	public String gProcessor()
	{
		return processor;
	}
	public String gRAM()
	{
		return RAM;
	}
	public String gVRAM()
	{
		return VRAM;
	}
	public String gDisplay()
	{
		return display;
	}
	public String gHDD()
	{
		return HDD;
	}
	public String gROM()
	{
		return ROM;
	}
	public void sPCID(String pcID)
	{
		this.pcID=pcID;
	}
	public void sCType(String CType)
	{
		this.CType=CType;
	}
	public void sProcessor(String processor)
	{
		this.processor=processor;
	}
	public void sRAM(String RAM)
	{
		this.RAM=RAM;
	}
	public void sVRAM(String VRAM)
	{
		this.VRAM=VRAM;
	}
	public void sDisplay(String display)
	{
		this.display=display;
	}
	public void sHDD(String HDD)
	{
		this.HDD=HDD;
	}
	public void sROM(String ROM)
	{
		this.ROM=ROM;
	}
}
