package com.overware.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.overware.domain.SampleVO;

@RestController	//데이터 자체를 반환한다. text/html 타입으로 처리 
@RequestMapping("/sample")
public class SampleController6 {
	
	@RequestMapping("/hello")
	public String sayHello() {
		//이렇게 하면 원래는 jsp로 연결되지만 RestContrller 기때문에 그냥 일반 문자열 처리가 된다. 그래서 /sample/hello 경로를 들어가면 결과값이 Hello World출력
		return "Hello World";
	}
	@RequestMapping("/sendVO")
	public SampleVO voReturn() {
		SampleVO sampleVo = new SampleVO();
		
		sampleVo.setFirstName("재훈");
		sampleVo.setLastName("심");
		sampleVo.setMno(123);
		
		return sampleVo;
	}
	@RequestMapping("/sendList") //List도 똑같이 적용됨 
	public List<SampleVO> voListReturn() {
		
		List<SampleVO> list= new ArrayList<SampleVO>();
		
		for(int i=0; i<10; i++) {
			SampleVO sampleVo = new SampleVO();
			sampleVo.setFirstName("재훈"+i);
			sampleVo.setLastName("심"+i);
			sampleVo.setMno(i);
			
			list.add(sampleVo);
		}
		return list;
	}
	@RequestMapping("/sendMap")
	public Map<Integer,SampleVO> sendMap(){
		
		Map<Integer,SampleVO> map= new HashMap<Integer,SampleVO>();
		
		for(int i=0; i<10; i++) {
			
			SampleVO sampleVo = new SampleVO();
			sampleVo.setFirstName("재훈"+i);
			sampleVo.setLastName("심"+i);
			sampleVo.setMno(i);
			
			map.put(i, sampleVo); //map에 결과 집어넣음 
		}
		return map;
	}
	@RequestMapping("/sendErrorNot") //ResponseEntity : 상태코드메세제를 데이터보낼때 같이 보낸다. 
	public ResponseEntity<Map<Integer,SampleVO>> sendErrorNot(){
		Map<Integer,SampleVO> map = new HashMap<Integer,SampleVO>();
		
			for(int i=0; i<10; i++) {
			
			SampleVO sampleVo = new SampleVO();
			sampleVo.setFirstName("재훈"+i);
			sampleVo.setLastName("심"+i);
			sampleVo.setMno(i);
			
			map.put(i, sampleVo); //map에 결과 집어넣음 
		}
		return new ResponseEntity<Map<Integer,SampleVO>>(map,HttpStatus.NOT_FOUND);
	} 
}
