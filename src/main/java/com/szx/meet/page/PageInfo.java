package com.szx.meet.page;

import java.util.List;
import java.util.Objects;

/**
 * @Author szx
 * @Date 2021/7/3 23:39
 * @Description
 */
public class PageInfo<T> {
    /**
     * 当前页码
     */
    private Long currentPage = 1L;

    /**
     * 当前页的记录数
     */
    private Long pageSize = 10L;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 总条数
     */
    private Long totalCount;

    /**
     * 记录集合
     */
    private List<T> records;

    public PageInfo() {
    }

    public PageInfo(Long currentPage, Long pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageInfo(Long currentPage, Long pageSize, Long totalPage, Long totalCount, List<T> records) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.records = records;
    }

    public Long getCurrentPage() {
        return Objects.isNull(currentPage) ? 1L : this.currentPage;
    }

    public Long getPageSize() {
        return Objects.isNull(pageSize) ? 10L : this.pageSize;
    }

    public Long getTotalPage() {
        return this.getTotalCount() % this.getPageSize() == 0 ?
                this.getTotalCount() / this.getPageSize()
                : this.getTotalCount() / this.getPageSize() + 1;
    }

    public Long getTotalCount() {
        return this.totalCount;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public PageInfo<T> setCurrentPage(final Long currentPage) {
        if (Objects.isNull(currentPage) || currentPage < 0) {
            this.currentPage = 1L;
        } else {
            this.currentPage = currentPage;
        }
        return this;
    }

    public PageInfo<T> setPageSize(final Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageInfo<T> setTotalPage(final Long totalPage) {
        this.totalPage = totalPage % pageSize == 0 ? totalPage / pageSize : (totalPage / pageSize + 1);
        return this;
    }

    public PageInfo<T> setTotalCount(final Long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public PageInfo<T> setRecords(final List<T> records) {
        this.records = records;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageInfo)) {
            return false;
        } else {
            PageInfo<?> other = (PageInfo) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getCurrentPage() != other.getCurrentPage()) {
                return false;
            } else if (this.getPageSize() != other.getPageSize()) {
                return false;
            } else if (this.getTotalPage() != other.getTotalPage()) {
                return false;
            } else if (this.getTotalCount() != other.getTotalCount()) {
                return false;
            } else {
                Object this$records = this.getRecords();
                Object other$records = other.getRecords();
                if (this$records == null) {
                    if (other$records != null) {
                        return false;
                    }
                } else if (!this$records.equals(other$records)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageInfo;
    }

    @Override
    public int hashCode() {
        int result = 1;
        long $currentPage = this.getCurrentPage();
        result = result * 59 + (int) ($currentPage >>> 32 ^ $currentPage);
        long $pageSize = this.getPageSize();
        result = result * 59 + (int) ($pageSize >>> 32 ^ $pageSize);
        long $totalPage = this.getTotalPage();
        result = result * 59 + (int) ($totalPage >>> 32 ^ $totalPage);
        long $totalCount = this.getTotalCount();
        result = result * 59 + (int) ($totalCount >>> 32 ^ $totalCount);
        Object $records = this.getRecords();
        result = result * 59 + ($records == null ? 43 : $records.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "PageInfo(currentPage=" + this.getCurrentPage() + ", pageSize=" + this.getPageSize() + ", totalPage=" + this.getTotalPage() + ", totalCount=" + this.getTotalCount() + ", records=" + this.getRecords() + ")";
    }
}
