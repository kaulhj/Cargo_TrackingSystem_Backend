package com.example.cah.controller;


import com.example.cah.controller.dto.ResponseDto;
import com.example.cah.controller.dto.Result;
import com.example.cah.data.repository.PositionRepository;
import com.example.cah.data.repository.UserRepository;
import com.example.cah.mapper.PositionMapper;
import com.example.cah.model.Position;
import com.example.cah.model.User;
import com.example.cah.model.getPositionAndName;
import com.example.cah.service.PositionService;
import com.example.cah.service.UserService;

import org.json.JSONException;




import org.json.simple.parser.JSONParser;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

@RestController
public class PositionController {

    @Autowired
    private PositionMapper positionMapper;

    public PositionController(PositionMapper positionMapper){
        this.positionMapper=positionMapper;
    }

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private UserService userService;

    @Autowired //메모리에 뜰때 메모리에 띄우기 의존성 주입, DI
    private UserRepository userRepository;

    @Autowired
    private PositionService positionService;

    @GetMapping("/pos/recent/{id}")
    public List<getPositionAndName> getPositionIdx(@PathVariable("id")int id){
        return positionMapper.getPosition(id);
    }


    @PostMapping("/pos/inform/{id}")
    public Result putInform(@PathVariable("id") int id, @RequestBody Position position ){
        if(id == 0)
            return new Result("유저고유번호를 입력해주세요",null);

        int setMNum = positionService.getLastMNum();
        if(positionService.getLastEndTag()==1){
            setMNum+=1;
            position.setEtc("출발");
        }


        User findUser = userRepository.findByUserId(id).orElseThrow(()-> {
            return new IllegalArgumentException("해당 유저는 없습니다.");
        });

        position.setMeasureId(setMNum);
        User setUsers = new User();
        setUsers.setUserId(id);
        position.setUser(setUsers);

        position.setCoordinate(coordToAddr(String.valueOf(position.getLongitude()), String.valueOf(position.getLatitude())));

        positionRepository.save(position);
        return new Result("정보입력이 완료되었습니다.", null);


    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/pos/end")
    public Result informEnd(){
         positionMapper.informEnd();
         positionRepository.updateEndTag();
         return new Result("측정이 종료되었습니다.", null);
    }



    public static String coordToAddr(String longitude, String latitude){
        String url = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x="+longitude+"&y="+latitude;
        String addr = "";
        try{
            addr = getRegionAddress(getJSONData(url));
            //LOGGER.info(addr);
        }catch(Exception e){
            System.out.println("주소 api 요청 에러");
            e.printStackTrace();
        }
        String[] tok = addr.split(" ");
        int len = (tok.length < 3) ? tok.length : 3;
        String val="";
        for(int i=0;i<len;i++)
            val+=tok[i]+" ";
        return val;
    }

    /**
     * REST API로 통신하여 받은 JSON형태의 데이터를 String으로 받아오는 메소드
     */
    private static String getJSONData(String apiUrl) throws Exception {
        HttpURLConnection conn = null;
        StringBuffer response = new StringBuffer();


        String auth = "KakaoAK " + "apiKey";

        //URL 설정
        URL url = new URL(apiUrl);

        conn = (HttpURLConnection) url.openConnection();

        //Request 형식 설정
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Requested-With", "curl");
        conn.setRequestProperty("Authorization", auth);

        //request에 JSON data 준비
        conn.setDoOutput(true);

        //보내고 결과값 받기
        int responseCode = conn.getResponseCode();
        if (responseCode == 400) {
            System.out.println("400:: 해당 명령을 실행할 수 없음");
        } else if (responseCode == 401) {
            System.out.println("401:: Authorization가 잘못됨");
        } else if (responseCode == 500) {
            System.out.println("500:: 서버 에러, 문의 필요");
        } else { // 성공 후 응답 JSON 데이터받기

            Charset charset = Charset.forName("UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
        }

        return response.toString();
    }

    /**
     * JSON형태의 String 데이터에서 주소값(address_name)만 받아오기
     */
    private static String getRegionAddress(String jsonString) throws JSONException {
        String value = "";
        JSONObject jObj = (JSONObject) JSONValue.parse(jsonString);
        JSONObject meta = (JSONObject) jObj.get("meta");
        long size = (long) meta.get("total_count");

        if(size>0){
            JSONArray jArray = (JSONArray) jObj.get("documents");
            JSONObject subJobj = (JSONObject) jArray.get(0);
            JSONObject roadAddress =  (JSONObject) subJobj.get("road_address");

            if(roadAddress == null){
                JSONObject subsubJobj = (JSONObject) subJobj.get("address");
                value = (String) subsubJobj.get("address_name");
            }else{
                value = (String) roadAddress.get("address_name");
            }

            if(value.equals("") || value==null){
                subJobj = (JSONObject) jArray.get(1);
                subJobj = (JSONObject) subJobj.get("address");
                value =(String) subJobj.get("address_name");
            }
        }
        return value;
    }



}
