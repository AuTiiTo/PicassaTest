package globant.picassatest.common;

/**
 * @author s.ruiz
 */

public class MainItem {
    int resourseImageId;
    String name;
    String description;
    String url;

    public MainItem(int pResourseImageId,
            String pName,
            String pDescription,
            String pUrl) {
        setResourseImageId(pResourseImageId);
        setName(pName);
        setDescription(pDescription);
        setUrl(pUrl);
    }

    public int getResourseImageId() {
        return resourseImageId;
    }

    public void setResourseImageId(int resourseImageId) {
        this.resourseImageId = resourseImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
