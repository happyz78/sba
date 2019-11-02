package com.ibm.fsd.sba.jwt.config;

import java.util.Collection;

import com.ibm.fsd.sba.jwt.feign.UserFeign;
import com.ibm.fsd.sba.jwt.model.ResponseDto;
import com.ibm.fsd.sba.jwt.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserFeign userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.error("in the SecurityAuthenticationProvider!" + authentication);
        String userName = authentication.getName();// 这个获取表单输入中的用户名
        String password = (String) authentication.getCredentials();// 这个是表单中输入的密码
        log.error("in the SecurityAuthenticationProvider2!" + password);

        UserDto user = new UserDto();
        user.setUserName(userName);
        ResponseDto<UserDto> result = userService.queryUser(user);

        /** 判断用户是否存在 */
        user = result.getData();
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

		log.error("in the SecurityAuthenticationProvider2!" + user.getPassword());
        /** 判断密码是否正确 */
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }

        UserDetails userDetails = generateUserDetails(user);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		log.error("in the SecurityAuthenticationProvider2!" + authorities);
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);// 构建返回的用户登录成功的token

	}

    private UserDetails generateUserDetails(UserDto user) {
        StringBuilder sb = new StringBuilder();
        switch (user.getUserType()) {
            case "2":
                sb.append("ROLE_ADMIN,ROLE_MENTOR,ROLE_USER");
                break;
            case "1":
                sb.append("ROLE_MENTOR,ROLE_USER");
                break;
            case "0":
                sb.append("ROLE_USER");
                break;
        }
        Collection<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(sb.toString());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }

    @Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
