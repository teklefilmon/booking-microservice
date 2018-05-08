package com.nice.booking.model;

import javax.persistence.*;

@Entity
@Table(name = "BOOKINGS")
public class Booking
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PLATE_NUMBER")
    private String plateNumber;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "NUMBER_OF_DAYS")
    private int numberOfDays;

    protected Booking()
    {
    }

    public Booking(String plateNumberParam, String customerId, int numberOfDays)
    {
        super();
        this.plateNumber = plateNumberParam;
        this.username = customerId;
        this.numberOfDays = numberOfDays;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPlateNumber()
    {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber)
    {
        this.plateNumber = plateNumber;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getNumberOfDays()
    {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays)
    {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((plateNumber == null) ? 0 : plateNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        if (username == null)
        {
            if (other.username != null)
                return false;
        }
        else if (!username.equals(other.username))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (plateNumber == null)
        {
            if (other.plateNumber != null)
                return false;
        }
        else if (!plateNumber.equals(other.plateNumber))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Booking [id=" + id + ", plateNumber=" + plateNumber + ", username=" + username + ", numberOfDays=" + numberOfDays + "]";
    }

}
