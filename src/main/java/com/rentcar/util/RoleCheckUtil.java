package com.rentcar.util;

import com.rentcar.bean.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleCheckUtil {
	public static List getRoleCheck(List<Role> list) {
		ArrayList<Map> arrayList = new ArrayList<Map>();
		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap();
			map.put("id", list.get(i).getId());
			map.put("name", list.get(i).getRoleName());
			map.put("disabled", list.get(i).getActivate().equals("on") ? false : true);
			arrayList.add(map);
		}
		return arrayList;
	}
}
