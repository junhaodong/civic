package hu.ait.civic.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.StringUtils;

public class Address {

    @SerializedName("line1")
    @Expose
    public String line1;
    @SerializedName("line2")
    @Expose
    public String line2;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("zip")
    @Expose
    public String zip;

    public String getAddressString() {
        String line1Formatted = "";
        String line2Formatted = "";
        String cityFormatted = "";
        if (StringUtils.isNotBlank(line1)){
            line1Formatted = line1 + ", ";
        }
        if (StringUtils.isNotBlank(line2)) {
            line2Formatted = line2 + "\n";
        }
        if (StringUtils.isNotBlank(city)) {
            cityFormatted = city + ", ";
        }

        return String.format("%s%s%s%s %s", line1Formatted, line2Formatted, cityFormatted, state, zip);
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
