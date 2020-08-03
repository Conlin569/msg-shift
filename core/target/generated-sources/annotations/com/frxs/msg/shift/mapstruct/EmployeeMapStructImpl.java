package com.frxs.msg.shift.mapstruct;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.dal.entity.Employee;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-03T09:17:42+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
public class EmployeeMapStructImpl implements EmployeeMapStruct {

    @Override
    public EmployeeDto toEmployeeDto(Employee Employee) {
        if ( Employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( Employee.getId() );
        employeeDto.setName( Employee.getName() );
        employeeDto.setWechatWorkId( Employee.getWechatWorkId() );
        employeeDto.setDepartmentId( Employee.getDepartmentId() );
        employeeDto.setStatus( Employee.getStatus() );

        return employeeDto;
    }
}
