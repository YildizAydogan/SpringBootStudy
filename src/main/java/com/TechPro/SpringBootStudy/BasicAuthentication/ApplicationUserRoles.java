package com.TechPro.SpringBootStudy.BasicAuthentication;



import static com.TechPro.SpringBootStudy.BasicAuthentication.ApplicationUserPermission.STUDENT_READ;
import static com.TechPro.SpringBootStudy.BasicAuthentication.ApplicationUserPermission.STUDENT_WRITE;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


public enum ApplicationUserRoles {

  STUDENT(Sets.newHashSet(STUDENT_READ)), ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE));

  private final Set<ApplicationUserPermission> permissions;
  ApplicationUserRoles(Set<ApplicationUserPermission> permissions){
      this.permissions = permissions;

  }
public Set<ApplicationUserPermission> izinlerigetirenmethod(){
      return permissions;
}
public Set<SimpleGrantedAuthority> izinler() {
      Set<SimpleGrantedAuthority>onaylananIzinler=izinlerigetirenmethod().stream().map(
              t-> new SimpleGrantedAuthority(t.getPermission())).
              collect(Collectors.toSet());

    onaylananIzinler.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

       return onaylananIzinler;
}


}
