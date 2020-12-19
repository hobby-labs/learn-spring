package com.github.hobbylabs.learnspring.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CustomerMapper {
    public List<String> selectNameAll();
}
