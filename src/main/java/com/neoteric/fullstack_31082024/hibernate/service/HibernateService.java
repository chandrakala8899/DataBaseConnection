package com.neoteric.fullstack_31082024.hibernate.service;

import com.neoteric.fullstack_31082024.hibernate.model.AccountEntity;
import com.neoteric.fullstack_31082024.hibernate.model.AccountEntityAddress;
import com.neoteric.fullstack_31082024.hibernate.model.HibernateUtils;
import com.neoteric.fullstack_31082024.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HibernateService {
    public Account searchAccount(String accoutnnumber) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query<AccountEntity> query = session.createQuery("From AccountEntity a where a.accountnumber = :accountinput");
        query.setParameter("accountinput", accoutnnumber);
        AccountEntity accountEntity =  query.list().get(0);

        Account account = Account.builder().accountnumber(accountEntity.getAccountnumber())
                .mobilenumber(accountEntity.getMobilenumber())
                .name(accountEntity.getName())
                .pan(accountEntity.getPan())
                .balance(accountEntity.getBalance())
//                .address( Address.builder()
//                        .add1(accountEntity.getAccountEntityAddressesList().get(0).getAdd1())
//                        .add2(accountEntity.getAccountEntityAddressesList().get(0).getAdd2())
//                        .city(accountEntity.getAccountEntityAddressesList().get(0).getCity())
//                        .pincode(accountEntity.getAccountEntityAddressesList().get(0).getPincode())
//                        .state(accountEntity.getAccountEntityAddressesList().get(0).getState())
//                        .build())
                .build();


        return account;
    }




    public String oneToManyusingHibernateFromUI(Account  account){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
         Session session =  sessionFactory.openSession();
         Transaction  transaction = session.beginTransaction();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountnumber(UUID.randomUUID().toString());
        accountEntity.setMobilenumber(account.getMobilenumber());
        accountEntity.setPan(account.getPan());
        accountEntity.setName(account.getName());
        accountEntity.setBalance(account.getBalance());

        List<AccountEntityAddress> accountEntityAddresses= new ArrayList<>();
        AccountEntityAddress address = new AccountEntityAddress();
        address.setAdd1(account.getAddress().getAdd1());
        address.setAdd2(account.getAddress().getAdd2());
        address.setCity(account.getAddress().getCity());
        address.setPincode(account.getAddress().getPincode());
        address.setState(account.getAddress().getState());
         address.setStatuscode(1);
         address.setAccountEntity(accountEntity);
         accountEntityAddresses.add(address);
         accountEntity.setAccountEntityAddressesList(accountEntityAddresses);

        session.persist(accountEntity);
        transaction.commit();
        return accountEntity.getAccountnumber();

    }
    public  String createAccountUsingHibernate(Account account){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountnumber(UUID.randomUUID().toString());
        accountEntity.setMobilenumber(account.getMobilenumber());
        accountEntity.setPan(account.getPan());
        accountEntity.setName(account.getName());
      accountEntity.setBalance(account.getBalance());

        session.persist(accountEntity);
        transaction.commit();

        return accountEntity.getAccountnumber();
    }
}
