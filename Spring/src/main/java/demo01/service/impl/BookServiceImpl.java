package demo01.service.impl;

import demo01.dao.BookDao;
import demo01.dao.impl.BookDaoImpl;
import demo01.service.BookService;
import org.junit.Test;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    // 还是采用的set方法注入，所以必须有目标外部bean属性的set方法
    public void setBookDao(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public void add() {
        bookDao.delete();
        System.out.println("service add...");
    }
}
