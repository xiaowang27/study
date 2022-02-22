package demo03.dao;


import demo03.entity.BookEntity;

import java.util.List;

public interface BookDao {
    // 添加方法
    void addBook(BookEntity bookEntity);

    // 修改方法
    void update(BookEntity book);

    // 删除方法
    void delete(int i);

    // 查询返回单个值
    int findSum();

    // 查询返回对象类型
    BookEntity findBookInfo(int bookId);

    // 查询返回集合
    List<BookEntity> findBookList();

    // 批量添加
    void batchAdd(List<Object[]> batchArgs);

    // 批量修改
    void batchUpdate(List<Object[]> batchArgs);

    // 批量删除
    void batchDelete(List<Object[]> list);
}
