package net.togogo.bean;

public class Application {
    private Integer id;

    private Integer user_Id;

    private Integer detail_Id;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return user_Id;
    }

    public void setUserId(Integer userId) {
        this.user_Id = userId;
    }

    public Integer getDetailId() {
        return detail_Id;
    }

    public void setDetailId(Integer detailId) {
        this.detail_Id = detailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}