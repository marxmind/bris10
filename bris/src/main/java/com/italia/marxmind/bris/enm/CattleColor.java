package com.italia.marxmind.bris.enm;

/**
 * 
 * @author mark italia
 * @since 10/23/2017
 * @version 1.0
 *
 */
public enum CattleColor {
	
	NA(0, "N/A"),
	BLACK(1,"BLACK"),
	WHITE(2, "WHITE"),
	BROWN(3, "BROWN"),
	SPOTTED_BW(4, "SPOTTED BLACK AND WHITE"),
	SPOTTED_BRW(5, "SPOTTED BROWN AND WHITE"),
	MIX_COLORS(6, "MULTIPLE COLORS"),
	DARK_RED(7, "DARK RED"),
	SPOTTED_WHITE(8, "SPOTTED WHITE"),
	SPOTTED_RED(9, "SPOTTED RED"),
	GRAY(10, "GRAY"),
	SPOTTED_BROWN(11, "SPOTTED BROWN");
	
	private int id;
	private String name;
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	private CattleColor(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public static String typeName(int id){
		for(CattleColor type : CattleColor.values()){
			if(id==type.getId()){
				return type.getName();
			}
		}
		return CattleColor.NA.getName();
	}
	public static int typeId(String name){
		for(CattleColor type : CattleColor.values()){
			if(name.equalsIgnoreCase(type.getName())){
				return type.getId();
			}
		}
		return CattleColor.NA.getId();
	}
	
}
