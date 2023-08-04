package com.howthere.app.object;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Search {
    private String title;
    private String content;
}
