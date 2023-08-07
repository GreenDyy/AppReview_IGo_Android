package Model;

public class model_hienthithongtinnguoidung {

    String Fullname;
    String DateOfBirth;
    String NumberPhone;
    String Gmail;

    public model_hienthithongtinnguoidung(String fullname, String dateOfBirth, String numberPhone, String gmail) {
        Fullname = fullname;
        DateOfBirth = dateOfBirth;
        NumberPhone = numberPhone;
        Gmail = gmail;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getNumberPhone() {
        return NumberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        NumberPhone = numberPhone;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }
}