package com.italia.marxmind.bris.converter;

import com.italia.marxmind.bris.controller.Kinds;
import com.italia.marxmind.bris.enm.CaseKind;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;

/**
 * 
 * @author mark italia
 * @since 03/06/2018
 * @version 1.0
 *
 */


public class KindConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		for(CaseKind k : CaseKind.values()){
			String s = k.getId()+"";
			if(s.equalsIgnoreCase(value)){
				Kinds n = new Kinds();
				n.setId(k.getId()+"");
				n.setName(k.getName());
				return n;
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value instanceof Kinds){
			Kinds kind = (Kinds)value;
			return kind.getId();
		}
		
		return null;
	}

}
