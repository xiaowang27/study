package demo01.dao.impl;

import demo01.dao.BookDao;

public class BookDaoImpl implements BookDao {

    @Override
    public void delete() {
        System.out.println("DAO delete ...");
    }
}
