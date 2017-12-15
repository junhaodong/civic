package hu.ait.civic.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Representative implements Serializable {

    public String title;

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("photoUrl")
    @Expose
    public String photoUrl;
    @SerializedName("party")
    @Expose
    public String party;

    @SerializedName("address")
    @Expose
    public Address[] addresses;
    @SerializedName("phones")
    @Expose
    public String[] phones;
    @SerializedName("urls")
    @Expose
    public String[] urls;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Address[] getAddresses() {
        return addresses;
    }

    public void setAddresses(Address[] addresses) {
        this.addresses = addresses;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }
}
