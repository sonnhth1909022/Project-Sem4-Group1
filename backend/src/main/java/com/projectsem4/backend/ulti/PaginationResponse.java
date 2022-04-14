package com.projectsem4.backend.ulti;

public class PaginationResponse<T> {
    public int totalItems;
    public T datas;
    public int totalPages;
    public int currentPage;
}
