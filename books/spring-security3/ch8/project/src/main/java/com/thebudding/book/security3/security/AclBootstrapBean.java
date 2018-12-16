package com.thebudding.book.security3.security;

import com.thebudding.book.security3.dao.IProductDao;
import com.thebudding.book.security3.data.Category;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class AclBootstrapBean {

  @Autowired
  private MutableAclService mutableAclService;

  @Autowired
  private IProductDao productDao;

  @Autowired
  private PlatformTransactionManager transactionManager;

  @PostConstruct
  public void aclBootstrapBean() {
    Collection<Category> categories = productDao.getCategories();
    Iterator<Category> iterator = categories.iterator();
    final Category category1 = iterator.next();
    final Category category2 = iterator.next();

    GrantedAuthorityImpl roleUser = new GrantedAuthorityImpl("ROLE_USER");
    GrantedAuthorityImpl roleAdmin = new GrantedAuthorityImpl("ROLE_ADMIN");
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        "admin", "admin",
        Arrays.asList(new GrantedAuthority[]{roleUser, roleAdmin})
    );
    SecurityContextHolder.getContext().setAuthentication(token);

    final Sid userRole = new GrantedAuthoritySid("ROLE_USER");
    final Sid adminRole = new GrantedAuthoritySid("ROLE_ADMIN");

    final Sid adminUser = new PrincipalSid("admin");
    final Sid admin2User = new PrincipalSid("admin2");

    TransactionTemplate tt = new TransactionTemplate(transactionManager);
    tt.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
        MutableAcl createAclCategory1 =
            mutableAclService.createAcl(new ObjectIdentityImpl(category1));
        createAclCategory1.setOwner(adminRole);
        createAclCategory1.insertAce(0, BasePermission.READ, adminRole, true);
        mutableAclService.updateAcl(createAclCategory1);

        MutableAcl createAclCategory2 =
            mutableAclService.createAcl(new ObjectIdentityImpl(category2));
        createAclCategory2.setOwner(admin2User);
        createAclCategory2.insertAce(0, CustomPermission.ADMIN_READ, admin2User, true);
        mutableAclService.updateAcl(createAclCategory2);
      }
    });
    SecurityContextHolder.clearContext();
  }
}
