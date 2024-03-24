package com.woohahaapps.career.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageInformation {

    private Integer count_per_page;
    private Integer current_page;
    private Long total_count;
    private Integer max_page;
}
