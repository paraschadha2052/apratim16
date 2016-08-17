package com.dityish.apratim2k16;

/**
 * Created by HP on 10/25/2015.
 */
public class SponsorsModel implements Comparable<SponsorsModel> {
    private String id;
    private String heading;
    private int pr;
    private String url;

    SponsorsModel() {
    }

    SponsorsModel(String id, String heading, int pr, String url) {
        this.id = id;
        this.heading = heading;
        this.pr = pr;
        this.url = url;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return this.heading;
    }

    public void setPR(int pr) {
        this.pr = pr;
    }

    public int getPR() {
        return this.pr;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getURL() {
        return this.url;
    }

    @Override
    public int compareTo(SponsorsModel d) {
        return this.getPR() - d.getPR();
    }
}
