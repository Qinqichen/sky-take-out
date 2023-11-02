package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * 添加菜品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除菜品
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    DishVO getById(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 根据类别查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> getByCategoryId(Long categoryId);
}
