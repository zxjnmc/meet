package com.szx.meet.controller;


import com.szx.meet.ao.AddTagRequest;
import com.szx.meet.ao.UpdateTagRequest;
import com.szx.meet.entity.PersonTag;
import com.szx.meet.response.ApiResult;
import com.szx.meet.service.PersonTagService;
import com.szx.meet.vo.PersonTagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author szx
 * @since 2021-07-11
 */
@RestController
@RequestMapping("/person-tag")
public class PersonTagController {

    @Autowired
    private PersonTagService personTagService;

    @PostMapping("/add")
    public ApiResult<Void> addTag(@RequestBody AddTagRequest addTagRequest) {
        personTagService.add(addTagRequest);
        return ApiResult.success();
    }

    @GetMapping("/get/{id}")
    public ApiResult<PersonTag> getById(@PathVariable("id") Integer id) {
        return ApiResult.success(personTagService.getById(id));
    }

    @GetMapping("/list")
    public ApiResult<List<PersonTagResponse>> list() {
        return ApiResult.success(personTagService.listAll());
    }

    @PostMapping("/update")
    public ApiResult<Void> update(@RequestBody UpdateTagRequest updateTagRequest) {
        personTagService.update(updateTagRequest);
        return ApiResult.success();
    }
}

