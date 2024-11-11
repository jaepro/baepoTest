package com.example.jwt.prop;
//이 파일은 application.properties 파일에서 우리가 미리 정의해둔 시크릿 키를 가져올 것이다.

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data      //게터 세터 생성
@Component //(얘를 빈으로 등록)
@ConfigurationProperties("com.example.jwt") //com.example.jwt경로 하위 속성들을 지정
//이렇게 application.properties에서 com.~~를 매핑을 하면 여기 내부에서 시크릿키의 값을 가져올 수 있다.

public class JwtProp {
    //com.example.jwt.secreet-key  =>  secretKey : {인토딩된 시크릿 키}
    //private으로 설정했으므로 다른 곳에서도 사용할 수 있도록 게터, 세터, 빈 설정하기 (@Data, @Component)
    private String secretKey;

    
}
