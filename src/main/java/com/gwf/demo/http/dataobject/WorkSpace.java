package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/8/23.
 */
@Data
@NoArgsConstructor
public class WorkSpace {
    private String name;
    private String description;
    private Environments environments;
    private String defaultEnv;
    private List<Command> commands;
    private List<Project> projects;
    private List<Link> links;
}
