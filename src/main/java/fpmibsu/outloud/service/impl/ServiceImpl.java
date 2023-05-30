package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.dao.Transaction;
import fpmibsu.outloud.service.Service;

abstract public class ServiceImpl implements Service {
    protected Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
