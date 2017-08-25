package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/8/25.
 */
@Data
@NoArgsConstructor
public class DevMachine {
    private Attributes attributes;
    private List<String> agents;
    private Servers servers;
}
