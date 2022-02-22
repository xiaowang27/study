package demo03.test;

import demo03.entity.BookEntity;
import demo03.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestBook {
    @Test
    public void add() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springXmlDemo03/demo03Spring.xml");
        BookService bean = context.getBean("bookService", BookService.class);

        BookEntity book = new BookEntity();
        book.setBookId(13);
        book.setBookName("三国演义");
        book.setBookStatus("正常");

        bean.add(book);
    }

    @Test
    public void update() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springXmlDemo03/demo03Spring.xml");
        BookService bean = context.getBean("bookService", BookService.class);

        BookEntity book = new BookEntity();
        book.setBookId(11);
        book.setBookName("水浒传");
        book.setBookStatus("不正常");

        bean.update(book);
    }

    @Test
    public void delete() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springXmlDemo03/demo03Spring.xml");
        BookService bean = context.getBean("bookService", BookService.class);

        bean.delete(11);
    }

    @Test
    public void findSum() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springXmlDemo03/demo03Spring.xml");
        BookService bean = context.getBean("bookService", BookService.class);

        int sum = bean.findSum();
        System.out.println("共有："+sum+"条数据");
    }

    @Test
    public void findBookInfo() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springXmlDemo03/demo03Spring.xml");
        BookService bean = context.getBean("bookService", BookService.class);

        BookEntity bookEntity = bean.findBookInfo(11);
        System.out.println(bookEntity);
    }

    @Test
    public void findBookList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springXmlDemo03/demo03Spring.xml");
        BookService bean = context.getBean("bookService", BookService.class);

        List<BookEntity> bookList = bean.findBookList();
        for(BookEntity b : bookList){
            System.out.println(b);
        }
    }

    @Test
    public void batchAdd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springXmlDemo03/demo03Spring.xml");
        BookService bean = context.getBean("bookService", BookService.class);

        String nameArray[] = {"活着","在细雨中呐喊","Thinking in java"};
        List<Object[]> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Object[] o = {null,nameArray[i],"正常"};
            list.add(o);
        }
        bean.batchAdd(list);
    }

    @Test
    public void batchUpdate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springXmlDemo03/demo03Spring.xml");
        BookService bean = context.getBean("bookService", BookService.class);

        String nameArray[] = {"许三观卖血记","睡在我上铺的兄弟","骆驼祥子"};
        List<Object[]> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Object[] o = {nameArray[i],"不正常",14+i};
            list.add(o);
        }
        bean.batchUpdate(list);
    }


    @Test
    public void batchDelete() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springXmlDemo03/demo03Spring.xml");
        BookService bean = context.getBean("bookService", BookService.class);

        List<Object[]> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Object[] o = {14+i};
            list.add(o);
        }
        bean.batchDelete(list);
    }

}
