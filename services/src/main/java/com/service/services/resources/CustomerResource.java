package com.service.services.resources;

import com.service.businesslogic.customer.CustomerService;
import com.service.businesslogic.customer.CustomerServiceImpl;
import com.service.dto.CustomerDTO;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private CustomerService customerService;

    public CustomerResource() {
        this.customerService = new CustomerServiceImpl();
    }

    @GET
    @Path("/{id}")
    public CustomerDTO getCustomer(@PathParam("id") Integer id) {
        Optional<CustomerDTO> customerDTO = customerService.getCustomerById(id);
        if(customerDTO.isPresent()) {
            return customerDTO.get();
        }
        throw new NotFoundException("No Customer Exists");
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/create")
    public void createCustomer(@NotNull CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
    }

}