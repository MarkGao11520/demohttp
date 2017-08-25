package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/8/24.
 */
@Data
@NoArgsConstructor
public class Project {
    private String name;
    private String type;
    private String path;
    private Attributes attributes;
    private Source source;
    private String description;
    private List<Link> links;
    private List<Problem> problems;
    private List<String> mixins;
}
