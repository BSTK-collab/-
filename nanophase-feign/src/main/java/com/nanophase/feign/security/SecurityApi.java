//package com.nanophase.feign.security;
//
//import com.nanophase.common.dto.NanophaseUserDTO;
//import com.nanophase.common.util.R;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import java.util.Map;
//
//@FeignClient("nanophase-security")
//public interface SecurityApi {
//
//    @RequestMapping(method = RequestMethod.POST,value = "hello")
//    String hello();
//
//    @RequestMapping(method = RequestMethod.POST,value = "/oauth/token")
//    R getToken(@RequestParam Map<String,String> map);
//
//    @RequestMapping(method = RequestMethod.POST,value = "/security/loadUserByUsername")
//    R loadUserByUsername(@RequestBody NanophaseUserDTO nanophaseUserDTO);
//}
