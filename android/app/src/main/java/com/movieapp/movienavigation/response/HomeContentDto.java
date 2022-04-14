package com.movieapp.movienavigation.response;

import java.util.List;

public class HomeContentDto {
    private List<MovieDto> listTrending;
    private List<MovieDto> listHot;
    private List<MovieDto> listAction;
    private List<MovieDto> listRomance;
    private List<MovieDto> listTelevision;



    private List<MovieDto> listPremium;
    private List<MovieDto> data;

    public HomeContentDto(List<MovieDto> listPremium) {
        this.listPremium = listPremium;
    }

    public List<MovieDto> getData() {
        return data;
    }

    public void setData(List<MovieDto> data) {
        this.data = data;
    }

    public List<MovieDto> getListTrending() {
        return listTrending;
    }

    public void setListTrending(List<MovieDto> listTrending) {
        this.listTrending = listTrending;
    }

    public List<MovieDto> getListHot() {
        return listHot;
    }

    public void setListHot(List<MovieDto> listHot) {
        this.listHot = listHot;
    }

    public List<MovieDto> getListAction() {
        return listAction;
    }

    public void setListAction(List<MovieDto> listAction) {
        this.listAction = listAction;
    }

    public List<MovieDto> getListRomance() {
        return listRomance;
    }

    public void setListRomance(List<MovieDto> listRomance) {
        this.listRomance = listRomance;
    }

    public List<MovieDto> getListTelevision() {
        return listTelevision;
    }

    public void setListTelevision(List<MovieDto> listTelevision) {
        this.listTelevision = listTelevision;

    }
    public List<MovieDto> getListPremium() {
        return listPremium;
    }

    public void setListPremium(List<MovieDto> listPremium) {
        this.listPremium = listPremium;
    }
}
