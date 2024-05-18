package com.sky.controller.admin;


import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {



    public static final String KEY = "SHOP_STATUS";


    @Autowired
    private RedisTemplate redisTemplate;


    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("营业状态：{}",status==1?"营业":"打烊");

        redisTemplate.opsForValue().set(KEY,status);

        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("查询店铺营业状态")
    public Result<Integer> getStatus() {
        log.info("查询营业状态");

        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);

        return Result.success(shopStatus);
    }

}
