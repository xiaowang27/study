package demo03.dao;

import demo03.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    // 注入jdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addBook(BookEntity bookEntity) {
        // 1.定义sql
        String sql = "insert into t_book values(?,?,?)";
        // 2. 获取参数
        Integer bookId = bookEntity.getBookId();
        String bookName = bookEntity.getBookName();
        String bookStatus = bookEntity.getBookStatus();
        // 3.调用jdbcTemplate方法，添加数据
        int add = jdbcTemplate.update(sql, bookId, bookName, bookStatus);
        if (add == 1) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Override
    public void update(BookEntity book) {
        // 1.定义sql
        String sql = "update t_book set book_name=?,book_status = ? where book_id = ?";
        // 2.获取参数
        Integer bookId = book.getBookId();
        String bookName = book.getBookName();
        String bookStatus = book.getBookStatus();
        // 3.调用jdbcTemplate方法
        int update = jdbcTemplate.update(sql, bookName, bookStatus, bookId);
        if (update == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Override
    public void delete(int i) {
        // 1.定义sql
        String sql = "delete from t_book where book_id=?";
        // 2.调用jdbcTemplate方法
        int delete = jdbcTemplate.update(sql, i);
        if (delete == 1) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    @Override
    public int findSum() {
        String sql = "select count(*) from t_book";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public BookEntity findBookInfo(int bookId) {
        String sql = "select * from t_book where book_id=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<BookEntity> (BookEntity.class),bookId);
    }

    @Override
    public List<BookEntity> findBookList() {
        String sql = "select * from t_book";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<BookEntity>(BookEntity.class));
    }

    @Override
    public void batchAdd(List<Object[]> batchArgs) {
        String sql = "insert into t_book values(?,?,?)";
        // 底层就是将List中的集合便利传递给sql语句执行一遍
        int[] books = jdbcTemplate.batchUpdate(sql,batchArgs);
        System.out.println(Arrays.toString(books));
    }

    @Override
    public void batchUpdate(List<Object[]> batchArgs) {
        String sql = "update t_book set book_name=?,book_status=? where book_id=?";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(ints));
    }

    @Override
    public void batchDelete(List<Object[]> list) {
        String sql = "delete from t_book where book_id=? ";
        int[] ints = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(ints));
    }
}
