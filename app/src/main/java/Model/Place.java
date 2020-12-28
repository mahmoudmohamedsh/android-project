package Model;

public class Place {
    private String title;
    private String dec;
    private String location;
    private String price;
    private String image;
    //private List<User>;
    private String type;
    private String capacity ;
    private String timestamp;
    private String userid;
    public Place(){}
    public Place(String title, String dec, String location,
                 String price, String image, String type,
                 String capacity , String timeStamp,String userId) {
        this.title = title;
        this.dec = dec;
        this.location = location;
        this.price = price;
        this.image = image;
        this.type = type;
        this.capacity = capacity;
        this.timestamp = timeStamp;
        this.userid = userId;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public String getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timestamp = timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

}
