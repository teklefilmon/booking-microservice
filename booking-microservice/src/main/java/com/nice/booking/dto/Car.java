/**
 * @copyright 2018 NICE Systems Ltd.
 * @author fghebremariam
 * @since May 2, 2018
 */
package com.nice.booking.dto;

import java.math.BigDecimal;

/**
 * TODO Describe the purpose of this class.
 *
 * @since May 2, 2018
 * @author fghebremariam
 */
public class Car
{
    
    private Long id;
    private String type;
    private String model;
    private String plateNumber;
    private BigDecimal dailyPrice;
    private Availability availability;

    protected Car()
    {
    }

    public Car(String type, String model, BigDecimal dailyPrice, String plateNumber)
    {
        super();
        this.type = type;
        this.model = model;
        this.dailyPrice = dailyPrice;
        this.plateNumber = plateNumber;
        this.availability = Availability.AVAILABLE;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public BigDecimal getDailyPrice()
    {
        return dailyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice)
    {
        this.dailyPrice = dailyPrice;
    }

    public String getPlateNumber()
    {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber)
    {
        this.plateNumber = plateNumber;
    }

    public Availability getAvailability()
    {
        return availability;
    }

    public void setAvailability(Availability availability)
    {
        this.availability = availability;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
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
        Car other = (Car) obj;
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
        return "Car [id=" + id + ", type=" + type + ", model=" + model + ", plateNumber=" + plateNumber + ", dailyPrice=" + dailyPrice + "]";
    }

    public enum Availability
    {
        AVAILABLE,
        NOT_AVAILABLE
    }
}
