package se.prolore.pageobjects;

public interface SearchPageLoadable extends LoadableComponent {

  ResultsPage searchFor(String term);
}
