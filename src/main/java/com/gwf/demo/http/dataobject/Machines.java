package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/8/25.
 */
@Data
@NoArgsConstructor
public class Machines extends HashMap<String,Object>{

  public void setDevMachine(DevMachine devMachine){
      this.put("dev-machine",devMachine);
  }
}
