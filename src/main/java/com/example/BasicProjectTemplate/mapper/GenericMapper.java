package com.example.BasicProjectTemplate.mapper;

public interface GenericMapper<I, O> {
    O map(I input);
}
