package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/8/24.
 */
@Data
@NoArgsConstructor
public class Link {
    private String method;
    private List<Parameters> parameters;
    private String consumes;
    private String produces;
    private RequestBody requestBody;
    private String rel;
    private String href;
}
