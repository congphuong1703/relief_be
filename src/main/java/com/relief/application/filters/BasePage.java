package com.relief.application.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class BasePage<T> {
    private List<T> data;
    private Pageable pageable;

    public BasePage(Page<T> page) {
        data = page.getContent();
        pageable = new Pageable(page);
    }

    public BasePage() {
    }

    @Data
    @AllArgsConstructor
    public static class Pageable {
        private int page = 0;
        private int size = 10;
        private long total = 0;
        private int totalPage = 0;

        public <T> Pageable(Page<T> page) {
            size = page.getSize();
            this.page = page.getNumber();
            this.total = page.getTotalElements();
            this.totalPage = page.getTotalPages();
        }
    }
}
