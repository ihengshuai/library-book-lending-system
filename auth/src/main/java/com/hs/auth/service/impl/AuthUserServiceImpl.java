package com.hs.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hs.auth.mapper.*;
import com.hs.authservice.entity.*;
import com.hs.authservice.model.MenuModel;
import com.hs.authservice.model.RoleModel;
import com.hs.authservice.model.UserModel;
import com.hs.authservice.service.IAuthUserService;
import com.hs.core.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenkk
 * @since 2025-12-30
 */
@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {

    public final AuthRoleMapper authRoleMapper;

    public final AuthUserRoleMapper authUserRoleMapper;

    public final AuthMenuMapper authMenuMapper;

    public final AuthRoleMenuMapper authRoleMenuMapper;

    @Override
    public UserModel selectOne(String username) {
        UserModel user = null;
        QueryWrapper<AuthUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        AuthUser authUser = this.baseMapper.selectOne(queryWrapper);
        if (null == authUser) {
            throw new AuthException("401", "用户不存在");
        }
        user = BeanUtil.copyProperties(authUser, UserModel.class);

        QueryWrapper<AuthUserRole> userRoleQw = new QueryWrapper<>();
        userRoleQw.eq("user_id", authUser.getId());
        List<AuthUserRole> authUserRoles = authUserRoleMapper.selectList(userRoleQw);

        if (CollUtil.isNotEmpty(authUserRoles)) {
            // todo 查询角色列表获取角色编码
            List<Long> roleIds = authUserRoles.stream().map(AuthUserRole::getRoleId).toList();
            QueryWrapper<AuthRole> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.in("id", roleIds);
            List<AuthRole> roles = authRoleMapper.selectList(roleQueryWrapper);
            List<RoleModel> roleModels = BeanUtil.copyToList(roles, RoleModel.class);
            user.setRoles(roleModels);


            QueryWrapper<AuthRoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
            roleMenuQueryWrapper.in("role_id", roleIds);
            List<AuthRoleMenu> authRoleMenus = authRoleMenuMapper.selectList(roleMenuQueryWrapper);
            Set<GrantedAuthority> grantedAuthorities;
            if (CollUtil.isNotEmpty(authRoleMenus)) {
                List<Long> menuIds = authRoleMenus.stream().map(AuthRoleMenu::getMenuId).toList();
                QueryWrapper<AuthMenu> menuQueryWrapper = new QueryWrapper<>();
                menuQueryWrapper.in("id", menuIds);
                List<AuthMenu> authMenus = authMenuMapper.selectList(menuQueryWrapper);
                List<MenuModel> menuModels = BeanUtil.copyToList(authMenus, MenuModel.class);
                user.setMenus(menuModels);
               /*         grantedAuthorities = new HashSet<>();
                for (AuthMenu menuEntity : authMenus) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(menuEntity.getPerms()));
                }*/


            }

        }


        return user;
    }


}
