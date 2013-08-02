package fuj1n.modjam2_src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

	public static List copyList(List par1List){
		List returnList = new ArrayList();
		
		Iterator i1 = par1List.iterator();
		while(i1.hasNext()){
			returnList.add(i1.next());
		}
		
		return returnList;
	}
	
}
