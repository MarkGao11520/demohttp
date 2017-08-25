package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gaowenfeng on 2017/8/25.
 */
@Data
@NoArgsConstructor
public class Recipe {
    private String location;
    private String type;
}
