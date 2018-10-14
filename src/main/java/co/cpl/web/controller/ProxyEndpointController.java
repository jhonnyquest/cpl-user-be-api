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
import java.util.List;
import java.util.Properties;

import co.cpl.dto.UserDto;
import co.cpl.service.BusinessManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Object> findUsers(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
												HttpServletRequest request) {
		ResponseEntity<Object> responseEntity;
		try {
            List<UserDto> users = businessManager.findUsers(limit, offset);
            responseEntity = ResponseEntity.ok(createSuccessResponse(ResponseKeyName.USER_RESPONSE, users));
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}
		return responseEntity;
	}

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody UserDto user, HttpServletRequest request) {
        ResponseEntity<Object> responseEntity;
        try {
            String userId = businessManager.createUser(user);
            HashMap<String, String> response = new HashMap<>();
            response.put("user_id", userId);
            responseEntity = ResponseEntity.ok(createSuccessResponse(ResponseKeyName.USER_RESPONSE, response));
        } catch (HttpClientErrorException ex) {
            responseEntity = setErrorResponse(ex, request);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody UserDto user, HttpServletRequest request) {
        ResponseEntity<Object> responseEntity;
        try {
            businessManager.updateUser(user);
            HashMap<String, Boolean> response = new HashMap<>();
            response.put("success", true);
            responseEntity = ResponseEntity.ok(createSuccessResponse(ResponseKeyName.USER_RESPONSE, response));
        } catch (HttpClientErrorException ex) {
            responseEntity = setErrorResponse(ex, request);
        }
        return responseEntity;
    }

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findUserById(@PathVariable("id") String id,
											   HttpServletRequest request) {
		//transactionValidator.validate(User, result);
		//TODO: build custom validator for face_plate, if it apply
		ResponseEntity<Object> responseEntity;
		try {
			UserDto users = businessManager.findUserById(id);
			responseEntity = ResponseEntity.ok(createSuccessResponse(ResponseKeyName.USER_RESPONSE, users));
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}
		return responseEntity;
	}

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String id,
                                               HttpServletRequest request) {
        ResponseEntity<Object> responseEntity;
        try {
            businessManager.deleteUser(id);
            HashMap<String, Boolean> response = new HashMap<>();
            response.put("success", true);
            responseEntity = ResponseEntity.ok(createSuccessResponse(ResponseKeyName.USER_RESPONSE, response));
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
				.body(createLoginFailResponse(ResponseKeyName.USER_RESPONSE, map, ex));

	}
}
