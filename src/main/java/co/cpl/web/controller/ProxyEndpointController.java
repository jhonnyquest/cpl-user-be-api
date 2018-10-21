/******************************************************************
 *  
 * This code is for the Complaints service project.
 *
 * 
 * © 2018, Complaints Management All rights reserved.
 * 
 * 
 ******************************************************************/

package co.cpl.web.controller;
import java.util.HashMap;
import java.util.Properties;

import co.cpl.dto.UsersDto;
import co.cpl.service.BusinessManager;
import co.cpl.validators.UsersValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import co.cpl.enums.ResponseKeyName;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

/**
 * Only service exposition point of services to FE layer
 * 
 * @author jmunoz
 * @version 1.0.0
 * @since 08/09/2018
 *
 */

@RestController
@RequestMapping("/v1")
public class ProxyEndpointController extends BaseRestController {

	private static final Logger LOGGER = LogManager.getLogger(ProxyEndpointController.class);

	/** The error properties. */
	@Autowired
	@Qualifier("errorProperties")
	private Properties errorProperties;

	@Autowired
	BusinessManager businessManager;

	@Autowired
    UsersValidator loadRequestValidator;

	// This is an example of how to create proxy endpoints controller methods
	// please build your own constants based on this

//	/**
//	 * loadPayment method: perform a new money load for an specific customer
//	 *
//	 * @param load the whole information necessary to perform money load
//	 * @author jmunoz
//	 * @since 12/08/2018
//	 * @return load confirmation registry
//	 */
//    @RequestMapping(value = "/payment/load", method = RequestMethod.POST)
//    public ResponseEntity<Object> loadPayment(@Validated @RequestBody UsersDto load,
//                                                 BindingResult result, HttpServletRequest request) {
//		loadRequestValidator.validate(load, result);
//		ResponseEntity<Object> responseEntity = apiValidator(result);
//		if (responseEntity != null) {
//			return responseEntity;
//		}
//
//		try {
//			Users registry = businessManager.loadPayment(load);
//			responseEntity =  ResponseEntity.ok(createSuccessResponse(ResponseKeyName.PAYMENT_RESPONSE, registry));
//		} catch (HttpClientErrorException ex) {
//			responseEntity = setErrorResponse(ex, request);
//		}
//
//		return responseEntity;
//    }

	/**
	* saveUser method: allows to create a new user from an object
	*
	* @param load the whole information necessary to create user
	* @author omarquez
	* @since 12/08/2018
	* @return load confirmation registry
	*/
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Object> saveUser(@Validated @RequestBody UsersDto load,
											  BindingResult result, HttpServletRequest request) {

		ResponseEntity<Object> responseEntity = apiValidator(result);
		if (responseEntity != null) {
			return responseEntity;
		}
		try {
			Boolean registry = businessManager.saveUser(load);
			responseEntity =  ResponseEntity.ok(ResponseKeyName.USERS_RESPONSE);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}

		return responseEntity;
	}

	/**
	 * updateUser method: allows to modify the data of the user that is sent (for now only imei and status)
	 *
	 * @param load the whole information necessary to perform update user
	 * @author omarquez
	 * @since 12/08/2018
	 * @return load confirmation registry
	 */
	@RequestMapping(value = "/users/{user}", method = RequestMethod.POST)
	public ResponseEntity<Object> updateUser(@Validated @RequestBody UsersDto load,
										   BindingResult result, HttpServletRequest request) {

		ResponseEntity<Object> responseEntity = apiValidator(result);
		if (responseEntity != null) {
			return responseEntity;
		}
		try {
			Boolean registry = businessManager.updateUser(load);
			responseEntity =  ResponseEntity.ok(ResponseKeyName.USERS_RESPONSE);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}

		return responseEntity;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@Validated @RequestBody UsersDto load,
										   BindingResult result, HttpServletRequest request) {

		ResponseEntity<Object> responseEntity = apiValidator(result);
		if (responseEntity != null) {
			return responseEntity;
		}
		try {
			UsersDto registry = businessManager.login(load);
			responseEntity =  ResponseEntity.ok(ResponseKeyName.USERS_RESPONSE);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}

		return responseEntity;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findUserById(@PathVariable("id") String id,
												HttpServletRequest request) {
		//transactionValidator.validate(Users, result);
		//TODO: build custom validator for face_plate, if it apply
		ResponseEntity<Object> responseEntity;
		try {
			UsersDto users = businessManager.findUserById(id);
			responseEntity = ResponseEntity.ok(ResponseKeyName.USERS_RESPONSE);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}
		return responseEntity;
	}

	private ResponseEntity<Object> setErrorResponse(HttpClientErrorException ex, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		HttpStatus status;
		switch (ex.getStatusCode().value()) {
			case 404:
				map.put("message", "Not Found");
				status = HttpStatus.NOT_FOUND;
				break;
			case 401:
				map.put("message", "Access denied");
				status = HttpStatus.UNAUTHORIZED;
				break;
			case 400:
				map.put("message", "bad request");
				status = HttpStatus.BAD_REQUEST;
				break;
			case 406:
				map.put("message", "invalid parameter");
				map.put("detail", ex.getMessage());
				status = HttpStatus.NOT_ACCEPTABLE;
				break;
            case 412:
                map.put("message", "invalid parameter");
                map.put("detail", ex.getMessage());
                status = HttpStatus.PRECONDITION_FAILED;
                break;
			case 500:
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			case 503:
				status = HttpStatus.SERVICE_UNAVAILABLE;
				break;
			default:
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				map.put("message", "There was a problem trying to resolve the request");
		}
		return  ResponseEntity.status(status)
				.body(ResponseKeyName.USERS_RESPONSE);

	}
}
