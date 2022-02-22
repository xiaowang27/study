package demo03.service;

import demo03.dao.BookDao;
import demo03.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // 注入dao
    @Autowired
    private BookDao bookDao;

    // 添加方法
    public void add(BookEntity book) {
        bookDao.addBook(book);
    }

    // 修改方法
    public void update(BookEntity book){
        bookDao.update(book);
    }

    // 删除方法
    public void delete(int bookId){
        bookDao.delete(bookId);
    }

    // 查询返回单个值
    public int findSum() {
        return bookDao.findSum();
    }

    // 查询返回对象
    public BookEntity findBookInfo(int bookId){
        return bookDao.findBookInfo(bookId);
    }

    // 查询返回集合
    public List<BookEntity> findBookList() {
        return bookDao.findBookList();
    }

    // 批量添加
    public void batchAdd(List<Object[]> batchArgs){
        bookDao.batchAdd(batchArgs);
    }

    public void batchUpdate(List<Object[]> list) {
        bookDao.batchUpdate(list);
    }

    public void batchDelete(List<Object[]> list) {
        bookDao.batchDelete(list);
    }
}
