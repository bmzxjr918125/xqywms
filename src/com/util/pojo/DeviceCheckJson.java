package com.util.pojo;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONArray;

/**
 * <p>ClassName: DeviceCheckJson</p>
 * <p>@Description: 巡检信息json对应pojo</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-11-30上午9:59:50</p>
 */
public class DeviceCheckJson implements Serializable {
    private static final long serialVersionUID = -6154961457805198841L;
	
    private int id;
    
    private String name;
    
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String jaStr = "[{\"id\":1,\"name\":\"是否运行正常\",\"desc\":\"\"},"+
                " {\"id\":2,\"name\":\"是否制冷正常\",\"desc\":\"\"},"+
                        "{\"id\":3,\"name\":\"是否运行正常\",\"desc\":\"\"}]";
            JSONArray ja =   JSONArray.fromObject(jaStr);  
            
        @SuppressWarnings("deprecation")
        List<DeviceCheckJson> list =   JSONArray.toList(ja, DeviceCheckJson.class);
           for(int i= 0;i<list.size();i++){
               System.out.println("id:"+list.get(i).getId()+" name:"+list.get(i).getName()+" desc:"+list.get(i).getDesc());
           }
    }
    
}
