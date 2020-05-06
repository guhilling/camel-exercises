/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package it.hilling.examples.cxf.wsdlfirst.server;

import it.hilling.customerservice.Customer;
import it.hilling.customerservice.CustomerType;
import it.hilling.customerservice.NoSuchCustomer;
import it.hilling.customerservice.NoSuchCustomerException;

import java.math.BigDecimal;
import java.util.*;

public class BackendService {

    private Map<String, Customer> customers = new HashMap<>();

    public BackendService() {
        addCustomer("Johns, Mary", 10000, "Pine Street 200");
        addCustomer("Johns, Marvin", 30000, "Pine Street 200");
        addCustomer("Jones", 30000, "Oak Street 201");
    }

    public List<Customer> getCustomersByName(String name) throws NoSuchCustomerException {

        List<Customer> results = new ArrayList<>();

        for (Customer customer : customers.values()) {
            if (customer.getName().startsWith(name)) {
                results.add(customer);
            }
        }

        if (results.isEmpty()) {
            NoSuchCustomer noSuchCustomer = new NoSuchCustomer();
            noSuchCustomer.setCustomerName(name);
            throw new NoSuchCustomerException("Did not find any matching customer for name=" + name,
                    noSuchCustomer);
        }

        return results;
    }

    public Customer updateCustomer(Customer customer) throws NoSuchCustomerException {
        if (customers.containsKey(customer.getName())) {
            customers.put(customer.getName(), customer);
        } else {
            NoSuchCustomer noSuchCustomer = new NoSuchCustomer();
            noSuchCustomer.setCustomerName(customer.getName());
            throw new NoSuchCustomerException("Did not find any matching customer for name=" + customer.getName(),
                    noSuchCustomer);
        }
        return customer;
    }

    private void addCustomer(String name, int revenue, String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.getAddress().add(address);
        Date bDate = new GregorianCalendar(2009, 1, 1).getTime();
        customer.setBirthDate(bDate);
        customer.setNumOrders(1);
        customer.setRevenue(revenue);
        customer.setTest(BigDecimal.valueOf(1.5));
        customer.setType(CustomerType.BUSINESS);
        customers.put(name, customer);
    }

}
