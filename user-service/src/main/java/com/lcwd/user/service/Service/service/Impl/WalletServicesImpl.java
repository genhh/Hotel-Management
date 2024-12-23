package com.lcwd.user.service.Service.service.Impl;

import com.zh.hotel.entity.TransactionType;
import com.zh.hotel.entity.Transactions;
import com.zh.hotel.entity.User;
import com.zh.hotel.entity.Wallet;
import com.lcwd.user.service.Service.exception.UserException;
import com.lcwd.user.service.Service.exception.WalletException;
import com.zh.hotel.mapper.UserMapper;
import com.zh.hotel.mapper.WalletMapper;
import com.lcwd.user.service.Service.service.WalletServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class WalletServicesImpl implements WalletServices {



    @Autowired
    private WalletMapper wrepo;

    @Autowired
    private UserMapper urepo;


    /**
     * Adds money to a wallet.
     *
     * @param walletId The ID of the wallet to add money to.
     * @param amount   The amount of money to add.
     * @return The updated wallet details.
     */
    @Override
    public Wallet addMoney(Integer walletId, Float amount) {
        if(walletId==null || amount == null)
            throw new WalletException("Invalid Details");
        Wallet ob= wrepo.selectById(walletId);


        ob.setBalance(ob.getBalance()+amount);
        Transactions trans= new Transactions();
        trans.setTransactionDate(LocalDateTime.now());
        trans.setAmount(amount);
        trans.setType(TransactionType.CREDIT);
        trans.setCurrentBalance(ob.getBalance());

        ob.getTransactions().add(trans);
        wrepo.insert(ob);

        return ob;
    }

    /**
     * Retrieves all transactions associated with a wallet.
     *
     * @param walletId The ID of the wallet to retrieve transactions for.
     * @return A list of transactions associated with the wallet.
     */
    @Override
    public List<Transactions> getAllTranactions(Integer walletId) {
        if(walletId==null )
            throw new WalletException("Invalid Details");
        Wallet ob= wrepo.selectById(walletId);

        List<Transactions>  list=  ob.getTransactions();


        return list;
    }

    /**
     * Pays a ride bill using a wallet.
     *
     * @param walletId The ID of the wallet to pay the bill from.
     * @param bill     The bill amount to be paid.
     * @return The updated wallet details.
     */

    @Override
    public Wallet payRideBill(Integer walletId, Float bill) {
        if(walletId==null || bill == null)
            throw new WalletException("Invalid Details");

        Wallet ob= wrepo.selectById(walletId);


        if(ob.getBalance()<bill)
        {
            float required= bill - ob.getBalance();
            throw new WalletException("Wallet Balance is low please add "+required+" amount first into your wallet");
        }

        ob.setBalance(ob.getBalance()-bill);
        Transactions trans= new Transactions();
        trans.setTransactionDate(LocalDateTime.now());
        trans.setAmount(bill);
        trans.setType(TransactionType.DEBIT);
        trans.setCurrentBalance(ob.getBalance());

        ob.getTransactions().add(trans);

         wrepo.insert(ob);
        return ob;
    }


    /**
     * Creates a new wallet for a user with the given email.
     *
     * @param email The email of the user for whom to create a wallet.
     * @return The created wallet details.
     */
    @Override
    public Wallet createWallet(String email) {
        if(email==null )
            throw new WalletException("Invalid Details");

        User user= urepo.findByEmail(email);
        if(user==null)
            throw new UserException("No User Found");
        log.info("user is "+user);

        Wallet ob2= user.getWallet();
        if(ob2!=null)
            throw new WalletException("Wallet has been already  allocated for this user So Another Wallet cannot be created ");

        Wallet ob= new Wallet();
        ob.setBalance(0.0f);
        ob.setUserId(user.getUserId());

        wrepo.insert(ob);

        return ob;
    }

    /**
     * Retrieves the details of a wallet by its ID.
     *
     * @param id The ID of the wallet to retrieve.
     * @return The wallet details.
     */
    @Override
    public Wallet getWallet(Integer id) {
        Wallet res= wrepo.selectById(id);
        return res;
    }

    /**
     * Retrieves the wallet associated with a logged-in user by their email.
     *
     * @param email The email of the logged-in user.
     * @return The wallet details associated with the user.
     */
    @Override
    public Wallet getLoggedUserWallet(String email) {
        if(email==null)
            throw new UserException("Invalid user details provided");

        User user= urepo.findByEmail(email);
        if(user==null)
            throw new UserException("No User Found");
        Wallet res= user.getWallet();
        if(res==null)
            throw new WalletException("NO Wallet found ");
        return res;

    }

    @Override
    public Float getBalance(String email) {
        if(email==null)
            throw new UserException("Invalid user details provided");

        User user= urepo.findByEmail(email);
        if(user==null)
            throw new UserException("No User Found");
        Wallet res= user.getWallet();
        if(res==null)
            throw new WalletException("NO Wallet found ");
        return res.getBalance();
    }
}
