
package zombiestarter;

public class Item {

    private final String name;

    private final String html;

    //constructor
    Item(String name, String html) {
        this.name = name;
        this.html = html;
    }

    //get name method
    public String getName() {
        return name;
    }

    //get html for image method
    public String getHtml() {
        return html;
    }
}
