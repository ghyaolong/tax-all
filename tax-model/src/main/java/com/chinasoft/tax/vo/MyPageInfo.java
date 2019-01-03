package com.chinasoft.tax.vo;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

//自定义分页，
public class MyPageInfo<T> extends PageInfo<T> {

    private int totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
    private int number;
    private int numberOfElements;

    public MyPageInfo() {
    }

    public MyPageInfo(List<T> list) {
        super(list);
        if (list != null && list.size() > 0) {

        }
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;

        if (totalElements > 0) {
            if (totalElements % getPageSize() == 0) {
                totalPages = totalElements / getPageSize();
            } else {
                totalPages = totalElements / getPageSize() + 1;
            }


            if (getPageNum() == 1) {
                first = true;
            } else {
                first = false;
            }

            if (getPageNum() == totalPages) {
                last = true;
            } else if (getPageNum() < totalElements) {
                last = false;
            }
        }else{
            first = true;
            last = false;
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumber() {
        return getPageNum();
    }

    public void setNumber(int number) {
        this.number = number;
        setPageNum(number);
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
