package com.github.renatotakaoka.filmes_api.projections;

public interface UserDetailProjection {

    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();

}
