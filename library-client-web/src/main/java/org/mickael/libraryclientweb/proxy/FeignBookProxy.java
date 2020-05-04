package org.mickael.libraryclientweb.proxy;

import org.mickael.libraryclientweb.bean.book.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "library-ms-book", url = "localhost:8100")//il faut modifier les uri avec le nom du ms à appeler
//@RibbonClient(name = "micro service à appeler")
public interface FeignBookProxy {


    /* ================================ */
    /* === No Authentication needed === */
    /* ================================ */


    /* =============================== */
    /* ==== Authentication needed ==== */
    /* =============================== */



    /* ==== AUTHORS ==== */
    @GetMapping("/api/authors")
    List<AuthorBean> getAuthors();

    @GetMapping("/api/authors/{id}")
    AuthorBean retrieveAuthor(@PathVariable("id") Integer id);

    @PostMapping("/api/authors/new-author")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<AuthorBean> createNewAuthor(@Valid @RequestBody AuthorBean newAuthorBean);

    @PutMapping("/api/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    AuthorBean updateAuthor(@PathVariable("id") Integer id, @RequestBody AuthorBean authorBean);

    @DeleteMapping("/api/authors/{id}")
    void deleteAuthor(@PathVariable("id") Integer id);




    /* ==== BOOKS ==== */
    @GetMapping("/api/books")
    List<BookBean> getBooks(/*@RequestHeader("Authorization") String accessToken*/);

    @GetMapping("/api/books/search")
    List<BookBean> getBooksBySearchValue(@SpringQueryMap SearchBean searchBean/*,
                                         @RequestHeader("Authorization") String accessToken*/);

    @GetMapping("/api/books/{id}")
    BookBean retrieveBook(@PathVariable("id") Integer id/*,@RequestHeader("Authorization") String accessToken*/);

    @PostMapping("/api/books/new-book")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<BookBean> createNewBook(@Valid @RequestBody BookBean newBookBean);

    @PutMapping("/api/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    BookBean updateBook(@PathVariable("id") Integer id, @RequestBody BookBean bookBean);

    @DeleteMapping("/api/books/{id}")
    void deleteBook(@PathVariable("id") Integer id);

    /* ==== CATEGORY ==== */
    @GetMapping("/api/categories")
    List<CategoryBean> getCategories();

    @GetMapping("/api/categories/{id}")
    CategoryBean retrieveCategory(@PathVariable("id") Integer id);

    @PostMapping("/api/categories/new-category")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CategoryBean> createNewCategory(@Valid @RequestBody CategoryBean newCategoryBean);

    @PutMapping("/api/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    CategoryBean updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryBean categoryBean);

    @DeleteMapping("/api/categories/{id}")
    void deleteCategory(@PathVariable("id") Integer id);


    /* ==== COPY ==== */
    @GetMapping("/api/copies")
    List<CopyBean> getCopies();

    @GetMapping("/api/copies/search")
    List<CopyBean> getCopiesBySearchValue(@RequestParam(value = "criteria", required = false) String criteria,
                                          @RequestParam(value = "searchValue", required = false) String searchValue,
                                          @RequestParam(value = "available", required = false) boolean available/*,
                                          @RequestHeader("Authorization") String accessToken*/);
    @GetMapping("/api/copies/available/{id}")
    List<CopyBean> getCopiesAvailableForOneBook(@PathVariable("id") Integer id/*, @RequestHeader("Authorization") String accessToken*/);

    @GetMapping("/api/copies/book/{id}")
    List<CopyBean> getCopiesForOneBook(@PathVariable("id") Integer id/*, @RequestHeader("Authorization") String accessToken*/);


    @GetMapping("/api/copies/{id}")
    CopyBean retrieveCopy(@PathVariable("id") Integer id);

    @PostMapping("/api/copies/new-copy")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CopyBean> createNewCopy(@Valid @RequestBody CopyBean newCopyBean);

    @PutMapping("/api/copies/{id}")
    @ResponseStatus(HttpStatus.OK)
    CopyBean updateCopy(@PathVariable("id") Integer id, @RequestBody CopyBean copyBean);

    @DeleteMapping("/api/copies/{id}")
    void deleteCopy(@PathVariable("id") Integer id);



    /* ==== COVER ==== */
    @GetMapping("/api/covers")
    List<CoverBean> getCovers();

    @GetMapping("/api/covers/{id}")
    CoverBean retrieveCover(@PathVariable("id") Integer id);

    @PostMapping("/api/covers/new-cover")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CoverBean> createNewCover(@Valid @RequestBody CoverBean newCoverBean);

    @PutMapping("/api/covers/{id}")
    @ResponseStatus(HttpStatus.OK)
    CoverBean updateCover(@PathVariable("id") Integer id, @RequestBody CoverBean coverBean);

    @DeleteMapping("/api/covers/{id}")
    void deleteCover(@PathVariable("id") Integer id);

    /* ==== EDITION ==== */
    @GetMapping("/api/editions")
    List<EditionBean> getEditions();

    @GetMapping("/api/editions/{id}")
    EditionBean retrieveEdition(@PathVariable("id") Integer id);

    @PostMapping("/api/editions/new-edition")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EditionBean> createNewEdition(@Valid @RequestBody EditionBean newEditionBean);

    @PutMapping("/api/editions/{id}")
    @ResponseStatus(HttpStatus.OK)
    EditionBean updateEdition(@PathVariable("id") Integer id, @RequestBody EditionBean editionBean);

    @DeleteMapping("/api/editions/{id}")
    void deleteEdition(@PathVariable("id") Integer id);



}
