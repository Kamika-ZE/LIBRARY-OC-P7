package org.mickael.libraryclientweb.controller;


import org.mickael.libraryclientweb.bean.book.BookBean;
import org.mickael.libraryclientweb.bean.book.CopyBean;
import org.mickael.libraryclientweb.bean.book.SearchBean;
import org.mickael.libraryclientweb.proxy.FeignBookProxy;
import org.mickael.libraryclientweb.security.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final FeignBookProxy feignBookProxy;
    private static final String LIST_BOOK = "books";
    private static final String LIST_COPY = "copies";
    private static final String BOOK = "book";


    @Autowired
    public BookController(FeignBookProxy feignBookProxy) {
        this.feignBookProxy = feignBookProxy;
    }

    @GetMapping("/catalog")
    public String showCatalog(Model model, @CookieValue(value = CookieUtils.HEADER, required = false)String accessToken){
        model.addAttribute(LIST_BOOK, feignBookProxy.getBooks(/*"Bearer " + accessToken*/));
        model.addAttribute("searchAttribut", new SearchBean());
        return "catalog";
    }

    @PostMapping("/catalog/search")
    public String displaySearchResult(@ModelAttribute("searchAttribut") SearchBean searchBean, Model model,
                                      @CookieValue(value = CookieUtils.HEADER, required = false)String accessToken){
        try{
            List<BookBean> books = feignBookProxy.getBooksBySearchValue(searchBean/*,"Bearer " + accessToken*/);
            model.addAttribute(LIST_BOOK, books);
            return "catalog";
        } catch (Exception e){
            List<BookBean> books = new ArrayList<>();
            model.addAttribute(LIST_BOOK, books);
            model.addAttribute("NoResult", "Pas de résultats");
            return "catalog";
        }
    }

    @GetMapping("/catalog/book/{id}")
    public String showBook(@PathVariable Integer id, Model model, @CookieValue(value = CookieUtils.HEADER, required = false)String accessToken){
        accessToken = "Bearer " + accessToken;
        BookBean book = feignBookProxy.retrieveBook(id/*, accessToken*/);
        List<CopyBean> copies;
        try{
            copies = feignBookProxy.getCopiesAvailableForOneBook(id/*, accessToken*/);
        } catch (Exception e){
            copies = new ArrayList<>();
        }
        book.setCopies(copies);
        model.addAttribute("book", book);
        return BOOK;
    }

}
