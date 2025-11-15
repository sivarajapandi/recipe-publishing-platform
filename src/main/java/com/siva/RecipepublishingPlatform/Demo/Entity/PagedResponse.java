package com.siva.RecipepublishingPlatform.Demo.Entity;

import java.util.List;

//meta-data about paged response
//dto for PagedResponse
//provides pagination details along with the actual data content for a paged API response
public class PagedResponse <T>{
    private int pagesize;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private boolean last;
    private boolean first;
    //what is the use of the List<> content here
    //List<T> content is used to hold the actual data of the current page
    private List<T> content;

    private boolean hasNext;
    private boolean hasPrevious;

    //Default constructor
    public PagedResponse(){

    }

    public PagedResponse(int pagesize, int pageNumber, int totalPages, long totalElements, boolean last, boolean first, List<T> content, boolean hasNext, boolean hasPrevious) {
        this.pagesize = pagesize;
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.last = last;
        this.first = first;
        this.content = content;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
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

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public void setPageSize(int size) {
    }
}
