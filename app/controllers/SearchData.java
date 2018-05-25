package controllers;

import play.data.validation.Constraints;

/**
 * A form processing DTO that maps to the search form.
 *
 * Using a class specifically for form binding reduces the chances
 * of a parameter tampering attack and makes code clearer, because
 * you can define constraints against the class.
 */
public class SearchData {

    @Constraints.Required
    private String query;

    public SearchData() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}