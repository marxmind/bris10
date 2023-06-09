package com.italia.marxmind.bris.bean;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
/**
 * 
 * @author Mark Italia
 * @since 12-09-2018
 * @version 1.0
 *
 *  source https://stackoverflow.com/questions/7653698/pautocomplete-itemlabel-throws-the-class-java-lang-string-does-not-have-the/7653775#7653775
 */
@FacesConverter(value="brisConverter")
public class BrisConverter implements Converter{
	
	private static Map<Object, String> entities = new WeakHashMap<Object, String>();
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String uuid) {
		for (Entry<Object, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object entity) {
		synchronized (entities) {
            if (!entities.containsKey(entity)) {
                String uuid = UUID.randomUUID().toString();
                entities.put(entity, uuid);
                return uuid;
            } else {
                return entities.get(entity);
            }
        }
	}

}
