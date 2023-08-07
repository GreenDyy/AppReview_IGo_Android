package Model;

public class model_Save {
    String imgUrl;
    String text;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public model_Save(String imgUrl, String text) {
        this.imgUrl = imgUrl;
        this.text = text;
    }
}
