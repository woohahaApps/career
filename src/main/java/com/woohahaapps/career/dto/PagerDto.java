package com.woohahaapps.career.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PagerDto<T> {
    private PageInformation page;
    private T dto;
}
