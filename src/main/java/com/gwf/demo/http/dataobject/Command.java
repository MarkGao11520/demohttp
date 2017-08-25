package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gaowenfeng on 2017/8/24.
 */
@Data
@NoArgsConstructor
public class Command {
    private String name;
    private String type;
    private Attributes attributes;
    private String commandLine;

}
