package com.tripoin.rmu.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Achmad Fauzi on 4/15/2015 : 11:50 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class EmployeeResultDTO {

    @JsonProperty("employee")
    private ArrayList<EmployeeDTO> employeeDTO;

    @JsonProperty("returnStatus")
    private String returnStatus;

    @JsonProperty("returnStatusMsg")
    private String returnStatusMessage;

    public ArrayList<EmployeeDTO> getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(ArrayList<EmployeeDTO> employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnStatusMessage() {
        return returnStatusMessage;
    }

    public void setReturnStatusMessage(String returnStatusMessage) {
        this.returnStatusMessage = returnStatusMessage;
    }

    @Override
    public String toString() {
        return "EmployeeResultDTO{" +
                "employeeDTO=" + employeeDTO +
                ", returnStatus='" + returnStatus + '\'' +
                ", returnStatusMessage='" + returnStatusMessage + '\'' +
                '}';
    }
}
