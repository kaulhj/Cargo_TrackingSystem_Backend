package com.example.cah.controller;


import com.example.cah.controller.dto.ResponseDto;
import com.example.cah.controller.dto.Result;
import com.example.cah.data.entity.KakaoProfile;
import com.example.cah.data.entity.OAuthToken;
import com.example.cah.mapper.PositionMapper;
import com.example.cah.mapper.UserMapper;
import com.example.cah.model.Position;
import com.example.cah.model.RoleType;
import com.example.cah.model.User;
import com.example.cah.data.repository.UserRepository;
import com.example.cah.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired //메모리에 뜰때 메모리에 띄우기 의존성 주입, DI
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;




    @GetMapping("/test1")
    public String test1(){
        String val = passwordEncoder.encode("1234");
        if(passwordEncoder.matches("1234", val))
            return "암호화성공 ";
        else
            return "암호화실패";
    }



    @GetMapping("/user/list")
    public List<User> getUserList(){return userMapper.getUserList();
    }


        @PostMapping("/user/join")
        public String save(@RequestBody User user){
            User findUser = userRepository.findByEmail(user.getEmail()).orElseGet(()-> {
                return null;
            });

            if(findUser!=null){
                return "동일한 이메일로 가입된 유저가 존재합니다";
            }


            userService.회원가입(user);
            return "회원가입 성공했습니다.";
        }


        @GetMapping("/test")
        public  String test(){
            return "테스트성공";
        }


    @PostMapping("/user/login")
    public ResponseDto login(
            @RequestBody User user){
        //user의 4번을 찾으면 db에서 못찾으면 user가 null이 되니까 null이 리턴이 되니까 프로그램에 문제가 있음. 옵셔널로 유저객체를 감싸서
        //가져올테니 null인지 아닌지 판단해서 return하라.\

        if(user.getEmail() == null || user.getPassword() == null)
            return new ResponseDto<>("이메일과 패스워드를 모두 입력해주세요",0);
        User findUser = userRepository.findByEmail(user.getEmail()).orElseThrow(()-> {
            return new IllegalArgumentException("해당 유저는 없습니다.");
        });

        if(!passwordEncoder.matches( user.getPassword(),findUser.getPassword()))
            return new ResponseDto<>("비밀번호가 틀렸습니다.",null);


        //user객체는 자바 오브젝트
        //스프링부트는 messageConverter라는 것이 응답시에 자동 작동
        //만약 자바 오브젝트를 리턴하게 되면 messageConverter가 jackson 라이브러리를 호출해서 user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
        return new ResponseDto<User>("로그인 성공",findUser);
    }



    //위경도, 날씨 주입 api


//
//    @GetMapping("/auth/kakao/callback")
//    public @ResponseBody
//    String kakaoCallback(String code) { //Data를 리턴해주는 컨트롤러 함수
//
//        System.out.println("인가코드:" + code);
//
//        try {
//            RestTemplate rt = new RestTemplate();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//            params.add("grant_type", "authorization_code");
//            params.add("client_id", "e289eaea04d1431421f1a03e9525d022");
//            params.add("redirect_uri", "http://localhost:8050/auth/kakao/callback");                /** **/
//            params.add("code", code);
//
//            HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
//
//            ResponseEntity<String> response = rt.exchange(
//                    "https://kauth.kakao.com/oauth/token",
//                    HttpMethod.POST,
//                    kakaoTokenRequest,
//
//                    String.class
//            );
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            OAuthToken oAuthToken = null;
//            try {
//                oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
//            } catch (JsonMappingException e) {
//                e.printStackTrace();
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//
//            //jwt 고유 토큰 생성하거나 기존유저면 그걸 디비에서 가져와서
//
//            System.out.println("카카오 액세스 토큰 : " + oAuthToken.getAccess_token());
//
//////////////////
//
//            RestTemplate rt2 = new RestTemplate();
//            //2
//
//            HttpHeaders headers2 = new HttpHeaders();
//            headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
//            headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//
//            HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
//
//            ResponseEntity<String> response2 = rt2.exchange(
//                    "https://kapi.kakao.com/v2/user/me",
//                    HttpMethod.POST,
//                    kakaoProfileRequest2,
//
//                    String.class
//            );
//
//
//            ObjectMapper objectMapper2 = new ObjectMapper();
//            KakaoProfile kakaoProfile = null;
//            try {
//                kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//
//
//            System.out.println(kakaoProfile);
//            System.out.println("-----");
//            System.out.println("카카오 아이디(번호)" + kakaoProfile.getId());
//            System.out.println("카카오이메일:" + kakaoProfile.getKakao_account().getEmail());
//            System.out.println("블로그서버 유저네임:" + kakaoProfile.properties.getNickname());
//            System.out.println("블로그서버 이메일:" + kakaoProfile.getKakao_account().getEmail());
//
//            UUID garbagePassword = UUID.randomUUID();
//
//            System.out.println("블로그 서버 패스워드: ");
//
//
//            User kakaoUser = User.builder()
//                    .username(kakaoProfile.properties.getNickname())
//                    .password("kakao")
//                    .email(kakaoProfile.getKakao_account().getEmail())
//                    .build();
//
//            User originUser = userService.회원찾기(kakaoProfile.getKakao_account().getEmail());
//
//            if (originUser.getUsername()== null) {
//                System.out.println("기존 회원이 아닙니다.................");
//                userService.회원가입(kakaoUser);
//            }
//
//            //로그인 처리
////            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
////            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//            //jwt랑 로그인 성공 유무 주면됨
//            return "http://localhost:5500/authorized.html";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            return "x";
//        }
////     가입자 혹은 비가입자 체크  해서 처리
//
//
//    }


    }

