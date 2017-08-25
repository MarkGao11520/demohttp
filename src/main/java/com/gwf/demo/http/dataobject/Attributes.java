package com.gwf.demo.http.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gaowenfeng on 2017/8/24.
 */
@Data
@NoArgsConstructor
public class Attributes {
    private String goal;
    private String previewUrl;
    private String memoryLimitBytes;
}
