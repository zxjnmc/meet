package com.szx.meet.page;

import java.util.List;

/**
 * @Author szx
 * @Date 2021/7/3 23:39
 * @Description
 */
public class PageInfo<T> {
    private List<T> records;// 存放需要显示的实体类数据
    private long currentPage = 1L;// 当前页码数（默认给1），需要传参
    private long pageSize = 10L; // 每页显示的行数，需要传参
    // this.totalPage = rows % pageSize == 0 ? rows / pageSize : (rows / pageSize + 1);
    private long totalPage;// 总页数，是根据总行数和每页显示的行数计算出来的结果
    private long totalRows;// 总行数，总行数是查询出来的数据表总记录数

    // 对私有属性的封装
    // 不需要对外提供totalPage总页数的set设值方法，因为totalPage是根据总行数和每页显示的行数求出来的
    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        if (pageSize == 0) {
            pageSize = 10L;
        }
        this.pageSize = pageSize;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public long getTotalRows() {
        return totalRows;
    }

    /**
     * 设置总行数据并求出总页数
     *
     * @param totalRows 此参数是总行数
     */
    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
        //页数根据传入的总行数以及每页显示的行数，求出总页数
        this.totalPage = totalRows % pageSize == 0 ? totalRows / pageSize : (totalRows / pageSize + 1);
    }

    /**
     * 设置页码
     *
     * @param currentPage 当前页数
     */
    public void setCurrentPage(long currentPage) {
        //如果传入的页码为空或者小于0  就默认给1
        if (currentPage < 0) {
            this.currentPage = 1L;
            //如果当前页码数大于总页码数，就让当前页码数等于最大页码数
        } else if (currentPage > this.totalPage && this.totalPage > 0) {
            this.currentPage = this.totalPage;
            //都符合条件就让当前页码数等于传入的页码数
        } else {
            this.currentPage = currentPage;
        }
    }


}
