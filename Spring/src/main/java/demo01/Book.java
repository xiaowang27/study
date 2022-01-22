package demo01;

public class Book {
    private String bookName;

    public Book() {
    }

    // 有参构造注入
    public Book(String bookName) {
        this.bookName = bookName;
    }

    // set方法注入
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

}
