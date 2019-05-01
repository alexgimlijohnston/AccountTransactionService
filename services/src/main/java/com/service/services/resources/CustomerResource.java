package com.service.services.resources;

import com.service.businesslogic.customer.CustomerService;
import com.service.businesslogic.customer.CustomerServiceImpl;
import com.service.dto.CustomerDTO;
import com.service.dto.CustomerDTO;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private CustomerService customerService;

    public CustomerResource() {
        this.customerService = new CustomerServiceImpl();
    }

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") Integer id) {
        Optional<CustomerDTO> customerDTO = customerService.getCustomerById(id);
        return customerDTO.isPresent()
                ? Response.ok().entity(customerDTO.get()).build()
                : Response.status(404).entity(String.format("Unable to find customer with id &d", id)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/create")
    public Response createCustomer(@NotNull CustomerDTO customerDTO) {
        try {
            customerService.createCustomer(customerDTO);
            return Response.ok().entity(String.format("Customer with id %d was successfully created", customerDTO.getCustomerId())).build();
        } catch (Exception e) {
            return Response.status(400).entity(String.format("Customer with id %d could not be created", customerDTO.getCustomerId())).build();
        }
    }

}