/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.service.impl;

import co.cpl.service.BusinessManager;
import co.cpl.data.UsersRepository;
import org.springframework.stereotype.Component;


/***
 * Implementation for business manager module
 *
 * @author jmunoz
 *
 */
@Component
public class BussinessManagerImpl implements BusinessManager{

    private static UsersRepository usersRepository;

    public BussinessManagerImpl() {
        usersRepository = new UsersRepository();
    }

    // All business methods should be implemented here
    // example:
    // @Override
    // public boolean isPayed(String serviceId) {
    //        Optional<Service> service = paymentsRepository.getService(serviceId);
    //        if (service.isPresent()) {
    //            return true;
    //        }
    //        return false;
    //    }

}
