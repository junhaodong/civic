package hu.ait.civic.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CivicResult {

    @SerializedName("normalizedInput")
    @Expose
    public Address address;
    @SerializedName("offices")
    @Expose
    public Office[] offices;
    @SerializedName("officials")
    @Expose
    public Representative[] representatives;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Office[] getOffices() {
        return offices;
    }

    public void setOffices(Office[] offices) {
        this.offices = offices;
    }

    public Representative[] getRepresentatives() {
        return representatives;
    }

    public void setRepresentatives(Representative[] representatives) {
        this.representatives = representatives;
    }
}
