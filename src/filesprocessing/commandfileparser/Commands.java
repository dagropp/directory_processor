package filesprocessing.commandfileparser;

/**
 * This class creates Commands object with FILTER and ORDER String commands.
 */
public class Commands {
    /* Class members - variables */
    private String filter; // Filter commands.
    private String order; // Order commands.

    /* Public instance methods */

    /**
     * @return Filter commands.
     */
    public String getFilter() {
        return this.filter;
    }

    /**
     * @return Order commands.
     */
    public String getOrder() {
        return this.order;
    }

    /* Package-private instance methods */

    /**
     * Sets filter commands variable.
     *
     * @param filter Specified filter commands to set.
     */
    void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * Sets order commands variable.
     *
     * @param order Specified order commands to set.
     */
    void setOrder(String order) {
        this.order = order;
    }

    /**
     * @return True if both filter and order commands are not null.
     */
    boolean validList() {
        return this.filter != null && this.order != null;
    }

    @Override
    public String toString() {
        return "*COMMAND FILE CONTENT*\nFILTER: " + this.getFilter() + "\nORDER: " + this.getOrder();
    }
}
