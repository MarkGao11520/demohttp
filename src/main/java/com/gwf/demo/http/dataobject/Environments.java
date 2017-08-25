package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * Created by gaowenfeng on 2017/8/24.
 */
@Data
@NoArgsConstructor
public class Environments extends HashMap<String,Environment>{

    public void setEnvironment(String key,Environment environment){
        this.put(key,environment);
    }


}
