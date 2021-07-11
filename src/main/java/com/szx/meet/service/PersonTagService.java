package com.szx.meet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szx.meet.ao.AddTagRequest;
import com.szx.meet.ao.UpdateTagRequest;
import com.szx.meet.entity.PersonTag;
import com.szx.meet.vo.PersonTagResponse;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author szx
 * @since 2021-07-11
 */
public interface PersonTagService extends IService<PersonTag> {

    /**
     * 功能描述:新增tag标签
     *
     * @param addTagRequest addTagRequest
     * @return void
     * @author szx
     * @date 2021/7/11 10:23
     */
    void add(AddTagRequest addTagRequest);

    /**
     * 功能描述:逻辑删除标签
     *
     * @param id id
     * @return void
     * @author szx
     * @date 2021/7/11 10:26
     */
    void delete(Integer id);

    /**
     * 功能描述:修改标签
     *
     * @param updateTagRequest updateTagRequest
     * @return void
     * @author szx
     * @date 2021/7/11 10:27
     */
    void update(UpdateTagRequest updateTagRequest);

    /**
     * 功能描述:根据名称查询tag
     *
     * @param name name
     * @return com.szx.meet.entity.PersonTag
     * @author szx
     * @date 2021/7/11 10:36
     */
    PersonTag getByName(String name);

    List<PersonTagResponse> listAll();
}
