package com.sky.service;

import com.sky.dto.SetmealDTO;

public interface SetmealService {

    /**
     * 添加新套餐
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);
}
