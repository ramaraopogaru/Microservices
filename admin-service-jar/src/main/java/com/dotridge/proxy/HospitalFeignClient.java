package com.dotridge.proxy;

/*import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;*/
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@RibbonClient
//@FeignClient(name="hospital-proxy",url="localhost:9093/hospitalManagement/")
//@FeignClient(name="hospital-proxy")
public interface HospitalFeignClient {

	@RequestMapping(value="/hospitalManagement/getHospitalByName/{hospName}")
	public ResponseEntity<String> getHospitalByName(@PathVariable("hospName") String hospName);
}
