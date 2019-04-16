package com.example.niilonkirjakauppa.web;

import com.example.niilonkirjakauppa.domain.Book;
import com.example.niilonkirjakauppa.domain.BookRepository;
import com.example.niilonkirjakauppa.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository drepository;

    //KIRJAUTUMINEN

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    //KIRJALISTA

    @RequestMapping("/booklist")
    public String bookList(Model model){
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/books")
    public @ResponseBody
    List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return repository.findById(bookId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)

    public String deleteBook(@PathVariable("id")Long bookid, Model model){
        repository.deleteById(bookid);
        return "redirect:../booklist";
    }
    //KIRJAN LISÄÄMINEN, TALLENTAMINEN & EDITOINTI
    @RequestMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", drepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id")Long bookid, Model model) {
        model.addAttribute("book", repository.findById(bookid));
        model.addAttribute("categories", drepository.findAll());
        return "editbook";
    }




}
