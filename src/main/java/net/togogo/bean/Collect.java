package net.togogo.bean;

public class Collect {
    private Integer id;

    private Integer user_Id;

    private Integer detail_Id;

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

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", user_Id=" + user_Id +
                ", detail_Id=" + detail_Id +
                '}';
    }
}