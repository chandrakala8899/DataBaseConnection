package com.neoteric.fullstack_31082024.hibernate.service;

import com.neoteric.fullstack_31082024.hibernate.model.AccountEntity;
import com.neoteric.fullstack_31082024.model.Account;
import com.neoteric.fullstack_31082024.model.Address;
import jakarta.persistence.*;

import java.util.List;

public class JpaConfigservice {
    public Account creatingAccountFromJpa(String accountnumber) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    EntityManager entityManager = emf.createEntityManager();
       entityManager.getTransaction().begin();

            entityManager.getTransaction().begin();
            jakarta.persistence.Query query = entityManager.createQuery(
                    "FROM AccountEntity a WHERE a.accountnumber = :accountInput", AccountEntity.class);
            query.setParameter("accountInput",accountnumber);
        List<AccountEntity> accountEntities = query.getResultList();

            AccountEntity accountEntity = accountEntities.get(0);

        Account account = Account.builder()
                    .accountnumber(accountEntity.getAccountnumber())
                    .mobilenumber(accountEntity.getMobilenumber())
                    .name(accountEntity.getName())
                    .pan(accountEntity.getPan())
                    .balance(accountEntity.getBalance())

                .address(Address.builder()
                            .add1(accountEntity.getAccountEntityAddressesList().get(0).getAdd1())
                            .add2(accountEntity.getAccountEntityAddressesList().get(0).getAdd2())
                            .city(accountEntity.getAccountEntityAddressesList().get(0).getCity())
                            .pincode(accountEntity.getAccountEntityAddressesList().get(0).getPincode())
                            .state(accountEntity.getAccountEntityAddressesList().get(0).getState())
                            .build())

                    .build();

            entityManager.getTransaction().commit();
  emf.close();

return  account;
    }



}

