package beroepsproduct.dion.test.com.beroepsproduct.entities;

/**
 * Created by Dion on 3/10/2016.
 */
public class Verzekering {

    private long verz_id;
    private String verzType;
    private Number verzBegin;
    private Number verzEnd;
    private String verzUserName;

    public Verzekering(long verz_id, String verzType, Number verzBegin, Number verzEnd, String verzUserName) {
        this.verz_id = verz_id;
        this.verzType = verzType;
        this.verzBegin = verzBegin;
        this.verzEnd = verzEnd;
        this.verzUserName = verzUserName;

    }

    public long getVerz_id() {
        return verz_id;
    }

    public void setVerz_id(long verz_id) {
        this.verz_id = verz_id;
    }

    public String getVerzType() {
        return verzType;
    }

    public void setVerzType(String verzType) {
        this.verzType = verzType;
    }

    public Number getVerzBegin() {
        return verzBegin;
    }

    public void setVerzBegin(Number verzBegin) {
        this.verzBegin = verzBegin;
    }

    public Number getVerzEnd() {
        return verzEnd;
    }

    public void setVerzEnd(Number verzEnd) {
        this.verzEnd = verzEnd;
    }

    public String getVerzUserName() {
        return verzUserName;
    }

    public void setVerzUserName(String verzUserName) {
        this.verzUserName = verzUserName;
    }

}
