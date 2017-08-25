package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/8/24.
 */
@Data
@NoArgsConstructor
public class Parameters {
    private String name;
    private String type;
    private String defaultValue;
    private String description;
    private List<String> valid;
    private String required;
}
