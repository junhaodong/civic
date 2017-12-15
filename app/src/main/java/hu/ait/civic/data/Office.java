package hu.ait.civic.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Office {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("officialIndices")
    @Expose
    public int[] officialIndices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getOfficialIndices() {
        return officialIndices;
    }

    public void setOfficialIndices(int[] officialIndices) {
        this.officialIndices = officialIndices;
    }
}
