package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dao.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

/**
* Created by mejty on 13.12.14.
*/
public class AuthenticationService extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    private GenericDao genericDAO;

    private TransactionTemplate transactionTemplate;

    public AuthenticationService() {
        this.setUserCache(new NullUserCache());
    }

    public void setGenericDAO(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        // Do nothing
    }

    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        //only public methods can be marked as transactional
        return (UserDetails) transactionTemplate.execute(new TransactionCallback() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    UserDetails ud = null;

                    cz.cvut.fel.wpa.tracker.bo.User u;
                    try {
                        u = genericDAO.getByPropertyUnique("username", username, cz.cvut.fel.wpa.tracker.bo.User.class);
                    } catch (EmptyResultDataAccessException erdaex) {
                        throw new BadCredentialsException("User not found.");
                    }
                    String password = (String) upat.getCredentials();
                    if (u == null || !u.hasPassword(password) || !u.isState()) {
                        AuthenticationException e = new BadCredentialsException("Invalid credentials.");
                        throw e;
                    } else {
                        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
//                        auths.add(new SimpleGrantedAuthority("ROLE_"+u.getRole().getName()));
                            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                            if(u.getRole().getName().equals("ADMIN")){
                                auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                                auths.add(new SimpleGrantedAuthority("ROLE_SALESMAN"));
                            }
                        if(u.getRole().getName().equals("SALESMAN")){
                            auths.add(new SimpleGrantedAuthority("ROLE_SALESMAN"));
                        }
                        ud = new User(u.getUserName(), u.getPassword(), auths);
                    }
                    return ud;
                } catch(AuthenticationException e){
                    status.setRollbackOnly();
                    throw e;
                }catch (Exception e) {
                    LOG.error("Error occured during retrieveUser call", e);
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
            }
        });
    }
}