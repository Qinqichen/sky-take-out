package com.sky.service;

import com.sky.dto.DishDTO;

public interface DishService {

    /**
     * 添加菜品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);
}
