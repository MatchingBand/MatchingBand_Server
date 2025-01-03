package com.mate.band.global.security.info;

import com.mate.band.global.security.constants.OAuthType;
import com.mate.band.global.security.info.impl.KakaoOAuth2UserInfo;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(OAuthType oauthType, Map<String, Object> attributes) {
        switch (oauthType) {
            case KAKAO -> {return new KakaoOAuth2UserInfo(attributes);}
        }
        throw new OAuth2AuthenticationException("INVALID PROVIDER TYPE");
    }
}
