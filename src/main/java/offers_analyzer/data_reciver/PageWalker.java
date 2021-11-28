package offers_analyzer.data_reciver;

import org.apache.logging.log4j.Level;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PageWalker {

    private static final Logger logger = LogManager.getLogger(PageWalker.class);

    public List<JobOffer> walk(String firstPageUrl) {
        logger.info("Start parsing pages with offers lists");
        Document firstPage = getDocument(firstPageUrl);
        List<String> pages = getPages(firstPage);
        logger.info("Start parsing pages with offers");
        return pages.stream()
                .filter(url -> Objects.equals(firstPageUrl, url))
                .map(url -> new JobOffer(url, getDocument(url)))
                .toList();
    }

    private List<String> getPages(Document firstPage) {
        List<List<String>> pagesWithOffers = new ArrayList<>();
        pagesWithOffers.add(this.getOffersLinks(firstPage));

        Optional<Document> nextPage = getNextPage(firstPage);
        while(nextPage.isPresent()) {
            nextPage.map(this::getOffersLinks)
                    .map(pagesWithOffers::add);
            nextPage = getNextPage(nextPage.get());
        }
        return pagesWithOffers.stream()
                .flatMap(Collection::stream)
                .toList();
    }

    private Optional<Document> getNextPage(Document doc) {
        return Optional.ofNullable(doc.getElementsByClass("s-Pagination_Next").first())
                .map(element -> element.attr("href"))
                .filter(href -> !Objects.equals("#", href))
                .map(href -> "https://students.pl" + href)
                .map(this::getDocument);
    }

    private Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException exception) {
            logger.log(Level.ERROR, "Error while connecting to page", exception);
            return null;
        }
    }

    public List<String> getOffersLinks(Document document) {
        return document.getElementsByClass("n-BlockListing_headerTitle").stream()
                .map(element -> element.attr("href"))
                .map(href -> "https://students.pl" +  href)
                .collect(Collectors.toList());
    }
}
