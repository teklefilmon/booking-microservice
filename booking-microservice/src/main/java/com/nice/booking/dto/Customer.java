/**
 * @copyright 2018 NICE Systems Ltd.
 * @author fghebremariam
 * @since May 2, 2018
 */
package com.nice.booking.dto;

import java.util.Objects;


/**
 * TODO Describe the purpose of this class.
 *
 * @since May 2, 2018
 * @author fghebremariam
 */
public class Customer
{
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;

    protected Customer()
    {
    }

    public Customer(String firstName, String lastName, String email, String userName)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
    }

    public static Customer of(Customer customer)
    {
        Objects.requireNonNull(customer, "Customer must not be null");
        return new Customer(customer.firstName, customer.lastName, customer.email, customer.userName);
    }

    public static Customer of(String firstName, String lastName, String email, String userName)
    {
        return new Customer(firstName, lastName, email, userName);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.userName);
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;

        Customer customer = (Customer) other;
        return Objects.equals(this.userName, customer.userName);
    }
}
