package com.thebudding.book.security3.security;

import java.io.Serializable;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.ObjectIdentity;

public class NullAclCache implements AclCache {

  @Override
  public void evictFromCache(Serializable serializable) {

  }

  @Override
  public void evictFromCache(ObjectIdentity objectIdentity) {

  }

  @Override
  public MutableAcl getFromCache(ObjectIdentity objectIdentity) {
    return null;
  }

  @Override
  public MutableAcl getFromCache(Serializable serializable) {
    return null;
  }

  @Override
  public void putInCache(MutableAcl mutableAcl) {

  }

  @Override
  public void clearCache() {

  }
}
