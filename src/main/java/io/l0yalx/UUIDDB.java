package io.l0yalx;

import java.util.LinkedList;
import java.util.List;

public class UUIDDB {
	private List<Long> uuids = new LinkedList<Long>();
	private static UUIDDB uuiddb = null;
	
	private UUIDDB(){
		uuids.add(1l);
	}
	
	public static UUIDDB getInstance(){
		if (uuiddb != null){
			return uuiddb;
		}
		
		uuiddb = new UUIDDB();
		return uuiddb;
	}
	
	public boolean isKnownUUID(long uuid){
		if (uuids.contains(uuid)){
			return true;
		}
		return false;
	}
}
